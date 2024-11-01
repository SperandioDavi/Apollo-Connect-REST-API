package com.syndesi.apolloApi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.syndesi.apolloApi.model.Curriculo;
import com.syndesi.apolloApi.model.Jovem;
import com.syndesi.apolloApi.service.CurriculoService;
import com.syndesi.apolloApi.service.JovemService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;



@RestController
@RequestMapping("/curriculos")
public class CurriculoController {
    @Autowired
    private CurriculoService curriculoService;

    @Autowired
    private JovemService jovemService;

    @GetMapping
    public List<Curriculo> listarTodos(){
        return curriculoService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public Curriculo buscarPorId(@PathVariable Long id){
        return curriculoService.buscarPorId(id);
    }

    @GetMapping("/jovens/{idJovem}")
    public List<Curriculo> listarCurriculosDoJovem(@PathVariable("idJovem") Long idJovem) {
        return curriculoService.listarCurriculosDoJovem(idJovem);
    }
    
    @PostMapping("/{idJovem}")
    public Curriculo criar(@Valid @RequestBody Curriculo curriculo, @PathVariable("idJovem") Long idJovem) {
        Jovem jovem = jovemService.buscarPorId(idJovem);
        curriculo.setJovem(jovem);
        return curriculoService.criar(curriculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCurriculo(@PathVariable Long id, @RequestBody Curriculo curriculoAtualizado) {
        try{
            Curriculo curriculo = curriculoService.atualizar(id, curriculoAtualizado);
            return ResponseEntity.ok(curriculo);
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Currículo não encontrado");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o currículo");
        }
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        curriculoService.deletar(id);
    }
}