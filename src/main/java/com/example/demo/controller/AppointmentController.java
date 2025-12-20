

package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.AppointmentDTO;
import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/{visitorId}/{hostId}")
    public ResponseEntity<ApiResponse> createAppointment(
            @PathVariable Long visitorId,
            @PathVariable Long hostId,
            @RequestBody AppointmentDTO dto) {

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setPurpose(dto.getPurpose());

        Appointment saved =
                appointmentService.createAppointment(visitorId, hostId, appointment);

        return new ResponseEntity<>(
                new ApiResponse(true, "Appointment created", toDTO(saved)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Appointment fetched",
                        toDTO(appointmentService.getAppointment(id)))
        );
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity<ApiResponse> getByHost(@PathVariable Long hostId) {
        List<AppointmentDTO> list = appointmentService.getAppointmentsForHost(hostId)
                .stream().map(this::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse(true, "Appointments by host", list));
    }

    @GetMapping("/visitor/{visitorId}")
    public ResponseEntity<ApiResponse> getByVisitor(@PathVariable Long visitorId) {
        List<AppointmentDTO> list = appointmentService.getAppointmentsForVisitor(visitorId)
                .stream().map(this::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse(true, "Appointments by visitor", list));
    }

    private AppointmentDTO toDTO(Appointment a) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(a.getId());
        dto.setVisitorId(a.getVisitor().getId());
        dto.setHostId(a.getHost().getId());
        dto.setAppointmentDate(a.getAppointmentDate());
        dto.setPurpose(a.getPurpose());
        dto.setStatus(a.getStatus());
        return dto;
    }
}
