package com.example.alugueiscarros.service;
import com.example.alugueiscarros.entity.Usuario;
import com.example.alugueiscarros.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService<T extends Usuario>  {

    protected UsuarioRepository<T> usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder;
    public T registrar(T usuario) {
        // Verifica se o login já existe
        if (usuarioRepository.findByLogin(usuario.getLogin()) != null) {
            throw new RuntimeException("Login já existe");
        }
        // Criptografa a senha
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }
    public T login(String login, String senha) {
        T usuario = usuarioRepository.findByLogin(login);
        if (usuario != null && passwordEncoder.matches(senha, usuario.getSenha())) {
            return usuario;
        }
        throw new RuntimeException("Login ou senha inválidos");
    }
}
