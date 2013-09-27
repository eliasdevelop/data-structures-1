package lista11;

import java.util.Comparator;

import utilitarios.Keyboard;
import utilitarios.Sort;

public class DemoTentativaLinear {

	static int tamTabela, numItens;
	static Pessoa[] tabela;

	static Pessoa deletado = new Pessoa(-1);

	static class CompararPessoas implements Comparator<Pessoa> {

		@Override
		public int compare(Pessoa o1, Pessoa o2) {
			return o1.getNomePessoa().compareToIgnoreCase(o2.getNomePessoa());
		}

	}
	
	public static void redimensione(){
		Pessoa[] novaTabela = tabela;
		int numItensAux = numItens;
		tamTabela = tamTabela + (tamTabela/2);
		tabela = new Pessoa[tamTabela];
		for (int i = 0; i < novaTabela.length; i++) {
			if ((novaTabela[i] != deletado) && (novaTabela[i] != null)) {
				add(novaTabela[i]);
			}
		}
		numItens = numItensAux;
	}

	// Testa se x é primo
	public static boolean isPrime(int x) {
		int divisor;
		if (x < 4)
			return true;

		if ((x % 2) == 0)
			return false;

		divisor = 3;

		while (!((divisor * divisor > x) || (x % divisor == 0)))
			divisor = divisor + 2;

		if (divisor * divisor > x)
			return true;
		else
			return false;
	}

	// Retorna o próximo número primo maior do que x
	public static int nextPrime(int x) {
		if (x < 2)
			return 3;

		if (x % 2 == 0)
			x++;
		else
			x = x + 2;

		while (!isPrime(x))
			x = x + 2;

		return x;

	}

	static int funcHash(int codigo) {
		return codigo % tamTabela;
	}

	static boolean add(Pessoa pessoa) {
		int k = funcHash(pessoa.getCodPessoa());
		// Procura a próxima posição disponível
		while ((tabela[k] != null) && (tabela[k] != deletado)) {
			if (pessoa.getCodPessoa() == tabela[k].getCodPessoa()) {
				return false;
			}
			k = (k + 1) % tamTabela;
		}
		// O dado vai ser armazenado na posição j
		int j = k;

		// Verifica se o dado já existe na tabela
		while (tabela[k] != null) {
			if ((tabela[k] != deletado)
					&& (pessoa.getCodPessoa() == tabela[k].getCodPessoa())) {
				return false;
			}
			k = (k + 1) % tamTabela;
		}

		// Armazena o dado na posição j
		tabela[j] = pessoa;
		numItens++;
		return true;

	}

	static boolean contains(Pessoa pessoa) {
		return (retrieve(pessoa) != null);
	}

	static Pessoa retrieve(Pessoa pessoa) {
		int k = funcHash(pessoa.getCodPessoa());
		// Verifica se o dado já existe na tabela
		while (tabela[k] != null) {
			if ((tabela[k] != deletado)
					&& (pessoa.getCodPessoa() == tabela[k].getCodPessoa())) {
				return tabela[k];
			}
			k = (k + 1) % tamTabela;
		}
		return null;
	}

	static boolean remove(Pessoa pessoa) {
		int k = funcHash(pessoa.getCodPessoa());
		// Verifica se o dado já existe na tabela
		while (tabela[k] != null) {
			if ((tabela[k] != deletado)
					&& (pessoa.getCodPessoa() == tabela[k].getCodPessoa())) {
				tabela[k] = deletado;
				numItens--;
				return true;
			}
			k = (k + 1) % tamTabela;
		}
		return false;
	}

	static void incluir() {
		char resp;
		do {
			double numI = numItens;
			double tamT = tamTabela;
			double porcentagem = (numI/tamT);
			if ((porcentagem) >= 0.70){
				redimensione();
			}
			Keyboard.clrscr();
			int codigo = Keyboard.readInt("Entrar com o codigo: ");
			Pessoa pessoa = new Pessoa(codigo);
			if (contains(pessoa))
				System.out.println("Pessoa ja existente");
			else {
				String nome = Keyboard.readString("Entrar com o nome: ");
				pessoa.setNomePessoa(nome);
				add(pessoa);
			}
			resp = Keyboard.readChar("Outra pessoa(s/n)?: ");

		} while (resp == 's');

	}

	static void excluir() {
		Keyboard.clrscr();
		int codigo = Keyboard.readInt("Entrar com o codigo: ");
		Pessoa pessoa = new Pessoa(codigo);
		pessoa = retrieve(pessoa);
		if (pessoa == null)
			System.out.println("Pessoa inexistente");
		else {
			System.out.println(pessoa.getNomePessoa());
			char resp = Keyboard.readChar("Deseja excluir(s/n)? ");
			if (resp == 's') {
				remove(pessoa);
				System.out.println("Pessoa excluida");
			} else
				System.out.println("Pessoa nao excluida");
		}

		Keyboard.waitEnter();
	}

	static void imprimirTabela() {
		// Imprime o conteúdo da tabela
		Keyboard.clrscr();
		System.out.println("Indice  Codigo  Nome da Pessoa");
		System.out.println("------  ------  ------------------------------");
		for (int i = 0; i < tabela.length; i++) {
			if (tabela[i] == deletado) {
				System.out.printf("%6d  %6s  %-30s\n", i, "" , "Deletado");
			} else if (tabela[i] == null) {
				System.out.printf("%6d  %6s  %-30s\n", i, "" , "");
			} else {
				System.out.printf("%6d  %6d  %-30s\n", i,
						tabela[i].getCodPessoa(), tabela[i].getNomePessoa());
			}
		}
		Keyboard.waitEnter();

	}

	static void imprimirAlfabetica() {
		// Imprime em ordem alfabética as pessoas armazenadas
		// na tabela
		Keyboard.clrscr();
		System.out.println("Codigo  Nome da Pessoa");
		System.out.println("------  ------------------------------");
		Pessoa[] tabelaAlfa = new Pessoa[numItens];
		int j = 0;
		for (int i = 0; i < tabela.length; i++) {
			if ((tabela[i] != deletado) & (tabela[i] != null)) {
				tabelaAlfa[j] = tabela[i];
				j++;
			}
		}
		Sort.quickSort(tabelaAlfa, new CompararPessoas());
		for (int i = 0; i < tabelaAlfa.length; i++) {
			System.out.printf("%6d  %-30s\n", tabelaAlfa[i].getCodPessoa(), tabelaAlfa[i].getNomePessoa());
		}
		Keyboard.waitEnter();

	}

	public static void main(String[] args) {
		int opcao;
		tamTabela = Keyboard.readInt("Entrar com o tamanho da tabela: ");
		tamTabela = nextPrime(tamTabela);
		tabela = new Pessoa[tamTabela];
		do {
			Keyboard.clrscr();
			opcao = Keyboard.menu("Incluir/Excluir/Imprimir Tabela/"
					+ "Imprimir em ordem alfabetica/Terminar");
			switch (opcao) {
			case 1:
				incluir();
				break;
			case 2:
				excluir();
				break;
			case 3:
				imprimirTabela();
				break;
			case 4:
				imprimirAlfabetica();
				break;
			}

		} while (opcao < 5);
		System.out.println("\nFim do programa");
	}

}
