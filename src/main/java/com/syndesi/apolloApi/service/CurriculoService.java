package com.syndesi.apolloApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syndesi.apolloApi.model.Curriculo;
import com.syndesi.apolloApi.model.Jovem;
import com.syndesi.apolloApi.repository.CurriculoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class CurriculoService {
    @Autowired
    private CurriculoRepository curriculoRepository;

    private JovemService jovemService;

    public List<Curriculo> listarCurriculosDoJovem(Long id){
        Jovem jovem = jovemService.buscarPorId(id);
        return curriculoRepository.findByJovem(jovem);
    }

    public Curriculo criar(@Valid Curriculo curriculo) {
        return curriculoRepository.save(curriculo);
    }

    public Curriculo atualizar(Long id, Curriculo curriculoAtualizado) {
        Curriculo curriculoExistente = curriculoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Currículo não encontrado"));
        
        curriculoExistente.setNivelEscolaridade(curriculoAtualizado.getNivelEscolaridade());
        curriculoExistente.setHabilidadesProfissionais(curriculoAtualizado.getHabilidadesProfissionais());
        curriculoExistente.setHabilidadesInterpessoais(curriculoAtualizado.getHabilidadesInterpessoais());
        curriculoExistente.setInformacoesAdicionais(curriculoAtualizado.getInformacoesAdicionais());
        curriculoExistente.setCertificados(curriculoAtualizado.getCertificados());
        return curriculoExistente;
    }

    public void deletar(Long id){
        curriculoRepository.deleteById(id);
    }
}