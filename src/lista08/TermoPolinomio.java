package lista08;

public class TermoPolinomio implements Comparable<TermoPolinomio>{
	private float coeficiente;
	private int expoente;
	
	public TermoPolinomio(float coeficiente, int expoente) {
		super();
		this.coeficiente = coeficiente;
		this.expoente = expoente;
	}

	public float getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(float coeficiente) {
		this.coeficiente = coeficiente;
	}

	public int getExpoente() {
		return expoente;
	}

	public void setExpoente(int expoente) {
		this.expoente = expoente;
	}
	
	@Override 
	public boolean equals(Object termo){
		return (expoente == ((TermoPolinomio)termo).getExpoente())
;	}

	@Override
	public int compareTo(TermoPolinomio termo) {
		if (expoente > termo.expoente)
			return -1;
		if (expoente == termo.expoente)
			return 0;
		return 1;
	}
}
