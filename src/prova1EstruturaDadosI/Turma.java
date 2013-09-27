package prova1EstruturaDadosI;

import java.io.Serializable;

import listasSequenciais.ListaSeqOrd;

public class Turma implements Comparable<Turma>, Serializable {

	private int codDisc;
	private String codTurma;
	private int codProf;
	private int totalAlunos;
	private ListaSeqOrd<Aluno> listaAluno;
	

	public Turma(String codTurma) {
		super();
		this.codTurma = codTurma;
		listaAluno = new ListaSeqOrd<Aluno>(10);
	}
	
	public ListaSeqOrd<Aluno> getListaAluno(){
		return listaAluno;
	}
	
	public void aumentarAlunos(){
		totalAlunos++;
	}
	
	public void retirarAluno(Aluno aluno){
		listaAluno.remove(aluno);
		totalAlunos--;
	}
	
	public int getTotalAlunos(){
		return totalAlunos;
	}
	
	public int getCodProf(){
		return codProf;
	}
	
	public int getCodDisc() {
		return codDisc;
	}

	public String getCodTurma() {
		return codTurma;
	}
	
	public void setCodProf(int codProf){
		this.codProf = codProf;
	}

	public void setCodDisc(int codDisc) {
		this.codDisc = codDisc;
	}

	@Override
	public int compareTo(Turma o) {
		if (codTurma.compareToIgnoreCase(o.codTurma) < 0)
			return -1;
		if (codTurma.compareToIgnoreCase(o.codTurma) == 0){
			if (codDisc == o.codDisc)
				return 0;
			if (codDisc < o.codDisc)
				return -1;
			return 1;
		}
		return 1;
	}

}
