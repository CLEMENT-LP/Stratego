package be.ephec.pions;
/**
 * Classe Pion d�finit une pi�ce du jeu
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




}
