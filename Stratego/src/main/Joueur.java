package main;

public class Joueur {
	private String name;
	private boolean drapeauTombe;
	
	public Joueur(String name) {
		super();
		this.name = name;
		this.drapeauTombe = false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDrapeauTombe() {
		return drapeauTombe;
	}
	public void setDrapeauTombe(boolean drapeauTombe) {
		this.drapeauTombe = drapeauTombe;
	}
	
}
