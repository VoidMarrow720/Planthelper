package com.MIL8I8.Planthelper;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/soils")
public class SoilController {
    private final SoilRepository repo;

    public SoilController(SoilRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Soil> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Soil create(@RequestBody Soil soil) {
        return repo.save(soil);
    }
}
