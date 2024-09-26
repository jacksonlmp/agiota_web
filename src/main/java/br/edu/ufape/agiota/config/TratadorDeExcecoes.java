package br.edu.ufape.agiota.config;

import br.edu.ufape.agiota.fachada.exceptions.OperacaoInvalidaException;
import br.edu.ufape.agiota.fachada.exceptions.OperacaoNaoPermitidaException;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeExcecoes {

    @Autowired
    private MessageSource messageSource;
    private ResponseEntity<Object> construirResposta(String tipo, String mensagem, HttpStatus status) {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("tipo", tipo);
        resposta.put("mensagem", mensagem);

        Map<String, Object> erro = new HashMap<>();
        erro.put("erro", resposta);

        return new ResponseEntity<>(erro, status);
    }

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    protected ResponseEntity<Object> tratarExcecaoOperacaoNaoPermitida(OperacaoNaoPermitidaException ex) {
        return construirResposta("OperacaoNaoPermitida", ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    protected ResponseEntity<Object> tratarExcecaoRegistroNaoEncontrado(RegistroNaoEncontradoException ex) {
        return construirResposta("RegistroNaoEncontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperacaoInvalidaException.class)
    protected ResponseEntity<Object> tratarExcecaoOperacaoInvalida(OperacaoInvalidaException ex) {
        return construirResposta("OperacaoInvalida", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormularioDTO> handle(MethodArgumentNotValidException exception) {
        List<ErroDeFormularioDTO> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        for(FieldError f : fieldErrors){
            String mensagem = messageSource.getMessage(f, LocaleContextHolder.getLocale()); // Traduzindo mensagem para idioma do cabeçalho da requisição
            ErroDeFormularioDTO erro = new ErroDeFormularioDTO(f.getField(), mensagem);
            dto.add(erro);
        }

        return dto;
    }
}
