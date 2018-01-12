package com.alves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.model.Endereco;

/**
 * 
 * @author alves
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
}
