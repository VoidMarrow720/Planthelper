package com.MIL8I8.Planthelper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/plants")
public class PlantController {

    private final PlantRepository repo;
    private final LiteRepository liteRepo;
    private final WaterRepository waterRepo;
    private final SoilRepository soilRepo;
    private final TempRepository tempRepo;
    private final HumRepository humRepo;
    private final GroupRepository groupRepo;

    public PlantController(PlantRepository repo,
                           LiteRepository liteRepo,
                           WaterRepository waterRepo,
                           SoilRepository soilRepo,
                           TempRepository tempRepo,
                           HumRepository humRepo,
                           GroupRepository groupRepo) {
        this.repo = repo;
        this.liteRepo = liteRepo;
        this.waterRepo = waterRepo;
        this.soilRepo = soilRepo;
        this.tempRepo = tempRepo;
        this.humRepo = humRepo;
        this.groupRepo = groupRepo;
    }

    @GetMapping
    public List<Plant> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Plant addPlant(@RequestBody Plant plant) {
        // Resolve single-valued Group
        if (plant.getGroup() != null && plant.getGroup().getId() != null) {
            groupRepo.findById(plant.getGroup().getId()).ifPresent(plant::setGroup);
        }

        // Resolve collection-valued references (lites, waters, soils, temps, hums)
        if (plant.getLites() != null) {
            var resolved = new java.util.HashSet<Lite>();
            for (Lite l : plant.getLites()) {
                if (l != null && l.getId() != null) {
                    liteRepo.findById(l.getId()).ifPresent(resolved::add);
                }
            }
            plant.setLites(resolved);
        }
        if (plant.getWaters() != null) {
            var resolved = new java.util.HashSet<Water>();
            for (Water w : plant.getWaters()) {
                if (w != null && w.getId() != null) {
                    waterRepo.findById(w.getId()).ifPresent(resolved::add);
                }
            }
            plant.setWaters(resolved);
        }
        if (plant.getSoils() != null) {
            var resolved = new java.util.HashSet<Soil>();
            for (Soil s : plant.getSoils()) {
                if (s != null && s.getId() != null) {
                    soilRepo.findById(s.getId()).ifPresent(resolved::add);
                }
            }
            plant.setSoils(resolved);
        }
        if (plant.getTemps() != null) {
            var resolved = new java.util.HashSet<Temp>();
            for (Temp t : plant.getTemps()) {
                if (t != null && t.getId() != null) {
                    tempRepo.findById(t.getId()).ifPresent(resolved::add);
                }
            }
            plant.setTemps(resolved);
        }
        if (plant.getHums() != null) {
            var resolved = new java.util.HashSet<Hum>();
            for (Hum h : plant.getHums()) {
                if (h != null && h.getId() != null) {
                    humRepo.findById(h.getId()).ifPresent(resolved::add);
                }
            }
            plant.setHums(resolved);
        }

        return repo.save(plant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}