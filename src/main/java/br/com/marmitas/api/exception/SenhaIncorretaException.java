package br.com.marmitas.api.exception;

public class SenhaIncorretaException extends RuntimeException{
    public SenhaIncorretaException(){
        super("Senha incorreta.");
    }
}
