package com.alves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.model.Produto;

/**
 * 
 * @author alves
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
}
