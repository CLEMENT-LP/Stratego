package be.ephec.pions;
/**
 * Classe Pion définit une pièce du jeu et gère ses déplacements ainsi que les combats entre pions
 * 
 * @author CLEMENT Louis-Philippe
 * @author OBIANG NDAM Steeves
 * @version 16/12/2013
 */
public class Pion {
	private String description;
	private int value;
	private String imagePath;
	private int id;//à utiliser pour que les pions ne se battent pas entre eux
	/**
	 * Construit un pion avec une valeur de puissance de l'unité, son nom/grade, un identifiant relié au joueur auquel il appartient
	 * et un chemin d'accès à l'image qui le représente
	 * 
	 * @param description!=null: chaine de caractère qui donne le nom/grade du pion
	 * @param value : un entier définissant la puissance de l'unité dans le cas d'un combat classique
	 * @param imagePath!=null : une chaine de caractère/chemin d'accès à l'image du pion
	 * @param id : un entier qui le définit comme appartenant à un joueur/une armée
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
	 * @return le grade du pion : chaine de caractères 
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Change le nom/grade de l'unité
	 * 
	 * @param description!=null: chaine de caractère qui donne le grade du pion
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 
	 * @return value : un entier définissant la puissance de l'unité dans le cas d'un combat classique
	 */
	public int getValue() {
		return value;
	}
	/**
	 * 
	 * @return imagePath!=null : une chaine de caractère/chemin d'accès à l'image du pion
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * 
	 * @return id : un entier qui définit le pion comme appartenant à un joueur/une armée
	 */
	public int getId() {
		return id;
	}
	/**
	 * Vérification si un bouton ne contient pas de pion : Pion
	 * 
	 * @return true si le bouton contient un pion, false sinon
	 */
	public boolean isNotVide(){
		if (this==null) return false;
		else return true;
	}
	/**
	 * Résultat d'un combat entre deux pions
	 * 
	 * @param defense!=null : Pion
	 * @return un entier :  5 si la partie est gagnée/ 3 si combat gagné/0 si combat impossible/1 match nul/-1 combat perdu
	 */
	public int combatGagne(Pion defense){
		if(this.getId()==defense.getId()) return 0;
		else if(defense.getValue()==0)return 5;
		else if(this.getValue()==1 && defense.getValue()==10) return 3;//combat espion-marechal
		else if(this.getValue()==3 && defense.getValue()==11) return 3;//déminage
		else if(this.getValue()>defense.getValue())return 3;
		else if(this.getValue()==defense.getValue())return 1;
		else return -1;
	}
	/**
	 * Gère l'autorisation de déplacement d'un pion
	 * 
	 * @param x0>=0 : entier qui donne la position du pion qui se déplace selon la coordonnée x  d'un repère othogonal de type (x,y) 
	 * @param y0>=0 : entier qui donne la position du pion qui se déplace selon la coordonnée y  d'un repère othogonal de type (x,y) 
	 * @param x>=0 : entier qui donne la position de l'endroit où veut aller le pion selon la coordonnée x  d'un repère othogonal de type (x,y)
	 * @param y>=0 : entier qui donne la position de l'endroit où veut aller le pion selon la coordonnée y  d'un repère othogonal de type (x,y)
	 * @return true si le déplacement a lieu en croix autour du pion avec une case de déplacement, false si ce n'est pas le cas où si le pion est une bombe
	 */
	public boolean deplacementAutorise(int x0, int y0, int x, int y ){
		if(this.getDescription()=="bomb") return false;
		else if(y==y0 && x==x0) return false;
		else if((y==y0 && Math.abs(x0-x)<2) || (x0==x && Math.abs(y0-y)<2)) return true;
		else return false;
	}



}
