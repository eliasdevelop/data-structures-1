package lista09;


public class Pedido {
	
	private int numPedido;
	private Produto produto;
	private String dataPedido;
	private int qntPedida;
	
	public Pedido(int numPedido, Produto produto, String dataPedido, int qntPedida) {
		super();
		this.numPedido = numPedido;
		this.produto = produto;
		this.dataPedido = dataPedido;
		this.qntPedida = qntPedida;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQntPedida() {
		return qntPedida;
	}

	public void setQntPedida(int qntPedida) {
		this.qntPedida = qntPedida;
	}

	public int getNumPedido() {
		return numPedido;
	}

	public String getDataPedido() {
		return dataPedido;
	}
	
	
}
