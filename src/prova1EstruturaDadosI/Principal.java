package prova1EstruturaDadosI;

import java.util.Comparator;

import listasSequenciais.ListaSeqOrd;
import utilitarios.Keyboard;
import utilitarios.MyIterator;
import utilitarios.ObjectFile;
import utilitarios.Sort;

public class Principal {

	static ListaSeqOrd<Aluno> listaAlunos;
	static ListaSeqOrd<Disciplina> listaDisciplinas;
	static ListaSeqOrd<Turma> listaTurmas;
	static ListaSeqOrd<Professor> listaProfessores;
	static ObjectFile arqListas = new ObjectFile("provaEstruturaTeste2.dat");

	static class ComparaAluno implements Comparator<Aluno> {

		@Override
		public int compare(Aluno o1, Aluno o2) {
			return o1.getNomeAluno().compareToIgnoreCase(o2.getNomeAluno());
		}

	}

	static class ComparaTurma implements Comparator<TurmaAux> {

		@Override
		public int compare(TurmaAux o1, TurmaAux o2) {
			return o1.codTurma.compareToIgnoreCase(o2.codTurma);
		}

	}

	public static void main(String[] args) {
		int opcao;
		lerArquivo();
		do {
			Keyboard.clrscr();
			opcao = Keyboard
					.menu("Incluir Aluno/Listar Alunos/Excluir Alunos/"
							+ "Incluir Disciplina/Listar Disciplinas/Excluir Disciplinas/"
							+ "Incluir Turma/Lista Turmas/Excluir Turma/Matricular Aluno/Listar Alunos Matriculados/Incluir Professor/Listar Professor/Excluir Professor/Listar Turmas do Professor/Registrar Notas/Listar Historico de Aluno/Terminar");
			switch (opcao) {
			case 1:
				incluirAluno();
				break;
			case 2:
				listarAlunos();
				break;
			case 3:
				excluirAlunos();
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
				incluirTurma();
				break;
			case 8:
				listarTurmas();
				break;
			case 9:
				excluirTurmas();
				break;
			case 10:
				matricularAluno();
				break;
			case 11:
				listarAlunosMatriculados();
				break;
			case 12:
				incluirProfessor();
				break;
			case 13:
				listarProfessor();
				break;
			case 14:
				excluirProfessor();
				break;
			case 15:
				listarTurmasProfessor();
				break;
			case 16:
				registrarNotas();
				break;
			case 17:
				historicoAluno();
				break;
			}

		} while (opcao < 18);
		gravarArquivo();
		System.out.println("Programa Encerrado");
	}

	static void historicoAluno() {
		Keyboard.clrscr();
		int numMat = Keyboard.readInt("Entrar com o numero da Matricula:");
		Aluno aluno = new Aluno(numMat);
		aluno = listaAlunos.retrieve(aluno);
		if (aluno == null) {
			System.out.println("Aluno inexistente!");
		} else {
			System.out.println("Historico Escolar");
			System.out.println("NumMat:" + aluno.getNumMat() + " Nome: "
					+ aluno.getNomeAluno());
			System.out
					.println("CodDisc  Nome da Disciplina  Nota1  Nota2  Media");
			System.out
					.println("-------  ------------------  -----  -----  -----");
			MyIterator<Turma> it = aluno.getTurmas().iterator();
			Turma turma = it.getFirst();
			while (turma != null) {
				Disciplina disciplina = new Disciplina(turma.getCodDisc());
				disciplina = listaDisciplinas.retrieve(disciplina);
				double media = (turma.getListaAluno().retrieve(aluno).getNota1() + turma.getListaAluno().retrieve(aluno).getNota2()) / 2;
				System.out.printf("%7d  %-18s  %5.1f  %5.1f  %5.1f",
						turma.getCodDisc(), disciplina.getNomeDisc(),
						turma.getListaAluno().retrieve(aluno).getNota1(), turma.getListaAluno().retrieve(aluno).getNota2(), media);
				turma = it.getNext();
			}
		}
		Keyboard.waitEnter();
	}

	static void registrarNotas() {
		Keyboard.clrscr();
		String codTurma = Keyboard.readString("Entrar com o codigo da Turma:");
		int codDisc = Keyboard.readInt("Entrar com o codigo da Disciplina:");
		Turma turma = new Turma(codTurma);
		turma.setCodDisc(codDisc);
		turma = listaTurmas.retrieve(turma);
		if (turma == null) {
			System.out.println("Turma inexistente!");
		} else {
			MyIterator<Aluno> it = turma.getListaAluno().iterator();
			Aluno aluno = it.getFirst();
			while (aluno != null) {
				System.out.println("Aluno: " + aluno.getNomeAluno());
				int nota1 = Keyboard.readInt("Entrar com Nota1:");
				int nota2 = Keyboard.readInt("Entrar com Nota2:");
				aluno.setNota1(nota1);
				aluno.setNota2(nota2);
				aluno = it.getNext();
			}
			System.out.println("Notas Adicionadas!");

		}
		Keyboard.waitEnter();

	}

	static void listarTurmasProfessor() {
		Keyboard.clrscr();
		int codProf = Keyboard.readInt("Entrar com o codigo do Professor:");
		Professor professor = new Professor(codProf);
		professor = listaProfessores.retrieve(professor);
		if (professor == null) {
			System.out.println("Professor inexistente!");
		} else {
			System.out
					.println("CodDisc  CodTurma  Nome Disciplina  Total de Alunos");
			System.out
					.println("-------  --------  ---------------  ---------------");
			MyIterator<Turma> it = listaTurmas.iterator();
			Turma turma = it.getFirst();
			while (turma != null) {
				if (turma.getCodProf() == codProf) {
					Disciplina disciplina = new Disciplina(turma.getCodDisc());
					disciplina = listaDisciplinas.retrieve(disciplina);
					System.out.printf("%7d  %-8s  %-14s  %15d\n",
							turma.getCodDisc(), turma.getCodTurma(),
							disciplina.getNomeDisc(), turma.getTotalAlunos());
					turma = it.getNext();
				}
			}

		}

		Keyboard.waitEnter();

	}

	static void excluirProfessor() {
		Keyboard.clrscr();
		int codigo = Keyboard.readInt("Entrar com o codigo: ");
		Professor professor = new Professor(codigo);
		professor = listaProfessores.retrieve(professor);
		if (professor == null) {
			System.out.println("Professor inexistente");
		} else {
			System.out.println("Nome do Professor: " + professor.getNome());
			char resp = Keyboard.readChar("Deseja excluir?");
			if (resp == 's') {
				if (buscaProfessor(codigo)) {
					System.out.println("O Professor não pode ser excluido!");
				} else {
					listaProfessores.remove(professor);
					System.out.println("Professor removido");
				}
			} else {
				System.out.println("Professor nao removido");
			}
		}
		Keyboard.waitEnter();

	}

	static boolean buscaProfessor(int codigo) {
		MyIterator<Turma> it = listaTurmas.iterator();
		Turma turma = it.getFirst();
		while (turma != null) {
			if (turma.getCodProf() == codigo) {
				return true;
			}
			turma = it.getNext();
		}
		return false;
	}

	static void listarProfessor() {
		Keyboard.clrscr();
		if (listaProfessores.isEmpty()) {
			System.out.println("Sem professores cadastrados");
		} else {
			System.out.println("Codigo  Nome do Professor");
			System.out.println("------  -------------------------");
			MyIterator<Professor> it = listaProfessores.iterator();
			Professor professor = it.getFirst();
			while (professor != null) {
				System.out.printf("%6d  %-30s\n", professor.getCodigo(),
						professor.getNome());
				professor = it.getNext();
			}
		}
		Keyboard.waitEnter();

	}

	static void incluirProfessor() {
		char resp;
		Keyboard.clrscr();
		do {
			int codigo = Keyboard.readInt("Entrar com o codigo: ");
			Professor professor = new Professor(codigo);
			if (listaProfessores.contains(professor)) {
				System.out.println("Codigo ja existente");
			} else {
				String nome = Keyboard.readString("Entrar com o nome: ");
				professor.setNome(nome);
				listaProfessores.add(professor);
			}
			resp = Keyboard.readChar("Outro Professor?");
		} while (resp == 's');

	}

	static void listarAlunosMatriculados() {
		Keyboard.clrscr();
		String codTurma = Keyboard.readString("Entrar com a turma:");
		Turma turma = new Turma(codTurma);
		int codDisc = Keyboard.readInt("Entrar com o codigo da disciplina:");
		turma.setCodDisc(codDisc);
		turma = listaTurmas.retrieve(turma);
		if (turma == null) {
			System.out.println("Turma inexistente!");
		} else {
			Disciplina disciplina = new Disciplina(codDisc);
			disciplina = listaDisciplinas.retrieve(disciplina);
			System.out.println("CodDisc: " + turma.getCodDisc() + " CodTurma: "
					+ turma.getCodTurma() + " NomeDisc: "
					+ disciplina.getNomeDisc());
			System.out.println("NMat  Nome do Aluno");
			System.out.println("----  -------------------------------------");
			Aluno[] alunoVet = new Aluno[listaAlunos.size()];
			int i = 0;
			MyIterator<Aluno> it = listaAlunos.iterator();
			Aluno aluno = it.getFirst();
			while (aluno != null) {
				alunoVet[i] = aluno;
				aluno = it.getNext();
				i++;
			}
			Sort.selecao(alunoVet, new ComparaAluno());
			for (int j = 0; j < alunoVet.length; j++) {
				if (alunoVet[j].getTurmas().contains(turma))
					System.out.printf("%4d  %-30s\n", alunoVet[j].getNumMat(),
							alunoVet[j].getNomeAluno());
			}
		}
		Keyboard.waitEnter();
	}

	static void matricularAluno() {
		Keyboard.clrscr();
		char resp;
		do {
			int numMat = Keyboard.readInt("Entrar com o numero de matricula: ");
			Aluno aluno = new Aluno(numMat);
			if (listaAlunos.contains(aluno)) {
				int codDisc = Keyboard
						.readInt("Entrar com o codigo da disciplina: ");
				Disciplina disciplina = new Disciplina(codDisc);
				if (listaDisciplinas.contains(disciplina)) {
					String codTurma = Keyboard
							.readString("Entrar com o codigo da Turma: ");
					Turma turma = new Turma(codTurma);
					turma.setCodDisc(codDisc);
					turma = listaTurmas.retrieve(turma);
					if (turma != null) {
						aluno = listaAlunos.retrieve(aluno);
						if (aluno.getTurmas() != null) {
							if (aluno.getTurmas().contains(turma)) {
								System.out
										.println("Aluno já matriculado na turma");
							} else {
								aluno.matricular(turma);
								turma.getListaAluno().add(aluno);
								turma.aumentarAlunos();
								System.out
										.println("Aluno Matriculado com sucesso");
							}
						} else {
							aluno.matricular(turma);
							System.out.println("Aluno Matriculado com sucesso");
						}
					} else {
						System.out.println("Turma inexistente");
					}
				} else {
					System.out.println("Disciplina inexistente");
				}

			} else {
				System.out.println("Aluno inexistente");
			}
			resp = Keyboard.readChar("Outra inclusao? ");
		} while (resp == 's');
	}

	static boolean buscaTurma(Turma turma) {
		MyIterator<Aluno> it = listaAlunos.iterator();
		Aluno aluno = it.getFirst();
		while (aluno != null) {
			ListaSeqOrd<Turma> listaTurmas = aluno.getTurmas();
			if (listaTurmas != null) {
				if (listaTurmas.contains(turma)) {
					return true;
				}
			}
			aluno = it.getNext();
		}
		return false;
	}

	static void excluirTurmas() {
		Keyboard.clrscr();
		String codTurma = Keyboard.readString("Entrar com o codigo da Turma: ");
		Turma turma = new Turma(codTurma);
		int codDisc = Keyboard.readInt("Entrar com o codigo da Disciplina: ");
		turma.setCodDisc(codDisc);
		turma = listaTurmas.retrieve(turma);
		if (turma == null) {
			System.out.println("Turma inexistente");
		} else {
			System.out.println("Turma: " + turma.getCodTurma());
			char resp = Keyboard.readChar("Deseja excluir?");
			if (resp == 's') {
				if (buscaTurma(turma)) {
					System.out.println("A Turma não pode ser excluida!");
				} else {
					listaTurmas.remove(turma);
					System.out.println("Turma removida");
				}
			} else {
				System.out.println("Turma nao removido");
			}
		}
		Keyboard.waitEnter();
	}

	static void listarTurmas() {
		Keyboard.clrscr();
		if (listaTurmas.isEmpty()) {
			System.out.println("Sem Turmas cadastradas");
		} else {
			System.out.println("Cod Disc  Nome da Disciplina      Turma");
			System.out.println("--------  ----------------------  -----");

			TurmaAux[] turmaAux = new TurmaAux[listaTurmas.size()];
			int i = 0;
			MyIterator<Turma> it = listaTurmas.iterator();
			Turma turma = it.getFirst();
			while (turma != null) {
				Disciplina disciplina = new Disciplina(turma.getCodDisc());
				disciplina = listaDisciplinas.retrieve(disciplina);
				turmaAux[i] = new TurmaAux(turma.getCodDisc(),
						disciplina.getNomeDisc(), turma.getCodTurma());
				turma = it.getNext();
				i++;
			}
			Sort.selecao(turmaAux, new ComparaTurma());
			for (int j = 0; j < turmaAux.length; j++) {
				System.out.printf("%8d  %-22s  %5s\n", turmaAux[j].codDisc,
						turmaAux[j].nomeDisc, turmaAux[j].codTurma);
			}
		}
		Keyboard.waitEnter();
	}

	static void incluirTurma() {
		char resp;
		Keyboard.clrscr();
		do {
			String codTurma = Keyboard
					.readString("Entrar com o codigo da Turma: ");
			Turma turma = new Turma(codTurma);
			int codDisc = Keyboard.readInt("Entrar com o codigo disciplina: ");
			Disciplina disciplina = new Disciplina(codDisc);
			int codProf = Keyboard
					.readInt("Entrar com o codigo do Professor: ");
			Professor professor = new Professor(codProf);
			if (listaDisciplinas.contains(disciplina)) {
				turma.setCodDisc(codDisc);
				if (listaProfessores.contains(professor)) {
					turma.setCodProf(codProf);
					if (!listaTurmas.contains(turma)) {
						listaTurmas.add(turma);
						System.out.println("Turma adicionada!");
					} else {
						System.out.println("Turma existente");
					}
				} else {
					System.out.println("Professor não existente!");
				}
			} else {
				System.out.println("Disciplina não existente!");
			}
			resp = Keyboard.readChar("Outra turma?");
		} while (resp == 's');
	}

	static boolean buscaDisciplina(int codDisc) {
		MyIterator<Turma> it = listaTurmas.iterator();
		Turma turma = it.getFirst();
		while (turma != null) {
			if (turma.getCodDisc() == codDisc) {
				return true;
			}
			turma = it.getNext();
		}
		return false;
	}

	static void excluirDisciplina() {
		Keyboard.clrscr();
		int codDisc = Keyboard.readInt("Entrar com o codigo da disciplina: ");
		Disciplina disciplina = new Disciplina(codDisc);
		disciplina = listaDisciplinas.retrieve(disciplina);
		if (disciplina == null) {
			System.out.println("Disciplina inexistente");
		} else {
			System.out.println("Nome da Disciplina: "
					+ disciplina.getNomeDisc());
			char resp = Keyboard.readChar("Deseja excluir?");
			if (resp == 's') {
				if (buscaDisciplina(codDisc)) {
					System.out.println("A Disciplina não pode ser excluida!");
				} else {
					listaDisciplinas.remove(disciplina);
					System.out.println("Disciplina removido");
				}
			} else {
				System.out.println("Disciplina nao removido");
			}
		}
		Keyboard.waitEnter();
	}

	static void listarDisciplinas() {
		Keyboard.clrscr();
		if (listaDisciplinas.isEmpty()) {
			System.out.println("Sem Disciplinas cadastradas");
		} else {
			System.out.println("CodDisc  Nome da Disciplina");
			System.out.println("-------  -------------------------");
			MyIterator<Disciplina> it = listaDisciplinas.iterator();
			Disciplina disciplina = it.getFirst();
			while (disciplina != null) {
				System.out.printf("%7d  %-30s\n", disciplina.getCodDisc(),
						disciplina.getNomeDisc());
				disciplina = it.getNext();
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

	static void excluirAlunos() {
		Keyboard.clrscr();
		int numMat = Keyboard.readInt("Entrar com o numero de matricula: ");
		Aluno aluno = new Aluno(numMat);
		aluno = listaAlunos.retrieve(aluno);
		if (aluno == null) {
			System.out.println("Aluno inexistente");
		} else {
			System.out.println("Nome do Aluno: " + aluno.getNomeAluno());
			char resp = Keyboard.readChar("Deseja excluir?");
			if (resp == 's') {
				listaAlunos.remove(aluno);
				MyIterator<Turma> it = aluno.getTurmas().iterator();
				Turma turma = it.getFirst();
				while(turma != null){
					listaTurmas.retrieve(turma).retirarAluno(aluno);
					turma = it.getNext();
				}
				System.out.println("Aluno removido");
			} else {
				System.out.println("Aluno nao removido");
			}
		}
		Keyboard.waitEnter();
	}

	static void listarAlunos() {
		Keyboard.clrscr();
		if (listaAlunos.isEmpty()) {
			System.out.println("Sem alunos cadastrados");
		} else {
			System.out.println("NumMat  Nome do Alunor");
			System.out.println("------  -------------------------");
			MyIterator<Aluno> it = listaAlunos.iterator();
			Aluno aluno = it.getFirst();
			while (aluno != null) {
				System.out.printf("%6d  %-30s\n", aluno.getNumMat(),
						aluno.getNomeAluno());
				aluno = it.getNext();
			}
		}
		Keyboard.waitEnter();

	}

	static void incluirAluno() {
		char resp;
		Keyboard.clrscr();
		do {
			int numMat = Keyboard.readInt("Entrar com o numero de matricula: ");
			Aluno aluno = new Aluno(numMat);
			if (listaAlunos.contains(aluno)) {
				System.out.println("Matricula ja existente");
			} else {
				String nome = Keyboard.readString("Entrar com o nome: ");
				aluno.setNomeAluno(nome);
				listaAlunos.add(aluno);
			}
			resp = Keyboard.readChar("Outro Aluno?");
		} while (resp == 's');
	}

	static void gravarArquivo() {
		arqListas.rewrite();
		arqListas.write(listaAlunos);
		arqListas.write(listaDisciplinas);
		arqListas.write(listaTurmas);
		arqListas.write(listaProfessores);
		arqListas.closeFile();

	}

	static void lerArquivo() {
		if (arqListas.reset()) {
			listaAlunos = (ListaSeqOrd<Aluno>) arqListas.read();
			listaDisciplinas = (ListaSeqOrd<Disciplina>) arqListas.read();
			listaTurmas = (ListaSeqOrd<Turma>) arqListas.read();
			listaProfessores = (ListaSeqOrd<Professor>) arqListas.read();
			arqListas.closeFile();
		} else {
			listaAlunos = new ListaSeqOrd<Aluno>(10);
			listaDisciplinas = new ListaSeqOrd<Disciplina>(10);
			listaTurmas = new ListaSeqOrd<Turma>(10);
			listaProfessores = new ListaSeqOrd<Professor>(10);
		}
	}
}
