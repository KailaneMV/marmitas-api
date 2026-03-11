package br.com.marmitas.api.exception;

public class UsuarioInativoException extends RuntimeException{
    public UsuarioInativoException(){
        super("Usuário Inativo.");
    }
}
