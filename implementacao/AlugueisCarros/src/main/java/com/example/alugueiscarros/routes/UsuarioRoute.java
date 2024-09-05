package com.example.alugueiscarros.routes;

import com.example.alugueiscarros.models.*;
import com.example.alugueiscarros.services.*;


import  com.example.alugueiscarros.utils.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UsuarioRoute {

    private class UsuarioResponse {
        private final Boolean error;
        private final String message;
        private final List<UsuarioModel> usuarios;

        public UsuarioResponse(Boolean error, String message, List<UsuarioModel> usuarios) {
            this.error = error;
            this.message = message;
            this.usuarios = usuarios;
        }

        public UsuarioResponse(Boolean error, String message) {
            this(error, message, null);
        }
    }

    @PostMapping("/usuario/new")
    public String createUsuario(@RequestBody UsuarioModel usuario) {
        final String[] response = new String[1];
        usuario.setIdUsuario(UUID.randomUUID().toString());
        UsuarioService.addUsuario(usuario, (success, message) -> {
            if (success) {
                response[0] = HttpResponseUtil.toJson(new UsuarioResponse(false, message));
            } else {
                response[0] = HttpResponseUtil.toJson(new UsuarioResponse(true, message));
            }
        });
        return response[0];
    }

    @GetMapping("/usuario/list")
    public String getAllUsuarios() {
        final String[] response = new String[1];
        UsuarioService.listUsuarios((success, usuarios) -> {
            if (success) {
                response[0] = HttpResponseUtil.toJson(new UsuarioResponse(false, "Got user list.", usuarios));
            } else {
                response[0] = HttpResponseUtil.toJson(new UsuarioResponse(true, "Error while retrieving users."));
            }
        });
        return response[0];
    }

    @GetMapping("/usuario/{usuarioId}")
    public String getUsuario(@PathVariable String usuarioId) {
        final String[] response = new String[1];
        UsuarioService.getUsuario(usuarioId, (success, usuarios) -> {
            if (success) {
                response[0] = HttpResponseUtil.toJson(new UsuarioResponse(false, "Got user list.", usuarios));
            } else {
                response[0] = HttpResponseUtil.toJson(new UsuarioResponse(true, "Error while retrieving user."));
            }
        });
        return response[0];
    }

    @PutMapping("/usuario/{usuarioId}")
    public String updateUsuario(@PathVariable String usuarioId, @RequestBody UsuarioModel usuario) {
        usuario.setIdUsuario(usuarioId);
        final String[] response = new String[1];
        UsuarioService.updateUsuario(usuario, (success, message) -> {
            if (success) {
                response[0] = HttpResponseUtil.toJson(new UsuarioResponse(false, message));
            } else {
                response[0] = HttpResponseUtil.toJson(new UsuarioResponse(true, message));
            }
        });
        return response[0];
    }

    @DeleteMapping("/usuario/{usuarioId}")
    public String deleteUsuario(@PathVariable String usuarioId) {
        final String[] response = new String[1];
        UsuarioService.deleteUsuario(usuarioId, (success, message) -> {
            if (success) {
                response[0] = HttpResponseUtil.toJson(new UsuarioResponse(false, message));
            } else {
                response[0] = HttpResponseUtil.toJson(new UsuarioResponse(true, message));
            }
        });
        return response[0];
    }
}