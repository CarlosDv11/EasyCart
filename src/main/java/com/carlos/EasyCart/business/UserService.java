package com.carlos.EasyCart.business;

import com.carlos.EasyCart.infrastructure.dto.UserRequestDTO;
import com.carlos.EasyCart.infrastructure.dto.UserResponseDTO;
import com.carlos.EasyCart.infrastructure.entity.UserRole;
import com.carlos.EasyCart.infrastructure.entity.User;
import com.carlos.EasyCart.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO salvarUsuario(UserRequestDTO dto) {
        User user = User.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(passwordEncoder.encode(dto.senha()))
                .cpf(dto.cpf())
                .telefone(dto.telefone())
                .role(UserRole.USER)
                .ativo(dto.ativo() != null ? dto.ativo() : true)
                .build();

        return converterParaResponseDTO(repository.save(user));
    }

    public List<UserResponseDTO> buscarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO buscarPorId(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
        return converterParaResponseDTO(user);
    }

    public void deletarUsuario(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar: Usuário não encontrado.");
        }
        repository.deleteById(id);
    }

    public UserResponseDTO buscarPorEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + email));
        return converterParaResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO atualizarUsuario(Integer id, UserRequestDTO dto) {
        User userEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (dto.nome() != null) userEntity.setNome(dto.nome());
        if (dto.email() != null) userEntity.setEmail(dto.email());
        if (dto.telefone() != null) userEntity.setTelefone(dto.telefone());
        if (dto.ativo() != null) userEntity.setAtivo(dto.ativo());

        return converterParaResponseDTO(repository.save(userEntity));
    }

    private UserResponseDTO converterParaResponseDTO(User entity) {
        return new UserResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getAtivo(),
                entity.getDataCriacao()
        );
    }
}