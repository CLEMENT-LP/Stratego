package main;
/**
 * 
 * @author Kraken
 *
 */
public class Unit {
	private String description;
	private int value;
	private String imagePath;
	public Unit(String description, int value, String imagePath) {
		super();
		this.description = description;
		this.value = value;
		this.imagePath = imagePath;
	}
	public String getDescription() {
		return description;
	}
	public int getValue() {
		return value;
	}
	public String getImagePath() {
		return imagePath;
	}
	
	
	
}
