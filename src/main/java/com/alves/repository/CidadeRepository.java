package com.alves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.model.Cidade;

/**
 * 
 * @author alves
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
}
