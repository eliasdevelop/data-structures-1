package exeListaDupEncadeada;

import listasEncadeadas.NoDupEnc;
import utilitarios.*;


public class Demo {
	
	static NoDupEnc<Aluno> noCabeca = new NoDupEnc<Aluno>();
	
	static void incluir(){
		char resp = 's';
		Keyboard.clrscr();
		do{
			int numMat = Keyboard.readInt("Entre com o numero de matricula: ");
			String nome = Keyboard.readString("Entre com o nome do aluno: ");
			Aluno aluno = new Aluno(numMat,nome);
			new NoDupEnc<Aluno>(aluno,noCabeca.getAnt(), noCabeca);
			resp = Keyboard.readChar("Outro aluno(s/n)?");
			
		} while (resp == 's');
		
	}
	
	static void remover(){
		Keyboard.clrscr();
		int numMat = Keyboard.readInt("Entre com o numero de matricula: ");
		NoDupEnc<Aluno> p = noCabeca.getProx();
		while (p != noCabeca){
			if (p.getObj().getNumMat() == numMat){
				p.remove();
				System.out.println("Aluno Removido");
				return;
			}else
				p.getProx();
		}		
		System.out.println("Matrícula Inexistente");
		Keyboard.waitEnter();
		
	}
	
	static void listar(){
		Keyboard.clrscr();
		System.out.println("NumMat  Nome do Aluno");
		System.out.println("------  -------------");
		NoDupEnc<Aluno> p = noCabeca.getProx();
		while (p != noCabeca){
			System.out.printf("%4d    %-20s\n",p.getObj().getNumMat(), p.getObj().getNome());
			p = p.getProx();
		}
		Keyboard.waitEnter();
	}

	public static void main(String[] args) {
		int opcao;
		do{
			Keyboard.clrscr();
			opcao = Keyboard.menu("Incluir/Remover/Listar/Terminar");
			switch (opcao){
				case 1:
					incluir();
					break;
				case 2:
					remover();
					break;
				case 3:
					listar();
					break;
			}
			
		} while (opcao < 4);

	}

}
