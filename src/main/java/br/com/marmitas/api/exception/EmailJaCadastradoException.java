package br.com.marmitas.api.exception;

public class EmailJaCadastradoException extends RuntimeException{
    public EmailJaCadastradoException(){
        super("Este e-mail já está cadastrado.");
    }
}
