package exeListaSeqNaoOrd;

import java.io.Serializable;

public class Professor implements Comparable<Professor>, Serializable{
	private int numMat;
	private String nome;
	
	public Professor(int numMat, String nome) {
	   super();
	   this.numMat = numMat;
	   this.nome = nome;
   }

	public Professor(int numMat) {
	   super();
	   this.numMat = numMat;
   }
	
	public String getNome() {
   	return nome;
   }

	public void setNome(String nome) {
   	this.nome = nome;
   }

	public int getNumMat() {
   	return numMat;
   }

	@Override
   public int compareTo(Professor o) {
		if (numMat < o.getNumMat())
			return -1;
		if (numMat == o.getNumMat())
			return 0;
		return 1;
   }

}
