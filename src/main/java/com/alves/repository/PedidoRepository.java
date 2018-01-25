package com.alves.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alves.model.Cliente;
import com.alves.model.Pedido;

/**
 * 
 * @author alves
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	@Transactional(readOnly = true)
	Page<Pedido> findByCliente(Cliente cliente, Pageable page);
	
}
