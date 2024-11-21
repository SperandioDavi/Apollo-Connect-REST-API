package com.syndesi.apolloApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syndesi.apolloApi.model.Jovem;
import com.syndesi.apolloApi.service.JovemService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;



@RestController
@CrossOrigin("*")
@RequestMapping("/jovens")
public class JovemController {
    @Autowired
    private JovemService jovemService;

    @GetMapping
    public List<Jovem> listarTodos() {
        return jovemService.listarTodos();
    }

    @GetMapping("/{id}")
    public Jovem buscarPorId(@PathVariable Long id) {
        return jovemService.buscarPorId(id);
    }
    
    @PostMapping
    public ResponseEntity<Jovem> criar(@Valid @RequestBody Jovem jovem) {
        Jovem novoJovem = jovemService.criar(jovem);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoJovem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarJovem(@PathVariable Long id, @Valid @RequestBody Jovem jovemAtualizado) {
        try{
            Jovem jovem = jovemService.atualizar(id, jovemAtualizado);
            return ResponseEntity.ok(jovem);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jovem não encontrado");
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar dados do jovem");
        }
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        jovemService.deletar(id);        
    }
}