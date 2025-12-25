package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Appointment;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByHostId(Long hostId);
    List<Appointment> findByVisitorId(Long visitorId);
}


// package com.example.demo.repository;

// import com.example.demo.model.Appointment;
// import java.util.List;
// import java.util.Optional;

// public interface AppointmentRepository {

//     Appointment save(Appointment appointment);

//     Optional<Appointment> findById(Long id);

//     List<Appointment> findByHostId(Long hostId);

//     List<Appointment> findByVisitorId(Long visitorId);

//     List<Appointment> findAll();
// }
