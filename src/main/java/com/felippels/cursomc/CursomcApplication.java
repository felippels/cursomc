package 
com.felippels.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.felippels.cursomc.domain.Categoria;
import com.felippels.cursomc.domain.Cidade;
import com.felippels.cursomc.domain.Cliente;
import com.felippels.cursomc.domain.Endereco;
import com.felippels.cursomc.domain.Estado;
import com.felippels.cursomc.domain.Produto;
import com.felippels.cursomc.domain.enums.TipoPessoa;
import com.felippels.cursomc.repositories.CategoriaRepository;
import com.felippels.cursomc.repositories.CidadeRepository;
import com.felippels.cursomc.repositories.ClienteRepository;
import com.felippels.cursomc.repositories.EnderecoRepository;
import com.felippels.cursomc.repositories.EstadoRepository;
import com.felippels.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepositorio; 
	@Autowired
	private ProdutoRepository produtoRepositorio;
	@Autowired
	private EstadoRepository estadoRepositorio; 
	@Autowired
	private CidadeRepository cidadeRepositorio;
	@Autowired
	private ClienteRepository clienteRepositorio;
	@Autowired
	private EnderecoRepository enderecoRepositorio;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "almoxarife");
		Categoria cat4 = new Categoria(null, "Contabilidade");
		
		Produto prod1 = new Produto(null, "Computador", 800.00);
		Produto prod2 = new Produto(null, "Mouse", 20.00);
		Produto prod3 = new Produto(null, "Canetar", 1.20);
		Produto prod4 = new Produto(null, "Café", 4.50);
		Produto prod5 = new Produto(null, "livro", 45.50);
		
		cat1.getProdutosLista().addAll(Arrays.asList(prod1,prod2));
		cat2.getProdutosLista().addAll(Arrays.asList(prod3));
		cat3.getProdutosLista().addAll(Arrays.asList(prod4));
		cat4.getProdutosLista().addAll(Arrays.asList(prod5));
		
		prod1.getCategoriasLista().addAll(Arrays.asList(cat1));
		prod2.getCategoriasLista().addAll(Arrays.asList(cat1));
		prod3.getCategoriasLista().addAll(Arrays.asList(cat2));
		prod4.getCategoriasLista().addAll(Arrays.asList(cat3));
		prod5.getCategoriasLista().addAll(Arrays.asList(cat4));
		
		categoriaRepositorio.save(Arrays.asList(cat1,cat2,cat3,cat4));
		produtoRepositorio.save(Arrays.asList(prod1,prod2,prod3,prod4));

		
		Estado estado1 = new Estado(null, "Ceará");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Fortaleza", estado1);
		Cidade cidade2 = new Cidade(null, "Campinas", estado2);
		estado1.getCidadeLsta().add(cidade1);
		estado2.getCidadeLsta().add(cidade2);
		
		estadoRepositorio.save(Arrays.asList(estado1, estado2));
		cidadeRepositorio.save(Arrays.asList(cidade1, cidade2));
		
		Cliente cliente1 = new Cliente(null, "Ana", "ana@email.com", "123456789", TipoPessoa.PESSOAFISICA);
		Cliente cliente2 = new Cliente(null, "Mirian", "Mirian@email.com", "123456789", TipoPessoa.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList ("12345678","87654321"));
		cliente2.getTelefones().addAll(Arrays.asList ("12345678","87654321"));
		
		Endereco endereco1 = new Endereco(null, "Assunção", "1461","ap 402", "Fátima","600000",cliente1,cidade1);
		Endereco endereco2 = new Endereco(null, "Acioli", "195","altos", "PI","600000",cliente2,cidade1);
		cliente1.getEnderecoLista().add(endereco1);
		cliente2.getEnderecoLista().add(endereco2);
		
		clienteRepositorio.save(Arrays.asList(cliente1, cliente2));
		enderecoRepositorio.save(Arrays.asList(endereco1, endereco2));

		
		
	}
	
	
}

