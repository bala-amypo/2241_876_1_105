package com.example.demo.repository;

import com.example.demo.model.VisitLog;
import java.util.List;
import java.util.Optional;

public interface VisitLogRepository {

    VisitLog save(VisitLog visitLog);

    Optional<VisitLog> findById(Long id);

    List<VisitLog> findByCheckOutTimeIsNull();
}
