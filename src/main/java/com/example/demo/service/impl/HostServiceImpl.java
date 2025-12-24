package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.exception.ResourceNotFoundException;

public class HostServiceImpl {

    HostRepository hostRepository;

    public HostServiceImpl() {}

    public Host createHost(Host h) {
        return hostRepository.save(h);
    }

    public Host getHost(Long id) {
        return hostRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Host not found"));
    }
}
