package exeListaSeqNaoOrd;

import java.util.Comparator;

import listasSequenciais.ListaSeqNaoOrd;
import utilitarios.*;

public class Demo {

	static ListaSeqNaoOrd<Professor> listaProfessores;
	static ListaSeqNaoOrd<Disciplina> listaDisciplinas;
	static ListaSeqNaoOrd<ProfDisc> listaProfDisc;
	static ObjectFile arqListas = new ObjectFile("Listas.dat");

	static class ComparaProfessor implements Comparator<ProfDiscAux> {

		@Override
		public int compare(ProfDiscAux o1, ProfDiscAux o2) {
			return o1.nomeProf.compareToIgnoreCase(o2.nomeProf);
		}

	}

	static void incluirProfessor() {
		char resp;
		Keyboard.clrscr();
		do {
			int numMat = Keyboard.readInt("Entrar com o numero de matricula: ");
			Professor professor = new Professor(numMat);
			if (listaProfessores.contains(professor)) {
				System.out.println("Matricula ja existente");
			} else {
				String nome = Keyboard.readString("Entrar com o nome: ");
				professor.setNome(nome);
				listaProfessores.add(professor);
			}
			resp = Keyboard.readChar("Outro professor?");
		} while (resp == 's');
	}

	static void listarProfressores() {
		Keyboard.clrscr();
		System.out.println("NumMat  Nome do Professor");
		System.out.println("------  -------------------------");
		MyIterator<Professor> it = listaProfessores.iterator();
		Professor professor = it.getFirst();
		while (professor != null) {
			System.out.printf("%6d  %-30s\n", professor.getNumMat(),
					professor.getNome());
			professor = it.getNext();
		}
		Keyboard.waitEnter();
	}
	
	static boolean buscaProfessor(int numMat){
		MyIterator<ProfDisc> it = listaProfDisc.iterator();
		ProfDisc profDisc = it.getFirst();
		while (profDisc != null){
			if(profDisc.getNumMat() == numMat){
				return true;
			}
			profDisc = it.getNext();		
		}
		return false;
	}

	static void excluirProfessores() {
		Keyboard.clrscr();
		int numMat = Keyboard.readInt("Entrar com o numero de matricula: ");
		Professor professor = new Professor(numMat);
		professor = listaProfessores.retrieve(professor);
		if (professor == null) {
			System.out.println("Professor inexistente");
		} else {
			System.out.println("Nome do professor: " + professor.getNome());
			char resp = Keyboard.readChar("Deseja excluir?");
			if (resp == 's') {
				if(buscaProfessor(numMat)){
					System.out.println("O professor não pode ser excluido!");
				}else{
					listaProfessores.remove(professor);
					System.out.println("Professor removido");
				}
			} else {
				System.out.println("Professor nao removido");
			}

		}
		Keyboard.waitEnter();
	}

	static void incluirDisciplina() {
		char resp;
		Keyboard.clrscr();
		do {
			int codDisc = Keyboard
					.readInt("Entrar com o codigo da disciplina: ");
			Disciplina disciplina = new Disciplina(codDisc);
			if (listaDisciplinas.contains(disciplina)) {
				System.out.println("Disciplina ja existente");
			} else {
				String nomeDisc = Keyboard
						.readString("Entrar com o nome da disciplina: ");
				disciplina.setNomeDisc(nomeDisc);
				listaDisciplinas.add(disciplina);
			}
			resp = Keyboard.readChar("Outra disciplina?");
		} while (resp == 's');
	}

	static void listarDisciplinas() {
		Keyboard.clrscr();
		System.out.println("CodDisc  Nome da Disciplina");
		System.out.println("-------  -------------------------");
		MyIterator<Disciplina> it = listaDisciplinas.iterator();
		Disciplina disciplina = it.getFirst();
		while (disciplina != null) {
			System.out.printf("%7d  %-30s\n", disciplina.getCodDisc(),
					disciplina.getNomeDisc());
			disciplina = it.getNext();
		}
		Keyboard.waitEnter();
	}

	static void gravarArquivo() {
		arqListas.rewrite();
		arqListas.write(listaProfessores);
		arqListas.write(listaDisciplinas);
		arqListas.write(listaProfDisc);
		arqListas.closeFile();
	}

	static void lerArquivo() {
		if (arqListas.reset()) {
			listaProfessores = (ListaSeqNaoOrd<Professor>) arqListas.read();
			listaDisciplinas = (ListaSeqNaoOrd<Disciplina>) arqListas.read();
			listaProfDisc = (ListaSeqNaoOrd<ProfDisc>) arqListas.read();
			arqListas.closeFile();
		} else {
			listaProfessores = new ListaSeqNaoOrd<Professor>(10);
			listaDisciplinas = new ListaSeqNaoOrd<Disciplina>(10);
			listaProfDisc = new ListaSeqNaoOrd<ProfDisc>(10);
		}
	}

	static void excluirDisciplina() {

	}

	static void incluirProfDisc() {
		Keyboard.clrscr();
		char resp;
		do {
			int numMat = Keyboard.readInt("Entrar com o numero de matricula: ");
			Professor professor = new Professor(numMat);
			if (listaProfessores.contains(professor)) {
				int codDisc = Keyboard
						.readInt("Entrar com o codigo da disciplina: ");
				Disciplina disciplina = new Disciplina(codDisc);
				if (listaDisciplinas.contains(disciplina)) {
					ProfDisc profDisc = new ProfDisc(numMat, codDisc);
					if (listaProfDisc.contains(profDisc)) {
						System.out.println("Já existe professor na disciplina");
					} else {
						listaProfDisc.add(profDisc);
					}
				} else {
					System.out.println("Disciplina inexistente");
				}

			} else {
				System.out.println("Professor inexistente");
			}
			resp = Keyboard.readChar("Outra inclusao? ");
		} while (resp == 's');
	}

	/*
	 * static void listarProfDisc(){ Keyboard.clrscr(); System.out.println(
	 * "NumMat  Nome do Professor     CodDisc  Nome da Disciplina ");
	 * System.out.
	 * println("------  --------------------  -------  ------------------");
	 * MyIterator<ProfDisc> it = listaProfDisc.iterator(); ProfDisc profDisc =
	 * it.getFirst(); while(profDisc != null){ Professor professor = new
	 * Professor(profDisc.getNumMat()); professor =
	 * listaProfessores.retrieve(professor); Disciplina disciplina = new
	 * Disciplina(profDisc.getCodDisc()); disciplina =
	 * listaDisciplinas.retrieve(disciplina);
	 * System.out.printf("%6d  %-20s  %7d  %-20s\n", professor.getNumMat(),
	 * professor.getNome(), disciplina.getCodDisc(), disciplina.getNomeDisc());
	 * } }
	 */

	static void listarProfDisc() {
		Keyboard.clrscr();
		System.out
				.println("NumMat  Nome do Professor     CodDisc  Nome do Professor");
		System.out
				.println("------  --------------------  -------  -----------------");

		ProfDiscAux[] profDiscAux = new ProfDiscAux[listaProfDisc.size()];
		int i = -1;
		MyIterator<ProfDisc> it = listaProfDisc.iterator();
		ProfDisc profDisc = it.getFirst();
		while (profDisc != null) {
			i++;
			Professor professor = new Professor(profDisc.getNumMat());
			professor = listaProfessores.retrieve(professor);
			Disciplina disciplina = new Disciplina(profDisc.getCodDisc());
			disciplina = listaDisciplinas.retrieve(disciplina);
			profDiscAux[i] = new ProfDiscAux(professor.getNumMat(),
					professor.getNome(), disciplina.getCodDisc(),
					disciplina.getNomeDisc());
			profDisc = it.getNext();
		}
		Sort.quickSort(profDiscAux, new ComparaProfessor());
		
		for (int j = 0; j < profDiscAux.length; j++) {
			System.out.printf("%6d  %-20s  %7d  %-20s\n", profDiscAux[j].numMat,
					profDiscAux[j].nomeProf, profDiscAux[j].codDisc, profDiscAux[j].nomeDisc);
		}
		Keyboard.waitEnter();
	}

	public static void main(String[] args) {

		int opcao;
		lerArquivo();
		do {
			Keyboard.clrscr();
			opcao = Keyboard
					.menu("Incluir Professor/Listar Professor/Excluir Professor/"
							+ "Incluir Disciplina/Listar Disciplinas/Excluir Disciplinas/"
							+ "Incluir Prof na Disciplina/Listar Prof Disciplinas/Terminar");
			switch (opcao) {
			case 1:
				incluirProfessor();
				break;
			case 2:
				listarProfressores();
				break;
			case 3:
				excluirProfessores();
				break;
			case 4:
				incluirDisciplina();
				break;
			case 5:
				listarDisciplinas();
				break;
			case 6:
				excluirDisciplina();
				break;
			case 7:
				incluirProfDisc();
				break;
			case 8:
				listarProfDisc();
				break;
			}

		} while (opcao < 9);
		gravarArquivo();
	}

}
