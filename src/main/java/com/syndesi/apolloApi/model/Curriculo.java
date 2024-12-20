package com.syndesi.apolloApi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Curriculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nivelEscolaridade;
    
    @ElementCollection
    private List<String> habilidadesProfissionais;

    @ElementCollection
    private List<String> habilidadesInterpessoais;
    
    private String informacoesAdicionais;

    @ElementCollection
    private List<String> certificados;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "jovem_id")
    private Jovem jovem; 

    public Curriculo(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNivelEscolaridade() {
        return nivelEscolaridade;
    }

    public void setNivelEscolaridade(String nivelEscolaridade) {
        this.nivelEscolaridade = nivelEscolaridade;
    }

    public List<String> getHabilidadesProfissionais() {
        return habilidadesProfissionais;
    }

    public void setHabilidadesProfissionais(List<String> habilidadesProfissionais) {
        this.habilidadesProfissionais = habilidadesProfissionais;
    }

    public List<String> getHabilidadesInterpessoais() {
        return habilidadesInterpessoais;
    }

    public void setHabilidadesInterpessoais(List<String> habilidadesInterpessoais) {
        this.habilidadesInterpessoais = habilidadesInterpessoais;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public List<String> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<String> certificados) {
        this.certificados = certificados;
    }

    public Jovem getJovem() {
        return jovem;
    }

    public void setJovem(Jovem jovem) {
        this.jovem = jovem;
    }
}