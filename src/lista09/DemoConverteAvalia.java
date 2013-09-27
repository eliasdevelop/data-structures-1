package lista09;

import pilha.*;
import utilitarios.*;
/**
 * Programa que converte uma expressão infixa 
 * para posfixa e avalia uma expressão na 
 * notação posfixa. 
 * Os operandos só poderão ser números inteiros 
 * ou reais e entre dois operandos deverá existir pelo
 * menos um espaço em branco.
 * Os operadores aceitos são: +, -, *, / e ^(exponenciação).
 * 
 * @author Raimundo Machado Costa
 * 
 */
public class DemoConverteAvalia {
	
	static boolean erro;
	static int inic;

	static int hierarquia(char operador){
		switch (operador){
			case '(': return 1;
			case '+': return 2;
			case '-': return 2;
			case '*': return 3;
			case '/': return 3;
			case '^': return 4;
			default : return 0;
		}
	}
	
	static boolean precede(char op1, char op2){
		if (op1 == op2)
			if (op1 == '^')
				return false;
			else
				return true;
		
		return (hierarquia(op1) >= hierarquia(op2));
	}	
	
	//Converte infixa para posfixa. Se ocorrer algum erro,
	//o método retorna null
	static String infixaToPosfixa(String expInfixa){
		char op;
		String expPosFixa;
		String expPosFixaAux;
		Pilha<Character> p = new PilhaEnc<Character>();
		expPosFixa = "";
		int j = 0;
		String variavel = "";
		
		for (int i = 0; i < expInfixa.length(); i++) {
			variavel = "";
			j = i;
			op = expInfixa.charAt(i);
			if (op == ' ')
				continue;
			if ("()^+-*/".indexOf(op) == -1){
				if ("1234567890".indexOf(op) == -1){
					if ( (i != (expInfixa.length()-1)) && (expInfixa.charAt((i+1)) != ' ')){
						while (" ()^+-*/1234567890".indexOf(expInfixa.charAt(j)) == -1){
							variavel += expInfixa.charAt(j);
							j++;
						}
					} else {
						variavel = String.valueOf(op);
					}
					op = Keyboard.readChar("Entre com um valor para a variável " + variavel + ":" );
					expInfixa = expInfixa.replace(variavel, String.valueOf(op));
				}
				expPosFixa = expPosFixa + op;
				
			}else{
				switch (op){
					case '(':
						//Processa abre parênteses
						p.empilhe('(');
						break;
					case ')':
						//Processa fecha parênteses
						if (p.isEmpty()){
							return null;
						}
						else{
							char opTopo = p.desempilhe();
							while (opTopo != '('){
								expPosFixa = expPosFixa + " " + opTopo;
								if (p.isEmpty()){
									return null;
								}
								opTopo = p.desempilhe();
							}
						}
						break;
					default:
						//Processa operador
						char opTopo;
						if (!p.isEmpty()){
							opTopo = p.getTopo();
							while (!p.isEmpty() && precede(opTopo, op)){
								opTopo = p.desempilhe();
								expPosFixa = expPosFixa + opTopo;
								if (!p.isEmpty())
									opTopo = p.getTopo();			
							}
						}
						p.empilhe(op);
						expPosFixa = expPosFixa + " ";
				}
			}
		} //for
		
		while (!p.isEmpty()){
			op = p.desempilhe();
			if (op == '('){
				return null;
			}
			expPosFixa = expPosFixa + " " + op;
		}
		return expPosFixa;
	}
	
	static double calcule(char operador, double opnd1, double opnd2){
		switch (operador){
			case '+': return opnd1 + opnd2;
			case '-': return opnd1 - opnd2;
			case '*': return opnd1 * opnd2;
			case '/': return opnd1 / opnd2;
			case '^': return Math.pow(opnd1, opnd2);
		}
		return 0;
	}

	static double obtemValor(String expressao){
		//Método que extrai o número real que se encontra na expressão
		//começando a partir de inic
		String valorString = "";
		char ch;
		while (inic < expressao.length()){
			ch = expressao.charAt(inic);
			if ("0123456789.".indexOf(ch) >= 0){
				valorString = valorString + ch;
				inic++;
			}
			else
				break;
		}
		return Double.valueOf(valorString);
	}
	
	static double avaliePosFixa(String expPosfixa){
		Pilha<Double> p = new PilhaEnc<Double>();
		char ch;
		double opnd1, opnd2, valor;

		inic = 0;
		while (inic < expPosfixa.length()){
			ch = expPosfixa.charAt(inic);
			if (ch == ' '){
				inic++;
				continue;
			}
			if ("^*/+-".indexOf(ch) >= 0){
				if (p.isEmpty()){
					erro = true;
					return 0;
				}
				opnd2 = p.desempilhe();
				if (p.isEmpty()){
					erro = true;
					return 0;
				}
				opnd1 = p.desempilhe();
				valor = calcule(ch, opnd1, opnd2);
				p.empilhe(valor);
				inic++;
			}
			else{
				if ("0123456789".indexOf(ch) >= 0){
					valor = obtemValor(expPosfixa);
					p.empilhe(valor);
				}
				else{
					erro = true;
					return 0;
				}
			}
		} //while
		
		valor = p.desempilhe();
		
		if (!p.isEmpty()){
			erro = true;
			return 0;
		}
		else{
			erro = false;
			return valor;
		}
	}

	public static void main(String[] args) {
		char resp;
		do{
			String expInfixa = Keyboard.readString("Entrar com a expressao na notacao infixa: ");
			String expPosfixa = infixaToPosfixa(expInfixa);
			if (expPosfixa == null){
				System.out.println("Expressao invalida");
			}
			else{
				System.out.println("Expressao posfixa: " + expPosfixa);
				double resultado = avaliePosFixa(expPosfixa);
				if (erro){
					System.out.println("Expressao invalida");
				}
				else{
					System.out.println("Resultado = " + resultado);
				}
			}
			resp = Keyboard.readChar("Outra expressao? ");
		} while (resp == 's');

	}

}
