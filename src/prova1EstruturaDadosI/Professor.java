package prova1EstruturaDadosI;

import java.io.Serializable;

public class Professor implements Comparable<Professor>, Serializable{
	
	private int codigo;
	private String nome;
	
   public Professor(int codigo) {
	   super();
	   this.codigo = codigo;
   }
	
	public String getNome() {
   	return nome;
   }

	public void setNome(String nome) {
   	this.nome = nome;
   }

	public int getCodigo() {
   	return codigo;
   }

	@Override
   public int compareTo(Professor o) {
		if (codigo < o.getCodigo())
			return -1;
		if (codigo == o.getCodigo())
			return 0;
		return 1;
   }

}
