package store.games.api.domain;

import jakarta.xml.bind.ValidationException;

public class ValidacaoException extends RuntimeException{
    public ValidacaoException(String e){
        super(e);
    }
}
