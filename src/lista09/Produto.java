package lista09;

public class Produto implements Comparable<Produto> {
	
	private int codProduto;
	private String nome;
	private int Estoque;
	private double preco;
	
	public Produto(int codProduto){
		super();
		this.codProduto = codProduto;
	}
	
	public Produto(int codProduto, String nome, int qntEstoque, double preco) {
		super();
		this.codProduto = codProduto;
		this.nome = nome;
		this.Estoque = qntEstoque;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQntEstoque() {
		return Estoque;
	}

	public void setQntEstoque(int qntEstoque) {
		this.Estoque = qntEstoque;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getCodProduto() {
		return codProduto;
	}

	@Override
	public int compareTo(Produto o) {
		if (codProduto < o.codProduto)
			return -1;
		if (codProduto == o.codProduto)
			return 0;
		return 1;
	}

	
}
