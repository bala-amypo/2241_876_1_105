

package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.AlertNotificationDTO;
import com.example.demo.entity.AlertNotification;
import com.example.demo.service.AlertNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/alerts")
public class AlertNotificationController {

    private final AlertNotificationService alertService;

    public AlertNotificationController(AlertNotificationService alertService) {
        this.alertService = alertService;
    }

    @PostMapping("/send/{visitLogId}")
    public ResponseEntity<ApiResponse> sendAlert(@PathVariable Long visitLogId) {

        AlertNotification alert = alertService.sendAlert(visitLogId);

        return new ResponseEntity<>(
                new ApiResponse(true, "Alert sent", toDTO(alert)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAlert(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Alert fetched", toDTO(alertService.getAlert(id)))
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllAlerts() {
        List<AlertNotificationDTO> list = alertService.getAllAlerts()
                .stream().map(this::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(
                new ApiResponse(true, "All alerts", list)
        );
    }

    private AlertNotificationDTO toDTO(AlertNotification a) {
        AlertNotificationDTO dto = new AlertNotificationDTO();
        dto.setId(a.getId());
        dto.setVisitLogId(a.getVisitLog().getId());
        dto.setSentTo(a.getSentTo());
        dto.setAlertMessage(a.getAlertMessage());
        dto.setSentAt(a.getSentAt());
        return dto;
    }
}
