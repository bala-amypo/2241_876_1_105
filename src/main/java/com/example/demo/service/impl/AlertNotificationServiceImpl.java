package com.example.demo.service.impl;

import com.example.demo.model.AlertNotification;
import com.example.demo.model.VisitLog;
import com.example.demo.repository.AlertNotificationRepository;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.service.AlertNotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertNotificationServiceImpl implements AlertNotificationService {

    private final AlertNotificationRepository alertRepository;
    private final VisitLogRepository visitLogRepository;

    // ✅ Constructor injection
    public AlertNotificationServiceImpl(AlertNotificationRepository alertRepository,
                                        VisitLogRepository visitLogRepository) {
        this.alertRepository = alertRepository;
        this.visitLogRepository = visitLogRepository;
    }

    @Override
    public AlertNotification sendAlert(Long visitLogId) {

        if (alertRepository.findByVisitLogId(visitLogId).isPresent()) {
            throw new IllegalArgumentException("Alert already sent");
        }

        VisitLog visitLog = visitLogRepository.findById(visitLogId)
                .orElseThrow(() -> new RuntimeException("VisitLog not found"));

        AlertNotification alert = new AlertNotification();
        alert.setVisitLog(visitLog);
        alert.setSentTo(visitLog.getHost().getEmail());
        alert.setAlertMessage("Visitor arrived");

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