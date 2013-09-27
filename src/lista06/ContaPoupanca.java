package lista06;

import java.io.Serializable;

public class ContaPoupanca extends Conta implements Serializable{

	public ContaPoupanca(int numeroConta) {
		super(numeroConta);
	}

	public void rendimentoConta(double juros) {
		saldoConta = super.getSaldoConta() + ((super.getSaldoConta() * juros) / 100);		
	}

	@Override
	public boolean saque(double valorDoSaque) {
		if (valorDoSaque > super.getSaldoConta()) {
			return false;
		} else {
			saldoConta = saldoConta - valorDoSaque;
			return true;

		}
	}

}
