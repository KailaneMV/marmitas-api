package br.com.marmitas.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public class CadastroUsuarioRequest {

    @NotBlank(message = "O nome é obrigatório.")
    String nome;

    @NotBlank(message =  "O e-mail é obrigatório.")
    @Email(message = "Insira um e-mail válido")
    String email;

    @NotBlank(message =  "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    String senha;

    public String getNome(){
        return nome;
    }

    public String getEmail(){
        return email;
    }

    public String getSenha(){
        return senha;
    }

}
