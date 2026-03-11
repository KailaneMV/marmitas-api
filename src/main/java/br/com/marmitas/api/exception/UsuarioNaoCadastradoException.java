package br.com.marmitas.api.exception;

public class UsuarioNaoCadastradoException extends RuntimeException{
    public UsuarioNaoCadastradoException(){
        super("Usuário não cadastrado.");
    }
}
