package br.edu.iftm.springmongo.controllers;

import br.edu.iftm.springmongo.models.User;
import br.edu.iftm.springmongo.models.dto.UserDTO;
import br.edu.iftm.springmongo.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return userService.findAll();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<UserDTO> findById(ObjectId id) {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO user) {
        return userService.update(user);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") ObjectId id) {
        return userService.delete(id);
    }
}
