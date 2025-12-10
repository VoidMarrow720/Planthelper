package com.MIL8I8.Planthelper;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> create(@RequestBody Hum hum) {
        var tipe = hum.getTipe();
        if (tipe == null) {
            return ResponseEntity.badRequest().body("'tipe' is required");
        }
        // try to parse numeric humidity (allow decimals, comma or dot)
        String normalized = tipe.replace(',', '.').trim();
        double value;
        try {
            value = Double.parseDouble(normalized);
        } catch (NumberFormatException ex) {
            return ResponseEntity.badRequest().body("Humidity must be a number between 0 and 100");
        }
        if (value < 0.0 || value > 100.0) {
            return ResponseEntity.badRequest().body("Humidity must be between 0 and 100 (inclusive)");
        }
        // store the normalized value back as string (preserve existing schema)
        // round to max 2 decimal places for storage
        hum.setTipe(String.valueOf(Math.round(value * 100.0) / 100.0));
        var saved = repo.save(hum);
        return ResponseEntity.ok(saved);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
