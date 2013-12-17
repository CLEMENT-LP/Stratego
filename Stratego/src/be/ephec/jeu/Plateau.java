package be.ephec.jeu;

import javax.swing.*;
import javax.swing.border.LineBorder;
















import be.ephec.pions.BDDPions;
import be.ephec.pions.CaseButton;
import be.ephec.pions.Pion;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Classe Plateau permet de créer l'interface graphique du jeu ainsi que les actions qui doivent être effectuées
 * en interaction avec les éléments de celle-ci
 *     
 * @author CLEMENT Louis-Philippe
 * @author OBIANG NDAM Steeves
 * @version 16/12/2013
 */
//Note: oui ça ne servait à rien de documenter des méthodes private, mais c'est intéressant pour nous si on continue le développement par la suite.

public class Plateau extends JFrame 
{
	private final int size = 10;//taille côté tableau de jeu
	//private JLabel result;
	private CaseButton [][] cb;//plateau de jeu
	private CaseButton [][] cbTab1;//plateau des pièces du joueur1
	private CaseButton [][] cbTab2;//plateau des pièces du joueur2
	private CaseButton pionCurrent;//pion en mémoire
	private ArrayList<Pion> listePionsWhite=new ArrayList<Pion>();
	private ArrayList<Pion> listePionsBlack=new ArrayList<Pion>();
	private Pion[][] listePionsBackground=new Pion[10][10];
	private int l;//position ligne en mémoire
	private int c;//position colonne en mémoire
	private Color background;//couleur de fond en mémoire
	private Pion neutre=new Pion("neutre",-1,"",0);
	private Joueur joueur1;
	private Joueur joueur2;
	private Joueur joueurCurrent;
	private int endPlace=-1;// avancée des phases de jeu, sécurité ordre actions
	private String[][] cacheImagesPions=new String[10][10];//cache temporaire permettant de repositionner les images des pions du joueur lorsque son tour vient
	private int idCurrent=1;//vérification du joueur en train d'effectuer son tour
	private int nbDeplacement=0;//compteur: un seul déplacement permis par tour
	private boolean launch=false;//phase de jeu après placement
	/**
	 * Création du plateau de jeu
	 */
	public Plateau ()
	{
		joueur1=new Joueur("joueur1");
		joueur2=new Joueur("joueur2");
		joueurCurrent=joueur1;
		initGUI();
		addWindowListener(new Fermeture());//fermer le processus java lorsqu'on quitte la fenêtre
	}
	private class Fermeture extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	}
	private class Message extends Component
	{
		public void paint(Graphics g)
		{
			g.drawString("Fin du jeu, le joueur "+idCurrent+" a gagné!", 75, 100);
		}
	}
	private class CadreTexte extends Frame
	{

		public CadreTexte()
		{
			setTitle("Résultat du combat");
			setSize(300, 200);
			setLocationRelativeTo(null);
			addWindowListener(new Fermeture());
			add(new Message());
		}
	}

	private void initCombat(){
		Frame frame = new CadreTexte();
		frame.show();
	}
	private void initGUI(){

		BDDPions bddPions=new BDDPions();//création BDD Pions
		listePionsBackground=bddPions.getListePionsBackground();
		listePionsBlack=bddPions.getListePionsBlack();
		listePionsWhite=bddPions.getListePionsWhite();

		this.setTitle("Stratego");//titre fenêtre

		Box hbAll=Box.createHorizontalBox();
		Box vbPlateau=Box.createVerticalBox();
		Box vb1 = Box.createHorizontalBox(); //boite pour les boutons

		JPanel jp = new JPanel (new GridLayout(size, size)); // panneau pour le dessin de la matrice 
		JPanel jpP1 = new JPanel (new GridLayout(5, 8)); //pions P1
		JPanel jpP2 = new JPanel (new GridLayout(5, 8));//pions P2

		JButton placerAuto=new JButton("1 PLACE AUTO");
		JButton placer = new JButton("1 PLACER");
		JButton launch = new JButton("3 START");
		JButton endPlacer = new JButton("2 FIN PLACER");
		JButton tourStart = new JButton("4 DEBUT TOUR");
		JButton tourEnd = new JButton("5 FIN TOUR");

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
				cb [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(pion.getImagePath())));
				cb [i][j].setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, true));

				cb [i][j].setPreferredSize (new Dimension (50, 50));//dimension boutons
				jp.add (cb[i][j]);

				cb[i][j].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent evt) {

						buttonMousePressed(evt);
					}
				});

			}

		}
		desactivation(cb);//interdire les déplacements lors de l'apparition de l'interface du jeu

		/*
		 * Ajout de Listener sur les boutons régissant les différentes phases de jeu
		 */
		launch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				launchActionPerformed(evt);
			}
		});

		placerAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				placerAutoActionPerformed(evt);
			}
		});

		placer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				placerActionPerformed(evt);
			}
		});

		endPlacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				endPlacerActionPerformed(evt);
			}
		});


		tourStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				tourStartActionPerformed(evt);
			}
		});

		tourEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				tourEndActionPerformed(evt);
			}
		});


		//ADD elements to the box

		vb1.add (placerAuto);
		vb1.add (placer);
		vb1.add (endPlacer);
		vb1.add (launch); 
		vb1.add(tourStart);
		vb1.add (tourEnd);


		vbPlateau.add(jpP1);
		vbPlateau.add(vb1);
		vbPlateau.add(jpP2);
		hbAll.add(jp);
		hbAll.add(vbPlateau);
		getContentPane().add (hbAll); // on ajoute tout au contenu et on positionne 
		this.setResizable(false);//désactive le redimensionnement
		this.pack();
		this.setVisible(true); // on rend visible la fenetre
		this.setLocationRelativeTo(null);//centrer la fenêtre


	}




	//Phase 1 option 1
	private void placerAutoActionPerformed(ActionEvent evt) {	
		if(endPlace==-1){//éviter de relancer si le processus a déjà été mis en marche
			activation(cb);
			//Random
			ArrayList<Pion> NewlistePionsWhite=listePionsWhite;
			Collections.shuffle(NewlistePionsWhite);
			//poser les pions
			int compteur=0;
			for(int i=0;i<4;i++){
				for(int j=0;j<10;j++){
					Pion pion=NewlistePionsWhite.get(compteur);
					cb [i][j].setPion(pion);
					cb [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(pion.getImagePath())));
					compteur++;
				}
			}
			//Random
			ArrayList<Pion> NewlistePionsBlack=listePionsBlack;
			Collections.shuffle(NewlistePionsBlack);
			compteur=0;
			for(int i=6;i<10;i++){
				for(int j=0;j<10;j++){
					Pion pion=NewlistePionsBlack.get(compteur);
					cb [i][j].setPion(pion);
					cb [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(pion.getImagePath())));
					compteur++;
				}
			}
			notVisible(cbTab2);
			notVisible(cbTab1);
			desactivation(cb, 2, 4, 3, 5);
			desactivation(cb, 6, 4, 7, 5);
			endPlace=2;

			visible(cb);
			desactivation(cb, 2, 4, 3, 5);
			desactivation(cb, 6, 4, 7, 5);
			cacherImagesPions(1);
			cacherImagesPions(2);
			launch=true;
			if(idCurrent!=1)idCurrent=1;
		}
	}

	//Phase 1 option 2 : Placer les pions sur le plateau de jeu
	private void placerActionPerformed(ActionEvent evt) {	
		if(endPlace==-1){//éviter de relancer si le processus a déjà été mis en marche
			background=Color.BLUE;
			activation(cb);
			notVisible(cb,0,0,9,5);
			notVisible(cbTab1);
			endPlace++;
		}
	}
	//Phase 1'option 2: le joueur dit qu'il a posé ses pions
	private void endPlacerActionPerformed(ActionEvent evt) {

		if(countPionOnTab(cb)==40 || endPlace==1 ){
			background=Color.RED;
			System.out.println("tout les pions sont posés");

			visible(cbTab1);
			visible(cb);
			notVisible(cb,0,4,9,9);//inverser activation
			endPlace++;
			idCurrent++;
		}

	}
	//Phase 2: lancement du jeu 1vs1
	private void launchActionPerformed(ActionEvent evt) {
		if(endPlace>1){
			visible(cb);
			desactivation(cb, 2, 4, 3, 5);
			desactivation(cb, 6, 4, 7, 5);
			cacherImagesPions(1);
			cacherImagesPions(2);
			launch=true;
			if(idCurrent!=1)idCurrent=1;

		}
	}

	private void tourStartActionPerformed(ActionEvent evt) {
		//remettre ses icones visibles
		montrerImagesPions(idCurrent);
	}

	//Phase 2': Après chaque action, chgt de tour/joueur
	private void tourEndActionPerformed(ActionEvent evt) {
		//mettre ses icones invisibles avant le jeu de l'adversaire
		if(nbDeplacement==1){
			cacherImagesPions(idCurrent);
			if(idCurrent==2)idCurrent=1;
			else idCurrent=2;
			if(launch)nbDeplacement--;
		}
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
				if(endPlace>1)deplacement=attaque.deplacementAutorise( l, c, lNew, cNew);//WARNING POSSIBLE
				//Vérifie que le déplacement est permis
				if(deplacement && nbDeplacement==0 ){

					int gagne=attaque.combatGagne(defense);

					//Regarde lequel des pions gagne l'affrontement
					if(gagne==0 && launch)nbDeplacement--;
					else if(gagne==0);//Match impossible
					//Match gagné
					else if(gagne==3){
						((CaseButton)evt.getSource()).setPion(pionCurrent.getPion());
						((CaseButton)evt.getSource()).setIcon(new ImageIcon(getClass().getClassLoader().getResource(pionCurrent.getPion().getImagePath())));
						pionCurrent=null;

						//Remettre le bon fond là où on a pris la pièce qu'on dépose ailleurs
						background(l,c);
						//((CaseButton)evt.getSource()).background();
					}
					//Match perdu
					else if(gagne==-1){
						pionCurrent=null;
						//Remettre le bon fond là où on a pris la pièce qu'on dépose ailleurs
						background(l,c);
						//((CaseButton)evt.getSource()).background(cb, l, c, background, listePionsBackground, neutre);
					}
					else if (gagne==5){
						initCombat();
					}
					//Match nul
					else{
						pionCurrent=null;
						((CaseButton)evt.getSource()).setPion(neutre);
						background(l, c);
						background(lNew, cNew);
					}
					if(launch)nbDeplacement++;
				}
			}
			//CLIC GAUCHE

			else if(((CaseButton)evt.getSource()).getPion().isNotVide() && ((CaseButton)evt.getSource()).getPion().getValue()>-1 && ((CaseButton)evt.getSource()).getPion().getId()==idCurrent){//éviter pions interdits
				pionCurrent=((CaseButton)evt.getSource());//prend le bouton sélectionné en mémoire
				l=((CaseButton)evt.getSource()).getI();
				c=((CaseButton)evt.getSource()).getJ();
				background=((CaseButton)evt.getSource()).getBackground();//regarde d'où provient la pièce

			}
			else if(((CaseButton)evt.getSource()).getPion().isNotVide()==false){
				System.out.println("Pas de pion à sélectionner ici");
				System.out.println(((CaseButton)evt.getSource()).getPion().getDescription());
			}

		}catch(NullPointerException e){
			System.out.println("Déplacement non autorisé");
		}
	}
	/**
	 * Faire disparaitre tout un tableau
	 * 
	 * @param cb!=null : tableau de boutons
	 */
	private void notVisible(CaseButton[][] cb){
		for(int i=0;i<cb.length;i++){
			for(int j=0;j<cb[i].length;j++){
				cb[i][j].setVisible(false);
			}
		}
	}


	/**
	 * Faire réapparaitre tout un tableau
	 * 
	 * @param cb!=null : tableau de boutons
	 */
	private void visible(CaseButton[][] cb){
		for(int i=0;i<cb.length;i++){
			for(int j=0;j<cb[i].length;j++){
				cb[i][j].setVisible(true);
			}
		}
	}
	/**
	 * Faire disparaitre une partie de tableau
	 * 
	 * @param cb!=null : tableau de boutons
	 * @param l0>=0 : ligne de départ
	 * @param c>=0 : colonne de départ
	 * @param l>=0 : ligne d'arrivée
	 * @param c>=0 : colonne d'arrivée
	 */
	private void notVisible(CaseButton[][] cb, int l0, int c0, int l, int c){
		for(int i=c0;i<c+1;i++){
			for(int j=l0;j<l+1;j++){
				cb[i][j].setVisible(false);
			}
		}
	}

	/**
	 * Indique "interdit" dans la description des pions contenus sur les cases interdites au placement
	 * @param cb!=null : tableau de boutons
	 * @param l0>=0 : ligne de départ
	 * @param c>=0 : colonne de départ
	 * @param l>=0 : ligne d'arrivée
	 * @param c>=0 : colonne d'arrivée
	 */
	private void desactivation(CaseButton[][] cb, int l0, int c0, int l, int c){
		for(int i=c0;i<c+1;i++){
			for(int j=l0;j<l+1;j++){
				cb[i][j].getPion().setDescription("interdit");
			}
		}

	}
	/**
	 * Indique "interdit" dans la description des pions contenus sur l'ensemble du tableau
	 * @param cb!=null : tableau de boutons
	 */
	private void desactivation(CaseButton[][] cbTab){
		for(int i=0;i<cb.length;i++){
			for(int j=0;j<cb[i].length;j++){
				cb[i][j].getPion().setDescription("interdit");
			}
		}

	}
	/**
	 * Indique "neutre" dans la description des pions contenus sur l'ensemble du tableau
	 * @param cb!=null : tableau de boutons
	 */
	private void activation(CaseButton[][] cbTab){
		for(int i=0;i<cb.length;i++){
			for(int j=0;j<cb[i].length;j++){
				cb[i][j].getPion().setDescription("neutre");
			}
		}

	}
	/**
	 * Compte le nombre de pions (hors neutre et interdit) présents sur un tableau
	 * 
	 * @param cbTab!=null : tableau de boutons
	 * @return entier : le nombre de pions (hors neutre et interdit) présents sur le tableau
	 */
	private int countPionOnTab(CaseButton[][] cbTab){
		int count=0;
		for(int i=0;i<cb.length;i++){
			for(int j=0;j<cb[i].length;j++){
				if(cb[i][j].getPion().getValue()>-1) count++;
			}
		}
		return count;
	}
	/**
	 * Masquer les icones du joueur lorsque l'adversaire joue et les garder en mémoire
	 * 
	 * @param (id==1) || (id==2) : entier qui désigne un des joueurs
	 */
	private void cacherImagesPions(int id){
		String path;
		if(id==1)path="images/black/black.png";
		else path="images/white/white.png";
		for(int i=0;i<cb.length;i++){
			for(int j=0;j<cb[i].length;j++){
				cacheImagesPions[i][j]=cb[i][j].getPion().getImagePath();
				if(cb[i][j].getPion().getValue()>-1 && cb[i][j].getPion().getId()==id) cb [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(path)));
			}
		}

	}
	/**
	 * Remettre les icones visibles lorsque l'adversaire a fini de jouer
	 * @param (id==1) || (id==2) : entier qui désigne un des joueurs
	 */
	private void montrerImagesPions(int id){

		for(int i=0;i<cb.length;i++){
			for(int j=0;j<cb[i].length;j++){
				if(cb[i][j].getPion().getId()==id)cb [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(cacheImagesPions[i][j])));
			}
		}

	}

	/**
	 * Gère le background d'un bouton
	 * 
	 * @param l>=0 : position du bouton , n° ligne du tableau
	 * @param c>=0 : position du bouton , n° colonne du tableau
	 * 
	 */
	private void background(int l,int c){
		if(background==Color.RED){
			cbTab1 [l][c].setIcon(new ImageIcon(getClass().getClassLoader().getResource("")));
			cbTab1 [l][c].setPion(null);
		}
		else if(background==Color.BLUE){
			cbTab2 [l][c].setIcon(new ImageIcon(getClass().getClassLoader().getResource("")));
			cbTab2 [l][c].setPion(null);
		}
		else{
			cb [l][c].setIcon(new ImageIcon(getClass().getClassLoader().getResource(listePionsBackground[l][c].getImagePath())));
			cb [l][c].setPion(neutre);
		}
	}
}


