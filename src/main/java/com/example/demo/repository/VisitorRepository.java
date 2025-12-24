package com.example.demo.repository;

import com.example.demo.model.Visitor;
import java.util.List;
import java.util.Optional;

public interface VisitorRepository {

    Visitor save(Visitor visitor);

    Optional<Visitor> findById(Long id);

    List<Visitor> findAll();
}
