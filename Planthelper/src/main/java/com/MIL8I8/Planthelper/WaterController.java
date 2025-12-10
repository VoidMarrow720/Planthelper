package com.MIL8I8.Planthelper;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/waters")
public class WaterController {
    private final WaterRepository repo;

    public WaterController(WaterRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Water> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Water create(@RequestBody Water water) {
        return repo.save(water);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
