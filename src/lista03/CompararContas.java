package lista03;

import java.util.Comparator;

public class CompararContas implements Comparator<Conta> {

	@Override
	public int compare(Conta o1, Conta o2) {
		return o1.compareTo(o2);
	}

}
