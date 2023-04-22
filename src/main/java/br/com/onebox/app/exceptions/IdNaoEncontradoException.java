package br.com.onebox.app.exceptions;

public class IdNaoEncontradoException extends RuntimeException {
    public IdNaoEncontradoException(String msg){
        super (msg);
    }
}
