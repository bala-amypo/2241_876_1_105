package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.exception.ResourceNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

public class AlertNotificationServiceImpl {

    AlertNotificationRepository alertRepository;
    VisitLogRepository visitLogRepository;

    public AlertNotificationServiceImpl() {}

    public AlertNotification sendAlert(Long visitLogId) {

        if (alertRepository.findByVisitLogId(visitLogId).isPresent()) {
            throw new IllegalArgumentException("Alert already sent");
        }

        VisitLog log = visitLogRepository.findById(visitLogId)
            .orElseThrow(() -> new ResourceNotFoundException("VisitLog not found"));

        AlertNotification a = new AlertNotification();
        a.setVisitLog(log);
        a.setSentTo(log.getHost().getEmail());
        a.setAlertMessage("Visitor Arrived");
        a.setSentAt(LocalDateTime.now());

        log.setAlertSent(true);
        visitLogRepository.save(log);

        return alertRepository.save(a);
    }

    public AlertNotification getAlert(Long id) {
        return alertRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Alert not found"));
    }

    public List<AlertNotification> getAllAlerts() {
        return alertRepository.findAll();
    }
}
