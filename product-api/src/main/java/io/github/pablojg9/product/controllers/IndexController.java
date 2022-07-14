package io.github.pablojg9.product.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/status")
public class IndexController {

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getApiStatus() {

        HashMap<String, Object> response = new HashMap<String, Object>();

        response.put("service", "product-api");
        response.put("status", "up");
        response.put("httpStatus", HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }
}
