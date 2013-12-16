package be.ephec.pions;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * Classe CaseButton permet de g�rer les boutons qui sont la base des interactions
 * du programme. On g�re les d�placements de pi�ces sur ces boutons, 
 * les combats et les changements des images de fonds qui doivent accompagner le d�placement des pi�ces
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
	 * Construit un bouton avec son emplacement de coordonn�es(i,j) dans un rep�re orthogonal
	 * et un pion
	 * 
	 * @param i>0 : entier qui donne la position du bouton selon la coordonn�e y  d'un rep�re othogonal de type (x,y)
	 * @param j>0 : entier qui donne la position du bouton selon la coordonn�e x  d'un rep�re othogonal de type (x,y)
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
	 * @return i>=0 : entier qui donne la position du bouton selon la coordonn�e y  d'un rep�re othogonal de type (x,y)
	 */
	public int getI()
	{
		return i;
	}
	/**
	 * 
	 * @return j>=0 : entier qui donne la position du bouton selon la coordonn�e x  d'un rep�re othogonal de type (x,y)
	 */
	public int getJ()
	{
		return j;
	}
	/**
	 * 
	 * @param i>=0 : entier qui donne la position du bouton selon la coordonn�e y  d'un rep�re othogonal de type (x,y)
	 */
	public void setI(int i) {
		this.i = i;
	}
	/**
	 * 
	 * @param j>=0 : entier qui donne la position du bouton selon la coordonn�e x  d'un rep�re othogonal de type (x,y)
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
	 * @return la description du pion du bouton : chaine de caract�res
	 */
	public String toString(){
		return pion.getDescription();

	}
	/**
	 * V�rification si un bouton ne contient pas de pion : Pion
	 * 
	 * @return true si le bouton contient un pion, false sinon
	 */
	public boolean isNotVide(){
		if (this.getPion()==null) return false;
		else return true;
	}
	/**
	 * R�sultat d'un combat entre deux pions
	 * 
	 * @param attaque!=null : Pion
	 * @param defense!=null : Pion
	 * @return un entier :  5 si la partie est gagn�e/ 3 si combat gagn�/0 si combat impossible/1 match nul/-1 combat perdu
	 */
	public static int combatGagne(Pion attaque, Pion defense){
		
		if(attaque.getId()==defense.getId()) return 1;
		else if(defense.getValue()==0)return 5;
		else if(attaque.getValue()==1 && defense.getValue()==10) return 3;//combat espion-marechal
		else if(attaque.getValue()==3 && defense.getValue()==11) return 3;//d�minage
		else if(attaque.getValue()>defense.getValue())return 3;
		else if(attaque.getValue()==defense.getValue())return 1;
		else return -1;
	}
	/**
	 * G�re l'autorisation de d�placement d'un pion
	 * 
	 * @param attaque!=null : Pion
	 * @param x0>=0 : entier qui donne la position du pion qui se d�place selon la coordonn�e x  d'un rep�re othogonal de type (x,y) 
	 * @param y0>=0 : entier qui donne la position du pion qui se d�place selon la coordonn�e y  d'un rep�re othogonal de type (x,y) 
	 * @param x>=0 : entier qui donne la position de l'endroit o� veut aller le pion selon la coordonn�e x  d'un rep�re othogonal de type (x,y)
	 * @param y>=0 : entier qui donne la position de l'endroit o� veut aller le pion selon la coordonn�e y  d'un rep�re othogonal de type (x,y)
	 * @return true si le d�placement a lieu en croix autour du pion avec une case de d�placement, false si ce n'est pas le cas o� si le pion est une bombe
	 */
	public boolean deplacementAutorise(Pion attaque, int x0, int y0, int x, int y ){
		if(attaque.getDescription()=="bomb") return false;
		else if((y==y0 && Math.abs(x0-x)<2) || (x0==x && Math.abs(y0-y)<2)) return true;
		else return false;
	}
	/**
	 * G�re le background des diff�rents tableaux
	 * 
	 * @param cb!=null : tableau des boutons du plateau principal
	 * @param cb1!=null : tableau des boutons du plateau contenant les pions du joueur1
	 * @param cb2!=null : tableau des boutons du plateau contenant les pions du joueur2
	 * @param l>=0 : position de l'�l�ment , n� ligne du tableau
	 * @param c>=0 : position de l'�l�ment , n� colonne du tableau
	 * @param background : couleur de fond du tableau
	 * @param listePionsBackground!=null : tableau des "pions" constituant le fond du tableau de jeu
	 * @param neutre!=null : pion neutre
	 */
	public void background(CaseButton[][] cb, CaseButton[][] cb1, CaseButton[][] cb2, int l, int c, Color background, Pion[][] listePionsBackground, Pion neutre ){
		if(background==Color.RED){
			cb1 [l][c].setIcon(new ImageIcon(getClass().getClassLoader().getResource("")));
			cb1[l][c].setPion(null);
		}
		else if(background==Color.BLUE){
			cb2 [l][c].setIcon(new ImageIcon(getClass().getClassLoader().getResource("")));
			cb2 [l][c].setPion(null);
		}
		else{
			cb [l][c].setIcon(new ImageIcon(getClass().getClassLoader().getResource(listePionsBackground[l][c].getImagePath())));
			cb [l][c].setPion(neutre);
		}
	}



}
