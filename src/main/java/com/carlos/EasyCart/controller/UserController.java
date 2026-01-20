package com.carlos.EasyCart.controller;

import com.carlos.EasyCart.business.UserService;
import com.carlos.EasyCart.infrastructure.dto.UserRequestDTO;
import com.carlos.EasyCart.infrastructure.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> salvarUsuario(@RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.salvarUsuario(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(userService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.buscarPorId(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> buscarPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.buscarPorEmail(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizarUsuario(@PathVariable Integer id, @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.atualizarUsuario(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) {
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}