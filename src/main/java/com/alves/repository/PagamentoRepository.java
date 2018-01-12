package com.alves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.model.Pagamento;

/**
 * 
 * @author alves
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{
	
}
