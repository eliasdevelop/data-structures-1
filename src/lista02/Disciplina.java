package lista02;

import java.io.Serializable;

public abstract class Disciplina implements Serializable{

	private int codDisc;
	private String nomeDisc;

	public Disciplina(int codDisc, String nomeDisc) {
		super();
		this.codDisc = codDisc;
		this.nomeDisc = nomeDisc;
	}

	public abstract double media();

	public abstract String imprimir();
	
	public int getCodDisc() {
		return codDisc;
	}

	public String getNomeDisc() {
		return nomeDisc;
	}
	
	
}
