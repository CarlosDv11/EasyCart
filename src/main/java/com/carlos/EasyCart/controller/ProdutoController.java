package com.carlos.EasyCart.controller;

import com.carlos.EasyCart.business.ProdutoService;
import com.carlos.EasyCart.infrastructure.dto.ProdutoRequestDTO;
import com.carlos.EasyCart.infrastructure.dto.ProdutoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Integer id, @RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}