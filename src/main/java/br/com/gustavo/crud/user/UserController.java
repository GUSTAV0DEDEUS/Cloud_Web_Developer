package br.com.gustavo.crud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private  IUserRepository userRepository;
    @PostMapping("/create/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByname(userModel.getName());

        if(user != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario Existe");
        }

        var userCreate = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreate);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable UUID id){
        var userFind = this.userRepository.findById(id);
        if(userFind.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userFind.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }

    @GetMapping("/all")
    public ResponseEntity findAll(){
        List<UserModel> users = userRepository.findAll();
        if(users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable UUID id) {
        Optional<UserModel> userFindOptional = this.userRepository.findById(id);

        if (userFindOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado o usuário com o Id: " + id);
        }

        UserModel userFind = userFindOptional.get();
        this.userRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Usuário com o nome: " + userFind.getName() + " deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable UUID id, @RequestBody UserModel updatedUser) {
        var user = userRepository.findById(id).orElseThrow(()->null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com o ID " + id + " não encontrado");
        }

        var existingUser = user;

        existingUser.setName(updatedUser.getName());
        existingUser.setUserAge(updatedUser.getUserAge());
        existingUser.setFirstNote(updatedUser.getFirstNote());
        existingUser.setSecondNote(updatedUser.getSecondNote());
        existingUser.setNameTeacher(updatedUser.getNameTeacher());
        existingUser.setNumberClass(updatedUser.getNumberClass());

        UserModel updatedUserModel = userRepository.save(existingUser);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUserModel);
    }
}

