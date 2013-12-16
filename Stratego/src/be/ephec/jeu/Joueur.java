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
	
	public Joueur(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
