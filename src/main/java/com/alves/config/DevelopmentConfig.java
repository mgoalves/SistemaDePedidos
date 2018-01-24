package com.alves.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
import com.alves.model.enums.Perfil;
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

@Configuration
@Profile("development")
public class DevelopmentConfig {

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
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean initalizeDatabase() throws ParseException {

		
		if(!"create".equals(strategy)) {
			return false;
		}
		
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
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "Televisão", 1200.00);
		Produto p8 = new Produto(null, "Mangueira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);
		p4.getCategorias().add(cat2);
		p5.getCategorias().add(cat3);
		p6.getCategorias().add(cat3);
		p7.getCategorias().add(cat4);
		p8.getCategorias().add(cat5);
		p9.getCategorias().add(cat6);
		p10.getCategorias().add(cat6);
		p11.getCategorias().add(cat7);

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Campinas", est2);
		Cidade cid2 = new Cidade(null, "Belo Horizonte", est1);
		Cidade cid3 = new Cidade(null, "Santos", est2);

		Cliente cli1 = new Cliente(null, "Maria", "marcello_doalves@hotmail.com", "1112223334445", TipoCliente.PESSOAFISICA, passwordEncoder.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("99998888", "98989898"));
		Cliente cli2 = new Cliente(null, "João", "joao@hotmail.com", "12345678901", TipoCliente.PESSOAFISICA, passwordEncoder.encode("123"));
		cli2.addPerfil(Perfil.ADMIN);
		Cliente cli3 = new Cliente(null, "José", "jose@hotmail.com", "12345678902", TipoCliente.PESSOAFISICA, passwordEncoder.encode("123"));
		Cliente cli4 = new Cliente(null, "Pedro", "pedro@hotmail.com", "12345678903", TipoCliente.PESSOAFISICA, passwordEncoder.encode("123"));
		Cliente cli5 = new Cliente(null, "Gabriel", "gabriel@hotmail.com", "12345678904", TipoCliente.PESSOAFISICA, passwordEncoder.encode("123"));

		Endereco end1 = new Endereco(null, "Rua 40", "20", null, "Vila Romana", "12345126", cli1, cid1);
		Endereco end2 = new Endereco(null, "Rua Matos", "105", "Apto 400", "Centro", "12345556", cli1, cid3);
		Endereco end3 = new Endereco(null, "Rua Matos", "105", "Apto 400", "Centro", "12345556", cli2, cid3);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("01/01/2018 00:00"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("01/02/2018 00:00"), cli1, end2);

		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("01/02/2018 10:08"),
				null);
		ped2.setPagamento(pag2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(cid1, cid2, cid3));
		clienteRepository.save(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
		enderecoRepository.save(Arrays.asList(end1, end2, end3));
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pag1, pag2));

		ItemPedido it1 = new ItemPedido(p1, ped1, 0.00, 1, 2000.00);
		ItemPedido it2 = new ItemPedido(p3, ped1, 0.00, 2, 80.00);
		ItemPedido it3 = new ItemPedido(p2, ped2, 100.00, 1, 800.00);

		itemPedidoRepository.save(Arrays.asList(it1, it2, it3));

		return true;
	}

}
