package com.MIL8I8.Planthelper;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/hums")
public class HumController {
    private final HumRepository repo;

    public HumController(HumRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Hum> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Hum create(@RequestBody Hum hum) {
        return repo.save(hum);
    }
}
