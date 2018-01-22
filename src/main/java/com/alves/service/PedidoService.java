package com.alves.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alves.exception.ObjectNotFoundException;
import com.alves.model.ItemPedido;
import com.alves.model.PagamentoComBoleto;
import com.alves.model.Pedido;
import com.alves.model.enums.EstadoPagamento;
import com.alves.repository.ItemPedidoRepository;
import com.alves.repository.PagamentoRepository;
import com.alves.repository.PedidoRepository;
import com.alves.repository.ProdutoRepository;

@Service
public class PedidoService {

	// Injections
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired 
	private BoletoService boletoService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EmailService emailService;

	// Buscar por ID -----------------------------------------------
	public Pedido findById(Long id) {

		Pedido pedido = pedidoRepository.findOne(id);

		if (pedido == null) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID: " + id + ", Tipo: " + Pedido.class.getName());
		}

		return pedido;
	}

	// Salvar um pedido -------------------------------------------------------
	public Pedido save(Pedido pedido) {

		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);

		if (pedido.getPagamento() instanceof PagamentoComBoleto) {

			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			
			boletoService.preencherBoleto(pagto, pedido.getInstante());
		}

		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		
		for (ItemPedido item : pedido.getItens()) {
			
			item.setDesconto(0.00);
			item.setProduto(produtoRepository.findOne(item.getProduto().getId()));
			item.setPreco(item.getProduto().getPreco());
			item.setPedido(pedido);
		}
		
		
		pedido.setCliente(clienteService.findById(pedido.getCliente().getId()));
		itemPedidoRepository.save(pedido.getItens());
		
		emailService.send(pedido);

		return pedido;
	}
}
