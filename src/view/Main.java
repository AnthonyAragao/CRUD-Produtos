package view;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import conection.ConnectionFactory;
import dao.ProdutoDAO;
import model.bean.Produto;



public class Main {
	
	public static void main(String[] args) {
		Scanner teclado;
		String descricao;
		int opc, qtd, id;
		double preco;
		Produto produto;
		ConnectionFactory conn = new ConnectionFactory();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtos;
		
		
		teclado = new Scanner(System.in);
		teclado.useLocale(Locale.ENGLISH);
		do {
			System.out.println("\n\nBem vindo ao Mercadinho do Irineu");
			System.out.println("Escolha uma opcao");
			System.out.println("1 - cadastra um produto");
			System.out.println("2 - Consulta");
			System.out.println("3 - Consulta total");
			System.out.println("4 - Atualizar um produto");
			System.out.println("5 - Deletar um produto");
			System.out.println("6 - Pesquisar se existe um produto");
			System.out.println("0 - Sair");
			opc = teclado.nextInt();
			
			teclado.nextLine(); //Limpando o buffer
			switch(opc) {
				case 1:
					System.out.println("\nInsira o nome do produto ");
					descricao = teclado.nextLine();
					System.out.println("\nInsira a quantidade: ");
					qtd = teclado.nextInt();
					System.out.println("Insira o preço: ");
					preco = teclado.nextDouble();
					produto = new Produto();
					produto.addConstrutor(descricao, qtd, preco);
					produtoDAO.create(produto);
					break;
					
				case 2:
					System.out.println("\n\nSelecione o numero do produto que voce busca: ");
					produtos = produtoDAO.read();
					for(Produto produtoLista : produtos) {
						System.out.println("\n" + produtoLista.getId() + " - " + produtoLista.getDescricao());
					}
					
					id = teclado.nextInt();
					
					System.out.println(produtoDAO.consulta(id));
					break;
					
				case 3:
					System.out.println("\n**********Lista de produtos*********");
					produtos = produtoDAO.read();
					for(Produto produtoLista : produtos) {
						System.out.println(produtoLista);
					}	
					break;
					
				
				case 4:
					System.out.println("\n\nSelecione o produto que voce deseja atualizar: ");
					produtos = produtoDAO.read();
					for(Produto produtoLista : produtos) {
						System.out.println(produtoLista.getId() + " - " + produtoLista.getDescricao());
					}
					id = teclado.nextInt();
					System.out.println("Nome atualizado: ");
					teclado.nextLine();//Limper o buffer pois o scanner estava bugado....
					descricao = teclado.nextLine();
					System.out.println("Quantidade de produtos: ");
					qtd = teclado.nextInt();
					System.out.println("Preco atualiado: ");
					preco = teclado.nextDouble();
					
					produto = new Produto();
					produto.addConstrutor(descricao, qtd, preco);
					produto.setId(id);
					produtoDAO.update(produto);
					break;
				
				
				case 5:
					System.out.println("\nSelecione o id que voce deseja excluir: ");
					produtos = produtoDAO.read();
					for(Produto produtoLista : produtos) {
						System.out.println(produtoLista.getId() + " - " + produtoLista.getDescricao());
					}
					
					id = teclado.nextInt();
					produtoDAO.delete(id);
					break;
					
					
				case 6:
					System.out.println("Selecione o nome do produto: ");
					descricao = teclado.nextLine();
					produtos = produtoDAO.pesquisar(descricao);
					for(Produto produtoLista : produtos) {
						System.out.println(produtoLista);
					}
					break;
					
					
					
				case 0:
					System.out.println("tchau coracaum <3");
					break;
			
				default:
					System.out.println("Opcao invalida bb");
					break;
					
			}		
		}while(opc != 0);
		
		
	}
}
