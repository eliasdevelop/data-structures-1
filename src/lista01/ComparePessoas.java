package lista01;

import java.util.Comparator;

public class ComparePessoas implements Comparator<Pessoa> {

	@Override
	public int compare(Pessoa o1, Pessoa o2) {
		return o1.compareTo(o2);
	}

}
