package br.edu.ufape.agiota.fachada.exceptions;

public class OperacaoInvalidaException extends RuntimeException {
    public OperacaoInvalidaException(String msg) {
        super(msg);
    }
}
