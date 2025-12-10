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
        // Resolve references by id if provided to ensure managed entities
        if (plant.getLite() != null && plant.getLite().getId() != null) {
            liteRepo.findById(plant.getLite().getId()).ifPresent(plant::setLite);
        }
        if (plant.getWater() != null && plant.getWater().getId() != null) {
            waterRepo.findById(plant.getWater().getId()).ifPresent(plant::setWater);
        }
        if (plant.getSoil() != null && plant.getSoil().getId() != null) {
            soilRepo.findById(plant.getSoil().getId()).ifPresent(plant::setSoil);
        }
        if (plant.getTemp() != null && plant.getTemp().getId() != null) {
            tempRepo.findById(plant.getTemp().getId()).ifPresent(plant::setTemp);
        }
        if (plant.getHum() != null && plant.getHum().getId() != null) {
            humRepo.findById(plant.getHum().getId()).ifPresent(plant::setHum);
        }
        if (plant.getGroup() != null && plant.getGroup().getId() != null) {
            groupRepo.findById(plant.getGroup().getId()).ifPresent(plant::setGroup);
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