package lista08;

import utilitarios.MyIterator;
import listasLinearesEncadeadas.ListaEncOrd;

public class Polinomio {
	
	private ListaEncOrd<TermoPolinomio> listaTermos;

	public Polinomio() {
		listaTermos = new ListaEncOrd<TermoPolinomio>();
	}

	public void insira(float coeficiente, int expoente) {
		TermoPolinomio termo = new TermoPolinomio(coeficiente, expoente);
		TermoPolinomio termoExist = listaTermos.retrieve(termo);
		if (termoExist != null) {
			if (termoExist.compareTo(termo) == 0) {
				termoExist.setCoeficiente(termoExist.getCoeficiente()
						+ coeficiente);
				if (termoExist.getCoeficiente() == 0) {
					listaTermos.remove(termoExist);
				}
			}
		} else {
			listaTermos.add(termo);
		}
	}

	public Polinomio[] divida(Polinomio q) {
		return null;
	}

	public Polinomio subtraia(Polinomio q) {
		Polinomio r = new Polinomio();
		MyIterator<TermoPolinomio> itP = listaTermos.iterator();
		MyIterator<TermoPolinomio> itQ = q.listaTermos.iterator();
		TermoPolinomio termoP = itP.getFirst();
		TermoPolinomio termoQ = itQ.getFirst();
		while ((termoQ != null) || (termoP != null)) {
			if ((termoP != null) && (termoQ != null))
				if (termoP.getExpoente() > termoQ.getExpoente()) {
					r.insira(termoP.getCoeficiente(), termoP.getExpoente());
					termoP = itP.getNext();
				} else if (termoQ.getExpoente() > termoP.getExpoente()) {
					r.insira(termoQ.getCoeficiente(), termoQ.getExpoente());
					termoQ = itQ.getNext();
				} else {
					if (termoQ.getCoeficiente() - termoP.getCoeficiente() != 0)
						r.insira(
								termoQ.getCoeficiente()
										- termoP.getCoeficiente(),
								termoQ.getExpoente());
					termoP = itP.getNext();
					termoQ = itQ.getNext();
				}
			else if (termoP != null)
				while (termoP != null) {
					r.insira(termoP.getCoeficiente(), termoP.getExpoente());
					termoP = itP.getNext();
				}
			else if (termoQ != null)
				while (termoQ != null) {
					r.insira(termoQ.getCoeficiente(), termoQ.getExpoente());
					termoQ = itQ.getNext();
				}
		}
		return r;
	}

	public Polinomio some(Polinomio q) {
		Polinomio r = new Polinomio();
		MyIterator<TermoPolinomio> itP = listaTermos.iterator();
		MyIterator<TermoPolinomio> itQ = q.listaTermos.iterator();
		TermoPolinomio termoP = itP.getFirst();
		TermoPolinomio termoQ = itQ.getFirst();
		while ((termoQ != null) || (termoP != null)) {
			if ((termoP != null) && (termoQ != null))
				if (termoP.getExpoente() > termoQ.getExpoente()) {
					r.insira(termoP.getCoeficiente(), termoP.getExpoente());
					termoP = itP.getNext();
				} else if (termoQ.getExpoente() > termoP.getExpoente()) {
					r.insira(termoQ.getCoeficiente(), termoQ.getExpoente());
					termoQ = itQ.getNext();
				} else {
					if (termoQ.getCoeficiente() + termoP.getCoeficiente() != 0)
						r.insira(
								termoQ.getCoeficiente()
										+ termoP.getCoeficiente(),
								termoQ.getExpoente());
					termoP = itP.getNext();
					termoQ = itQ.getNext();
				}
			else if (termoP != null)
				while (termoP != null) {
					r.insira(termoP.getCoeficiente(), termoP.getExpoente());
					termoP = itP.getNext();
				}
			else if (termoQ != null)
				while (termoQ != null) {
					r.insira(termoQ.getCoeficiente(), termoQ.getExpoente());
					termoQ = itQ.getNext();
				}
		}
		return r;
	}

	public Polinomio multiplique(Polinomio q) {
		Polinomio r = new Polinomio();
		MyIterator<TermoPolinomio> itP = listaTermos.iterator();
		MyIterator<TermoPolinomio> itQ = q.listaTermos.iterator();
		TermoPolinomio termoP = itP.getFirst();
		while (termoP != null) {
			TermoPolinomio termoQ = itQ.getFirst();
			while (termoQ != null) {
				r.insira(termoP.getCoeficiente() * termoQ.getCoeficiente(),
						termoP.getExpoente() + termoQ.getExpoente());
				termoQ = itQ.getNext();
			}
			termoP = itP.getNext();
		}
		return r;
	}

	public void imprima() {
		MyIterator<TermoPolinomio> itP = listaTermos.iterator();
		TermoPolinomio termoIt = itP.getFirst();
		String polinomio = "";
		while (termoIt != null){
			polinomio += termoIt.getCoeficiente();
			if (termoIt.getExpoente() > 1){
				polinomio += "x^" + termoIt.getExpoente();
			} else if (termoIt.getExpoente() == 1){
				polinomio += "x";
			}
			termoIt = itP.getNext();
			if ((termoIt != null)){
				if (termoIt.getCoeficiente() > 0)
					polinomio += " +";
			}
		}
		System.out.println(polinomio);
	}
}
