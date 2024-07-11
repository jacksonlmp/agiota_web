package br.edu.ufape.agiota.fachada.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException {
    public OperacaoNaoPermitidaException(String msg) {
        super(msg);
    }
}
