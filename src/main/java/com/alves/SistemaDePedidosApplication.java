package com.alves;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alves.model.Categoria;
import com.alves.model.Cidade;
import com.alves.model.Cliente;
import com.alves.model.Endereco;
import com.alves.model.Estado;
import com.alves.model.ItemPedido;
import com.alves.model.Pagamento;
import com.alves.model.PagamentoComBoleto;
import com.alves.model.PagamentoComCartao;
import com.alves.model.Pedido;
import com.alves.model.Produto;
import com.alves.model.enums.EstadoPagamento;
import com.alves.model.enums.TipoCliente;
import com.alves.repository.CategoriaRepository;
import com.alves.repository.CidadeRepository;
import com.alves.repository.ClienteRepository;
import com.alves.repository.EnderecoRepository;
import com.alves.repository.EstadoRepository;
import com.alves.repository.ItemPedidoRepository;
import com.alves.repository.PagamentoRepository;
import com.alves.repository.PedidoRepository;
import com.alves.repository.ProdutoRepository;

@SpringBootApplication
public class SistemaDePedidosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDePedidosApplication.class, args);
	}

	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired 
	private ItemPedidoRepository itemPedidoRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
		
			Categoria cat1 = new Categoria(null, "Info");
			Categoria cat2 = new Categoria(null, "EScritório");
			Categoria cat3 = new Categoria(null, "Cama mesa e banho");
	 		Categoria cat4 = new Categoria(null, "Eletrônicos");
			Categoria cat5 = new Categoria(null, "Jardinagem");
			Categoria cat6 = new Categoria(null, "Decoração");
			Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 40.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Campinas", est2);		
		Cidade cid2 = new Cidade(null, "Belo Horizonte", est1);		
		Cidade cid3 = new Cidade(null, "Santos", est2);
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "1112223334445", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("99998888", "98989898"));
		Cliente cli2 = new Cliente(null, "João", "joao@gmail.com", "12345678901", TipoCliente.PESSOAFISICA);
		Cliente cli3 = new Cliente(null, "José", "jose@gmail.com", "12345678902", TipoCliente.PESSOAFISICA);
		Cliente cli4 = new Cliente(null, "Pedro", "pedro@gmail.com", "12345678903", TipoCliente.PESSOAFISICA);
		Cliente cli5 = new Cliente(null, "Gabriel", "gabriel@gmail.com", "12345678904", TipoCliente.PESSOAFISICA);
		
		Endereco end1 = new Endereco(null, "Rua 21", "342", null, "Vila Jaragua", "74655090", cli1, cid1); 
		Endereco end2 = new Endereco(null, "Rua Matos", "105", "Apto 400", "Centro", "30270290", cli1, cid3);
		Endereco end3 = new Endereco(null, "Rua Matos", "105", "Apto 400", "Centro", "30270290", cli2, cid3);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("01/01/2018 00:00"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("01/02/2018 00:00"), cli1, end2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("01/02/2018 10:08"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(cid1, cid2, cid3));
		clienteRepository.save(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
		enderecoRepository.save(Arrays.asList(end1, end2, end3));
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pag1, pag2));
		
		ItemPedido it1 = new ItemPedido(p1, ped1, null, 1, 2000.00);
		ItemPedido it2 = new ItemPedido(p3, ped1, null, 2, 80.00);
		ItemPedido it3 = new ItemPedido(p2, ped2, 100.00, 1, 800.00);
		
		itemPedidoRepository.save(Arrays.asList(it1, it2, it3));
		
	}
}
 