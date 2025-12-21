

package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Visitor;
import com.example.demo.service.VisitorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createVisitor(@RequestBody Visitor visitor) {
        Visitor savedVisitor = visitorService.createVisitor(visitor);
        return new ResponseEntity<>(
                new ApiResponse(true, "Visitor created", savedVisitor),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllVisitors() {
        List<Visitor> visitors = visitorService.getAllVisitors();
        return ResponseEntity.ok(
                new ApiResponse(true, "Visitors fetched", visitors)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getVisitor(@PathVariable Long id) {
        Visitor visitor = visitorService.getVisitor(id);
        return ResponseEntity.ok(
                new ApiResponse(true, "Visitor fetched", visitor)
        );
    }
}
