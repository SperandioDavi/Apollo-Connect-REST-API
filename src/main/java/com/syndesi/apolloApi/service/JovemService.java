package com.syndesi.apolloApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syndesi.apolloApi.model.Curriculo;
import com.syndesi.apolloApi.model.Jovem;
import com.syndesi.apolloApi.repository.JovemRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class JovemService {
    
    @Autowired
    private JovemRepository jovemRepository;

    public List<Jovem> listarTodos(){
        return jovemRepository.findAll();
    }

    public Jovem buscarPorId(Long id){
        return jovemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Jovem não encontrado"));
    }

    public Jovem criar(Jovem jovem){
        List<Curriculo> curriculos = jovem.getCurriculos();
        for(Curriculo curriculo : curriculos){
            curriculo.setJovem(jovem);
        }
        return jovemRepository.save(jovem);
    }

    public Jovem atualizar(Long id, Jovem jovemAtualizado){
        // Verifica se o jovem existe
        Jovem jovemExistente = jovemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Jovem não encontrado"));

        // Atualiza os campos do jovem existente
        jovemExistente.setNome(jovemAtualizado.getNome());
        jovemExistente.setEmail(jovemAtualizado.getEmail());
        jovemExistente.setSenha(jovemAtualizado.getSenha());
        jovemExistente.setCpf(jovemAtualizado.getCpf());
        jovemExistente.setEndereco(jovemAtualizado.getEndereco());
        jovemExistente.setTelefone(jovemAtualizado.getTelefone());
        jovemExistente.setDataNascimento(jovemAtualizado.getDataNascimento());
        
        jovemExistente.getCurriculos().clear();
        jovemExistente.getCurriculos().addAll(jovemAtualizado.getCurriculos());
        
        return jovemRepository.save(jovemExistente);
    }

    public void deletar(Long id){
        if(!jovemRepository.existsById(id)){
            throw new IllegalArgumentException("Jovem não encontrado");
        }
        jovemRepository.deleteById(id);
    }  
}