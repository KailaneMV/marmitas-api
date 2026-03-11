package br.com.marmitas.api.dto;

import br.com.marmitas.api.model.TipoUsuario;
import br.com.marmitas.api.security.TokenService;

public record LoginResponse(String nome, String email, Long id, TipoUsuario tipo, String token) {
}
