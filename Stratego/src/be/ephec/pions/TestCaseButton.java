package be.ephec.pions;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Classe permettant de tester la méthode combatGagne() de la classe CaseButton
 * 
 * @author CLEMENT Louis-Philippe
 * @author OBIANG NDAM Steeves
 * @version 16/12/2013
 */
public class TestCaseButton {

	Pion bmarechal=new Pion("marshal",10,"images/black/marshal.jpg",1);
	Pion bgeneral=new Pion("general",9,"images/black/general.jpg",1);
	Pion bcolonel=new Pion("Colonel",8,"images/black/colonel.jpg",1);
	Pion bmajor=new Pion("major",7,"images/black/major.jpg",1);
	Pion bcapitaine=new Pion("captain",6,"images/black/captain.jpg",1);
	Pion blieutenant=new Pion("lieutnant",5,"images/black/leutnant.jpg",1);
	Pion bsergent=new Pion("sergent",4,"images/black/sergent.jpg",1);
	Pion beclaireur=new Pion("scout",2,"images/black/scout.jpg",1);
	Pion bdemineur=new Pion("deminor",3,"images/black/deminor.jpg",1);
	Pion bespion=new Pion("spy",1,"images/black/spy.jpg",1);
	Pion bbombe=new Pion("bomb",11,"images/black/bomb.jpg",1);
	Pion bdrapeau=new Pion("flag",0,"images/black/flag.jpg",1);

	Pion wmarechal=new Pion("marshal",10,"images/white/marshal.jpg",2);
	Pion wgeneral=new Pion("general",9,"images/white/general.jpg",2);
	Pion wcolonel=new Pion("Colonel",8,"images/white/colonel.jpg",2);
	Pion wmajor=new Pion("major",7,"images/white/major.jpg",2);
	Pion wcapitaine=new Pion("captain",6,"images/white/captain.jpg",2);
	Pion wlieutenant=new Pion("lieutnant",5,"images/white/leutnant.jpg",2);
	Pion wsergent=new Pion("sergent",4,"images/white/sergent.jpg",2);
	Pion weclaireur=new Pion("scout",2,"images/white/scout.jpg",2);
	Pion wdemineur=new Pion("deminor",3,"images/white/deminor.jpg",2);
	Pion wespion=new Pion("spy",1,"images/white/spy.jpg",2);
	Pion wbombe=new Pion("bomb",11,"images/white/bomb.jpg",2);
	Pion wdrapeau=new Pion("flag",0,"images/white/flag.jpg",2);
	
	/**
	 * Teste une attaque entre pions d'une même couleur
	 */
	@Test
	public void testFratricide() {
		System.out.println(CaseButton.combatGagne(bsergent, wsergent));
		assertEquals(CaseButton.combatGagne(bsergent, wsergent),1);
	}
	
	/**
	 * Teste une attaque de drapeau 
	 */
	@Test
	public void testDrapeau() {
		assertEquals(CaseButton.combatGagne(beclaireur, wdrapeau),5);
	}
	
	/**
	 * Teste une attaque espion -> maréchal
	 */
	@Test
	public void testEspion() {
		assertEquals(CaseButton.combatGagne(bespion, wmarechal),3);
	}
	
	/**
	 * Teste un déminage
	 */
	@Test
	public void testDeminage() {
		assertEquals(CaseButton.combatGagne(bdemineur, wbombe),3);
	}
	
	/**
	 * Teste une victoire classique
	 */
	@Test
	public void testNormalWin() {
		assertEquals(CaseButton.combatGagne(bgeneral, wcolonel),3);
	}
	
	/**
	 * Teste une égalité
	 */
	@Test
	public void testEgalite() {
		assertEquals(CaseButton.combatGagne(bmajor, wmajor),1);
	}
	
	/**
	 * Teste une bataille perdue
	 */
	@Test
	public void testPerdu() {
		assertEquals(CaseButton.combatGagne(bcolonel,wgeneral),-1);
	}
}
