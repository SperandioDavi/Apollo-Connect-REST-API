package com.syndesi.apolloApi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.syndesi.apolloApi.model.Curriculo;
import com.syndesi.apolloApi.service.CurriculoService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/curriculos")
public class CurriculoController {
    @Autowired
    private CurriculoService curriculoService;

    @GetMapping("/{idJovem}")
    public List<Curriculo> listarCurriculosDoJovem(@PathVariable Long idJovem) {
        return curriculoService.listarCurriculosDoJovem(idJovem);
    }
    
    @PostMapping
    public Curriculo criar(@Valid @RequestBody Curriculo curriculo) {
        Curriculo novoCurriculo = curriculoService.criar(curriculo);
        return novoCurriculo;
    }

    // ESTAVA REVISANDO ESSE MÉTODO DAQUI
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCurriculo(@PathVariable Long id, @RequestBody Curriculo curriculoAtualizado) {
        try{
            Curriculo curriculo = curriculoService.atualizar(id, curriculoAtualizado);
            return ResponseEntity.ok(curriculo);
        }
        catch()
        
        return entity;
    }
    
}