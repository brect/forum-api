package com.blimas.forum.controller;


import com.blimas.forum.controller.dto.TopicoDTO;
import com.blimas.forum.controller.form.TopicoForm;
import com.blimas.forum.model.Topico;
import com.blimas.forum.repository.CursoRepository;
import com.blimas.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping
    public List<TopicoDTO> lista(){
        List<Topico> topicos = topicoRepository.findAll();
        return TopicoDTO.getListTopicos(topicos);
    }

//    @RequestMapping("/topicos")
//    public List<TopicoDto> lista(String nomeCurso) {
//            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
//            return TopicoDto.converter(topicos);
//
//    }

    @PostMapping
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoForm.getTopico(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }


}
