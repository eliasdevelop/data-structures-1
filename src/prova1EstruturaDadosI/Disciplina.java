package prova1EstruturaDadosI;

import java.io.Serializable;

public class Disciplina implements Comparable<Disciplina>, Serializable{
	
	private int codDisc;
	private String nomeDisc;
	
	public Disciplina(int codDisc) {
		super();
		this.codDisc = codDisc;
	}

	public int getCodDisc() {
		return codDisc;
	}


	public String getNomeDisc() {
		return nomeDisc;
	}

	public void setNomeDisc(String nomeDisc) {
		this.nomeDisc = nomeDisc;
	}

	@Override
	public int compareTo(Disciplina o) {
		if (codDisc < o.codDisc)
			return -1;
		if (codDisc == o.codDisc)
			return 0;
		return 1;
	}

}
