package lista02;

import java.io.Serializable;

public class DisciplinaAnual extends Disciplina implements Serializable {

	private double nota1;
	private double nota2;
	private double nota3;
	private double nota4;
	private double nota5;
	private double nota6;
	
	public DisciplinaAnual(int codDisc, String nomeDisc, double nota1,
			double nota2, double nota3, double nota4, double nota5, double nota6) {
		super(codDisc, nomeDisc);
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.nota3 = nota3;
		this.nota4 = nota4;
		this.nota5 = nota5;
		this.nota6 = nota6;
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

	public double getNota4() {
		return nota4;
	}

	public double getNota5() {
		return nota5;
	}

	public double getNota6() {
		return nota6;
	}
	
	@Override
	public double media() {
		return ((((nota1 + nota2 + nota3 + nota4 + nota5) / 5.0) * 0.6) + (nota6 * 0.4));
	}

	@Override
	public String imprimir() {
		return nota1 + " " + nota2 + " " + nota3 + " " + nota4 + " " + nota5 + " " + nota6;
	}

}
