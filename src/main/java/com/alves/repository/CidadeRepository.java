package com.alves.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alves.model.Cidade;
import com.alves.model.Estado;

/**
 * 
 * @author alves
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	@Transactional(readOnly = true)
	List<Cidade> findByEstadoEqualsOrderByNome(@Param("estado") Estado estado);
}
