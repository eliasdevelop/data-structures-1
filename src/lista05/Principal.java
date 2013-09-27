package lista05;

import java.util.Comparator;

import utilitarios.*;

public class Principal {

	static Vetor<Conta> contas = new Vetor<Conta>(20);

	static ObjectFile arqContas = new ObjectFile("contas3.dat");

	static class CompareNomes implements Comparator<Conta> {

		@Override
		public int compare(Conta o1, Conta o2) {
			return o1.getNomeCliente().compareToIgnoreCase(o2.getNomeCliente());
		}

	}

	static class CompareSaldos implements Comparator<Conta> {
		@Override
		public int compare(Conta o1, Conta o2) {
			if (o1.getSaldoConta() < o2.getSaldoConta())
				return 1;
			if (o1.getSaldoConta() == o2.getSaldoConta())
				return 0;
			return -1;
		}
	}

	public static void main(String[] args) {
		int opcao = 1;
		lerArquivo();
		while (opcao < 8) {
			Keyboard.clrscr();
			opcao = Keyboard
					.menu("Cadastrar Contas/Listar Contas/Efetuar Depósitos/Efetuar Saques/Consultar Conta/Creditar Rendimentos"
							+ "/Remover Conta/Terminar");
			switch (opcao) {
			case 1:
				cadastrarContas();
				break;
			case 2:
				listarContas();
				break;
			case 3:
				efetuarDepositos();
				break;
			case 4:
				efetuarSaques();
				break;
			case 5:
				consultarConta();
				break;
			case 6:
				creditarRendimentos();
				break;
			case 7:
				removerConta();
				break;
			case 8:
				System.out.println("Programa Fechado");
				break;
			}
		}
		gravarArquivo();

	}

	private static void removerConta() {
		Keyboard.clrscr();
		int numeroConta = Keyboard.readInt("Digite o numero da conta:");
		MyIterator<Conta> buscar = contas.iterator();
		Conta buscador = buscar.getFirst();
		if (buscador == null) {
			Keyboard.readString("Conta inexistente!");
		} else {
			while (buscador != null) {
				if (buscador.getNumeroConta() == numeroConta) {
					buscar.remove();
					System.out.println("Conta removida com sucesso!");
				}
				buscador = buscar.getNext();
			}
		}
		Keyboard.waitEnter();
	}

	private static void creditarRendimentos() {
		Keyboard.clrscr();
		double juros = Keyboard.readDouble("Entrar com o juros:");
		MyIterator<Conta> buscar = contas.iterator();
		Conta buscador = buscar.getFirst();
		if (buscador == null) {
			System.out.println("Sem Contas cadastradas!");
		} else {
			while (buscador != null) {
				if (buscador instanceof ContaPoupanca) {
					((ContaPoupanca) buscador).rendimentoConta(juros);
				}
				buscador = buscar.getNext();
			}
			System.out.println("Contas Poupança creditadas com sucesso!");
		}
		Keyboard.waitEnter();
	}

	private static void consultarConta() {
		String tipo;
		Keyboard.clrscr();
		int numeroConta = Keyboard.readInt("Digite o numero da conta:");
		Conta conta = buscarConta(numeroConta);

		if (conta == null) {
			Keyboard.readString("Conta inexistente!");
		} else {
			if (conta instanceof ContaComum) {
				tipo = "Comum";
			} else if (conta instanceof ContaEspecial) {
				tipo = "Especial";
			} else {
				tipo = "Poupança";
			}
			System.out
					.println("Num Conta  Nome do Cliente                       Saldo  Tipo da Conta");
			System.out
					.println("---------  ------------------------------  -----------  -------------");
			System.out.printf("%9d  %-30s  %11.2f  %-13s",
					conta.getNumeroConta(), conta.getNomeCliente(),
					conta.getSaldoConta(), tipo);

		}
		Keyboard.waitEnter();

	}

	private static void efetuarSaques() {
		Keyboard.clrscr();
		int numeroConta = Keyboard.readInt("Entrar com o numero da conta:");
		if (buscarConta(numeroConta) != null) {
			double valorSaque = Keyboard
					.readDouble("Entrar com o valor a ser sacado:");
			if (buscarConta(numeroConta).saque(valorSaque) == true) {
				System.out.println("Valor sacado com sucesso!");
			} else {
				System.out.println("Desculpe, conta não possui credito/limite");
			}

		} else {
			System.out.println("Desculpe, conta inexistente!");
		}

		Keyboard.waitEnter();

	}

	private static void efetuarDepositos() {
		Keyboard.clrscr();
		int numeroConta = Keyboard.readInt("Entrar com o numero da conta:");
		if (buscarConta(numeroConta) != null) {
			double valorDepositado = Keyboard
					.readDouble("Entrar com o valor a ser depositado:");
			buscarConta(numeroConta).deposito(valorDepositado);
			System.out.println("Depositado com sucesso!");
		} else {
			System.out.println("Desculpe, conta inexistente!");
		}

		Keyboard.waitEnter();
	}

	private static void listarContas() {
		String tipo;

		Keyboard.clrscr();
        int opcao = Keyboard
				.menu("Ordem Alfabetica/Ordem decrescente de Saldo");
    	
        switch (opcao) {
		case 1:
			Conta[] contaTemp = new Conta[contas.size()];
			for (int i = 0; i < contaTemp.length; i++) {
				contaTemp[i] = contas.elementAt(i);
			}
			// Sort.selecao(contaTemp, new CompareNomes());
			// Sort.bolha(contaTemp, new CompareNomes());
			// Sort.insercao(contaTemp, new CompareNomes());
			Sort.quickSort(contaTemp, new CompareNomes());
			for (int i = 0; i < contaTemp.length; i++) {
				contas.replace(i, contaTemp[i]);
			}
			break;
		case 2:
			Conta[] contaTempz = new Conta[contas.size()];
			for (int i = 0; i < contaTempz.length; i++) {
				contaTempz[i] = contas.elementAt(i);
			}
			// Sort.selecao(contaTempz, new CompareSaldos());
			// Sort.bolha(contaTempz, new CompareSaldos());
			// Sort.insercao(contaTempz, new CompareSaldos());
			Sort.quickSort(contaTempz,new CompareSaldos());			
			for (int i = 0; i < contaTempz.length; i++) {
				contas.replace(i, contaTempz[i]);
			}
			break;
		}
       
		MyIterator<Conta> buscar = contas.iterator();
		Conta buscador = buscar.getFirst();
		if (buscador == null) {
			System.out.println("Sem Contas cadastradas!");
		} else {
			System.out
					.println("Num Conta  Nome do Cliente                       Saldo  Tipo da Conta");
			System.out
					.println("---------  ------------------------------  -----------  -------------");
			while (buscador != null) {
				if (buscador instanceof ContaComum) {
					tipo = "Comum";
				} else if (buscador instanceof ContaEspecial) {
					tipo = "Especial";
				} else {
					tipo = "Poupança";
				}
				System.out.printf("%9d  %-30s  %11.2f  %-13s\n",
						buscador.getNumeroConta(), buscador.getNomeCliente(),
						buscador.getSaldoConta(), tipo);
				buscador = buscar.getNext();
				
			}
		}
		Keyboard.waitEnter();

	}

	public static Conta buscarConta(int numeroConta) {

		MyIterator<Conta> buscar = contas.iterator();
		CompararContas comparador = new CompararContas();
		Conta buscador = buscar.getFirst();
		while (buscador != null) {
			if (comparador.compare(buscador, new ContaComum(numeroConta)) == 0) {
				return buscador;
			} else {
				buscador = buscar.getNext();
			}
		}
		return null;
	}

	private static void cadastrarContas() {
		char resp;
		do {
			Keyboard.clrscr();
			int numeroConta = Keyboard
					.readInt("Entrar com o Número da Conta: ");
			if (buscarConta(numeroConta) != null) {
				System.out.println("Desculpe, numero de conta ja existente.");
			} else {
				Keyboard.clrscr();
				int tipoConta = Keyboard
						.menu("Conta Comum/Conta Especial/Conta Poupança");
				switch (tipoConta) {
				case 1: {
					contas.insertAtEnd(new ContaComum(numeroConta));
					contas.elementAt(contas.size() - 1)
							.setNomeCliente(
									Keyboard.readString("Entrar com o nome do cliente: "));
					System.out.println("Conta Cadastrada com sucesso!");
					break;
				}
				case 2: {
					double limiteConta = Keyboard
							.readDouble("Entrar com o limite da conta: ");
					contas.insertAtEnd(new ContaEspecial(numeroConta,
							limiteConta));
					contas.elementAt(contas.size() - 1)
							.setNomeCliente(
									Keyboard.readString("Entrar com o nome do cliente: "));
					System.out.println("Conta Cadastrada com sucesso!");
					break;
				}
				case 3: {
					contas.insertAtEnd(new ContaPoupanca(numeroConta));
					contas.elementAt(contas.size() - 1)
							.setNomeCliente(
									Keyboard.readString("Entrar com o nome do cliente: "));
					System.out.println("Conta Cadastrada com sucesso!");
					break;
				}
				default: {
					System.out.println("Opção invalida");
					break;
				}
				}
			}
			resp = Keyboard.readChar("Outra Conta(s/n)?");

		} while (resp == 's');

	}

	static void lerArquivo() {
		if (arqContas.reset()) {
			Conta conta = (Conta) arqContas.read();
			while (conta != null) {
				contas.insertAtEnd(conta);
				conta = (Conta) arqContas.read();
			}
			arqContas.closeFile();
		}
	}

	static void gravarArquivo() {
		arqContas.rewrite();
		for (int i = 0; i < contas.size(); i++) {
			arqContas.write(contas.elementAt(i));
		}
		arqContas.closeFile();
	}
}
