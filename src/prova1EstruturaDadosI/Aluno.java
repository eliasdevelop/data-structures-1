package prova1EstruturaDadosI;

import java.io.Serializable;

import listasSequenciais.ListaSeqNaoOrd;
import listasSequenciais.ListaSeqOrd;

public class Aluno implements Comparable<Aluno>, Serializable{
	
	private int numMat;
	private String nomeAluno;
	private ListaSeqOrd<Turma> turmas;
	private double nota1;
	private double nota2;
		

	public Aluno(int numMat) {
		super();
		this.numMat = numMat;
		turmas = new ListaSeqOrd<Turma>(10);
	}

	@Override
	public int compareTo(Aluno o) {
		if (numMat < o.getNumMat())
			return -1;
		if (numMat == o.getNumMat())
			return 0;
		return 1;
	}
	
	public double getNota1() {
		return nota1;
	}
	
	public double getNota2() {
		return nota2;
	}
	
	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}
	
	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}

				
	public int getNumMat() {
		return numMat;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	
	public void matricular(Turma turma){
		turmas.add(turma);
	}
	
	public ListaSeqOrd<Turma> getTurmas() {
		return turmas;
	}
}
