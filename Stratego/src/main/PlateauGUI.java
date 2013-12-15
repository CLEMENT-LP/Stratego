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
	private final int size = 10;//taille côte tableau de jeu
	//private JLabel result;
	private CaseButton [][] cb;
	private CaseButton [][] cbTab1;
	private CaseButton [][] cbTab2;
	private CaseButton pionCurrent;
	private ArrayList<Pion> listePionsWhite=new ArrayList<Pion>();
	private ArrayList<Pion> listePionsBlack=new ArrayList<Pion>();
	private Pion[][] listePionsBackground=new Pion[10][10];
	private int l;
	private int c;
	private Color background;
	private Pion neutre=new Pion("neutre",-1,"",0);
	private Joueur joueur1;
	private Joueur joueur2;
	private Joueur joueurCurrent;
	private int endPlace=-1;//1e phase
	
	
	public PlateauGUI ()
	{
		joueur1=new Joueur("joueur1");
		joueur2=new Joueur("joueur2");
		joueurCurrent=joueur1;
		initGUI();
		addWindowListener(new Fermeture());//couper le programme lorsqu'on quitte la fenêtre
	}
	private class Fermeture extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	}
	/*
	private class Message extends Component
	{
		public void paint(Graphics g)
		{
			g.drawString("FIN DU JEU", 75, 100);
		}
	}*/
	
	private void initGUI(){

		BDDPions bddPions=new BDDPions();//création BDD Pions
		listePionsBackground=bddPions.getListePionsBackground();
		listePionsBlack=bddPions.getListePionsBlack();
		listePionsWhite=bddPions.getListePionsWhite();

		this.setTitle("Stratego");//titre fenêtre
		Box hbAll=Box.createHorizontalBox();//SCHEMA A FOURNIR (modulable pour ajout ultérieurs)
		Box vbPlateau=Box.createVerticalBox();
		Box vb1 = Box.createHorizontalBox(); // boite pour les boutons

		JPanel jp = new JPanel (new GridLayout(size, size)); // panneau pour le dessin de la matrice 
		JPanel jpP1 = new JPanel (new GridLayout(5, 8)); //pions P1
		JPanel jpP2 = new JPanel (new GridLayout(5, 8));//pions P2

		JButton placer = new JButton("PLACER LES PIONS");
		JButton launch = new JButton("START");
		JButton tourPlace = new JButton("FIN DE PLACEMENT");
		JButton tourEnd = new JButton("FIN DE TOUR");

		//launch.addActionListener (new LaunchButtonListener()); // bouton "Launch" avec écouteur d'action
		//Création des 3 tableaux de boutons
		cbTab1 = new CaseButton [5][8]; 
		int compteur=0;

		for(int i=0; i < 5; i++)
		{
			for(int j=0; j < 8; j++)
			{	
				Pion pion=listePionsWhite.get(compteur);
				cbTab1 [i][j] = new CaseButton(i,j,pion);
				cbTab1 [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(pion.getImagePath())));
				cbTab1 [i][j].setBackground (Color.RED);
				cbTab1 [i][j].setPreferredSize (new Dimension (50, 50));             
				jpP1.add (cbTab1[i][j]);

				cbTab1[i][j].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent evt) {

						buttonMousePressed(evt);
					}
				});
				compteur++;
			}
		}
		compteur=0;
		cbTab2 = new CaseButton [5][8]; 
		for(int i=0; i < 5; i++)
		{
			for(int j=0; j < 8; j++)
			{
				Pion pion=listePionsBlack.get(compteur);
				cbTab2 [i][j] = new CaseButton(i,j,pion);
				cbTab2 [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(pion.getImagePath())));
				cbTab2 [i][j].setBackground (Color.BLUE);
				cbTab2 [i][j].setPreferredSize (new Dimension (50, 50));            
				jpP2.add (cbTab2[i][j]);

				cbTab2[i][j].addMouseListener(new MouseAdapter() {
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
				cb [i][j] = new CaseButton(i,j,pion);
				cb [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(pion.getImagePath())));//AJOUT TEMP
				cb [i][j].setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));//AJOUT TEMP

				cb [i][j].setPreferredSize (new Dimension (50, 50));             //dimension boutons
				jp.add (cb[i][j]);

				cb[i][j].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent evt) {

						buttonMousePressed(evt);
					}
				});
				
			}
			
		}
		desactivation(cb);
		
		/*
		 * PHASES de JEU boutons
		 */
		launch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				launchActionPerformed(evt);
			}
		});

		placer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				placerActionPerformed(evt);
			}
		});
		
		tourPlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				tourPlaceActionPerformed(evt);
			}
		});
		
		tourEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				tourEndActionPerformed(evt);
			}
		});


		//ADD element to the box
		//vb1.add (vb1.createHorizontalGlue());//utile si il y a pls boutons
		      
		vb1.add (placer);
		vb1.add (tourPlace);
		vb1.add (launch);  
		vb1.add (tourEnd);
		

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

	
		/**
		 * faire disparaitre boutons
		 */
		public void NotVisible(CaseButton[][] cb){
			for(int i=0;i<cb.length;i++){
				for(int j=0;j<cb[i].length;j++){
					cb[i][j].setVisible(false);
				}
			}
		}
		/**
		 * faire réapparaitre boutons
		 */
		public void Visible(CaseButton[][] cb){
			for(int i=0;i<cb.length;i++){
				for(int j=0;j<cb[i].length;j++){
					cb[i][j].setVisible(true);
				}
			}
		}
		
		public void NotVisible(CaseButton[][] cb, int l0, int c0, int l, int c){
			for(int i=c0;i<c+1;i++){
				for(int j=l0;j<l+1;j++){
					cb[i][j].setVisible(false);
				}
			}
		}
			public void Visible(CaseButton[][] cb, int l0, int c0, int l, int c){
				for(int i=c0;i<c+1;i++){
					for(int j=l0;j<l+1;j++){
						cb[i][j].setVisible(true);
					}
				}

		}
		/**
		 * Indique interdit dans la description des pions contenus sur les cases interdites au placement
		 * @param cb
		 * @param l0
		 * @param c0
		 * @param l
		 * @param c
		 */
		public void desactivation(CaseButton[][] cb, int l0, int c0, int l, int c){
			for(int i=c0;i<c+1;i++){
				for(int j=l0;j<l+1;j++){
					cb[i][j].getPion().setDescription("interdit");
				}
			}

		}
		/**
		 * Désactive totallement
		 * @param cb
		 */
		public void desactivation(CaseButton[][] cbTab){
			for(int i=0;i<cb.length;i++){
				for(int j=0;j<cb[i].length;j++){
					cb[i][j].getPion().setDescription("interdit");
				}
			}

		}
		/**
		 * Active totallement
		 * @param cb
		 */
		public void activation(CaseButton[][] cbTab){
			for(int i=0;i<cb.length;i++){
				for(int j=0;j<cb[i].length;j++){
					cb[i][j].getPion().setDescription("neutre");
				}
			}

		}
		public int countPionOnTab(CaseButton[][] cbTab){
			int count=0;
			for(int i=0;i<cb.length;i++){
				for(int j=0;j<cb[i].length;j++){
					if(cb[i][j].getPion().getValue()>-1) count++;
				}
			}
			return count;
		}
		//créer un tableau qui retient les icones puis faire la substitution
		public void remplacerImagesPion(CaseButton[][] cbTab){
			int count=0;
			for(int i=0;i<cb.length;i++){
				for(int j=0;j<cb[i].length;j++){
					if(cb[i][j].getPion().getValue()>-1) count++;
				}
			}
			
		}
		
		//Phase 1 : Placer les pions sur le plateau de jeu
		private void placerActionPerformed(ActionEvent evt) {	
			if(endPlace==-1){//éviter de relancer si le processus a déjà été mis en marche
				activation(cb);
				NotVisible(cb,0,0,9,5);
				NotVisible(cbTab1);
				endPlace++;
			}
		}
		//Phase 1': le joueur dit qu'il a posé ses pions
		private void tourPlaceActionPerformed(ActionEvent evt) {
			System.out.println(countPionOnTab(cb));
			
			if(countPionOnTab(cb)==40 || endPlace==1 ){
				
				System.out.println("tout les pions sont posés");
				
				Visible(cbTab1);
				Visible(cb);
				NotVisible(cb,0,4,9,9);//inverser activation
				System.out.println(endPlace);
				//if(endPlace==1){
					//((CaseButton)evt.getSource()).setVisible(false);
				//}
				endPlace++;
			}
			else System.out.println("tout les pions NE sont PAS posés");
			
		}
		//Phase 2: lancement du jeu 1vs1
		private void launchActionPerformed(ActionEvent evt) {
			desactivation(cb, 2, 4, 3, 5);
			desactivation(cb, 6, 4, 7, 5);
		}
		//Phase 2': Après chaque action, chgt de tour/joueur
		private void tourEndActionPerformed(ActionEvent evt) {
			//changer plateau pions blancs/noirs
			
		}

		//Déplacement des pions
		private void buttonMousePressed(MouseEvent evt) {
			try{
				//CLIC DROIT

				int lNew=((CaseButton)evt.getSource()).getI();
				int cNew=((CaseButton)evt.getSource()).getJ();
				//Pas de pion à placer
				if(evt.getButton()==MouseEvent.BUTTON3 && pionCurrent.getPion()==null){
					System.out.println("Sélectionnez d'abord un pion à déplacer");
				}
				//Placer le pion en mémoire
				else if(evt.getButton()==MouseEvent.BUTTON3 && pionCurrent.getPion()!=null && cb[lNew][cNew].getPion().getDescription()!="interdit"){
					Pion attaque=pionCurrent.getPion();
					Pion defense=(((CaseButton)evt.getSource())).getPion();
					//Vérifie que l'on a quitté la phase du positionnement des pions
					boolean deplacement=true;
					if(endPlace>2)deplacement=((CaseButton)evt.getSource()).deplacementAutorise(attaque, l, c, lNew, cNew);
					//Vérifie que le déplacement est permis
					if(deplacement){
						int gagne=((CaseButton)evt.getSource()).combatGagne(attaque, defense);
						System.out.println(gagne);
						//Regarde lequel des pions gagne l'affrontement
						if(gagne==0);//Match impossible
						//Match gagné
						else if(gagne==3){
							((CaseButton)evt.getSource()).setPion(pionCurrent.getPion());
							((CaseButton)evt.getSource()).setIcon(new ImageIcon(getClass().getClassLoader().getResource(pionCurrent.getPion().getImagePath())));
							pionCurrent=null;
							//Remettre le bon fond là où on a pris la pièce qu'on dépose ailleurs
							((CaseButton)evt.getSource()).background(cb, cbTab1, cbTab2, l, c, background, listePionsBackground, neutre);
						}
						//Match perdu
						else if(gagne==-1){
							pionCurrent=null;
							//Remettre le bon fond là où on a pris la pièce qu'on dépose ailleurs
							((CaseButton)evt.getSource()).background(cb, cbTab1, cbTab2, l, c, background, listePionsBackground, neutre);
						}
						//Match nul
						else{
							pionCurrent=null;
							((CaseButton)evt.getSource()).setPion(neutre);
							((CaseButton)evt.getSource()).background(cb, cbTab1, cbTab2, l, c, background, listePionsBackground, neutre);
						}
						
					}
				}
				//CLIC GAUCHE
				//if(((CaseButton)evt.getSource()).isNotVide())System.out.println(((CaseButton)evt.getSource()).getPion().getDescription());
				else if(((CaseButton)evt.getSource()).isNotVide() && ((CaseButton)evt.getSource()).getPion().getValue()>-1 ){//éviter pions interdits
					pionCurrent=((CaseButton)evt.getSource());//prend le bouton sélectionné en mémoire
					l=((CaseButton)evt.getSource()).getI();
					c=((CaseButton)evt.getSource()).getJ();
					background=((CaseButton)evt.getSource()).getBackground();//regarde d'où provient la pièce
					System.out.println(pionCurrent);
				}
				else if(((CaseButton)evt.getSource()).isNotVide()==false){
					System.out.println("Pas de pion à sélectionner ici");
					System.out.println(((CaseButton)evt.getSource()).getPion().getDescription());
				}

			}catch(NullPointerException e){
				System.out.println("Déplacement non autorisé");
			}
		}
	}


