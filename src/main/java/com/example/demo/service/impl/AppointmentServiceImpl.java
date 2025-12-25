package com.example.demo.service.impl;

import com.example.demo.model.Appointment;
import com.example.demo.model.Host;
import com.example.demo.model.Visitor;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.HostRepository;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    // ⚠️ REQUIRED FIELD NAMES
    private AppointmentRepository appointmentRepository;
    private VisitorRepository visitorRepository;
    private HostRepository hostRepository;

    // Constructor used in tests
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  VisitorRepository visitorRepository,
                                  HostRepository hostRepository) {
        this.appointmentRepository = appointmentRepository;
        this.visitorRepository = visitorRepository;
        this.hostRepository = hostRepository;
    }

    public AppointmentServiceImpl() {}

    @Override
    public Appointment createAppointment(Long visitorId, Long hostId, Appointment appointment) {

        if (appointment.getAppointmentDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("appointmentDate cannot be past");
        }

        Visitor visitor = visitorRepository.findById(visitorId).orElseThrow();
        Host host = hostRepository.findById(hostId).orElseThrow();

        appointment.setVisitor(visitor);
        appointment.setHost(host);
        appointment.setStatus("SCHEDULED");

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    @Override
    public List<Appointment> getAppointmentsForHost(Long hostId) {
        return appointmentRepository.findByHostId(hostId);
    }

    @Override
    public List<Appointment> getAppointmentsForVisitor(Long visitorId) {
        return appointmentRepository.findByVisitorId(visitorId);
    }
}



// package com.example.demo.service.impl;

// import com.example.demo.model.*;
// import com.example.demo.repository.*;
// import com.example.demo.service.AppointmentService;
// import com.example.demo.exception.ResourceNotFoundException;

// import java.time.LocalDate;
// import java.util.List;

// public class AppointmentServiceImpl implements AppointmentService {

//     private final AppointmentRepository appointmentRepository;
//     private final VisitorRepository visitorRepository;
//     private final HostRepository hostRepository;

//     public AppointmentServiceImpl(AppointmentRepository ar, VisitorRepository vr, HostRepository hr) {
//         this.appointmentRepository = ar;
//         this.visitorRepository = vr;
//         this.hostRepository = hr;
//     }

//     @Override
//     public Appointment createAppointment(Long visitorId, Long hostId, Appointment appointment) {

//         if (appointment.getAppointmentDate().isBefore(LocalDate.now())) {
//             throw new IllegalArgumentException("appointmentDate cannot be past");
//         }

//         Visitor visitor = visitorRepository.findById(visitorId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));

//         Host host = hostRepository.findById(hostId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Host not found"));

//         appointment.setVisitor(visitor);
//         appointment.setHost(host);
//         appointment.setStatus("SCHEDULED");

//         return appointmentRepository.save(appointment);
//     }

//     @Override
//     public Appointment getAppointment(Long id) {
//         return appointmentRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
//     }

//     @Override
//     public List<Appointment> getAppointmentsForHost(Long hostId) {
//         return appointmentRepository.findByHostId(hostId);
//     }

//     @Override
//     public List<Appointment> getAppointmentsForVisitor(Long visitorId) {
//         return appointmentRepository.findByVisitorId(visitorId);
//     }
// }
