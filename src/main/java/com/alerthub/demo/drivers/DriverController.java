package com.alerthub.demo.drivers;

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
@RequestMapping(path = "api/v1/driver")
@Tag(name = "Driver", description = "Driver APIs")
public class DriverController {

    private final String fetchSuccessful = "Operation Successful";

    private final DriverService service;

    @Autowired
    public DriverController(DriverService service) {
        this.service = service;
    }

    @GetMapping(path = "{uid}")
    @Operation(summary = "Get Driver", description = "")
    public ResponseEntity<NetworkResult> getDriver(@PathVariable String uid) {
        try {
            Driver center = service.getDriver(uid);
            NetworkResult result = new NetworkResult(fetchSuccessful, center);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "")
    @Operation(summary = "Create Driver", description = "")
    public ResponseEntity<NetworkResult> createDriver(@RequestBody Driver center) {
        try {
            service.createDriver(center);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "{uid}")
    @Operation(summary = "Edit Driver", description = "")
    public ResponseEntity<NetworkResult> editDriver(@PathVariable String uid,
            @RequestBody(required = true) Driver center) {
        try {
            service.editDriver(uid, center);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     @GetMapping(path = "healthcenter_list/{id}")
    @Operation(summary = "Get all health center drivers", description = "")
    public ResponseEntity<NetworkResult> getAllHealthCenterDrivers(@PathVariable String id,
            @RequestParam Integer page) {
        try {
            final List<Driver> drivers = service.getAllHealthCenterDrivers(id, page);
            NetworkResult result = new NetworkResult(fetchSuccessful, drivers);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @DeleteMapping(path = "{uid}")
    @Operation(summary = "Delete Driver", description = "")
    public ResponseEntity<NetworkResult> deleteDriver(
            @RequestParam String uid) {
        try {
            service.deleteDriver(uid);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 

}
