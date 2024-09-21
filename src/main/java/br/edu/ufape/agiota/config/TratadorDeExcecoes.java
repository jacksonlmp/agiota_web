package br.edu.ufape.agiota.config;

import br.edu.ufape.agiota.fachada.exceptions.OperacaoNaoPermitidaException;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeExcecoes {

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    protected ResponseEntity<Object> tratarExcecaoRegistroDuplicado(OperacaoNaoPermitidaException ex) {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("tipo", "OperacaoNaoPermitida");
        resposta.put("mensagem", ex.getMessage());

        Map<String, Object> erro = new HashMap<>();
        erro.put("erro", resposta);

        return new ResponseEntity<>(erro, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    protected ResponseEntity<Object> tratarExcecaoRegistroNaoEncontrado(RegistroNaoEncontradoException ex) {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("tipo", "RegistroNaoEncontrado");
        resposta.put("mensagem", ex.getMessage());

        Map<String, Object> erro = new HashMap<>();
        erro.put("erro", resposta);

        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }
}
