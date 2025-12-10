package com.MIL8I8.Planthelper;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lites")
public class LiteController {
    private final LiteRepository repo;

    public LiteController(LiteRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Lite> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Lite create(@RequestBody Lite lite) {
        return repo.save(lite);
    }
}
