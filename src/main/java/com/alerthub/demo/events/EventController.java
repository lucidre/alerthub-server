
package com.alerthub.demo.events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alerthub.demo.NetworkResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "api/v1/event")
@Tag(name = "Event", description = "Event APIs")
public class EventController {
    private final String fetchSuccessful = "Operation Successful";

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(path = "/user_events")
    @Operation(summary = "Get user events", description = "")
    public ResponseEntity<NetworkResult> getUserEvents(@RequestParam String uid,
            @RequestParam Integer page) {
        List<Event> events = eventService.getUserEvents(uid, page);
        NetworkResult result = new NetworkResult(fetchSuccessful, events);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(path = "/home")
    @Operation(summary = "Get home events", description = "")
    public ResponseEntity<NetworkResult> getHomeEvents(@RequestParam Integer page) {
        try {
            final List<Event> events = eventService.getHomeEvents(page);
            NetworkResult result = new NetworkResult(fetchSuccessful, events);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/nearby")
    @Operation(summary = "Get nearby events", description = "")
    public ResponseEntity<NetworkResult> getNearbyEvents(@RequestParam Double radius,
            @RequestParam Double lng,
            @RequestParam Double lat, @RequestParam Integer page) {
        try {
            final List<Event> events = eventService.getNearbyEvents(radius, lng, lat, page);
            NetworkResult result = new NetworkResult(fetchSuccessful, events);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/map")
    @Operation(summary = "Get map events", description = "")
    public ResponseEntity<NetworkResult> getMapEvents(@RequestParam Double radius,
            @RequestParam Double lng,
            @RequestParam Double lat) {
        try {
            final List<Event> events = eventService.getMapEvents(radius, lng, lat);
            NetworkResult result = new NetworkResult(fetchSuccessful, events);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/search/{query}")
    @Operation(summary = "Get search events", description = "")
    public ResponseEntity<NetworkResult> searchEvents(@PathVariable("query") String query,
            @RequestParam Integer page) {
        try {
            final List<Event> events = eventService.searchEvents(query, page);
            NetworkResult result = new NetworkResult(fetchSuccessful, events);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "create_event")
    @Operation(summary = "create events", description = "")
    public ResponseEntity<NetworkResult> createEvent(@RequestParam String uid,
            @RequestBody Event event) {
        try {
            eventService.createEvent(uid, event);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "edit_event/{eventId}")
    @Operation(summary = "edit events", description = "")
    public ResponseEntity<NetworkResult> editEvent(@PathVariable String eventId,
            @RequestParam(required = true) String uid,
            @RequestBody(required = true) Event event) {
        try {
            eventService.editEvent(eventId, uid, event);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "{eventId}")
    @Operation(summary = "Get event details", description = "")
    public ResponseEntity<NetworkResult> getEvent(@PathVariable String eventId) {
        try {
            Event event = eventService.getEventDetails(eventId);
            NetworkResult result = new NetworkResult(fetchSuccessful, event);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "vote_event/{eventId}")
    @Operation(summary = "Get event details", description = "")
    public ResponseEntity<NetworkResult> voteEvent(
            @PathVariable String eventId, @RequestParam String userId, @RequestParam Boolean isEventTrue,
            @RequestParam(required = false) String comment) {
        try {
            eventService.voteEvent(eventId, userId, isEventTrue, comment);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "delete_event")
    @Operation(summary = "delete events", description = "")
    public ResponseEntity<NetworkResult> deleteEvent(@RequestParam String eventId,
            @RequestParam String uid) {
        try {
            eventService.deleteEvent(eventId, uid);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
