package br.com.marmitas.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Insira um e-mail válido.")
    String email;

    @NotBlank(message = "Digite sua senha.")
    @Size(min = 6, message = "Senha incorreta")
    String senha;

    public String getEmail(){
        return email;
    }

    public String getSenha(){
        return senha;
    }
}
