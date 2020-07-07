package com.blimas.forum.controller.dto;

import com.blimas.forum.model.Resposta;
import com.blimas.forum.model.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

public class RespostaDTO {

	private Long id;
	private String mensagem;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	private Usuario autor;

	public RespostaDTO() {
	}

	public RespostaDTO(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.autor = resposta.getAutor();
	}

	public Long getId() {
		return id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public Usuario getAutor() {
		return autor;
	}
}
