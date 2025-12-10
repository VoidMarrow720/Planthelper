package com.MIL8I8.Planthelper;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/temps")
public class TempController {
    private final TempRepository repo;

    public TempController(TempRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Temp> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Temp create(@RequestBody Temp temp) {
        return repo.save(temp);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
