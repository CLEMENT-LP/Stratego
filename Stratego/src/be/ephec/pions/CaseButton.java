package be.ephec.pions;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * Classe CaseButton permet de gérer les boutons qui sont la base des interactions
 * du programme. On gère via ces boutons les déplacements de pièces (en réalité des boutons), 
 * les combats et les changements des images de fonds qui doivent accompagner le déplacement des boutons
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
	 * @param i>0 : entier qui donne la position du bouton selon un axe d'un repère othogonal (x,y)
	 * @param j>0 : entier qui donne la position du bouton selon un axe d'un repère othogonal (x,y)
	 * (différent de celui donné par i)
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
	 * @return
	 */
	public int getI()
	{
		return i;
	}
	/**
	 * 
	 * @return
	 */
	public int getJ()
	{
		return j;
	}
	/**
	 * 
	 * @param i
	 */
	public void setI(int i) {
		this.i = i;
	}
	/**
	 * 
	 * @param j
	 */
	public void setJ(int j) {
		this.j = j;
	}
	/**
	 * 
	 * @return
	 */
	public Pion getPion()
	{
		return pion;
	}
	/**
	 * 
	 * @param pion
	 */
	public void setPion(Pion pion) {
		this.pion = pion;
	}
	/**
	 * 
	 */
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
	/**
	 * 
	 * @param attaque
	 * @param defense
	 * @return 5 si la partie est gagnée/ 3 si combat gagné/0 si combat impossible/1 match nul/-1 combat perdu
	 */
	public int combatGagne(Pion attaque, Pion defense){
		if(defense.getValue()==0)return 5;
		else if(attaque.getId()==defense.getId()) return 0;
		else if(attaque.getValue()==1 && defense.getValue()==10) return 3;//combat espion-marechal
		else if(attaque.getValue()==3 && defense.getValue()==11) return 3;//déminage
		else if(attaque.getValue()>defense.getValue())return 3;
		else if(attaque.getValue()==defense.getValue())return 1;
		else return -1;
	}
	/**
	 * 
	 * @param attaque
	 * @param x0
	 * @param y0
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean deplacementAutorise(Pion attaque, int x0, int y0, int x, int y ){
		if(attaque.getDescription()=="bomb") return false;
		else if((y==y0 && Math.abs(x0-x)<2) || (x0==x && Math.abs(y0-y)<2)) return true;
		else return false;
	}
	/**
	 * 
	 * @param cb
	 * @param cb1
	 * @param cb2
	 * @param l
	 * @param c
	 * @param background
	 * @param listePionsBackground
	 * @param neutre
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
