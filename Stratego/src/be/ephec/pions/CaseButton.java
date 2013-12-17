package be.ephec.pions;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * Classe CaseButton permet de gérer les boutons qui sont la base des interactions
 * du programme.
 * 
 * @author CLEMENT Louis-Philippe
 * @author OBIANG NDAM Steeves
 * @version 16/12/2013
 */
public class CaseButton extends JButton
{

	private int i; //ligne du bouton
	private int j; //colonne du bouton
	private Pion pion;
	/**
	 * Construit un bouton avec son emplacement de coordonnées(i,j) dans un repère orthogonal
	 * et un pion
	 * 
	 * @param i>0 : entier qui donne la position du bouton selon la coordonnée y  d'un repère othogonal de type (x,y)
	 * @param j>0 : entier qui donne la position du bouton selon la coordonnée x  d'un repère othogonal de type (x,y)
	 * @param pion : un Pion 
	 */
	public CaseButton (int i,int j, Pion pion) 
	{
		super();
		this.i=i;
		this.j=j;
		this.pion=pion;
	}
	/**
	 * 
	 * @return i>=0 : entier qui donne la position du bouton selon la coordonnée y  d'un repère othogonal de type (x,y)
	 */
	public int getI()
	{
		return i;
	}
	/**
	 * 
	 * @return j>=0 : entier qui donne la position du bouton selon la coordonnée x  d'un repère othogonal de type (x,y)
	 */
	public int getJ()
	{
		return j;
	}
	/**
	 * 
	 * @param i>=0 : entier qui donne la position du bouton selon la coordonnée y  d'un repère othogonal de type (x,y)
	 */
	public void setI(int i) {
		this.i = i;
	}
	/**
	 * 
	 * @param j>=0 : entier qui donne la position du bouton selon la coordonnée x  d'un repère othogonal de type (x,y)
	 */
	public void setJ(int j) {
		this.j = j;
	}
	/**
	 * 
	 * @return un pion : Pion
	 */
	public Pion getPion()
	{
		return pion;
	}
	/**
	 * 
	 * @param pion : Pion
	 */
	public void setPion(Pion pion) {
		this.pion = pion;
	}
	/**
	 * @return la description du pion du bouton : chaine de caractères
	 */
	public String toString(){
		return pion.getDescription();

	}
	
	
}
