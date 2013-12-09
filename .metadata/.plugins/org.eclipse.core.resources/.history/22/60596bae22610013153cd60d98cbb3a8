package main;
/**
 * Code du plateau
 * 
 * @author Kraken
 *
 */
public class Plateau {
	
	private final int size = 10; // cste representant la longueur du tableau
	    private int [][] plateau = new int [size][size]; // la matrice utilisee pour stocker l'information
	    private final int BLANC = 0; 
	    private final int NOIR = 1; // cstes de couleur pour le blanc et le noir (respectivement 0 & 1)
	    private boolean NotEmpty; // case remplie? (true = case noire, false = case blanche)
	    
	    /**
	     * @pre 0<=i< nombre de lignes du code barres 2D
	     *      0<=j< nombre de colonnes du code barres
	     * @post met le carre en ligne=i, colonne=j du code barre noir
	     *       si b==true et blanc sinon
	     */

	    public void set (int i, int j, boolean b)
	    {
	        if (b==true) { plateau [i][j] = NOIR; }
	        if (b==false) { plateau [i][j] = BLANC; }
	    }
	    
	    /**
	     * @pre 0<=i< nombre de lignes du plateau
	     *      0<=j< nombre de colonnes du plateau
	     * @post retourne true si le carre en ligne=i, colonne=j du code-
	     *      barre est rempli, false sinon
	     */

	    public boolean get (int i, int j)
	    {
	        if (plateau [i][j] == NOIR) { NotEmpty = true; }
	        if (plateau [i][j] == BLANC) { NotEmpty = false; }
	        return NotEmpty;
	    }
	    /**
	     * @pre - 
	     * @post retourne la taille du plateau/ nombre de cases en longueur(=largeur)
	     */
	    public int size()
	    {
	        return plateau.length;
	    }
}
