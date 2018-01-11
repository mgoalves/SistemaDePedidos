package com.alves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.model.Estado;

/**
 * 
 * @author alves
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{
	
}
