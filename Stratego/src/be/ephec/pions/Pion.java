package be.ephec.pions;
/**
 * Classe Pion d�finit une pi�ce du jeu et g�re ses d�placements ainsi que les combats entre pions
 * 
 * @author CLEMENT Louis-Philippe
 * @author OBIANG NDAM Steeves
 * @version 16/12/2013
 */
public class Pion {
	private String description;
	private int value;
	private String imagePath;
	private int id;//� utiliser pour que les pions ne se battent pas entre eux
	/**
	 * Construit un pion avec une valeur de puissance de l'unit�, son nom/grade, un identifiant reli� au joueur auquel il appartient
	 * et un chemin d'acc�s � l'image qui le repr�sente
	 * 
	 * @param description!=null: chaine de caract�re qui donne le nom/grade du pion
	 * @param value : un entier d�finissant la puissance de l'unit� dans le cas d'un combat classique
	 * @param imagePath!=null : une chaine de caract�re/chemin d'acc�s � l'image du pion
	 * @param id : un entier qui le d�finit comme appartenant � un joueur/une arm�e
	 */
	public Pion(String description, int value, String imagePath, int id) {
		super();
		this.description = description;
		this.value = value;
		this.imagePath = imagePath;
		this.id=id;
	}
	/**
	 * 
	 * @return le grade du pion : chaine de caract�res 
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Change le nom/grade de l'unit�
	 * 
	 * @param description!=null: chaine de caract�re qui donne le grade du pion
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 
	 * @return value : un entier d�finissant la puissance de l'unit� dans le cas d'un combat classique
	 */
	public int getValue() {
		return value;
	}
	/**
	 * 
	 * @return imagePath!=null : une chaine de caract�re/chemin d'acc�s � l'image du pion
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * 
	 * @return id : un entier qui d�finit le pion comme appartenant � un joueur/une arm�e
	 */
	public int getId() {
		return id;
	}
	/**
	 * V�rification si un bouton ne contient pas de pion : Pion
	 * 
	 * @return true si le bouton contient un pion, false sinon
	 */
	public boolean isNotVide(){
		if (this==null) return false;
		else return true;
	}
	/**
	 * R�sultat d'un combat entre deux pions
	 * 
	 * @param defense!=null : Pion
	 * @return un entier :  5 si la partie est gagn�e/ 3 si combat gagn�/0 si combat impossible/1 match nul/-1 combat perdu
	 */
	public int combatGagne(Pion defense){
		if(this.getId()==defense.getId()) return 0;
		else if(defense.getValue()==0)return 5;
		else if(this.getValue()==1 && defense.getValue()==10) return 3;//combat espion-marechal
		else if(this.getValue()==3 && defense.getValue()==11) return 3;//d�minage
		else if(this.getValue()>defense.getValue())return 3;
		else if(this.getValue()==defense.getValue())return 1;
		else return -1;
	}
	/**
	 * G�re l'autorisation de d�placement d'un pion
	 * 
	 * @param x0>=0 : entier qui donne la position du pion qui se d�place selon la coordonn�e x  d'un rep�re othogonal de type (x,y) 
	 * @param y0>=0 : entier qui donne la position du pion qui se d�place selon la coordonn�e y  d'un rep�re othogonal de type (x,y) 
	 * @param x>=0 : entier qui donne la position de l'endroit o� veut aller le pion selon la coordonn�e x  d'un rep�re othogonal de type (x,y)
	 * @param y>=0 : entier qui donne la position de l'endroit o� veut aller le pion selon la coordonn�e y  d'un rep�re othogonal de type (x,y)
	 * @return true si le d�placement a lieu en croix autour du pion avec une case de d�placement, false si ce n'est pas le cas o� si le pion est une bombe
	 */
	public boolean deplacementAutorise(int x0, int y0, int x, int y ){
		if(this.getDescription()=="bomb") return false;
		else if(y==y0 && x==x0) return false;
		else if((y==y0 && Math.abs(x0-x)<2) || (x0==x && Math.abs(y0-y)<2)) return true;
		else return false;
	}



}
