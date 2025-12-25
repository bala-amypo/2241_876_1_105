package com.example.demo.service.impl;

import com.example.demo.model.Host;
import com.example.demo.model.VisitLog;
import com.example.demo.model.Visitor;
import com.example.demo.repository.HostRepository;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitLogServiceImpl implements VisitLogService {

    @Autowired
    private VisitLogRepository visitLogRepository;

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private HostRepository hostRepository;

    // REQUIRED for TestNG
    public VisitLogServiceImpl() {
    }

    // Optional constructor
    public VisitLogServiceImpl(VisitLogRepository visitLogRepository,
                               VisitorRepository visitorRepository,
                               HostRepository hostRepository) {
        this.visitLogRepository = visitLogRepository;
        this.visitorRepository = visitorRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public VisitLog checkInVisitor(Long visitorId, Long hostId, String purpose) {

        Visitor visitor = visitorRepository.findById(visitorId)
                .orElseThrow(() -> new RuntimeException("Visitor not found"));

        Host host = hostRepository.findById(hostId)
                .orElseThrow(() -> new RuntimeException("Host not found"));

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
    public VisitLog getVisitLog(Long id) {
        return visitLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VisitLog not found"));
    }

    @Override
    public List<VisitLog> getActiveVisits() {
        return visitLogRepository.findByCheckOutTimeIsNull();
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

//     // REQUIRED for tests
//     private VisitLogRepository visitLogRepository;
//     private VisitorRepository visitorRepository;
//     private HostRepository hostRepository;

//     // ✅ NO-ARG constructor (TESTS)
//     public VisitLogServiceImpl() {}

//     // ✅ Constructor injection (RUNTIME)
//     public VisitLogServiceImpl(VisitLogRepository visitLogRepository,
//                                VisitorRepository visitorRepository,
//                                HostRepository hostRepository) {
//         this.visitLogRepository = visitLogRepository;
//         this.visitorRepository = visitorRepository;
//         this.hostRepository = hostRepository;
//     }

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
