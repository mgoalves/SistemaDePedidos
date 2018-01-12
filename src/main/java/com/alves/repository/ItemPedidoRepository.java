package com.alves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.model.ItemPedido;

/**
 * 
 * @author alves
 */
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{
	
}
