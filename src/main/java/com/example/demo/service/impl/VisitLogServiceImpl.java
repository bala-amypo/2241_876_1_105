package com.example.demo.service.impl;

import com.example.demo.model.Host;
import com.example.demo.model.VisitLog;
import com.example.demo.model.Visitor;
import com.example.demo.repository.HostRepository;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.service.VisitLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitLogServiceImpl implements VisitLogService {

    // ⚠️ REQUIRED FIELD NAMES
    private VisitLogRepository visitLogRepository;
    private VisitorRepository visitorRepository;
    private HostRepository hostRepository;

    public VisitLogServiceImpl() {}

    @Override
    public VisitLog checkInVisitor(Long visitorId, Long hostId, String purpose) {

        Visitor visitor = visitorRepository.findById(visitorId).orElseThrow();
        Host host = hostRepository.findById(hostId).orElseThrow();

        VisitLog visitLog = new VisitLog();
        visitLog.setVisitor(visitor);
        visitLog.setHost(host);
        visitLog.setCheckInTime(LocalDateTime.now());
        visitLog.setAccessGranted(true);
        visitLog.setAlertSent(false);

        return visitLogRepository.save(visitLog);
    }

    @Override
    public VisitLog checkOutVisitor(Long visitLogId) {

        VisitLog visitLog = visitLogRepository.findById(visitLogId)
                .orElseThrow(() -> new RuntimeException("VisitLog not found"));

        if (visitLog.getCheckInTime() == null) {
            throw new IllegalStateException("Visitor not checked in");
        }

        visitLog.setCheckOutTime(LocalDateTime.now());
        return visitLogRepository.save(visitLog);
    }

    @Override
    public List<VisitLog> getActiveVisits() {
        return visitLogRepository.findByCheckOutTimeIsNull();
    }

    @Override
    public VisitLog getVisitLog(Long id) {
        return visitLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VisitLog not found"));
    }
}


// package com.example.demo.service.impl;

// import com.example.demo.model.*;
// import com.example.demo.repository.*;
// import com.example.demo.service.VisitLogService;
// import com.example.demo.exception.ResourceNotFoundException;

// import java.time.LocalDateTime;
// import java.util.List;

// public class VisitLogServiceImpl implements VisitLogService {

//     private VisitLogRepository visitLogRepository;
//     private VisitorRepository visitorRepository;
//     private HostRepository hostRepository;

//     public VisitLogServiceImpl() {}

//     @Override
//     public VisitLog checkInVisitor(Long visitorId, Long hostId, String purpose) {

//         Visitor visitor = visitorRepository.findById(visitorId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));

//         Host host = hostRepository.findById(hostId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Host not found"));

//         VisitLog log = new VisitLog();
//         log.setVisitor(visitor);
//         log.setHost(host);
//         log.setPurpose(purpose);
//         log.setAccessGranted(true);
//         log.setCheckInTime(LocalDateTime.now());

//         return visitLogRepository.save(log);
//     }

//     @Override
//     public VisitLog checkOutVisitor(Long visitLogId) {

//         VisitLog log = visitLogRepository.findById(visitLogId)
//                 .orElseThrow(() -> new ResourceNotFoundException("VisitLog not found"));

//         if (log.getCheckInTime() == null) {
//             throw new IllegalStateException("Visitor not checked in");
//         }

//         log.setCheckOutTime(LocalDateTime.now());
//         return visitLogRepository.save(log);
//     }

//     @Override
//     public VisitLog getVisitLog(Long id) {
//         return visitLogRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("VisitLog not found"));
//     }

//     @Override
//     public List<VisitLog> getActiveVisits() {
//         return visitLogRepository.findByCheckOutTimeIsNull();
//     }
// }
