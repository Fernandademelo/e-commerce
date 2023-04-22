package br.com.onebox.app.exceptions;

public class CPFInvalidoException extends RuntimeException {
    public CPFInvalidoException(String message) {
        super(message);
    }
}
