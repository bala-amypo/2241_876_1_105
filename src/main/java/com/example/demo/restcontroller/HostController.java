

package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.HostDTO;
import com.example.demo.entity.Host;
import com.example.demo.service.HostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hosts")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createHost(@RequestBody HostDTO dto) {

        Host host = new Host();
        host.setHostName(dto.getHostName());
        host.setEmail(dto.getEmail());
        host.setDepartment(dto.getDepartment());
        host.setPhone(dto.getPhone());

        Host saved = hostService.createHost(host);

        return new ResponseEntity<>(
                new ApiResponse(true, "Host created", toDTO(saved)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getHost(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Host fetched", toDTO(hostService.getHost(id)))
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllHosts() {
        List<HostDTO> hosts = hostService.getAllHosts()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new ApiResponse(true, "All hosts", hosts)
        );
    }

    private HostDTO toDTO(Host host) {
        HostDTO dto = new HostDTO();
        dto.setId(host.getId());
        dto.setHostName(host.getHostName());
        dto.setEmail(host.getEmail());
        dto.setDepartment(host.getDepartment());
        dto.setPhone(host.getPhone());
        return dto;
    }
}
