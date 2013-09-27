package lista05;

import java.io.Serializable;

public class ContaComum extends Conta implements Serializable{

	public ContaComum(int numeroConta) {
		super(numeroConta);
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
