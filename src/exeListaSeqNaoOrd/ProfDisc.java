package exeListaSeqNaoOrd;

import java.io.Serializable;

public class ProfDisc implements Comparable<ProfDisc>, Serializable{
	private int numMat;
	private int codDisc;
	
	public ProfDisc(int numMat, int codDisc) {
	   super();
	   this.numMat = numMat;
	   this.codDisc = codDisc;
   }

	public int getNumMat() {
   	return numMat;
   }

	public int getCodDisc() {
   	return codDisc;
   }

	@Override
   public int compareTo(ProfDisc o) {
		if (numMat < o.numMat)
			return -1;
		if (numMat == o.numMat){
			if (codDisc == o.codDisc)
				return 0;
			if (codDisc < o.codDisc)
				return -1;
			return 1;
		}
		return 1;
   }
	
	
	
}
