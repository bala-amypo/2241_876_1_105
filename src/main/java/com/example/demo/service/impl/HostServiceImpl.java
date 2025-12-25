package com.example.demo.service.impl;

import com.example.demo.model.Host;
import com.example.demo.repository.HostRepository;
import com.example.demo.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostServiceImpl implements HostService {

    // ✅ REQUIRED for Swagger runtime
    @Autowired
    private HostRepository hostRepository;

    // ✅ REQUIRED for TestNG
    public HostServiceImpl() {}

    // Optional constructor (safe to keep)
    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public Host createHost(Host host) {
        return hostRepository.save(host);
    }

    @Override
    public Host getHost(Long id) {
        return hostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Host not found"));
    }
}


// package com.example.demo.service.impl;

// import com.example.demo.model.Host;
// import com.example.demo.repository.HostRepository;
// import com.example.demo.service.HostService;
// import org.springframework.stereotype.Service;

// @Service
// public class HostServiceImpl implements HostService {

//     // REQUIRED for tests (ReflectionTestUtils)
//     private HostRepository hostRepository;

//     // ✅ NO-ARG constructor (TESTS NEED THIS)
//     public HostServiceImpl() {}

//     // ✅ Constructor injection (SPRING RUNTIME)
//     public HostServiceImpl(HostRepository hostRepository) {
//         this.hostRepository = hostRepository;
//     }

//     @Override
//     public Host createHost(Host host) {
//         return hostRepository.save(host);
//     }

//     @Override
//     public Host getHost(Long id) {
//         return hostRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Host not found"));
//     }
// }


