package lista13;

import java.util.Comparator;

import utilitarios.Keyboard;
import utilitarios.MyIterator;
import utilitarios.Sort;
import hash.HashDuplo;

public class Demo {

	static HashDuplo<Pessoa> tabela;

	static class CompararPessoas implements Comparator<Pessoa> {

		@Override
		public int compare(Pessoa o1, Pessoa o2) {
			return o1.getNomePessoa().compareToIgnoreCase(o2.getNomePessoa());
		}
	}

	static void inserir() {
		char resp;
		do {
			Keyboard.clrscr();
			int codPessoa = Keyboard.readInt("Entrar com o codigo: ");
			Pessoa pessoa = new Pessoa(codPessoa);
			if (tabela.contains(pessoa))
				System.out.println("Codigo ja existente");
			else {
				pessoa.setNomePessoa(Keyboard.readString("Entrar com o nome: "));
				pessoa.setIdade(Keyboard.readInt("Entrar com a idade: "));
				if (tabela.add(pessoa)) {
					System.out.println("Pessoa cadastrada com sucesso");
				} else {
					System.out.println("Pessoa ja existente!");
				}
			}
			resp = Keyboard.readChar("Outra pessoa(s/n)? ");
		} while (resp == 's');

	}

	static void excluir() {
		Keyboard.clrscr();
		int codPessoa = Keyboard.readInt("Entrar com o codigo: ");
		Pessoa pessoa = new Pessoa(codPessoa);
		pessoa = tabela.retrieve(pessoa);
		if (pessoa == null)
			System.out.println("Codigo inexistente");
		else {
			System.out.println("Nome da pessoa: " + pessoa.getNomePessoa());
			char resp = Keyboard.readChar("Deseja excluir(s/n)? ");
			if (resp == 's') {
				tabela.remove(pessoa);
				System.out.println("Pessoa excluida");
			} else
				System.out.println("Pessoa nao excluida");
		}
		Keyboard.waitEnter();
	}

	static void consultar() {
		Keyboard.clrscr();
		int codPessoa = Keyboard.readInt("Entrar com o codigo: ");
		Pessoa pessoa = new Pessoa(codPessoa);
		pessoa = tabela.retrieve(pessoa);
		if (pessoa == null) {
			System.out.println("Codigo inexistente");
		} else {
			System.out.println("Nome da pessoa: " + pessoa.getNomePessoa());
			System.out.println("Idade: " + pessoa.getIdade());
		}
		Keyboard.waitEnter();
	}

	static void listarPessoas() {
		// Listar as pessoas em ordem alfabética
		Keyboard.clrscr();
		System.out.println("Codigo  Nome da Pessoa                  Idade");
		System.out.println("------  ------------------------------  -----");
		Pessoa[] tabelaAlfa = new Pessoa[tabela.size()];
		int j = 0;
		MyIterator<Pessoa> it = tabela.iterator();
		Pessoa pessoa = it.getFirst();
		while (pessoa != null) {
			tabelaAlfa[j] = pessoa;
			j++;
			pessoa = it.getNext();
		}
		Sort.quickSort(tabelaAlfa, new CompararPessoas());
		for (int i = 0; i < tabelaAlfa.length; i++) {
			System.out.printf("%6d  %-30s  %5d\n",
					tabelaAlfa[i].getCodPessoa(),
					tabelaAlfa[i].getNomePessoa(), tabelaAlfa[i].getIdade());
		}
		Keyboard.waitEnter();
	}

	static void removerIdade() {
		Keyboard.clrscr();
		boolean removido = false;
		int idade = Keyboard.readInt("Entrar com a Idade: ");
		MyIterator<Pessoa> it = tabela.iterator();
		Pessoa pessoa = it.getFirst();
		while (pessoa != null) {
			if (pessoa.getIdade() == idade) {
				it.remove();
				removido = true;
			}
			pessoa = it.getNext();
		}
		if (removido) {
			System.out.println("Pessoas removidas com sucesso!");
		} else {
			System.out.println("Não existem pessoas com essa idade!");
		}
		Keyboard.waitEnter();
	}

	static void altere() {
		// Alterar a idade e o nome de determinada pessoa
		Keyboard.clrscr();
		int codigo = Keyboard.readInt("Entrar com o codigo: ");
		Pessoa pessoa = new Pessoa(codigo);
		pessoa = tabela.retrieve(pessoa);
		if (pessoa == null) {
			System.out.println("Codigo Inexistente!");
		} else {
			System.out.println("Nome da pessoa: " + pessoa.getNomePessoa());
			char resp = Keyboard.readChar("Deseja alterar(s/n)? ");
			if (resp == 's') {
				String nome = Keyboard.readString("Entrar com o nome: ");
				int idade = Keyboard.readInt("Entrar com a idade: ");
				pessoa.setNomePessoa(nome);
				pessoa.setIdade(idade);
				System.out.println("Pessoa alterada com sucesso!");
			}else{
				System.out.println("Pessoa não alterada!");
			}
		}
		Keyboard.waitEnter();
	}

	public static void main(String[] args) {
		int opcao;
		int tamTabela = Keyboard.readInt("Entrar com o tamanho da tabela: ");
		tabela = new HashDuplo<Pessoa>(tamTabela);
		do {
			Keyboard.clrscr();
			opcao = Keyboard.menu("Inserir/Excluir/Consultar/Listar tabela"
					+ "/Listar Pessoas/Remover Idade/Alterar/Terminar");
			switch (opcao) {
			case 1:
				inserir();
				break;
			case 2:
				excluir();
				break;
			case 3:
				consultar();
				break;
			case 4:
				Keyboard.clrscr();
				tabela.imprimirTabela();
				Keyboard.waitEnter();
				break;
			case 5:
				listarPessoas();
				break;
			case 6:
				removerIdade();
				break;
			case 7:
				altere();
				break;
			}
		} while (opcao < 8);
		System.out.println("\nFim do programa");

	}
}
