package lista01;

import utilitarios.*;

public class Demo {
	static Pessoa[] pessoas = new Pessoa[20];
	static int totPessoas = 0;
	static ObjectFile arqPessoas;
	
	static private class PessoasIterator implements MyIterator<Pessoa>{

		@Override
		public Pessoa getFirst() {
			
			return null;
		}

		@Override
		public Pessoa getNext() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}

	static void cadastrarPessoas() {
		char resp;
		do {
			Keyboard.clrscr();
			if (totPessoas == pessoas.length) {
				System.out.println("Capacidade do array esgotada");
				Keyboard.waitEnter();
				return;
			}
			int codPessoa = Keyboard.readInt("Entrar com o codigo: ");
			String nome = Keyboard.readString("Entrar com o nome: ");
			pessoas[totPessoas] = new Pessoa(codPessoa, nome);
			totPessoas++;
			resp = Keyboard.readChar("Outra pessoas(s/n)?");

		} while (resp == 's');

	}

	static void listarPessoas() {
		Keyboard.clrscr();
		System.out.println("CodPessoa  Nome da Pessoa        Valor Total");
		System.out.println("---------  --------------------  -----------");
		for (int i = 0; i < totPessoas; i++) {
			System.out.printf("%6d     %-20s  %11.2f\n", pessoas[i]
					.getCodPessoa(), pessoas[i].getNome(), pessoas[i]
					.valorTotal());
		}
		Keyboard.waitEnter();
	}

	static int busquePessoa(int codPessoa) {
		Pessoa pessoa = new Pessoa(codPessoa, "");
		ComparePessoas comp = new ComparePessoas();
		for (int i = 0; i < totPessoas; i++) {
			if (comp.compare(pessoa, pessoas[i]) == 0)
				return 1;
		}
		return -1;
	}

	static void cadastrarBens() {
		Keyboard.clrscr();
		int codPessoa = Keyboard.readInt("Entrar com o codigo da pessoa:");
		int indice = busquePessoa(codPessoa);
		if (indice == -1) {
			System.out.println("Codigo inexistente");
			Keyboard.waitEnter();
			return;
		}
		char resp;
		do {
			int codBem = Keyboard.readInt("Entrar com o código do bem:");
			String descricao = Keyboard.readString("Descricao: ");
			String data = Keyboard.readData("Entrar com a data: ");
			float valor = Keyboard.readFloat("Entrar com o valor: ");
			if (pessoas[indice].insiraBem(codBem, descricao, data, valor)) {
				System.out.println("Bem inserido");
			} else {
				System.out.println("Bem nao inserido");
			}
			resp = Keyboard.readChar("Outro bem(s/n)?");
		} while (resp == 's');
	}

	static void listarBens() {
		Keyboard.clrscr();
		int codPessoa = Keyboard.readInt("Entrar com o codigo da pessoa: ");
		int indice = busquePessoa(codPessoa);
		if (indice == -1) {
			System.out.println("Codigo inexistente");
			Keyboard.waitEnter();
			return;
		}
		pessoas[indice].imprimaBens();
		Keyboard.waitEnter();
	}

	static void gravarArquivo() {
		arqPessoas.rewrite();
		for (int i = 0; i < totPessoas; i++) {
			arqPessoas.write(pessoas[i]);
		}
		arqPessoas.closeFile();
	}

	static void lerArquivo() {
		if (arqPessoas.reset()) {
			Pessoa pessoa = (Pessoa) arqPessoas.read();
			while (pessoa != null) {
				if (totPessoas == pessoas.length) {
					// redimensione();
				}
				pessoas[totPessoas] = pessoa;
				totPessoas++;
				pessoa = (Pessoa) arqPessoas.read();
			}
			arqPessoas.closeFile();
		}
	}

	public static void main(String[] args) {
		int opcao;
		lerArquivo();
		do {
			Keyboard.clrscr();
			opcao = Keyboard.menu("Cadastrar pessoas/Listar pessoas/"
					+ "Cadastrar bens/Listar bens/Terminar");
			switch (opcao) {
			case 1:
				cadastrarPessoas();
				break;
			case 2:
				listarPessoas();
				break;
			case 3:
				cadastrarBens();
				break;
			case 4:
				listarBens();
				break;
			}

		} while (opcao < 5);

	}

}
