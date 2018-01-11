package com.alves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.model.Categoria;

/**
 * 
 * @author alves
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
}
