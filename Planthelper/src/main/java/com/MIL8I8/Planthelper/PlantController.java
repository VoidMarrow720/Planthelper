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

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Plant add(@RequestBody Plant plant) {
        // id-t nem küldünk — @GeneratedValue generálja
        return repo.save(plant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plant> update(@PathVariable Long id, @RequestBody Plant update) {
        return repo.findById(id).map(existing -> {
            existing.setNev(update.getNev());
            existing.setLatinNev(update.getLatinNev());
            existing.setGid(update.getGid());
            existing.setLid(update.getLid());
            existing.setWid(update.getWid());
            existing.setSid(update.getSid());
            existing.setTid(update.getTid());
            existing.setHid(update.getHid());
            return ResponseEntity.ok(repo.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}