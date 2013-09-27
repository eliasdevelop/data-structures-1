package lista05;

import java.io.Serializable;

public abstract class Conta implements Serializable, Comparable<Conta> {

	protected int numeroConta;
	protected String nomeCliente;
	protected double saldoConta;

	public Conta(int numeroConta) {
		super();
		this.numeroConta = numeroConta;
	}

	public void deposito(double valorDepositado) {
		saldoConta = saldoConta + valorDepositado;
	}

	public abstract boolean saque(double valorDoSaque);

	public int getNumeroConta() {
		return numeroConta;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public double getSaldoConta() {
		return saldoConta;
	}
	

	@Override
	public int compareTo(Conta o) {
		if (numeroConta < o.getNumeroConta())
			return -1;
		if (numeroConta == o.getNumeroConta())
			return 0;
		return 1;
	}
	
}
