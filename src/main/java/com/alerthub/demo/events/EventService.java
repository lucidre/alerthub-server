
package com.alerthub.demo.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alerthub.demo.notifications.NotificationService;
import com.alerthub.demo.users.User;
import com.alerthub.demo.users.UserService;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private static final double EARTH_RADIUS = 6371.0; // Earth radius in kilometers
    private final NotificationService notificationService;
    private final UserService userService;

    @Autowired
    public EventService(EventRepository eventRepository, NotificationService notificationService,
            UserService userService) {
        this.eventRepository = eventRepository;
        this.notificationService = notificationService;
        this.userService = userService;
    }

    private PageRequest pageRequest(Integer page) {
        return PageRequest.of(page, 80, Sort.by(Sort.Direction.DESC, "updatedAt"));
    }

    private boolean isWithinRadius(double eventLat, double eventLng, double lat, double lng, double radius) {
        double latDistance = Math.toRadians(eventLat - lat);
        double lngDistance = Math.toRadians(eventLng - lng);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(eventLat))
                        * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;
        return distance <= radius;
    }

    public List<Event> getUserEvents(String uid, Integer page) {
        PageRequest pageable = pageRequest(page);

        if (uid == null) {
            throw new IllegalStateException("Pass user id.");
        }

        return eventRepository.findByCreatorId(uid, pageable);

    }

    public void createEvent(String uid, Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        event.setId(null);
        event.setCreatedAt(System.currentTimeMillis());
        event.setCreatorId(uid);
        eventRepository.save(event);
    }

    public List<Event> getHomeEvents(Integer page) {
        PageRequest pageable = pageRequest(page);
        return eventRepository.findAll(pageable).getContent();

    }

    public List<Event> getNearbyEvents(Double radius, Double lng, Double lat, Integer page) {

        if (radius == null || lat == null || lng == null) {
            throw new IllegalArgumentException("Radius, Latitude, and Longitude cannot be null");
        }

        List<Event> events = eventRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt"));

        return events.stream()
                .filter(event -> isWithinRadius(event.getLat(), event.getLng(), lat, lng, radius))
                .collect(Collectors.toList());
    }

    public List<Event> getMapEvents(Double radius, Double lng, Double lat) {

        if (radius == null || lat == null || lng == null) {
            throw new IllegalArgumentException("Radius, Latitude, and Longitude cannot be null");
        }

        List<Event> events = eventRepository.findAll();
        return events;
    }

    public List<Event> searchEvents(String query, Integer page) {
        if (query == null) {
            throw new IllegalArgumentException("Query cannot be null");
        }
        PageRequest pageable = pageRequest(page);
        return eventRepository.searchByNameOrLocation(query, pageable).getContent();
    }

    public void editEvent(String id, String uid, Event updatedEvent) {
        if (id == null || uid == null || updatedEvent == null) {
            throw new IllegalArgumentException("Event ID, User ID, and Event must not be null");
        }

        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event existingEvent = optionalEvent.get();
            if (existingEvent.getCreatorId().equals(uid)) {

                updatedEvent.setUpVote(existingEvent.getUpVote());
                updatedEvent.setDownVote(existingEvent.getDownVote());
                updatedEvent.setUpdatedAt(System.currentTimeMillis());
                updatedEvent.setCreatedAt(existingEvent.getCreatedAt());
                updatedEvent.setId(existingEvent.getId());

                if (existingEvent.getComments() != null) {
                    updatedEvent.setComments(existingEvent.getComments());
                } else {
                    updatedEvent.setComments(new ArrayList<>());
                }
                updatedEvent.setCreatorId(existingEvent.getCreatorId());
                eventRepository.save(updatedEvent);

            } else {
                throw new IllegalStateException("User is not authorized to edit this event");
            }
        } else {
            throw new IllegalStateException("Event not found");
        }
    }

    public Event getEventDetails(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Event ID must not be null");
        }
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();

            Optional<User> userOp = userService.getOptionalUser(event.getCreatorId());
            if (userOp.isPresent()) {
                User user = userOp.get();
                event.setCreatorCountry(user.getCountry());
                event.setCreatorEmail(user.getEmail());
                event.setCreatorImage(user.getImageUrl());
                event.setCreatorName(user.getFullName());
            }

            return event;
        } else {
            throw new IllegalStateException("Event not found");
        }
    }

    public void deleteEvent(String eventId, String uid) {
        if (eventId == null || uid == null) {
            throw new IllegalArgumentException("Event ID and User ID must not be null");
        }
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            if (event.getCreatorId().equals(uid)) {
                eventRepository.deleteById(eventId);
            } else {
                throw new IllegalStateException("User is not authorized to delete this event");
            }
        } else {
            throw new IllegalStateException("Event not found");
        }
    }

    public void voteEvent(String eventId, String userId, Boolean isTrue, String comment) {
        if (eventId == null || userId == null) {
            throw new IllegalArgumentException("Event ID and User ID must not be null");
        }

        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            if (isTrue) {
                event.setUpVote(Objects.requireNonNullElse(event.getUpVote(), 0) + 1);
            } else {
                event.setDownVote(Objects.requireNonNullElse(event.getDownVote(), 0) + 1);
            }

            if (event.getComments() == null) {
                final List<String> list = new ArrayList<>();
                event.setComments(list);
            }

            event.getComments().add(0, comment);
            eventRepository.save(event);

            System.err.println(notificationService == null);
            notificationService.addNotification(userId,
                    "Your comment and vote on the event \"" + event.getName() + "\" has been saved");
            notificationService.addNotification(event.getCreatorId(),
                    "A new comment and vote has been made on your event \"" + event.getName() + "\".");

        } else {
            throw new IllegalStateException("Event not found");
        }
    }

}
