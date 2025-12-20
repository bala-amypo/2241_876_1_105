
package com.example.demo.service;

import com.example.demo.entity.Host;
import java.util.List;

public interface HostService {

    Host createHost(Host host);

    Host getHost(Long id);

    Host getHostByEmail(String email);

    List<Host> getAllHosts();
}
