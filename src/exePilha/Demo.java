package exePilha;

import pilha.PilhaArray;
import utilitarios.Keyboard;

public class Demo {

	static String decBin(int n) {
		String resultado = "";
		PilhaArray<Integer> p = new PilhaArray<Integer>();
		while (n > 0) {
			p.empilhe(n % 2);
			n = n / 2;
		}

		while (!p.isEmpty()) {
			resultado = resultado + p.desempilhe();
		}
		return resultado;
	}

	static String decHex(int n) {
		String resultado = "";
		PilhaArray<Integer> p = new PilhaArray<Integer>();
		while (n > 0) {
			p.empilhe(n % 16);
			n = n / 16;
		}

		while (!p.isEmpty()) {
			int resto = p.desempilhe();
			switch (resto) {
			case 10:
				resultado = resultado + "A";
				break;
			case 11:
				resultado = resultado + "B";
				break;
			case 12:
				resultado = resultado + "C";
				break;
			case 13:
				resultado = resultado + "D";
				break;
			case 14:
				resultado = resultado + "E";
				break;
			case 15:
				resultado = resultado + "F";
				break;
			default:
				resultado = resultado + resto;
				break;
			}
		}
		return resultado;
	}

	public static void main(String[] args) {
		char resp;
		int n;
		do {
			n = Keyboard.readInt("Entrar com o numero inteiro:");
			System.out.println("Numero binario: " + decBin(n));
			System.out.println("Numero hexadecimal: " + decHex(n));
			resp = Keyboard.readChar("Outro numero? ");
		} while (resp == 's');
	
	}

}
