//Formato do erro(Deixa o erro entendível aos usuários)
package br.com.marmitas.api.exception;

public class ApiError {
    private int status;
    private String message;

    public ApiError(int status, String mensagem){
        this.status = status;
        this.message = mensagem;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
