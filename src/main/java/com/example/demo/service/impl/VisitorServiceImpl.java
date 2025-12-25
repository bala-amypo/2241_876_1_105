package com.example.demo.service.impl;

import com.example.demo.model.Visitor;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.service.VisitorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;

    // ✅ Constructor injection (Swagger + Tests)
    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public Visitor createVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    @Override
    public Visitor getVisitor(Long id) {
        return visitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitor not found"));
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }
}


// package com.example.demo.service.impl;

// import com.example.demo.model.Visitor;
// import com.example.demo.repository.VisitorRepository;
// import com.example.demo.service.VisitorService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class VisitorServiceImpl implements VisitorService {

//     // ⚠️ REQUIRED by ReflectionTestUtils
//     private VisitorRepository visitorRepository;

//     // Constructor used in tests
//     public VisitorServiceImpl(VisitorRepository visitorRepository) {
//         this.visitorRepository = visitorRepository;
//     }

//     // Default constructor
//     public VisitorServiceImpl() {}

//     @Override
//     public Visitor createVisitor(Visitor visitor) {
//         return visitorRepository.save(visitor);
//     }

//     @Override
//     public Visitor getVisitor(Long id) {
//         return visitorRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Visitor not found"));
//     }

//     @Override
//     public List<Visitor> getAllVisitors() {
//         return visitorRepository.findAll();
//     }
// }
