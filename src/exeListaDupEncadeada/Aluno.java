package exeListaDupEncadeada;


public class Aluno {
	private int numMat;
	private String nome;
	
	public Aluno(int numMat, String nome) {
	   super();
	   this.numMat = numMat;
	   this.nome = nome;
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
	
}
