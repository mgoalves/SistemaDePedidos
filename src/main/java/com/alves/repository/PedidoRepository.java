package com.alves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.model.Pedido;

/**
 * 
 * @author alves
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
}
