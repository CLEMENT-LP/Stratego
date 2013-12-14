package main;

import java.util.ArrayList;
/**
 * Crée une bdd avec toutes les images nécessaires au jeu
 * 
 * @author Kraken
 *
 */
public class BDDPions {

	private ArrayList<Unit> listePionsWhite=new ArrayList<Unit>();
	private ArrayList<Unit> listePionsBlack=new ArrayList<Unit>();
	private Unit[][] listePionsBackground=new Unit[10][10];
	
	public BDDPions(){
		createUnit();
	}
	
	public ArrayList<Unit> getListePionsWhite() {
		return listePionsWhite;
	}

	public ArrayList<Unit> getListePionsBlack() {
		return listePionsBlack;
	}

	public Unit[][] getListePionsBackground() {
		return listePionsBackground;
	}

	private void createUnit(){
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				listePionsBackground[i][j]=new Unit("neutre",-1,"images/background/01 ("+(j+i*10+1)+").gif");
			}
		}
		
		
		Unit bmarechal=new Unit("marshal",10,"images/black/marshal.jpg");
		Unit bgeneral=new Unit("general",9,"images/black/general.jpg");
		Unit bcolonel=new Unit("Colonel",8,"images/black/colonel.jpg");
		Unit bmajor=new Unit("major",7,"images/black/major.jpg");
		Unit bcapitaine=new Unit("captain",6,"images/black/captain.jpg");
		Unit blieutenant=new Unit("lieutnant",5,"images/black/leutnant.jpg");
		Unit bsergent=new Unit("sergent",4,"images/black/sergent.jpg");
		Unit beclaireur=new Unit("scout",2,"images/black/scout.jpg");
		Unit bdemineur=new Unit("deminor",3,"images/black/deminor.jpg");
		Unit bespion=new Unit("spy",1,"images/black/spy.jpg");
		Unit bbombe=new Unit("bomb",11,"images/black/bomb.jpg");
		Unit bdrapeau=new Unit("flag",0,"images/black/flag.jpg");

		Unit wmarechal=new Unit("marshal",10,"images/white/marshal.jpg");
		Unit wgeneral=new Unit("general",9,"images/white/general.jpg");
		Unit wcolonel=new Unit("Colonel",8,"images/white/colonel.jpg");
		Unit wmajor=new Unit("major",7,"images/white/major.jpg");
		Unit wcapitaine=new Unit("captain",6,"images/white/captain.jpg");
		Unit wlieutenant=new Unit("lieutnant",5,"images/white/leutnant.jpg");
		Unit wsergent=new Unit("sergent",4,"images/white/sergent.jpg");
		Unit weclaireur=new Unit("scout",2,"images/white/scout.jpg");
		Unit wdemineur=new Unit("deminor",3,"images/white/deminor.jpg");
		Unit wespion=new Unit("spy",1,"images/white/spy.jpg");
		Unit wbombe=new Unit("bomb",11,"images/white/bomb.jpg");
		Unit wdrapeau=new Unit("flag",0,"images/white/flag.jpg");

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
