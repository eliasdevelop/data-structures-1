package lista12;

import utilitarios.Keyboard;
import utilitarios.MyIterator;

public class DemoHashDuplo {

	static int tamTabela, numItens;
	static Pessoa[] tabela;	

	static Pessoa deletado = new Pessoa(-1);
	
	static class HashIterator implements MyIterator<Pessoa>{
		
		int indCorrente;
		@Override
      public Pessoa getFirst() {
	    for (int i = 0; i < tabela.length; i++) {
			if(tabela[i] == null || tabela[i] == deletado){
				indCorrente = i;
			}else{
				indCorrente = i;
				return tabela[i];
			}
		}
	    return null;
      }

		@Override 
      public Pessoa getNext() {
	    for (int i = (indCorrente+1); i < tabela.length; i++) {
	    	if(tabela[i] == null || tabela[i] == deletado){
				indCorrente = i;
			}else{
				indCorrente = i;
				return tabela[i];
			}
		}
	    return null;
      }

		@Override
      public void remove() {
	      tabela[indCorrente] = deletado;
      }
		
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
	
	static int hash2(int codigo) {
		int result = 1 + codigo % (tabela.length - 1);
		return result;
	}
	
	static void redimensione(){
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

	static boolean add(Pessoa pessoa) {
		if ((float)numItens / tamTabela >= 0.7f)
			redimensione();
		int k = funcHash(pessoa.getCodPessoa());
		int inicio = k;
		int incremento = hash2(pessoa.getCodPessoa());
		while ((tabela[k] != null) && (tabela[k] != deletado)){
			if (pessoa.getCodPessoa() == tabela[k].getCodPessoa()){
				return false;
			}
			k = (k + incremento) % tamTabela;
		}
		
		int j = k;
		
		while (tabela[k] != null){
			if (k == inicio)
				break;
			if ((tabela[k] != deletado) && 
					(pessoa.getCodPessoa() == tabela[k].getCodPessoa())){
				return false;
			}
			k = (k + incremento) % tamTabela;
		}
		
		tabela[j] = pessoa;
		numItens++;
		return true;
	}

	static boolean contains(Pessoa pessoa) {
		return (retrieve(pessoa) != null);
	}

	static Pessoa retrieve(Pessoa pessoa) {
		// Procura na tabela a pessoa que possua o mesmo
		// código da pessoa passada como parâmetro. Se achar
		// retorna a referência da pessoa que se encontra na
		// tabela. Se não achar, retorna null.
		int k = funcHash(pessoa.getCodPessoa());
		int inicio = k;
		int incremento = hash2(pessoa.getCodPessoa());
		while ((tabela[k] != null) && (tabela[k] != deletado)){
			if (pessoa.getCodPessoa() == tabela[k].getCodPessoa()){
				return tabela[k];
			}
			k = (k + incremento) % tamTabela;
		}
		
		while (tabela[k] != null){
			if (k == inicio)
				break;
			if ((tabela[k] != deletado) && 
					(pessoa.getCodPessoa() == tabela[k].getCodPessoa())){
				return tabela[k];
			}
			k = (k + incremento) % tamTabela;
		}
		return null;
	}

	static boolean remove(Pessoa pessoa) {
		MyIterator<Pessoa> it = new HashIterator();
		Pessoa pessoaIt = it.getFirst();
		while (pessoaIt != null){
			if (pessoaIt.compareTo(pessoa) == 0){
				it.remove();
				return true;
			}
			pessoaIt = it.getNext();
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
			}
			else
				System.out.println("Pessoa nao excluida");
		}

		Keyboard.waitEnter();
	}

	static void impimirTabela() {
		// Imprime o conteúdo da tabela
		Keyboard.clrscr();
		System.out.println("indice  Codigo  Nome da Pessoa");
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

	static void imprimirPessoas() {
		// Imprime as pessoas armazenadas
		// na tabela usando o iterator
		Keyboard.clrscr();
		System.out.println("Codigo  Nome da Pessoa");
		System.out.println("------  ------------------------------");
		MyIterator<Pessoa> it = new HashIterator();
		Pessoa pessoaIt = it.getFirst();
		while (pessoaIt != null){
			System.out.printf("%6d  %-30s\n", pessoaIt.getCodPessoa(), pessoaIt.getNomePessoa());
			pessoaIt = it.getNext();
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
			      + "Imprimir Pessoas/Terminar");
			switch (opcao) {
				case 1:
					incluir();
					break;
				case 2:
					excluir();
					break;
				case 3:
					impimirTabela();
					break;
				case 4:
					imprimirPessoas();
					break;
			}

		} while (opcao < 5);
		System.out.println("\nFim do programa");
	}

}
