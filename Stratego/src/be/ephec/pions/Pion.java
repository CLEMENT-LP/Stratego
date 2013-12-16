package be.ephec.pions;
/**
 * Classe Pion définit une pièce du jeu
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




}
