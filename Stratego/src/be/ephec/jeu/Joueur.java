package be.ephec.jeu;
/**
 * Classe Joueur permet de d�finir un joueur 
 * (� utiliser pour un d�veloppement ult�rieur)
 * 
 * @author CLEMENT Louis-Philippe
 * @author OBIANG NDAM Steeves
 * @version 16/12/2013
 */
public class Joueur {
	private String name;
	/**
	 * Cr�e un joueur d�finit par son nom
	 * @param name!=null : chaine de caract�res
	 */
	public Joueur(String name) {
		super();
		this.name = name;
	}
	/**
	 * 
	 * @return name : chaine de caract�res
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name!=null : chaine de caract�res
	 */
	public void setName(String name) {
		this.name = name;
	}
}
