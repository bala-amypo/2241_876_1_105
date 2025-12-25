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

    private final VisitLogRepository visitLogRepository;
    private final VisitorRepository visitorRepository;
    private final HostRepository hostRepository;

    // ✅ Constructor injection
    public VisitLogServiceImpl(VisitLogRepository visitLogRepository,
                               VisitorRepository visitorRepository,
                               HostRepository hostRepository) {
        this.visitLogRepository = visitLogRepository;
        this.visitorRepository = visitorRepository;
        this.hostRepository = hostRepository;
    }

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

// import com.example.demo.model.Host;
// import com.example.demo.model.VisitLog;
// import com.example.demo.model.Visitor;
// import com.example.demo.repository.HostRepository;
// import com.example.demo.repository.VisitLogRepository;
// import com.example.demo.repository.VisitorRepository;
// import com.example.demo.service.VisitLogService;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class VisitLogServiceImpl implements VisitLogService {

//     // ⚠️ REQUIRED FIELD NAMES
//     private VisitLogRepository visitLogRepository;
//     private VisitorRepository visitorRepository;
//     private HostRepository hostRepository;

//     public VisitLogServiceImpl() {}

//     @Override
//     public VisitLog checkInVisitor(Long visitorId, Long hostId, String purpose) {

//         Visitor visitor = visitorRepository.findById(visitorId).orElseThrow();
//         Host host = hostRepository.findById(hostId).orElseThrow();

//         VisitLog visitLog = new VisitLog();
//         visitLog.setVisitor(visitor);
//         visitLog.setHost(host);
//         visitLog.setCheckInTime(LocalDateTime.now());
//         visitLog.setAccessGranted(true);
//         visitLog.setAlertSent(false);

//         return visitLogRepository.save(visitLog);
//     }

//     @Override
//     public VisitLog checkOutVisitor(Long visitLogId) {

//         VisitLog visitLog = visitLogRepository.findById(visitLogId)
//                 .orElseThrow(() -> new RuntimeException("VisitLog not found"));

//         if (visitLog.getCheckInTime() == null) {
//             throw new IllegalStateException("Visitor not checked in");
//         }

//         visitLog.setCheckOutTime(LocalDateTime.now());
//         return visitLogRepository.save(visitLog);
//     }

//     @Override
//     public List<VisitLog> getActiveVisits() {
//         return visitLogRepository.findByCheckOutTimeIsNull();
//     }

//     @Override
//     public VisitLog getVisitLog(Long id) {
//         return visitLogRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("VisitLog not found"));
//     }
// }
