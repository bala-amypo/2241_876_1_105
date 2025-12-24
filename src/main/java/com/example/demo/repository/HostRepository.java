package com.example.demo.repository;

import com.example.demo.model.Host;
import java.util.Optional;

public interface HostRepository {

    Host save(Host host);

    Optional<Host> findById(Long id);

    Optional<Host> findByEmail(String email);
}
