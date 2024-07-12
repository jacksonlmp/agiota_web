package br.edu.ufape.agiota.fachada.exceptions;

public class RegistroJaExistenteException extends RuntimeException {
    public RegistroJaExistenteException(String msg) {
        super(msg);
    }
}
