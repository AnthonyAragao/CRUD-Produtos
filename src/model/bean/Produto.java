package model.bean;

public class Produto {
	//Atributos
	private int id;
	private String descricao;
	private int qtd;
	private double preco;
	
	
	//Add ao contrutor
	public void addConstrutor(String descricao, int qtd, double preco) {
		this.descricao = descricao;
		this.qtd = qtd;
		this.preco = preco;
	}
	
	//Renomeando toString
	@Override
	public String toString() {
		return "\nNome: " + descricao + "\nQuantidade: " + qtd + "\nPreço do produto: " + preco;
	}
	
	
	//Getters e Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
}
