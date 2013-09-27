package lista08;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Polinomio p = new Polinomio();
		p.insira(1, 4);
		p.insira(1, 3);
		p.insira(3, 2);
		p.insira(4, 1);
		p.insira(5, 0);
		p.imprima();
		Polinomio q = new Polinomio();
		q.insira(1, 2);
		q.insira(2,1);
		q.imprima();
		p.multiplique(q).imprima();

	}

}
