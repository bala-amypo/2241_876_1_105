package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.exception.ResourceNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

public class VisitLogServiceImpl {

    VisitLogRepository visitLogRepository;
    VisitorRepository visitorRepository;
    HostRepository hostRepository;

    public VisitLogServiceImpl() {}

    public VisitLog checkInVisitor(Long vid, Long hid, String purpose) {

        Visitor v = visitorRepository.findById(vid)
            .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));

        Host h = hostRepository.findById(hid)
            .orElseThrow(() -> new ResourceNotFoundException("Host not found"));

        VisitLog log = new VisitLog();
        log.setVisitor(v);
        log.setHost(h);
        log.setPurpose(purpose);
        log.setCheckInTime(LocalDateTime.now());
        log.setAccessGranted(true);
        log.setAlertSent(false);

        return visitLogRepository.save(log);
    }

    public VisitLog checkOutVisitor(Long id) {

        VisitLog log = visitLogRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("VisitLog not found"));

        if (log.getCheckInTime() == null) {
            throw new IllegalStateException("Visitor not checked in");
        }

        log.setCheckOutTime(LocalDateTime.now());
        return visitLogRepository.save(log);
    }

    public List<VisitLog> getActiveVisits() {
        return visitLogRepository.findByCheckOutTimeIsNull();
    }

    public VisitLog getVisitLog(Long id) {
        return visitLogRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("VisitLog not found"));
    }
}
