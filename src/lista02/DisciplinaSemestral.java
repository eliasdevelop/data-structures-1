package lista02;

import java.io.Serializable;

public class DisciplinaSemestral extends Disciplina implements Serializable{

	private double nota1;
	private double nota2;
	
	public DisciplinaSemestral(int codDisc, String nomeDisc, double nota1,
			double nota2) {
		super(codDisc, nomeDisc);
		this.nota1 = nota1;
		this.nota2 = nota2;
	}

	public double getNota1() {
		return nota1;
	}

	public double getNota2() {
		return nota2;
	}
	
	@Override
	public String imprimir(){
		return nota1 + " " + nota2;
	}

	@Override
	public double media() {
		return (((nota1 * 0.4) + (nota2 * 0.6)));
	}

}
