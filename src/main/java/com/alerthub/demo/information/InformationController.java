package com.alerthub.demo.information;

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
@RequestMapping(path = "api/v1/information")
@Tag(name = "Information", description = "Information APIs")
public class InformationController {

    private final String fetchSuccessful = "Operation Successful";

    private final InformationService informationService;

    @Autowired
    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping(path = "information")
    @Operation(summary = "Get  information", description = "")
    public ResponseEntity<NetworkResult> getUserInformations(@RequestParam Integer page) {
        List<Information> information = informationService.getInformation(page);
        NetworkResult result = new NetworkResult(fetchSuccessful, information);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping(path = "information")
    @Operation(summary = "create information", description = "")
    public ResponseEntity<NetworkResult> createInformation(@RequestBody Information information) {
        try {
            informationService.createInformation(information);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "information/{id}")
    @Operation(summary = "edit information", description = "")
    public ResponseEntity<NetworkResult> editInformation(@PathVariable String id,
            @RequestBody(required = true) Information information) {
        try {
            informationService.editInformation(id, information);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "information/{id}")
    @Operation(summary = "Get information details", description = "")
    public ResponseEntity<NetworkResult> getInformation(@PathVariable String id) {
        try {
            Information information = informationService.getInformationDetails(id);
            NetworkResult result = new NetworkResult(fetchSuccessful, information);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "information/{id}")
    @Operation(summary = "delete information", description = "")
    public ResponseEntity<NetworkResult> deleteInformation(@RequestParam String id) {
        try {
            informationService.deleteInformation(id);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
