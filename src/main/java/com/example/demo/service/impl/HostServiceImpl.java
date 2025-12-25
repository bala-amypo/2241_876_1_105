



package com.example.demo.service.impl;

import com.example.demo.model.Host;
import com.example.demo.repository.HostRepository;
import com.example.demo.service.HostService;
import org.springframework.stereotype.Service;

@Service
public class HostServiceImpl implements HostService {

    // ⚠️ REQUIRED by ReflectionTestUtils
    private HostRepository hostRepository;

    public HostServiceImpl() {}

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
