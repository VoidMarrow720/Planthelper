package com.MIL8I8.Planthelper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/plants")
public class PlantController {

    private final PlantRepository repo;

    public PlantController(PlantRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Plant> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Plant addPlant(@RequestBody Plant plant) {
        return repo.save(plant);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Plant> getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}