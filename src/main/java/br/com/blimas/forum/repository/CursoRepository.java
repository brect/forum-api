package br.com.blimas.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blimas.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}
