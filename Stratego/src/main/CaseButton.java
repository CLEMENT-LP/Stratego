package main;

import javax.swing.JButton;

public class CaseButton extends JButton
{
	private int i; //ligne du bouton
	private int j; //colonne du bouton
	private Unit pion;
	
	public CaseButton (int i,int j, Unit pion) 
	{
		super();
		this.i=i;
		this.j=j;
		this.pion=pion;
	}

	public int getI()
	{
		return i;
	}

	public int getJ()
	{
		return j;
	}
	public Unit getPion()
	{
		return pion;
	}
	public void setPion(Unit pion) {
		this.pion = pion;
	}
	public String toString(){
		return pion.getDescription();
		
	}
	/**
	 * Vérification contient Unit
	 * @return true si la case contient pas de pion sinon retourne false
	 */
	public boolean isNotVide(){
		if (this.getPion()==null) return false;
		else return true;
	}
	public void videPion(){
		this.pion=null;
	}
	public void placePion(Unit pion){
		setPion(pion);
	}

	

	
}
