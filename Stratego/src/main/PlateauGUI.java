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
 * @author lp
 *
 */
public class PlateauGUI extends JFrame 
{

	private Plateau plateau;
	//!\\ ajouter tableaux rouge et bleu de départ
	private JLabel result;
	private CaseButton [][] cb;
	private CaseButton [][] cb1;
	private CaseButton [][] cb2;
	private CaseButton pionCurrent;
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
		cb1 = new CaseButton [5][8]; 
		int compteur=0;
		for(int i=0; i < 5; i++)
		{
			for(int j=0; j < 8; j++)
			{	
				Unit pion=listePionsWhite.get(compteur);
				cb1 [i][j] = new CaseButton(i,j,pion);
				cb1 [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(pion.getImagePath())));
				cb1 [i][j].setBackground (Color.RED);
				cb1 [i][j].setPreferredSize (new Dimension (50, 50));             // on cree les boutons et on les met tous blancs
				jpP1.add (cb1[i][j]);
				
				cb1[i][j].addMouseListener(new MouseAdapter() {
					public void mouseReleased(MouseEvent evt) {
						buttonMouseReleased(evt);
					}
					public void mousePressed(MouseEvent evt) {
						buttonMousePressed(evt);
					}
				});
				
				//cb1 [i][j].addActionListener (new ColorButtonListener());
				/*
				cb1[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						MoveActionPerformed(evt);
					}
				});*/
				compteur++;
				//Unit temp=cb1[i][j].getPion();
			}
		}
		compteur=0;
		cb2 = new CaseButton [5][8]; 
		for(int i=0; i < 5; i++)
		{
			for(int j=0; j < 8; j++)
			{
				Unit pion=listePionsBlack.get(compteur);
				cb2 [i][j] = new CaseButton(i,j,pion);
				cb2 [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(pion.getImagePath())));
				cb2 [i][j].setBackground (Color.BLUE);
				cb2 [i][j].setPreferredSize (new Dimension (50, 50));             // on cree les boutons et on les met tous blancs
				jpP2.add (cb2[i][j]);
				
				/*
				cb2[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						MoveActionPerformed(evt);
					}
				});*/
				cb2[i][j].addMouseListener(new MouseAdapter() {
					public void mouseReleased(MouseEvent evt) {
						buttonMouseReleased(evt);
					}
					public void mousePressed(MouseEvent evt) {
						buttonMousePressed(evt);
					}
				});
				
				compteur++;
			}
		}
		cb = new CaseButton [size][size]; 
		for(int i=0; i < size; i++)
		{
			for(int j=0; j < size; j++)
			{
				cb [i][j] = new CaseButton(i,j,null);
				cb [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/background/01 ("+(j+i*10+1)+").gif")));//AJOUT TEMP
				cb [i][j].setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));//AJOUT TEMP
				//cb [i][j].setBackground (Color.WHITE);
				cb [i][j].setPreferredSize (new Dimension (50, 50));             // on cree les boutons et on les met tous blancs
				jp.add (cb[i][j]);
				
				
				cb[i][j].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						buttonMouseReleased(evt);
					}
					public void mousePressed(MouseEvent evt) {
						buttonMousePressed(evt);
					}
				});
				/*
				cb[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						DeposeActionPerformed(evt);
					}
				});
				
				cb[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						MoveActionPerformed(evt);
					}
				});*/
				
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
	
	public CaseButton[][] getCb() {
		return cb;
	}
	public void setCb(CaseButton[][] cb) {
		this.cb = cb;
	}
	public CaseButton[][] getCb1() {
		return cb1;
	}
	public void setCb1(CaseButton[][] cb1) {
		this.cb1 = cb1;
	}
	public CaseButton[][] getCb2() {
		return cb2;
	}
	public void setCb2(CaseButton[][] cb2) {
		this.cb2 = cb2;
	}

	private void buttonMousePressed(MouseEvent evt) {
		System.out.println("jButton1.mousePressed, event="+evt);
		//TODO add your code for jButton1.mousePressed
		if(((CaseButton)evt.getSource()).isVide()){
			pionCurrent= ((CaseButton)evt.getSource());
			
			//(CaseButton)evt.;
			System.out.println(pionCurrent);
			
			//System.out.println("true attendu"+selection);
			((CaseButton)evt.getSource()).setBackground(Color.GREEN);
		}
	}
	
	private void buttonMouseReleased(MouseEvent evt) {
		System.out.println("jButton1.mouseReleased, event="+evt);
		//TODO add your code for jButton1.mouseReleased
		if(pionCurrent!=null){
			((CaseButton)evt.getSource()).setPion(pionCurrent.getPion());
			((CaseButton)evt.getSource()).setIcon(new ImageIcon(getClass().getClassLoader().getResource(pionCurrent.getPion().getImagePath())));
			System.out.println(pionCurrent);
			pionCurrent=null;
			}
	}

	
	
	private void MoveActionPerformed(ActionEvent evt) {
		//System.out.println("jButton1.actionPerformed, event="+evt);
		//TODO add your code for jButton1.actionPerformed
		if(((CaseButton)evt.getSource()).isVide()){
			pionCurrent= ((CaseButton)evt.getSource());
			this.pionCurrent=pionCurrent;
			//(CaseButton)evt.;
			System.out.println(pionCurrent);
			
			//System.out.println("true attendu"+selection);
			((CaseButton)evt.getSource()).setBackground (Color.GREEN);
			//((CaseButton)evt.getSource()).setPion(null);
			
			((CaseButton)evt.getSource()).setEnabled(false);
		}
		
	}
	private void DeposeActionPerformed(ActionEvent evt) {
		if(pionCurrent!=null){
			((CaseButton)evt.getSource()).setPion(pionCurrent.getPion());
			((CaseButton)evt.getSource()).setIcon(new ImageIcon(getClass().getClassLoader().getResource(pionCurrent.getPion().getImagePath())));
			System.out.println(pionCurrent);
			pionCurrent=null;
			}
			//System.out.println("false attendu  "+selection);	
			
		
	}
	
	
	
	
		

}   


