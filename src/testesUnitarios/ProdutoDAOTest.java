package testesUnitarios;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import dao.ProdutoDAO;
import model.bean.Produto;


public class ProdutoDAOTest {
	@Test
	@Ignore
	public void inserir() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = new Produto();
		produto.addConstrutor("Chocolate", 10, 5.5);
		
		if(produtoDAO.create(produto)) {
			System.out.println("Salvo com sucesso! ");	
		}else {
			fail("Erro ao salvar");
		}
	}
	
	@Test
	@Ignore
	public void consulta() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto;
		produto = produtoDAO.consulta(1);
		System.out.println(produto); //Mesmo resultado do banco de dados
	}
	
	@Test
	@Ignore
	public void read() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		for(Produto p : produtoDAO.read()) {
			System.out.println(p);
		}
	}
	
	
	@Test
	public void pesquisar(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> lista = produtoDAO.pesquisar("f");
		for(Produto produto : lista) {
			System.out.println(produto);
		}
	}
	

	
	
	
	
	
}
