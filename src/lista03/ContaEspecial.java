package lista03;

import java.io.Serializable;

public class ContaEspecial extends Conta implements Serializable{

	private double limiteConta;

	public ContaEspecial(int numeroConta, double limiteConta) {
		super(numeroConta);
		this.limiteConta = limiteConta;
	}

	@Override
	public boolean saque(double valorDoSaque) {
		if (valorDoSaque > (super.getSaldoConta() + limiteConta)) {
			return false;
		} else {
			saldoConta = saldoConta - valorDoSaque;
			return true;
		}
	}

	public double getLimiteConta() {
		return limiteConta;
	}

	public void setLimiteConta(double limiteConta) {
		this.limiteConta = limiteConta;
	}

}
