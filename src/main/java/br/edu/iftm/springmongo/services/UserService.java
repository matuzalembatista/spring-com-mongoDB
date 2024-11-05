package br.edu.iftm.springmongo.services;

import br.edu.iftm.springmongo.models.User;
import br.edu.iftm.springmongo.models.dto.UserDTO;
import br.edu.iftm.springmongo.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<UserDTO>> findAll() {
        var dbUsers = userRepository.findAll();
        if (dbUsers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userDTOs = dbUsers.stream().map(user -> {
            var userDTO = new UserDTO(user);
            return userDTO;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }

    public ResponseEntity<UserDTO> findById(ObjectId id) {
        if (id == null) return ResponseEntity.badRequest().build();
        var dbUser = userRepository.findById(id);

        if (dbUser.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new UserDTO(dbUser.get()));
    }

    public ResponseEntity<UserDTO> save(User user) {
        if (user.getName().isBlank() || user.getAge() <= 0) return ResponseEntity.badRequest().build();
        user.setId(ObjectId.get());
        return ResponseEntity.ok(new UserDTO(userRepository.save(user)));
    }

    public ResponseEntity<UserDTO> update(UserDTO user) {
        if (user.getId() == null) return ResponseEntity.badRequest().build();
        var objectId = new ObjectId(user.getId());
        var dbUser = userRepository.findById(objectId);
        if (dbUser.isEmpty()) return ResponseEntity.notFound().build();
        var userToUpdate = dbUser.get();
        userToUpdate.setName(user.getName());
        userToUpdate.setAge(user.getAge());
        return ResponseEntity.ok(new UserDTO(userRepository.save(userToUpdate)));
    }

    public ResponseEntity<String> delete(ObjectId id) {
        if (id == null) return ResponseEntity.badRequest().build();
        userRepository.deleteById(id);
        var dbUser = userRepository.findById(id);
        if (dbUser.isPresent()) return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        return ResponseEntity.ok("Usuário deletado com sucesso");
    }

}
