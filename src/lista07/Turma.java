package lista07;

import java.io.Serializable;

public class Turma implements Comparable<Turma>, Serializable {

	private int codDisc;
	private String codTurma;

	public Turma(String codTurma) {
		super();
		this.codTurma = codTurma;
	}
	
	public int getCodDisc() {
		return codDisc;
	}

	public String getCodTurma() {
		return codTurma;
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
