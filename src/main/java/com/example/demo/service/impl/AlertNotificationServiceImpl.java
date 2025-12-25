package com.example.demo.service.impl;

import com.example.demo.model.AlertNotification;
import com.example.demo.model.Host;
import com.example.demo.model.VisitLog;
import com.example.demo.repository.AlertNotificationRepository;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.service.AlertNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertNotificationServiceImpl implements AlertNotificationService {

    @Autowired
    private AlertNotificationRepository alertRepository;

    @Autowired
    private VisitLogRepository visitLogRepository;

    // REQUIRED for TestNG
    public AlertNotificationServiceImpl() {
    }

    // Optional constructor
    public AlertNotificationServiceImpl(AlertNotificationRepository alertRepository,
                                        VisitLogRepository visitLogRepository) {
        this.alertRepository = alertRepository;
        this.visitLogRepository = visitLogRepository;
    }

    @Override
    public AlertNotification sendAlert(Long visitLogId) {

        VisitLog visitLog = visitLogRepository.findById(visitLogId)
                .orElseThrow(() -> new RuntimeException("VisitLog not found"));

        // Prevent duplicate alerts
        alertRepository.findByVisitLogId(visitLogId).ifPresent(a -> {
            throw new IllegalArgumentException("Alert already sent");
        });

        Host host = visitLog.getHost();
        if (host == null || host.getEmail() == null) {
            throw new RuntimeException("Host email not available");
        }

        AlertNotification alert = new AlertNotification();
        alert.setVisitLog(visitLog);
        alert.setSentTo(host.getEmail());
        alert.setAlertMessage("Visitor arrived");
        alert.setSentAt(LocalDateTime.now());

        visitLog.setAlertSent(true);
        visitLogRepository.save(visitLog);

        return alertRepository.save(alert);
    }

    @Override
    public AlertNotification getAlert(Long id) {
        return alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found"));
    }

    @Override
    public List<AlertNotification> getAllAlerts() {
        return alertRepository.findAll();
    }
}


// package com.example.demo.service.impl;

// import com.example.demo.model.AlertNotification;
// import com.example.demo.model.VisitLog;
// import com.example.demo.repository.AlertNotificationRepository;
// import com.example.demo.repository.VisitLogRepository;
// import com.example.demo.service.AlertNotificationService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class AlertNotificationServiceImpl implements AlertNotificationService {

//     // REQUIRED for tests
//     private AlertNotificationRepository alertRepository;
//     private VisitLogRepository visitLogRepository;

//     // ✅ NO-ARG constructor (TESTS)
//     public AlertNotificationServiceImpl() {}

//     // ✅ Constructor injection (RUNTIME)
//     public AlertNotificationServiceImpl(AlertNotificationRepository alertRepository,
//                                         VisitLogRepository visitLogRepository) {
//         this.alertRepository = alertRepository;
//         this.visitLogRepository = visitLogRepository;
//     }

//     @Override
//     public AlertNotification sendAlert(Long visitLogId) {

//         if (alertRepository.findByVisitLogId(visitLogId).isPresent()) {
//             throw new IllegalArgumentException("Alert already sent");
//         }

//         VisitLog visitLog = visitLogRepository.findById(visitLogId)
//                 .orElseThrow(() -> new RuntimeException("VisitLog not found"));

//         AlertNotification alert = new AlertNotification();
//         alert.setVisitLog(visitLog);
//         alert.setSentTo(visitLog.getHost().getEmail());
//         alert.setAlertMessage("Visitor arrived");

//         visitLog.setAlertSent(true);
//         visitLogRepository.save(visitLog);

//         return alertRepository.save(alert);
//     }

//     @Override
//     public AlertNotification getAlert(Long id) {
//         return alertRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Alert not found"));
//     }

//     @Override
//     public List<AlertNotification> getAllAlerts() {
//         return alertRepository.findAll();
//     }
// }


// package com.example.demo.service.impl;

// import com.example.demo.model.AlertNotification;
// import com.example.demo.model.VisitLog;
// import com.example.demo.repository.AlertNotificationRepository;
// import com.example.demo.repository.VisitLogRepository;
// import com.example.demo.service.AlertNotificationService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class AlertNotificationServiceImpl implements AlertNotificationService {

//     // ⚠️ REQUIRED FIELD NAMES
//     private AlertNotificationRepository alertRepository;
//     private VisitLogRepository visitLogRepository;

//     public AlertNotificationServiceImpl() {}

//     @Override
//     public AlertNotification sendAlert(Long visitLogId) {

//         if (alertRepository.findByVisitLogId(visitLogId).isPresent()) {
//             throw new IllegalArgumentException("Alert already sent");
//         }

//         VisitLog visitLog = visitLogRepository.findById(visitLogId)
//                 .orElseThrow(() -> new RuntimeException("VisitLog not found"));

//         AlertNotification alert = new AlertNotification();
//         alert.setVisitLog(visitLog);
//         alert.setSentTo(visitLog.getHost().getEmail());
//         alert.setAlertMessage("Visitor arrived");

//         visitLog.setAlertSent(true);
//         visitLogRepository.save(visitLog);

//         return alertRepository.save(alert);
//     }

//     @Override
//     public AlertNotification getAlert(Long id) {
//         return alertRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Alert not found"));
//     }

//     @Override
//     public List<AlertNotification> getAllAlerts() {
//         return alertRepository.findAll();
//     }
// }