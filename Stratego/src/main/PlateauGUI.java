package main;


import javax.swing.*;
import javax.swing.border.LineBorder;









import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
/**
 * Interface graphique du plateau, utilise classe Plateau pour d�finir le visuel
 * +comportement des diff�rents �l�ments
 *     
 * @author lp
 *
 */
public class PlateauGUI extends JFrame 
{
	private final int size = 10;//taille c�te tableau de jeu
	//private JLabel result;
	private CaseButton [][] cb;
	private CaseButton [][] cbTab1;
	private CaseButton [][] cbTab2;
	private CaseButton [][] cbTabCombat;
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
	private String[][] cacheImagesPions=new String[10][10];
	private boolean iconesNotVisible=false;
	private int idCurrent=2;

	public PlateauGUI ()
	{
		joueur1=new Joueur("joueur1");
		joueur2=new Joueur("joueur2");
		joueurCurrent=joueur1;
		initGUI();
		addWindowListener(new Fermeture());//couper le programme lorsqu'on quitte la fen�tre
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
	private void combatGUI(Pion attaque, Pion defense){
		System.out.println("COMBAT");
		this.setTitle("Combat");
		Box hb=Box.createHorizontalBox();
		JPanel jp = new JPanel (new GridLayout(1, 2));
		cbTabCombat= new CaseButton[1][2];
		cbTabCombat [0][0].setPreferredSize (new Dimension (50, 50)); 
		cbTabCombat [0][1].setPreferredSize (new Dimension (50, 50)); 
		//jp.add(cbTabCombat);
		//jp.add (cbTabCombat[0][0]);
		//jp.add (cbTabCombat[0][1]);

		hb.add(jp);
		getContentPane().add (hb); // on ajoute le tout au contenu et on positionne 
		//this.setResizable(false);//d�sactive redimensionnement
		
		//OBLIGATOIRE
		this.pack();
		this.setVisible(true); // on rend visible la fenetre
		this.setLocationRelativeTo(null);//centrer la fen�tre
		addWindowListener(new Fermeture());//couper le programme lorsqu'on quitte la fen�tre


	}
	private void initGUI(){

		BDDPions bddPions=new BDDPions();//cr�ation BDD Pions
		listePionsBackground=bddPions.getListePionsBackground();
		listePionsBlack=bddPions.getListePionsBlack();
		listePionsWhite=bddPions.getListePionsWhite();

		this.setTitle("Stratego");//titre fen�tre
		Box hbAll=Box.createHorizontalBox();//SCHEMA A FOURNIR (modulable pour ajout ult�rieurs)
		Box vbPlateau=Box.createVerticalBox();
		Box vb1 = Box.createHorizontalBox(); // boite pour les boutons

		JPanel jp = new JPanel (new GridLayout(size, size)); // panneau pour le dessin de la matrice 
		JPanel jpP1 = new JPanel (new GridLayout(5, 8)); //pions P1
		JPanel jpP2 = new JPanel (new GridLayout(5, 8));//pions P2

		JButton placerAuto=new JButton("PLACE AUTO");
		JButton placer = new JButton("1 PLACER");
		JButton launch = new JButton("3 START");
		JButton endPlacer = new JButton("2 FIN PLACEMENT");
		JButton tourStart = new JButton("4 DEBUT TOUR");
		JButton tourEnd = new JButton("5 FIN TOUR");

		//launch.addActionListener (new LaunchButtonListener()); // bouton "Launch" avec �couteur d'action
		//Cr�ation des 3 tableaux de boutons
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
				cb [i][j].setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, true));//AJOUT TEMP

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


		//ADD element to the box
		//vb1.add (vb1.createHorizontalGlue());//utile si il y a pls boutons

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

		getContentPane().add (hbAll); // on ajoute le tout au contenu et on positionne 
		this.setResizable(false);//d�sactive redimensionnement
		//OBLIGATOIRE
		this.pack();
		this.setVisible(true); // on rend visible la fenetre
		this.setLocationRelativeTo(null);//centrer la fen�tre


	}


	/**
	 * faire disparaitre boutons
	 */
	public void notVisible(CaseButton[][] cb){
		for(int i=0;i<cb.length;i++){
			for(int j=0;j<cb[i].length;j++){
				cb[i][j].setVisible(false);
			}
		}
	}
	/**
	 * faire r�apparaitre boutons
	 */
	public void visible(CaseButton[][] cb){
		for(int i=0;i<cb.length;i++){
			for(int j=0;j<cb[i].length;j++){
				cb[i][j].setVisible(true);
			}
		}
	}

	public void notVisible(CaseButton[][] cb, int l0, int c0, int l, int c){
		for(int i=c0;i<c+1;i++){
			for(int j=l0;j<l+1;j++){
				cb[i][j].setVisible(false);
			}
		}
	}
	public void visible(CaseButton[][] cb, int l0, int c0, int l, int c){
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
	 * D�sactive totallement
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
	/**
	 * Masquer les icones lorsque l'adversaire joue et les garder en m�moire
	 * @param id
	 */
	public void cacherImagesPions(int id){
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
	 * Remettre les icones lorsque l'adversaire a fini de jouer
	 * @param cbTab
	 */
	public void montrerImagesPions(int id){

		for(int i=0;i<cb.length;i++){
			for(int j=0;j<cb[i].length;j++){
				if(cb[i][j].getPion().getId()==id)cb [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(cacheImagesPions[i][j])));
			}
		}

	}


	private void placerAutoActionPerformed(ActionEvent evt) {	
		if(endPlace==-1){//�viter de relancer si le processus a d�j� �t� mis en marche
			activation(cb);
			
			//poser les pions
			int compteur=0;
			for(int i=0;i<4;i++){
				for(int j=0;j<10;j++){
					Pion pion=listePionsWhite.get(compteur);
					cb [i][j].setPion(pion);
					cb [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(pion.getImagePath())));
					compteur++;
				}
			}/*
			for(int i=4;i<6;i++){
				for(int j=0;j<10;j++){
					Pion pion=neutre;
					cb [i][j].setPion(pion);
					cb [i][j].getPion().setDescription("neutre");
					cb [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(listePionsBackground[i][j].getImagePath())));
					compteur++;
				}
			}*/
			compteur=0;
			for(int i=6;i<10;i++){
				for(int j=0;j<10;j++){
					Pion pion=listePionsBlack.get(compteur);
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
		}
	}
	
	//Phase 1 : Placer les pions sur le plateau de jeu
	private void placerActionPerformed(ActionEvent evt) {	
		if(endPlace==-1){//�viter de relancer si le processus a d�j� �t� mis en marche
			activation(cb);
			notVisible(cb,0,0,9,5);
			notVisible(cbTab1);
			endPlace++;
		}
	}
	//Phase 1': le joueur dit qu'il a pos� ses pions
	private void endPlacerActionPerformed(ActionEvent evt) {
		//System.out.println(countPionOnTab(cb));

		if(countPionOnTab(cb)==40 || endPlace==1 ){

			System.out.println("tout les pions sont pos�s");

			visible(cbTab1);
			visible(cb);
			notVisible(cb,0,4,9,9);//inverser activation
			System.out.println(endPlace);
			//if(endPlace==1){
			//((CaseButton)evt.getSource()).setVisible(false);
			//}
			endPlace++;
		}
		//else System.out.println("tout les pions NE sont PAS pos�s");

	}
	//Phase 2: lancement du jeu 1vs1
	private void launchActionPerformed(ActionEvent evt) {
		if(endPlace>1){
			visible(cb);
			desactivation(cb, 2, 4, 3, 5);
			desactivation(cb, 6, 4, 7, 5);
			cacherImagesPions(1);
			cacherImagesPions(2);


		}
	}

	private void tourStartActionPerformed(ActionEvent evt) {
		//remettre ses icones visibles
		montrerImagesPions(idCurrent);
		//System.out.println(idCurrent);


	}

	//Phase 2': Apr�s chaque action, chgt de tour/joueur
	private void tourEndActionPerformed(ActionEvent evt) {
		//mettre ses icones invisibles avant le jeu de l'adversaire
		cacherImagesPions(idCurrent);
		if(idCurrent==2)idCurrent=1;
		else idCurrent=2;

	}

	//D�placement des pions
	private void buttonMousePressed(MouseEvent evt) {
		try{
			//CLIC DROIT
			System.out.println(((CaseButton)evt.getSource()));
			int lNew=((CaseButton)evt.getSource()).getI();
			int cNew=((CaseButton)evt.getSource()).getJ();
			//Pas de pion � placer
			if(evt.getButton()==MouseEvent.BUTTON3 && pionCurrent.getPion()==null){
				System.out.println("S�lectionnez d'abord un pion � d�placer");
			}
			//Placer le pion en m�moire
			else if(evt.getButton()==MouseEvent.BUTTON3 && pionCurrent.getPion()!=null && cb[lNew][cNew].getPion().getDescription()!="interdit"){
				Pion attaque=pionCurrent.getPion();
				Pion defense=(((CaseButton)evt.getSource())).getPion();
				//V�rifie que l'on a quitt� la phase du positionnement des pions
				boolean deplacement=true;
				if(endPlace>1)deplacement=((CaseButton)evt.getSource()).deplacementAutorise(attaque, l, c, lNew, cNew);
				//V�rifie que le d�placement est permis
				if(deplacement){
		
					int gagne=((CaseButton)evt.getSource()).combatGagne(attaque, defense);
					System.out.println(gagne);
					//Regarde lequel des pions gagne l'affrontement
					if(gagne==0);//Match impossible
					//Match gagn�
					else if(gagne==3){
						//if(endPlace>1 && defense.getDescription()!="neutre") combatGUI(attaque, defense);
						if(endPlace>1 && defense.getDescription()!="neutre") combatGUI(pionCurrent.getPion(), neutre);
						((CaseButton)evt.getSource()).setPion(pionCurrent.getPion());
						((CaseButton)evt.getSource()).setIcon(new ImageIcon(getClass().getClassLoader().getResource(pionCurrent.getPion().getImagePath())));
						pionCurrent=null;
						//Remettre le bon fond l� o� on a pris la pi�ce qu'on d�pose ailleurs
						((CaseButton)evt.getSource()).background(cb, cbTab1, cbTab2, l, c, background, listePionsBackground, neutre);
					}
					//Match perdu
					else if(gagne==-1){
						pionCurrent=null;
						//Remettre le bon fond l� o� on a pris la pi�ce qu'on d�pose ailleurs
						((CaseButton)evt.getSource()).background(cb, cbTab1, cbTab2, l, c, background, listePionsBackground, neutre);
					}
					//Match nul
					else{
						pionCurrent=null;
						((CaseButton)evt.getSource()).setPion(neutre);
						((CaseButton)evt.getSource()).background(cb, cbTab1, cbTab2, l, c, background, listePionsBackground, neutre);
						((CaseButton)evt.getSource()).background(cb, cbTab1, cbTab2, lNew, cNew, background, listePionsBackground, neutre);
					}

				}
			}
			//CLIC GAUCHE
			//if(((CaseButton)evt.getSource()).isNotVide())System.out.println(((CaseButton)evt.getSource()).getPion().getDescription());
			else if(((CaseButton)evt.getSource()).isNotVide() && ((CaseButton)evt.getSource()).getPion().getValue()>-1 ){//�viter pions interdits
				pionCurrent=((CaseButton)evt.getSource());//prend le bouton s�lectionn� en m�moire
				l=((CaseButton)evt.getSource()).getI();
				c=((CaseButton)evt.getSource()).getJ();
				background=((CaseButton)evt.getSource()).getBackground();//regarde d'o� provient la pi�ce
				System.out.println(pionCurrent);
			}
			else if(((CaseButton)evt.getSource()).isNotVide()==false){
				System.out.println("Pas de pion � s�lectionner ici");
				System.out.println(((CaseButton)evt.getSource()).getPion().getDescription());
			}

		}catch(NullPointerException e){
			e.printStackTrace();
			System.out.println("D�placement non autoris�");
		}
	}
}


