package com.MIL8I8.Planthelper;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> create(@RequestBody Temp temp) {
        var tipe = temp.getTipe();
        if (tipe == null) {
            return ResponseEntity.badRequest().body("'tipe' is required");
        }
        String normalized = tipe.replace(',', '.').trim();
        double value;
        try {
            value = Double.parseDouble(normalized);
        } catch (NumberFormatException ex) {
            return ResponseEntity.badRequest().body("Temperature must be a number between -10 and 40");
        }
        if (value < -10.0 || value > 40.0) {
            return ResponseEntity.badRequest().body("Temperature must be between -10 and 40 (inclusive)");
        }
        // normalize stored value (round to 2 decimals)
        temp.setTipe(String.valueOf(Math.round(value * 100.0) / 100.0));
        var saved = repo.save(temp);
        return ResponseEntity.ok(saved);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
