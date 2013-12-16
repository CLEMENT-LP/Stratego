package main;
/**
 * 
 * @author Kraken
 *
 */
public class Pion {
	private String description;
	private int value;
	private String imagePath;
	private int id;//à utiliser pour que les pions ne se battent pas entre eux
	public Pion(String description, int value, String imagePath, int id) {
		super();
		this.description = description;
		this.value = value;
		this.imagePath = imagePath;
		this.id=id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getValue() {
		return value;
	}
	public String getImagePath() {
		return imagePath;
	}
	public int getId() {
		return id;
	}
	
	
	
	
}
