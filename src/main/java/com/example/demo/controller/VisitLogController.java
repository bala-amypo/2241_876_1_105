

package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.VisitLogDTO;
import com.example.demo.entity.VisitLog;
import com.example.demo.service.VisitLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/visits")
public class VisitLogController {

    private final VisitLogService visitLogService;

    public VisitLogController(VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
    }

    @PostMapping("/checkin/{visitorId}/{hostId}")
    public ResponseEntity<ApiResponse> checkIn(
            @PathVariable Long visitorId,
            @PathVariable Long hostId,
            @RequestBody String purpose) {

        VisitLog log = visitLogService.checkInVisitor(visitorId, hostId, purpose);

        return new ResponseEntity<>(
                new ApiResponse(true, "Visitor checked in", toDTO(log)),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/checkout/{id}")
    public ResponseEntity<ApiResponse> checkOut(@PathVariable Long id) {
        VisitLog log = visitLogService.checkOutVisitor(id);
        return ResponseEntity.ok(
                new ApiResponse(true, "Visitor checked out", toDTO(log))
        );
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse> activeVisits() {
        List<VisitLogDTO> list = visitLogService.getActiveVisits()
                .stream().map(this::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse(true, "Active visits", list));
    }

    private VisitLogDTO toDTO(VisitLog v) {
        VisitLogDTO dto = new VisitLogDTO();
        dto.setId(v.getId());
        dto.setVisitorId(v.getVisitor().getId());
        dto.setHostId(v.getHost().getId());
        dto.setCheckInTime(v.getCheckInTime());
        dto.setCheckOutTime(v.getCheckOutTime());
        dto.setPurpose(v.getPurpose());
        dto.setAccessGranted(v.getAccessGranted());
        return dto;
    }
}
