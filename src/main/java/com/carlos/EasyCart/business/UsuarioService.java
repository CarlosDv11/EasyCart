package com.carlos.EasyCart.business;

import com.carlos.EasyCart.infrastructure.dto.UsuarioRequestDTO;
import com.carlos.EasyCart.infrastructure.dto.UsuarioResponseDTO;
import com.carlos.EasyCart.infrastructure.entity.UserRole;
import com.carlos.EasyCart.infrastructure.entity.Usuario;
import com.carlos.EasyCart.infrastructure.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseDTO salvarUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(passwordEncoder.encode(dto.senha()))
                .cpf(dto.cpf())
                .telefone(dto.telefone())
                .role(UserRole.USER)
                .ativo(dto.ativo() != null ? dto.ativo() : true)
                .build();

        return converterParaResponseDTO(repository.save(usuario));
    }

    public List<UsuarioResponseDTO> buscarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(Integer id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
        return converterParaResponseDTO(usuario);
    }

    public void deletarUsuario(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar: Usuário não encontrado.");
        }
        repository.deleteById(id);
    }

    public UsuarioResponseDTO buscarPorEmail(String email) {
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + email));
        return converterParaResponseDTO(usuario);
    }

    @Transactional
    public UsuarioResponseDTO atualizarUsuario(Integer id, UsuarioRequestDTO dto) {
        Usuario usuarioEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (dto.nome() != null) usuarioEntity.setNome(dto.nome());
        if (dto.email() != null) usuarioEntity.setEmail(dto.email());
        if (dto.telefone() != null) usuarioEntity.setTelefone(dto.telefone());
        if (dto.ativo() != null) usuarioEntity.setAtivo(dto.ativo());

        return converterParaResponseDTO(repository.save(usuarioEntity));
    }

    private UsuarioResponseDTO converterParaResponseDTO(Usuario entity) {
        return new UsuarioResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getAtivo(),
                entity.getDataCriacao()
        );
    }
}