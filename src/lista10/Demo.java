package lista10;

import utilitarios.Keyboard;
import utilitarios.MyIterator;
import utilitarios.MyIteratorBack;
import deque.Deque;

public class Demo {

	static Deque<String> deque = new Deque<String>();
	
	public static void main(String[] args) {
		int opcao;
		do {
			Keyboard.clrscr();
			opcao = Keyboard
					.menu("Inserir no Inicio/Inserir no Final/Remover do Inicio/"
							+ "Remover do Final/Imprimir do Inicio para o Final/Imprimir do Final para o Inicio/"
							+ "Remover do Deque um Determinado Nome/Terminar");
			switch (opcao) {
			case 1:
				inserirInicio();
				break;
			case 2:
				inserirFinal();
				break;
			case 3:
				removerInicio();
				break;
			case 4:
				removerFinal();
				break;
			case 5:
				imprimirInicioFim();
				break;
			case 6:
				imprimirFimInicio();
				break;
			case 7:
				removerNome();
				break;
			}

		} while (opcao < 8);
		System.out.println("Programa Encerrado");

	}

	private static void removerNome() {
		String nomeRemover = Keyboard.readString("Digite o nome a ser removido");
		int quant = 0;
		MyIterator<String> it = deque.iterator();
		String nome = it.getFirst();
		while(nome != null){
			if(nome.equalsIgnoreCase(nomeRemover)){
				it.remove();
				quant++;
			}
			nome = it.getNext();
		}
		if(quant == 0){
			System.out.println("Nome não existente!");
		}else{
			System.out.println(quant + "nomes removidos!");
		}
		
	}

	private static void imprimirFimInicio() {
		System.out.println("Fim -> Inicio");
		MyIteratorBack<String> it = deque.iteratorBack();
		String nome = it.getLast();
		while(nome != null){
			System.out.println(nome);
			nome = it.getPrior();
		}	
	}

	private static void imprimirInicioFim() {
		System.out.println("Inicio -> Fim");
		MyIterator<String> it = deque.iterator();
		String nome = it.getFirst();
		while(nome != null){
			System.out.println(nome);
			nome = it.getNext();
		}	
	}

	private static void removerFinal() {
		String nomeRemovido;
		nomeRemovido = deque.removeEnd();
		if(nomeRemovido == null){
			System.out.println("Deque Vazio");
		}else{
			System.out.println(nomeRemovido + " removido com sucesso!");
		}	
	}

	private static void removerInicio() {
		String nomeRemovido;
		nomeRemovido = deque.removeBegin();
		if(nomeRemovido == null){
			System.out.println("Deque Vazio");
		}else{
			System.out.println(nomeRemovido + " removido com sucesso!");
		}		
	}

	private static void inserirFinal() {
		String nome = Keyboard.readString("Digite o nome a ser inserido:");
		deque.insertEnd(nome);
	}

	private static void inserirInicio() {
		String nome = Keyboard.readString("Digite o nome a ser inserido:");
		deque.insertBegin(nome);
	}

}



