package com.carlos.EasyCart.business;

import com.carlos.EasyCart.infrastructure.entity.Usuario;
import com.carlos.EasyCart.infrastructure.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    public List<Usuario> buscarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

    public void deletarUsuario(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar: Usuário não encontrado.");
        }
        repository.deleteById(id);
    }

    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + email));
    }

    @Transactional
    public Usuario atualizarUsuario(Integer id, Usuario dadosNovos) {
        Usuario usuarioEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioEntity.setNome(dadosNovos.getNome());
        usuarioEntity.setEmail(dadosNovos.getEmail());
        usuarioEntity.setTelefone(dadosNovos.getTelefone());
        usuarioEntity.setAtivo(dadosNovos.getAtivo());

        return repository.save(usuarioEntity);
    }

}