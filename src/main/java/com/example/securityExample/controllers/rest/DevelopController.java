package com.example.securityExample.controllers.rest;

import com.example.securityExample.entities.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class DevelopController {

    private final List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L, "Ivan", "Ivanov"),
            new Developer(2L, "Sergey", "Sergeev"),
            new Developer(3L, "Petr", "Petrov")
    ).collect(Collectors.toList());


    @GetMapping
    public List<Developer> getAll() {
        return DEVELOPERS;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('developers:read')")
    public Developer getById(@PathVariable Long id) {
        return DEVELOPERS.stream().
                filter(developer -> developer.getId().equals(id))
                .findAny().orElseThrow(RuntimeException::new);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('developers:write')")
    public Developer saveDeveloper(@RequestBody Developer developer) {
        DEVELOPERS.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('developers:write')")
    public void deleteDeveloper(@PathVariable Long id) {
        DEVELOPERS.removeIf(developer -> developer.getId().equals(id));
    }
}
