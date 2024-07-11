package br.edu.ufape.agiota.fachada.exceptions;

public class RegistroNaoEncontradoException extends RuntimeException {
    public RegistroNaoEncontradoException(String msg) {
        super(msg);
    }
}
