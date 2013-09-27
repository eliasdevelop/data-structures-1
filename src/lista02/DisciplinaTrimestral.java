package lista02;

import java.io.Serializable;

public class DisciplinaTrimestral extends Disciplina implements Serializable{
	
	private double nota1;
	private double nota2;
	private double nota3;
	
	public DisciplinaTrimestral(int codDisc, String nomeDisc, double nota1,
			double nota2, double nota3) {
		super(codDisc, nomeDisc);
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.nota3 = nota3;
	}

	public double getNota1() {
		return nota1;
	}

	public double getNota2() {
		return nota2;
	}
	
	public double getNota3() {
		return nota3;
	}
	
	@Override
	public String imprimir(){
		return nota1 + " " + nota2 + " " + nota3;
	}

	@Override
	public double media() {
		return ((nota1 + nota2 + nota3) / 3.0);
	}

}
