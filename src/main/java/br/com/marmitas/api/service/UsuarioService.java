package br.com.marmitas.api.service;

import br.com.marmitas.api.dto.CadastroUsuarioRequest;
import br.com.marmitas.api.dto.CadastroUsuarioResponse;
import br.com.marmitas.api.dto.LoginRequest;
import br.com.marmitas.api.dto.LoginResponse;
import br.com.marmitas.api.exception.EmailJaCadastradoException;
import br.com.marmitas.api.exception.UsuarioNaoCadastradoException;
import br.com.marmitas.api.exception.UsuarioInativoException;
import br.com.marmitas.api.exception.SenhaIncorretaException;
import br.com.marmitas.api.model.TipoUsuario;
import br.com.marmitas.api.model.Usuario;
import br.com.marmitas.api.repository.UsuarioRepository;

import br.com.marmitas.api.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    //fazer injeção do token
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder;
    private final TokenService tokenService;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder encoder,TokenService tokenService){
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
        this.tokenService = tokenService;
    }

    //funções

    public CadastroUsuarioResponse cadastrar(CadastroUsuarioRequest request){

        if (usuarioRepository.existsByEmail(request.getEmail())){
            throw new EmailJaCadastradoException();
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(request.getNome());
        novoUsuario.setSenha(encoder.encode(request.getSenha()));
        novoUsuario.setEmail(request.getEmail());
        novoUsuario.setTipo(TipoUsuario.COMUM);

        usuarioRepository.save(novoUsuario);

        return new CadastroUsuarioResponse(novoUsuario.getId(), novoUsuario.getNome(), novoUsuario.getEmail());
    }

    public LoginResponse login(LoginRequest request){

        Optional<Usuario> loginOpt = usuarioRepository.findByEmail(request.getEmail());

        if(loginOpt.isEmpty()){
            throw new UsuarioNaoCadastradoException();
        }

        Usuario usuario = loginOpt.get();

        if(!usuario.getAtivo()){
            throw new UsuarioInativoException();
        }

        if(!encoder.matches(request.getSenha(), usuario.getSenha())){
            throw new SenhaIncorretaException();
        }

        String token = tokenService.gerarToken(usuario);

        return new LoginResponse(usuario.getNome(),usuario.getEmail(), usuario.getId(), usuario.getTipo(), token);
    }

}
