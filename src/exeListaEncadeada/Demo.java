package exeListaEncadeada;

import listasEncadeadas.NoSimpEnc;
import utilitarios.*;


public class Demo {
	
	static NoSimpEnc<Aluno> inicio, fim;
	
	static void incluir(){
		char resp = 's';
		Keyboard.clrscr();
		do{
			int numMat = Keyboard.readInt("Entre com o numero de matricula: ");
			String nome = Keyboard.readString("Entre com o nome do aluno: ");
			Aluno aluno = new Aluno(numMat,nome);
			NoSimpEnc<Aluno> novoNo = new NoSimpEnc<Aluno>(aluno);
			if (inicio == null){
				inicio = fim = novoNo;
			} else {
				fim.setProx(novoNo);
				fim = novoNo;
			}
			resp = Keyboard.readChar("Outro aluno(s/n)?");
			
		} while (resp == 's');
		
	}
	
	static void remover(){
		Keyboard.clrscr();
		int numMat = Keyboard.readInt("Entre com o numero de matricula: ");
		NoSimpEnc<Aluno> pAnt = null;
		NoSimpEnc<Aluno> p = inicio;
		while (p != null){
			if (numMat == p.getObj().getNumMat()){
				if(p == inicio){
					inicio = inicio.getProx();
					if(inicio == null){
						fim = null;
					}
				}else {
					if (p == fim){
						fim = pAnt;
						pAnt.setProx(null);
					} else {
						pAnt.setProx(p.getProx());
					}
				}
				System.out.println("Aluno Removido");
				Keyboard.waitEnter();
				return;
			} else {
				
				pAnt = p;
				p = p.getProx();
			}	
		}
		System.out.println("Matrícula Inexistente");
		Keyboard.waitEnter();
		
	}
	
	static void listar(){
		Keyboard.clrscr();
		System.out.println("NumMat  Nome do Aluno");
		System.out.println("------  -------------");
		NoSimpEnc<Aluno> p = inicio;
		while (p != null){
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
