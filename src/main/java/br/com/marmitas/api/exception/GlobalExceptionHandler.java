//Tratamento dos erros
package br.com.marmitas.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){

        var fieldError = ex.getBindingResult().getFieldError();

        String mensagemLimpa = (fieldError != null) ? fieldError.getDefaultMessage() : "Erro de validação";

        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), mensagemLimpa);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ApiError> handleEmailJaCadastrado(EmailJaCadastradoException ex){

        ApiError error = new ApiError(HttpStatus.CONFLICT.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(UsuarioNaoCadastradoException.class)
    public ResponseEntity<ApiError> handleUsuarioNaoCadastrado(UsuarioNaoCadastradoException ex){

        ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(UsuarioInativoException.class)
    public ResponseEntity<ApiError> handleUsuarioInativo(UsuarioInativoException ex){

        ApiError error = new ApiError(HttpStatus.FORBIDDEN.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(SenhaIncorretaException.class)
    public ResponseEntity<ApiError> handleSenhaIncorreta(SenhaIncorretaException ex){

        ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
