package com.blimas.forum.controller.dto;

import com.blimas.forum.model.Curso;
import com.blimas.forum.model.StatusTopico;
import com.blimas.forum.model.Topico;
import com.blimas.forum.model.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesDoTopicoDTO {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private StatusTopico status;
	private Usuario autor;
	private Curso curso;
	private List<RespostaDTO> respostas;

	public DetalhesDoTopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();

		this.status = topico.getStatus();
		this.autor = topico.getAutor();
		this.respostas = new ArrayList<>();
		this.respostas = topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public Usuario getAutor() {
		return autor;
	}

	public Curso getCurso() {
		return curso;
	}

	public List<RespostaDTO> getRespostas() {
		return respostas;
	}
}
