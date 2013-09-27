package lista09;

import utilitarios.Keyboard;
import utilitarios.MyIterator;
import fila.FilaEnc;
import listasLinearesEncadeadas.ListaEncOrd;


public class Principal {

	static ListaEncOrd<Produto> listaProdutos = new ListaEncOrd<Produto>();
	static FilaEnc<Pedido> filaPedidos = new FilaEnc<Pedido>();
	static int numPedido = 1;
	
	public static void main(String[] args) {
		int opcao;
		do{
			opcao = Keyboard.menu("Cadastrar Produto/Listar Produtos/Aquisicao de Produtos/Registrar Pedido/" +
					"Processar Pedido/Listar Fila de Pedidos/Terminar");
			switch (opcao){
			case 1:
				cadastrarProduto();
				break;
			case 2:
				listarProdutos();
				break;
			case 3:
				aquisicaoProduto();
				break;
			case 4:
				registrarPedido();
				break;
			case 5:
				processarPedido();
				break;
			case 6:
				listarFilaPedidos();
				break;
			}
			
		} while (opcao < 7);
	}

	private static void cadastrarProduto() {
		char resp;
		do {
			int codProduto = Keyboard.readInt("Entrar com o código do Produto: ");
			Produto produto = new Produto(codProduto);
			produto = listaProdutos.retrieve(produto);
			if(produto != null){
				System.out.println("Código existente.");
			} else {
				String nome = Keyboard.readString("Entre com o nome do Produto: ");
				int estoque = Keyboard.readInt("Entre com a quantidade em estoque: ");
				double preco = Keyboard.readDouble("Entre com o preço: ");
				produto = new Produto(codProduto,nome,estoque,preco);
				listaProdutos.add(produto);
				System.out.println("Produto cadastrado com sucesso!");
			}
			resp = Keyboard.readChar("Novo Produto?");
		} while (resp == 's');
		Keyboard.clrscr();
	}

	private static void listarProdutos() {
		Keyboard.clrscr();
		System.out.println("CodProduto  Nome do Produto            Estoque     Preço");
		System.out.println("----------  -------------------------  ----------  ----------");
		MyIterator<Produto> it = listaProdutos.iterator();
		Produto produto = it.getFirst();
		while (produto != null) {
			System.out.printf("%10d  %-25s  %10d  %10.2f\n", produto.getCodProduto(),
					produto.getNome(),produto.getQntEstoque(), produto.getPreco());
			produto = it.getNext();
		}
		Keyboard.waitEnter();
		
	}

	private static void aquisicaoProduto() {
		char resp;
		do {
			listarProdutos();
			int codProduto = Keyboard.readInt("Código do Produto: ");
			Produto produto = listaProdutos.retrieve(new Produto(codProduto));
			if (produto != null){
				int qntPedida = Keyboard.readInt("Quantidade: ");
				while(qntPedida > produto.getQntEstoque()){
					System.out.println("Não há quantidade suficiente em estoque.");
					qntPedida = Keyboard.readInt("Quantidade: ");
				}
				double preco = Keyboard.readDouble("Entre com o novo preço: ");
				produto.setPreco(preco);
				resp = Keyboard.readChar("Produto: " + produto.getNome() + " Valor total: " + (produto.getPreco()*qntPedida) + "\nComprar?");
				if (resp == 's'){
					produto.setQntEstoque((produto.getQntEstoque()-qntPedida));
					if(produto.getQntEstoque() == 0)
						listaProdutos.remove(produto);
					System.out.println("Aquisição realizada com sucesso!");
				}
			}
			resp = Keyboard.readChar("Nova Aquisição?");
		} while (resp == 's');
		
	}

	private static void registrarPedido() {
		char resp;
		do {
			listarProdutos();
			int codProduto = Keyboard.readInt("Código do Produto: ");
			Produto produto = listaProdutos.retrieve(new Produto(codProduto));
			String data = Keyboard.readData("Data do Pedido: ");
			int qntPedida = Keyboard.readInt("Quantidade: ");
			Pedido pedido = new Pedido(numPedido++,produto,data,qntPedida);
			filaPedidos.insira(pedido);
			System.out.println("Pedido registrado com sucesso!");
			resp = Keyboard.readChar("Novo Pedido?");
		} while (resp == 's');
	}

	private static void processarPedido() {
		char resp = 's';
		do {
			Pedido pedido = filaPedidos.remova();
			if(pedido.getQntPedida() > pedido.getProduto().getQntEstoque()){
				System.out.println("Não há quantidade suficiente em estoque.");
				filaPedidos.insira(pedido);
				Keyboard.waitEnter();
			} else {
				System.out.println("\nPedido Processado");
				System.out.println("Numero do pedido: " + pedido.getNumPedido());
				System.out.println("Cod Prod: " + pedido.getProduto().getCodProduto());
				System.out.println("Nome do Produto: " + pedido.getProduto().getNome());
				System.out.println("Quantidade pedida: " + pedido.getQntPedida());
				System.out.println("Valor total da compra: " + (pedido.getQntPedida()*pedido.getProduto().getPreco()));
			}
			resp = Keyboard.readChar("Processar outro Pedido?");
		}while (resp == 's');
	}

	private static void listarFilaPedidos() {
		Keyboard.clrscr();
		System.out.println("NumPedido   Nome do Produto            Data Pedido   Quantidade    Preço total");
		System.out.println("----------  -------------------------  ------------  ------------  ------------");
		MyIterator<Pedido> it = filaPedidos.iterator();
		Pedido pedido = it.getFirst();
		while (pedido != null) {
			System.out.printf("%10d  %-25s  %12s  %12d  %12.2f\n", pedido.getNumPedido(), pedido.getProduto().getNome(),
					pedido.getDataPedido(),pedido.getQntPedida(),(pedido.getQntPedida()*pedido.getProduto().getPreco()));
			pedido = it.getNext();
		}
		Keyboard.waitEnter();
	}

}
