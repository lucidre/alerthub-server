package com.alerthub.demo.healthcenters;

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
@RequestMapping(path = "api/v1/center")
@Tag(name = "HealthCenter", description = "HealthCenter APIs")
public class HealthCenterController {

    private final String fetchSuccessful = "Operation Successful";

    private final HealthCenterService service;

    @Autowired
    public HealthCenterController(HealthCenterService service) {
        this.service = service;
    }

    @GetMapping(path = "healthcenter/{uid}")
    @Operation(summary = "Get HealthCenter Details", description = "")
    public ResponseEntity<NetworkResult> getHealthCenter(@PathVariable String uid) {
        try {
            HealthCenter center = service.getHealthCenter(uid);
            NetworkResult result = new NetworkResult(fetchSuccessful, center);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "heathcenter")
    @Operation(summary = "Create HealthCenter", description = "")
    public ResponseEntity<NetworkResult> createHealthCenter(@RequestBody HealthCenter center) {
        try {
            service.createHealthCenter(center);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "healthcenter/{uid}")
    @Operation(summary = "Edit HealthCenter", description = "")
    public ResponseEntity<NetworkResult> editHealthCenter(@PathVariable String uid,
            @RequestBody(required = true) HealthCenter center) {
        try {
            service.editHealthCenter(uid, center);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "healthcenter/{uid}")
    @Operation(summary = "Delete HealthCenter", description = "")
    public ResponseEntity<NetworkResult> deleteHealthCenter(
            @RequestParam String uid) {
        try {
            service.deleteHealthCenter(uid);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "healthcenter/nearby")
    @Operation(summary = "Get Nearby HealthCenters", description = "")
    public ResponseEntity<NetworkResult> getNearbyHealthCenters(@RequestParam Double radius,
            @RequestParam Double lng,
            @RequestParam Double lat, @RequestParam Integer page) {
        try {
            final List<HealthCenter> centers = service.getNearbyHealthCenters(radius, lng, lat, page);
            NetworkResult result = new NetworkResult(fetchSuccessful, centers);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "healthcenter/map")
    @Operation(summary = "Get Map HealthCenters", description = "")
    public ResponseEntity<NetworkResult> getMapHealthCenters(@RequestParam Double radius,
            @RequestParam Double lng,
            @RequestParam Double lat) {
        try {
            final List<HealthCenter> centers = service.getMapHealthCenters(radius, lng, lat);
            NetworkResult result = new NetworkResult(fetchSuccessful, centers);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/search/{query}")
    @Operation(summary = "Get Search HealthCenters", description = "")
    public ResponseEntity<NetworkResult> searchHealthCenters(@PathVariable("query") String query,
            @RequestParam Integer page) {
        try {
            final List<HealthCenter> centers = service.searchHealthCenters(query, page);
            NetworkResult result = new NetworkResult(fetchSuccessful, centers);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "add_user/{uid}")
    @Operation(summary = "Add user to healthcenter", description = "")
    public ResponseEntity<NetworkResult> addUserToCenter(@PathVariable String uid,
            @RequestBody(required = true) String userId) {
        try {
            service.addUserToCenter(uid, userId);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "remove_user/{uid}")
    @Operation(summary = "Remove user from healthcenter", description = "")
    public ResponseEntity<NetworkResult> removeUserFromCenter(@PathVariable String uid,
            @RequestBody(required = true) String userId) {
        try {
            service.removeUserFromCenter(uid, userId);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
