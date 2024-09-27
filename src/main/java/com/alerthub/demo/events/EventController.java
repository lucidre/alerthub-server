
package com.alerthub.demo.events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "api/v1/event")
@Tag(name = "Event", description = "Event APIs")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(path = "/user_events")
    @Operation(summary = "Get user events", description = "")
    public List<Event> getUserEvents(@RequestBody String uid, @RequestBody(required = false) int last) {
        return List.of();
    }

    @GetMapping(path = "/home")
    @Operation(summary = "Get home events", description = "")
    public List<Event> getHomeEvents(@RequestBody(required = false) int last) {
        return List.of();
    }

    @GetMapping(path = "/nearby")
    @Operation(summary = "Get nearby events", description = "")
    public List<Event> getNearbyEvents(@RequestBody(required = false) int last, @RequestBody int lat,
            @RequestBody int lng) {
        return List.of();
    }

    @GetMapping(path = "/search/{query}")
    @Operation(summary = "Get search events", description = "")
    public List<Event> searchEvents(@PathVariable("query") String query, @RequestBody(required = false) int last) {
        return List.of();
    }

    @PostMapping(path = "create_event")
    @Operation(summary = "create events", description = "")
    public void createEvent(@RequestBody String uid, @RequestBody Event event) {
        //
    }

    @PutMapping(path = "edit_event/{eventId}")
    @Operation(summary = "edit events", description = "")
    public void editEvent(@PathVariable("eventId") String eventId, @RequestBody String uid, @RequestBody Event event) {
        //
    }

    @DeleteMapping(path = "delete_event/{eventId}")
    @Operation(summary = "delete events", description = "")
    public void deleteEvent(@PathVariable("eventId") int id) {
        //
    }

}