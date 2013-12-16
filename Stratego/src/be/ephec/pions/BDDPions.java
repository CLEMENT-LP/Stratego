package be.ephec.pions;

import java.util.ArrayList;
/**
 * Classe BDDPions
 * 
 * @author CLEMENT Louis-Philippe
 * @author OBIANG NDAM Steeves
 * @version 16/12/2013
 */
public class BDDPions {

	private ArrayList<Pion> listePionsWhite=new ArrayList<Pion>();
	private ArrayList<Pion> listePionsBlack=new ArrayList<Pion>();
	private Pion[][] listePionsBackground=new Pion[10][10];
	/**
	 * Crée une "base de donnée" sous forme de tableau et listes avec toutes les images nécessaires au jeu
	 */
	public BDDPions(){
		createPion();
	}
	
	public ArrayList<Pion> getListePionsWhite() {
		return listePionsWhite;
	}

	public ArrayList<Pion> getListePionsBlack() {
		return listePionsBlack;
	}

	public Pion[][] getListePionsBackground() {
		return listePionsBackground;
	}

	private void createPion(){
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				listePionsBackground[i][j]=new Pion("neutre",-1,"images/background/01 ("+(j+i*10+1)+").gif",0);
			}
		}
		
		
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

		listePionsBlack.add(bmarechal);
		listePionsBlack.add(bgeneral);

		for(int i=0;i<2;i++)
		{
			listePionsBlack.add(bcolonel);
		}
		for(int i=0;i<3;i++)
		{
			listePionsBlack.add(bmajor);
		}
		for(int i=0;i<4;i++)
		{
			listePionsBlack.add(bcapitaine);
		}
		for(int i=0;i<4;i++)
		{
			listePionsBlack.add(blieutenant);
		}
		for(int i=0;i<4;i++)
		{
			listePionsBlack.add(bsergent);
		}
		for(int i=0;i<5;i++)
		{
			listePionsBlack.add(bdemineur);
		}
		for(int i=0;i<8;i++)
		{
			listePionsBlack.add(beclaireur);
		}
		for(int i=0;i<6;i++)
		{
			listePionsBlack.add(bbombe);
		}
		listePionsBlack.add(bespion);
		listePionsBlack.add(bdrapeau);

		listePionsWhite.add(wmarechal);
		listePionsWhite.add(wgeneral);

		for(int i=0;i<2;i++)
		{
			listePionsWhite.add(wcolonel);
		}
		for(int i=0;i<3;i++)
		{
			listePionsWhite.add(wmajor);
		}
		for(int i=0;i<4;i++)
		{
			listePionsWhite.add(wcapitaine);
		}
		for(int i=0;i<4;i++)
		{
			listePionsWhite.add(wlieutenant);
		}
		for(int i=0;i<4;i++)
		{
			listePionsWhite.add(wsergent);
		}
		for(int i=0;i<5;i++)
		{
			listePionsWhite.add(wdemineur);
		}
		for(int i=0;i<8;i++)
		{
			listePionsWhite.add(weclaireur);
		}
		for(int i=0;i<6;i++)
		{
			listePionsWhite.add(wbombe);
		}
		listePionsWhite.add(wespion);
		listePionsWhite.add(wdrapeau);
	}
}
