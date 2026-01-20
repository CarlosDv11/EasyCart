package com.carlos.EasyCart.business;

import com.carlos.EasyCart.infrastructure.dto.ProductRequestDTO;
import com.carlos.EasyCart.infrastructure.dto.ProductResponseDTO;
import com.carlos.EasyCart.infrastructure.entity.Product;
import com.carlos.EasyCart.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public ProductResponseDTO salvar(ProductRequestDTO dto) {
        Product product = Product.builder()
                .nome(dto.nome())
                .descricao(dto.descricao())
                .preco(dto.preco())
                .estoque(dto.estoque())
                .build();
        return converterParaResponse(repository.save(product));
    }

    public List<ProductResponseDTO> buscarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO buscarPorId(Integer id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return converterParaResponse(product);
    }

    public ProductResponseDTO atualizar(Integer id, ProductRequestDTO dto) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (dto.nome() != null) product.setNome(dto.nome());
        if (dto.preco() != null) product.setPreco(dto.preco());
        if (dto.estoque() != null) product.setEstoque(dto.estoque());
        if (dto.descricao() != null) product.setDescricao(dto.descricao());

        return converterParaResponse(repository.save(product));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    private ProductResponseDTO converterParaResponse(Product p) {
        return new ProductResponseDTO(p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getEstoque(), p.getDataCriacao());
    }
}