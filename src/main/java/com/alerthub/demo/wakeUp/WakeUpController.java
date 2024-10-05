package com.alerthub.demo.wakeUp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "api/v1/wakeup")
@Tag(name = "Wakeup", description = "Wakeup Server")
public class WakeUpController {

    @GetMapping
    @Operation(summary = "Wake up the server", description = "")
    public String wakeUp() {
        return "Server is awake!";
    }
}
