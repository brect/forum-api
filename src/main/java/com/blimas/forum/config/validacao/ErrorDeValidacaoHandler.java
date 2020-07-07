package com.blimas.forum.config.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormularioDTO> handler(MethodArgumentNotValidException methodArgumentNotValidException){
        List<ErroDeFormularioDTO> erroDeFormularioDTO = new ArrayList<>();

        List<FieldError> fildErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();

        fildErrors.forEach(error -> {
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            ErroDeFormularioDTO erroForm = new ErroDeFormularioDTO(error.getField(), mensagem);
            erroDeFormularioDTO.add(erroForm);
        });

        return erroDeFormularioDTO;
    }
}
