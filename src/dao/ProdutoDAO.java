package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import conection.ConnectionFactory;
import model.bean.Produto;


public class ProdutoDAO {
	//Atributos para conexao
	private Connection conn;
	private ConnectionFactory conexao;
	

	//Construtor
	public ProdutoDAO() {
		conexao = new ConnectionFactory();
	}
	

	//Criar um produto
	public boolean create(Produto produto) {
		conn = conexao.getConnection(); //Abrir a conexao
		PreparedStatement stmt = null;
		
		String sql = "INSERT INTO produto(descricao, qtd, preco) VALUES(?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, produto.getDescricao());
			stmt.setInt(2, produto.getQtd());
			stmt.setDouble(3, produto.getPreco());
			stmt.executeUpdate();
			System.out.println("Produto adicionado com sucesso!!!");
			return true; 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
			
		}finally {
			ConnectionFactory.closeConnection(conn, stmt); //Fechar conexao
		}
	}
	
	
	//Consulta por id
	public Produto consulta(int id) {
		conn = conexao.getConnection();
		PreparedStatement stmt = null;;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM produto WHERE ID =?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			rs.first();//Posicionar na primeira posicao
			
			Produto produto = new Produto();
			produto.setId(rs.getInt("id"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setPreco(rs.getDouble("preco"));
			produto.setQtd(rs.getInt("qtd"));
			return produto;
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		finally {	
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}
	}
	

	
	
	//Consulta total
	public List<Produto> read(){
		conn = conexao.getConnection();
		PreparedStatement stmt = null;;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM produto";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Produto> produtos = new ArrayList<>();
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setQtd(rs.getInt("qtd"));
				produtos.add(produto);
			}
			return produtos;
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
			
		}finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}
	}
	
	//Atualizar produto
	public void update(Produto produto) {
		conn = conexao.getConnection();
		PreparedStatement stmt = null;
		String sql = "UPDATE produto set descricao =?, qtd =?, preco=? WHERE id=?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, produto.getDescricao());
			stmt.setInt(2, produto.getQtd());
			stmt.setDouble(3, produto.getPreco());
			stmt.setInt(4, produto.getId());
			stmt.executeUpdate();
			System.out.println("Produto atualizado com Sucesso!!!");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			ConnectionFactory.closeConnection(conn, stmt);
		}
	}
	
	
	//Deletar um produto
	public void delete(int id) {
		conn = conexao.getConnection();
		PreparedStatement stmt = null;
		String sql = "DELETE FROM produto WHERE id =?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			System.out.println("Produto Excluido com sucesso!");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionFactory.closeConnection(conn, stmt);	
		}
	}
	
	//Returna uma lista de produtos com nomes aproximado a pesquisa
	public List<Produto> pesquisar(String nomeProduto){
		conn = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM produto WHERE descricao LIKE ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + nomeProduto + "%");
			rs = stmt.executeQuery();
			
			List<Produto> produtos = new ArrayList<>();
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setQtd(rs.getInt("qtd"));
				produtos.add(produto);
			}
			return produtos;
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}
	}
	

}
