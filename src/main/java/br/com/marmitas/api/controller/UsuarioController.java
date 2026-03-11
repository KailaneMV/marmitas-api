package br.com.marmitas.api.controller;

import br.com.marmitas.api.dto.LoginResponse;
import br.com.marmitas.api.dto.LoginRequest;
import br.com.marmitas.api.service.UsuarioService;
import br.com.marmitas.api.dto.CadastroUsuarioResponse;
import br.com.marmitas.api.dto.CadastroUsuarioRequest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    //Funções

    @PostMapping("/cadastro")
    public ResponseEntity<CadastroUsuarioResponse> cadastrar(@Valid @RequestBody CadastroUsuarioRequest request){

        CadastroUsuarioResponse response = usuarioService.cadastrar(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){

        LoginResponse response = usuarioService.login(request);

        return ResponseEntity.ok(response);
    }

}
