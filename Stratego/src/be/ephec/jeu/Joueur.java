package be.ephec.jeu;
/**
 * Classe Joueur permet de définir un joueur 
 * (à utiliser pour un développement ultérieur)
 * 
 * @author CLEMENT Louis-Philippe
 * @author OBIANG NDAM Steeves
 * @version 16/12/2013
 */
public class Joueur {
	private String name;
	/**
	 * Crée un joueur définit par son nom
	 * @param name!=null : chaine de caractères
	 */
	public Joueur(String name) {
		super();
		this.name = name;
	}
	/**
	 * 
	 * @return name : chaine de caractères
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name!=null : chaine de caractères
	 */
	public void setName(String name) {
		this.name = name;
	}
}
