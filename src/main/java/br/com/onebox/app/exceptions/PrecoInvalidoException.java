package br.com.onebox.app.exceptions;

public class PrecoInvalidoException extends RuntimeException {
    public PrecoInvalidoException (String msg){
        super (msg);
    }
}
