package lista02;

import utilitarios.*;

public class Principal{

	static Aluno[] alunos = new Aluno[20];
	static int totAlunos = 0;
	static ObjectFile arqAlunos = new ObjectFile("alunos.dat");

	public static void main(String[] args) {
		int opcao = 1;
		lerArquivo();
		while (opcao < 3) {
			Keyboard.clrscr();
			opcao = Keyboard.menu("Cadastrar Alunos/Listar Alunos/"
					+ "Terminar");
			switch (opcao) {
			case 1:
				cadastrarAlunos();
				break;
			case 2:
				listarAlunos();
				break;
			case 3:
				System.out.println("Programa Fechado");
				break;
			}
		}
	gravarArquivo();
	}
	
	static void lerArquivo() {
		if (arqAlunos.reset()) {
			Aluno aluno = (Aluno) arqAlunos.read();
			while (aluno != null) {
				if (totAlunos == alunos.length) {
					redimensione();
				}
				alunos[totAlunos] = aluno;
				totAlunos++;
				aluno = (Aluno) arqAlunos.read();
			}
			arqAlunos.closeFile();
		}
	}
	
	static void gravarArquivo(){
		arqAlunos.rewrite();
		for (int i = 0; i < totAlunos; i++) {
			arqAlunos.write(alunos[i]);
		}
		arqAlunos.closeFile();
	}

	static void cadastrarAlunos() {
		char resp;
		boolean cadastrou = false;
		do {
			Keyboard.clrscr();
			if (totAlunos == alunos.length) {
				redimensione();
			}
			int nMat = Keyboard.readInt("Entrar com o Matricula: ");
			String nome = Keyboard.readString("Entrar com o nome: ");
			int codCurso = Keyboard.readInt("Entrar com o codigo do Curso: ");
			alunos[totAlunos] = new Aluno(nMat, nome, codCurso);
			while (cadastrou == false) {
				int tipoMat = Keyboard
						.readInt("Selecione o tipo da materia:\n1 - Materia Semestral\n2 - Materia Trimestral\n3 - Materia Anual:");
				switch (tipoMat) {
				case 1: {
					int codDisc = Keyboard
							.readInt("Entrar com o codigo da Disciplina: ");
					String nomeDisc = Keyboard
							.readString("Entrar com o nome da Disciplina: ");
					double nota1 = Keyboard.readDouble("Entrar com a nota 1: ");
					double nota2 = Keyboard.readDouble("Entrar com a nota 2: ");
					if (alunos[totAlunos].insiraDisciplinaSemestral(codDisc,
							nomeDisc, nota1, nota2)) {
						System.out.println("Disciplina inserida");
						cadastrou = true;
					} else {
						System.out.println("Disciplina nao inserida");
					}
					break;
				}
				case 2: {
					int codDisc = Keyboard
							.readInt("Entrar com o codigo da Disciplina: ");
					String nomeDisc = Keyboard
							.readString("Entrar com o nome da Disciplina: ");
					double nota1 = Keyboard.readDouble("Entrar com a nota 1: ");
					double nota2 = Keyboard.readDouble("Entrar com a nota 2: ");
					double nota3 = Keyboard.readDouble("Entrar com a nota 3: ");
					if (alunos[totAlunos].insiraDisciplinatTrimestral(codDisc,
							nomeDisc, nota1, nota2, nota3)) {
						System.out.println("Disciplina inserida");
						cadastrou = true;
					} else {
						System.out.println("Disciplina nao inserida");
					}
					break;
				}
				case 3: {
					int codDisc = Keyboard
							.readInt("Entrar com o codigo da Disciplina: ");
					String nomeDisc = Keyboard
							.readString("Entrar com o nome da Disciplina: ");
					double nota1 = Keyboard.readDouble("Entrar com a nota 1: ");
					double nota2 = Keyboard.readDouble("Entrar com a nota 2: ");
					double nota3 = Keyboard.readDouble("Entrar com a nota 3: ");
					double nota4 = Keyboard.readDouble("Entrar com a nota 4: ");
					double nota5 = Keyboard.readDouble("Entrar com a nota 5: ");
					double nota6 = Keyboard.readDouble("Entrar com a nota 6: ");
					if (alunos[totAlunos].insiraDisciplinaAnual(codDisc,
							nomeDisc, nota1, nota2, nota3, nota4, nota5, nota6)) {
						System.out.println("Disciplina inserida");
						cadastrou = true;
					} else {
						System.out.println("Disciplina nao inserida");
					}
					break;

				}
				default: {
					System.out.println("Opção invalida");
					break;
				}
				}
			}

			totAlunos++;
			resp = Keyboard.readChar("Outra pessoas(s/n)?");

		} while (resp == 's');

	}

	static void listarAlunos() {
		Keyboard.clrscr();
		System.out
				.println("NMat  Nome do Aluno       CodDisc  Nome da Disciplina             Notas                          Media");
		System.out
				.println("----  ------------------  -------  -----------------------------  -----------------------------  -----");
		for (int i = 0; i < totAlunos; i++) {
			System.out.printf("%4d  %-18s  %-7d  %-29s  %-31s  %.1f\n",
					alunos[i].getNMat(), alunos[i].getNomeAluno(), (alunos[i].getDisciplinaMatriculada().getCodDisc()),
					(alunos[i].getDisciplinaMatriculada().getNomeDisc()),(alunos[i].getDisciplinaMatriculada().imprimir()),
					(alunos[i].getDisciplinaMatriculada().media()));
		}
		Keyboard.waitEnter();
	}
	
	static void redimensione(){
		Aluno[] alunosRed = new Aluno[alunos.length + 20];
		for (int i = 0; i < alunos.length; i++) {
			alunosRed[i] = alunos[i];
		}
		alunos = alunosRed;
	}

}
