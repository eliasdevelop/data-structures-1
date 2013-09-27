package lista03;

import utilitarios.*;

public class Principal {

	static Conta[] contas = new Conta[20];
	static int totContas = 0;
	static ObjectFile arqContas = new ObjectFile("contas.dat");

	public static void main(String[] args) {
		int opcao = 1;
		lerArquivo();
		while (opcao < 7) {
			Keyboard.clrscr();
			opcao = Keyboard
					.menu("Cadastrar Contas/Listar Contas/Efetuar Dep�sitos/Efetuar Saques/Consultar Conta/Creditar Rendimentos"
							+ "/Terminar");
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
				System.out.println("Programa Fechado");
				break;
			}
		}
		gravarArquivo();

	}

	private static void creditarRendimentos() {
		Keyboard.clrscr();

		double juros = Keyboard.readDouble("Entrar com o juros:");
		ContasIterator buscar = new ContasIterator();
		Conta buscador = buscar.getNext();
		if (buscador == null) {
			System.out.println("Sem Contas cadastradas!");
		} else {
			while (buscador != null) {
				if (buscador instanceof ContaPoupanca) {
					((ContaPoupanca) buscador).rendimentoConta(juros);
				}
				buscador = buscar.getNext();
			}
			System.out.println("Contas Poupan�a creditadas com sucesso!");
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
				tipo = "Poupan�a";
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
				System.out.println("Desculpe, conta n�o possui credito/limite");
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

		ContasIterator buscar = new ContasIterator();
		Conta buscador = buscar.getNext();
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
					tipo = "Poupan�a";
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

		ContasIterator buscar = new ContasIterator();
		CompararContas comparador = new CompararContas();
		Conta contaComparada = new ContaComum(numeroConta);
		Conta buscador = buscar.getNext();
		while (buscador != null) {
			if (comparador.compare(buscador, contaComparada) == 0) {
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
			if (totContas == contas.length) {
				redimensione();
			}
			int numeroConta = Keyboard
					.readInt("Entrar com o N�mero da Conta: ");
			if (buscarConta(numeroConta) != null) {
				System.out.println("Desculpe, numero de conta ja existente.");
			} else {
				Keyboard.clrscr();
				int tipoConta = Keyboard
						.menu("Conta Comum/Conta Especial/Conta Poupan�a");
				switch (tipoConta) {
				case 1: {
					contas[totContas] = new ContaComum(numeroConta);
					contas[totContas].setNomeCliente(Keyboard
							.readString("Entrar com o nome do cliente: "));
					System.out.println("Conta Cadastrada com sucesso!");
					break;
				}
				case 2: {
					double limiteConta = Keyboard
							.readDouble("Entrar com o limite da conta: ");
					contas[totContas] = new ContaEspecial(numeroConta,
							limiteConta);
					contas[totContas].setNomeCliente(Keyboard
							.readString("Entrar com o nome do cliente: "));
					System.out.println("Conta Cadastrada com sucesso!");
					break;
				}
				case 3: {
					contas[totContas] = new ContaPoupanca(numeroConta);
					contas[totContas].setNomeCliente(Keyboard
							.readString("Entrar com o nome do cliente: "));
					System.out.println("Conta Cadastrada com sucesso!");
					break;
				}
				default: {
					System.out.println("Op��o invalida");
					break;
				}
				}
			}
			totContas++;
			resp = Keyboard.readChar("Outra Conta(s/n)?");

		} while (resp == 's');

	}

	static void redimensione() {
		Conta[] contasRed = new Conta[contas.length + 20];
		for (int i = 0; i < contas.length; i++) {
			contasRed[i] = contas[i];
		}
		contas = contasRed;
	}

	static private class ContasIterator implements MyIterator<Conta> {

		private int contador = 0;

		@Override
		public Conta getFirst() {
			if (contas[0] == null) {
				return null;
			} else {
				contador++;
				return contas[0];
			}
		}

		@Override
		public Conta getNext() {
			if (contador > totContas) {
				return null;
			} else {
				contador++;
				return contas[contador - 1];
			}
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}

	static void lerArquivo() {
		if (arqContas.reset()) {
			Conta conta = (Conta) arqContas.read();
			while (conta != null) {
				contas[totContas] = conta;
				totContas++;
				conta = (Conta) arqContas.read();
			}
			arqContas.closeFile();
		}
	}

	static void gravarArquivo() {
		arqContas.rewrite();
		for (int i = 0; i < totContas; i++) {
			arqContas.write(contas[i]);
		}
		arqContas.closeFile();
	}
}
