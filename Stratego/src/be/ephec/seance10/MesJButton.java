package be.ephec.seance10;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class MesJButton extends JButton {
	private int ligne;
	private int colonne;
	public MesJButton(int colonne, int ligne){
		this.ligne=ligne;
		this.colonne= colonne;
	}
	public int getLigne() {
		return ligne;
	}
	public int getColonne() {
		return colonne;
	}

}
