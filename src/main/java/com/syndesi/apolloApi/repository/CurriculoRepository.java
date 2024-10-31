package com.syndesi.apolloApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syndesi.apolloApi.model.Curriculo;
import com.syndesi.apolloApi.model.Jovem;

import java.util.List;


public interface CurriculoRepository extends JpaRepository<Curriculo, Long>{
    List<Curriculo> findByJovem(Jovem jovem);
}