package store.games.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;


@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity TratarDadoNãolocalizado(ValidacaoException ex){

        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity tratarBadRequest(){
         return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, NullPointerException.class} )
    public ResponseEntity tratarValorInvalidoJson(){
        return ResponseEntity.badRequest().build();
    }
}


