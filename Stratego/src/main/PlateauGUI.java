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
	private final int size = 10;
	private JLabel result;
	private CaseButton [][] cb;
	private CaseButton [][] cb1;
	private CaseButton [][] cb2;
	private CaseButton pionCurrent;
	private ArrayList<Pion> listePionsWhite=new ArrayList<Pion>();
	private ArrayList<Pion> listePionsBlack=new ArrayList<Pion>();
	private Pion[][] listePionsBackground=new Pion[10][10];
	private int l;
	private int c;
	private Color background;

	public PlateauGUI ()
	{
		BDDPions bddPions=new BDDPions();//création BDD Pions
		listePionsBackground=bddPions.getListePionsBackground();
		listePionsBlack=bddPions.getListePionsBlack();
		listePionsWhite=bddPions.getListePionsWhite();

		this.setTitle("Stratego");//titre fenêtre
		Box hbAll=Box.createHorizontalBox();//SCHEMA A FOURNIR (modulable pour ajout ultérieurs)
		Box vbPlateau=Box.createVerticalBox();
		Box vb1 = Box.createVerticalBox(); // boite pour les boutons

		JPanel jp = new JPanel (new GridLayout(size, size)); // panneau pour le dessin de la matrice 
		JPanel jpP1 = new JPanel (new GridLayout(5, 8)); //pions P1
		JPanel jpP2 = new JPanel (new GridLayout(5, 8));//pions P2

		JButton launch = new JButton("START");
		//launch.addActionListener (new LaunchButtonListener()); // bouton "Launch" avec écouteur d'action
		//Création des 3 tableaux de boutons
		cb1 = new CaseButton [5][8]; 
		int compteur=0;

		for(int i=0; i < 5; i++)
		{
			for(int j=0; j < 8; j++)
			{	
				Pion pion=listePionsWhite.get(compteur);
				cb1 [i][j] = new CaseButton(i,j,pion);
				cb1 [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(pion.getImagePath())));
				cb1 [i][j].setBackground (Color.RED);
				cb1 [i][j].setPreferredSize (new Dimension (50, 50));             
				jpP1.add (cb1[i][j]);

				cb1[i][j].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent evt) {

						buttonMousePressed(evt);
					}
				});
				compteur++;
			}
		}
		compteur=0;
		cb2 = new CaseButton [5][8]; 
		for(int i=0; i < 5; i++)
		{
			for(int j=0; j < 8; j++)
			{
				Pion pion=listePionsBlack.get(compteur);
				cb2 [i][j] = new CaseButton(i,j,pion);
				cb2 [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(pion.getImagePath())));
				cb2 [i][j].setBackground (Color.BLUE);
				cb2 [i][j].setPreferredSize (new Dimension (50, 50));            
				jpP2.add (cb2[i][j]);

				cb2[i][j].addMouseListener(new MouseAdapter() {
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
				Pion pion=listePionsBackground[i][j];
				cb [i][j] = new CaseButton(i,j,null);
				cb [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(pion.getImagePath())));//AJOUT TEMP
				cb [i][j].setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));//AJOUT TEMP

				cb [i][j].setPreferredSize (new Dimension (50, 50));             // on cree les boutons et on les met tous blancs
				jp.add (cb[i][j]);

				cb[i][j].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent evt) {

						buttonMousePressed(evt);
					}
				});

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
		//OBLIGATOIRE
		this.pack();
		this.setVisible(true); // on rend visible la fenetre
		this.setLocationRelativeTo(null);//centrer la fenêtre
	}

	private void buttonMousePressed(MouseEvent evt) {

		//CLIC DROIT
		if(evt.getButton()==MouseEvent.BUTTON3 && pionCurrent.getPion()==null)System.out.println("Sélectionnez d'abord un pion à déplacer");
		else if(evt.getButton()==MouseEvent.BUTTON3 && pionCurrent.getPion()!=null){
			System.out.println("clic-droit");
			((CaseButton)evt.getSource()).setPion(pionCurrent.getPion());
			((CaseButton)evt.getSource()).setIcon(new ImageIcon(getClass().getClassLoader().getResource(pionCurrent.getPion().getImagePath())));
			System.out.println(pionCurrent);
			pionCurrent.setPion(null);

			if(background==Color.RED){	
				cb1 [l][c].setIcon(new ImageIcon(getClass().getClassLoader().getResource("")));
			}
			else if(background==Color.BLUE){
				cb2 [l][c].setIcon(new ImageIcon(getClass().getClassLoader().getResource("")));	
			}
			else{
				cb [l][c].setIcon(new ImageIcon(getClass().getClassLoader().getResource(listePionsBackground[l][c].getImagePath())));
			}
		}
		//CLIC GAUCHE
		else if(((CaseButton)evt.getSource()).isNotVide()){
			pionCurrent=((CaseButton)evt.getSource());//prend le pion sélectionné en mémoire
			//System.out.println(pionCurrent);
			//((CaseButton)evt.getSource()).setBackground (Color.GREEN);
			l=((CaseButton)evt.getSource()).getI();
			c=((CaseButton)evt.getSource()).getJ();
			//((CaseButton)evt.getSource()).setEnabled(false);
			background=((CaseButton)evt.getSource()).getBackground();//regarde d'où provient la pièce
		}
		else if(((CaseButton)evt.getSource()).isNotVide()==false){System.out.println("Pas de pion à sélectionner ici");}
	}
}   


