package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.exception.ResourceNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class AppointmentServiceImpl {

    AppointmentRepository appointmentRepository;
    VisitorRepository visitorRepository;
    HostRepository hostRepository;

    public AppointmentServiceImpl(AppointmentRepository a, VisitorRepository v, HostRepository h) {
        this.appointmentRepository = a;
        this.visitorRepository = v;
        this.hostRepository = h;
    }

    public Appointment createAppointment(Long vid, Long hid, Appointment a) {

        if (a.getAppointmentDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("appointmentDate cannot be past");
        }

        Visitor v = visitorRepository.findById(vid)
            .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));

        Host h = hostRepository.findById(hid)
            .orElseThrow(() -> new ResourceNotFoundException("Host not found"));

        a.setVisitor(v);
        a.setHost(h);
        a.setStatus("SCHEDULED");

        return appointmentRepository.save(a);
    }

    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
    }

    public List<Appointment> getAppointmentsForHost(Long id) {
        return appointmentRepository.findByHostId(id);
    }

    public List<Appointment> getAppointmentsForVisitor(Long id) {
        return appointmentRepository.findByVisitorId(id);
    }
}
