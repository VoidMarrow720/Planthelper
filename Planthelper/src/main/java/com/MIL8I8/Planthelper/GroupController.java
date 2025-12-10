package com.MIL8I8.Planthelper;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupRepository repo;

    public GroupController(GroupRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Group> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Group create(@RequestBody Group group) {
        return repo.save(group);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
