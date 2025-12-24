package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.exception.ResourceNotFoundException;
import java.util.*;

public class VisitorServiceImpl {

    VisitorRepository visitorRepository;

    public VisitorServiceImpl(VisitorRepository repo) {
        this.visitorRepository = repo;
    }

    public Visitor createVisitor(Visitor v) {
        return visitorRepository.save(v);
    }

    public Visitor getVisitor(Long id) {
        return visitorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
    }

    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }
}
