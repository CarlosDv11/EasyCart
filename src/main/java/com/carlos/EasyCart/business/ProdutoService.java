package com.carlos.EasyCart.business;

import com.carlos.EasyCart.infrastructure.dto.ProdutoRequestDTO;
import com.carlos.EasyCart.infrastructure.dto.ProdutoResponseDTO;
import com.carlos.EasyCart.infrastructure.entity.Produto;
import com.carlos.EasyCart.infrastructure.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        Produto produto = Produto.builder()
                .nome(dto.nome())
                .descricao(dto.descricao())
                .preco(dto.preco())
                .estoque(dto.estoque())
                .build();
        return converterParaResponse(repository.save(produto));
    }

    public List<ProdutoResponseDTO> buscarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    public ProdutoResponseDTO buscarPorId(Integer id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return converterParaResponse(produto);
    }

    public ProdutoResponseDTO atualizar(Integer id, ProdutoRequestDTO dto) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (dto.nome() != null) produto.setNome(dto.nome());
        if (dto.preco() != null) produto.setPreco(dto.preco());
        if (dto.estoque() != null) produto.setEstoque(dto.estoque());
        if (dto.descricao() != null) produto.setDescricao(dto.descricao());

        return converterParaResponse(repository.save(produto));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    private ProdutoResponseDTO converterParaResponse(Produto p) {
        return new ProdutoResponseDTO(p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getEstoque(), p.getDataCriacao());
    }
}