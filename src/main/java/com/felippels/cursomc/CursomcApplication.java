package 
com.felippels.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.felippels.cursomc.domain.Categoria;
import com.felippels.cursomc.domain.Produto;
import com.felippels.cursomc.repositories.CategoriaRepository;
import com.felippels.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepositorio; 
	@Autowired
	private ProdutoRepository ProdutoRepositorio;
	
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
		ProdutoRepositorio.save(Arrays.asList(prod1,prod2,prod3,prod4));

		
		
		
		
		
	}
	
	
}

