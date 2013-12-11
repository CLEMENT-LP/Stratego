package main;


import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
/**
 * Interface graphique du plateau, utilise classe Plateau pour définir le visuel
 * +comportement des différents éléments
 *     
 * @author LP
 *
 */
public class PlateauGUI extends JFrame
{

	private Plateau plateau;
	//!\\ ajouter tableaux rouge et bleu de départ
	private JLabel result;
	private caseButton [][] cb;
	private caseButton [][] cb1;
	private caseButton [][] cb2;
	private caseButton pionCurrent;
	private ArrayList<Unit> listePionsWhite=new ArrayList<Unit>();
	private ArrayList<Unit> listePionsBlack=new ArrayList<Unit>();
	private boolean selection=true; //unité en sélection?
	//private boolean select=true;
	private boolean placerPions=false;
	private boolean start=false;
	
	
	public void createUnit(){
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
	public PlateauGUI (Plateau plateau)
	{
		createUnit();//Liste des pions
		this.setTitle("Stratego");//titre fenêtre
		//this.setLocation(200,200);//positionnement lors du lancement depuis bord supérieur gauche
		this.plateau = plateau;//voir classe Plateau
		int size=plateau.size();
		Box hbAll=Box.createHorizontalBox();//SCHEMA A FOURNIR (modulable pour ajout ultérieurs)
		Box vbPlateau=Box.createVerticalBox();
		Box vb1 = Box.createVerticalBox(); // boite pour les boutons

		JPanel jp = new JPanel (new GridLayout(size, size)); // panneau pour le dessin de la matrice
		JPanel jpP1 = new JPanel (new GridLayout(5, 8)); //pions P1
		JPanel jpP2 = new JPanel (new GridLayout(5, 8));//pions P2

		JButton launch = new JButton("START");
		//launch.addActionListener (new LaunchButtonListener()); // bouton "Launch" avec écouteur d'action
		//CONTENU des boutons des Grid (exemple basique à remplacer par les objets UNIT)
		cb1 = new caseButton [5][8]; 
		int compteur=0;
		for(int i=0; i < 5; i++)
		{
			for(int j=0; j < 8; j++)
			{	
				Unit pion=listePionsWhite.get(compteur);
				cb1 [i][j] = new caseButton(i,j,pion);
				cb1 [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(pion.getImagePath())));
				cb1 [i][j].setBackground (Color.RED);
				cb1 [i][j].setPreferredSize (new Dimension (50, 50));             // on cree les boutons et on les met tous blancs
				jpP1.add (cb1[i][j]);
				//cb1 [i][j].addActionListener (new ColorButtonListener());
				cb1[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						MoveActionPerformed(evt);
					}
				});
				compteur++;
				//Unit temp=cb1[i][j].getPion();
			}
		}
		compteur=0;
		cb2 = new caseButton [5][8]; 
		for(int i=0; i < 5; i++)
		{
			for(int j=0; j < 8; j++)
			{
				Unit pion=listePionsBlack.get(compteur);
				cb2 [i][j] = new caseButton(i,j,pion);
				cb2 [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(pion.getImagePath())));
				cb2 [i][j].setBackground (Color.BLUE);
				cb2 [i][j].setPreferredSize (new Dimension (50, 50));             // on cree les boutons et on les met tous blancs
				jpP2.add (cb2[i][j]);
				cb2[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						MoveActionPerformed(evt);
					}
				});
				//cb2 [i][j].addActionListener (new ColorButtonListener());
				//cb2[i][j].addActionListener(new MoveUnitListener());
				/*
				cb2[i][j].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent evt) {
						MoveMousePressed(evt);
					}
				});*/
				compteur++;
			}
		}
		cb = new caseButton [size][size]; 
		for(int i=0; i < size; i++)
		{
			for(int j=0; j < size; j++)
			{
				cb [i][j] = new caseButton(i,j,null);
				cb [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/background/01 ("+(j+i*10+1)+").gif")));//AJOUT TEMP
				cb [i][j].setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));//AJOUT TEMP
				//cb [i][j].setBackground (Color.WHITE);
				cb [i][j].setPreferredSize (new Dimension (50, 50));             // on cree les boutons et on les met tous blancs
				jp.add (cb[i][j]);
				
				cb[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						DeposeActionPerformed(evt);
					}
				});
			
				if(selection){
				
				cb[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						MoveActionPerformed(evt);
					}
				});
				}
				
				
			
				
			}
		}

		//ADD element to the box
		//vb1.add (vb1.createVerticalGlue());//utile si il y a pls boutons
		vb1.add (launch);        

		vbPlateau.add(jpP1);
		vbPlateau.add(vb1);
		vbPlateau.add(jpP2);
		hbAll.add(jp);
		hbAll.add(vbPlateau);

		getContentPane().add (hbAll); // on ajoute le tout au contenu et on positionne 
		this.setResizable(false);//désactive redimensionnement
		//hbAll.setPreferredSize(new java.awt.Dimension(830, 420));
		//OBLIGATOIRE
		this.pack();
		this.setVisible(true); // on rend visible la fenetre
		this.setLocationRelativeTo(null);//centrer la fenêtre
	}
	
	public caseButton[][] getCb() {
		return cb;
	}
	public void setCb(caseButton[][] cb) {
		this.cb = cb;
	}
	public caseButton[][] getCb1() {
		return cb1;
	}
	public void setCb1(caseButton[][] cb1) {
		this.cb1 = cb1;
	}
	public caseButton[][] getCb2() {
		return cb2;
	}
	public void setCb2(caseButton[][] cb2) {
		this.cb2 = cb2;
	}

	
	private void MoveActionPerformed(ActionEvent evt) {
		//System.out.println("jButton1.actionPerformed, event="+evt);
		//TODO add your code for jButton1.actionPerformed
		if(((caseButton)evt.getSource()).isVide()){
			pionCurrent= ((caseButton)evt.getSource());
			//(caseButton)evt.;
			//System.out.println(pionCurrent);
			
			//System.out.println("true attendu"+selection);
			((caseButton)evt.getSource()).setBackground (Color.WHITE);
			//((caseButton)evt.getSource()).setPion(null);
			
		}
		
	}
	private void DeposeActionPerformed(ActionEvent evt) {
		if(pionCurrent!=null){
			((caseButton)evt.getSource()).setPion(pionCurrent.getPion());
			((caseButton)evt.getSource()).setIcon(new ImageIcon(getClass().getClassLoader().getResource(pionCurrent.getPion().getImagePath())));
			
			}
			//System.out.println("false attendu  "+selection);	
		pionCurrent=null;
			//selection=false;
	}
	
	
	
	
		private class caseButton extends JButton
		{
			private int i; //ligne du bouton
			private int j; //colonne du bouton
			private Unit pion;
			
			public caseButton (int i,int j, Unit pion) 
			{
				super();
				this.i=i;
				this.j=j;
				this.pion=pion;
			}

			public int getI()
			{
				return i;
			}

			public int getJ()
			{
				return j;
			}
			public Unit getPion()
			{
				return pion;
			}
			public void setPion(Unit pion) {
				this.pion = pion;
			}
			public String toString(){
				return pion.getDescription();
				
			}
			/**
			 * Vérification contient Unit
			 * @return true si la case contient pas de pion sinon retourne false
			 */
			public boolean isVide(){
				if (this.getPion()==null) return false;
				else return true;
			}
			public void videPion(){
				this.pion=null;
			}
			public void placePion(Unit pion){
				setPion(pion);
			}

			

			
		}
}   


