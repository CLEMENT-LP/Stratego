package main;

import javax.swing.JButton;

public class CaseButton extends JButton
{
	private int i; //ligne du bouton
	private int j; //colonne du bouton
	private Pion pion;
	
	public CaseButton (int i,int j, Pion pion) 
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
	public void setI(int i) {
		this.i = i;
	}

	public void setJ(int j) {
		this.j = j;
	}
	public Pion getPion()
	{
		return pion;
	}
	public void setPion(Pion pion) {
		this.pion = pion;
	}
	public String toString(){
		return pion.getDescription();
		
	}
	/**
	 * Vérification contient Pion
	 * @return true si la case contient pas de pion sinon retourne false
	 */
	public boolean isNotVide(){
		if (this.getPion()==null) return false;
		else return true;
	}
	/*
	public void videPion(){
		this.pion=null;
	}
	
	public void placePion(Pion pion){
		setPion(pion);
	}
	*/
	public boolean combatGagne(Pion attaque, Pion defense){
		if(attaque.getValue()>defense.getValue())return true;
		else return false;
	}
	public boolean deplacementAutorise(Pion attaque, int x0, int y0, int x, int y ){
		if(attaque.getImagePath()=="bomb")return false;
		else if((y==y0 && Math.abs(x0-x)<2) || (x0==x && Math.abs(y0-y)<2)) return true;
		else return false;
	}

	
}
