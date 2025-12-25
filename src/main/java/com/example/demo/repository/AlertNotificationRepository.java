package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.AlertNotification;
import java.util.Optional;

public interface AlertNotificationRepository extends JpaRepository<AlertNotification, Long> {
    Optional<AlertNotification> findByVisitLogId(Long visitLogId);
}


// package com.example.demo.repository;

// import com.example.demo.model.AlertNotification;
// import java.util.List;
// import java.util.Optional;

// public interface AlertNotificationRepository {

//     AlertNotification save(AlertNotification alertNotification);

//     Optional<AlertNotification> findById(Long id);

//     Optional<AlertNotification> findByVisitLogId(Long visitLogId);

//     List<AlertNotification> findAll();
// }
