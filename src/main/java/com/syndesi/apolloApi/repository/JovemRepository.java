package com.syndesi.apolloApi.repository;

import com.syndesi.apolloApi.model.Jovem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JovemRepository extends JpaRepository<Jovem, Long>{
    
}