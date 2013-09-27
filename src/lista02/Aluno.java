package lista02;

import java.io.Serializable;

public class Aluno implements Serializable{

	private int nMat;
	private String nomeAluno;
	private int codCurso;
	private Disciplina discMatriculada;

	public Aluno(int nMat, String nomeAluno, int codCurso) {
		super();
		this.nMat = nMat;
		this.nomeAluno = nomeAluno;
		this.codCurso = codCurso;
	}

	public boolean insiraDisciplinaSemestral(int codDisc, String nomeDisc,
			double nota1, double nota2) {
		discMatriculada = new DisciplinaSemestral(codDisc, nomeDisc, nota1,
				nota2);
		return true;
	}

	public boolean insiraDisciplinatTrimestral(int codDisc, String nomeDisc,
			double nota1, double nota2, double nota3) {
		discMatriculada = new DisciplinaTrimestral(codDisc, nomeDisc, nota1,
				nota2, nota3);
		return true;
	}

	public boolean insiraDisciplinaAnual(int codDisc, String nomeDisc,
			double nota1, double nota2, double nota3, double nota4, double nota5, double nota6) {
		discMatriculada = new DisciplinaAnual(codDisc, nomeDisc, nota1,
				nota2, nota3, nota4, nota5, nota6);
		return true;
	}
	
	public Disciplina getDisciplinaMatriculada(){
		return discMatriculada;
	}

	public int getNMat() {
		return nMat;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public int getCodCurso() {
		return codCurso;
	}

}
