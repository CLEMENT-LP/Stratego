package be.ephec.seance10;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;


/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class NewJFrame extends javax.swing.JFrame {
	private JPanel jPanel1;
	private final int L=10;
	private final int C=10;
	private int largeurJButton=50;
	private int hauteurJButton=50;
	private MesJButton tabJButton[][]= new MesJButton[C][L];


	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NewJFrame inst = new NewJFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public NewJFrame() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPanel1 = new JPanel();
				GridBagLayout jPanel1Layout = new GridBagLayout();
				/*
				jPanel1Layout.columnWidths = new int[] {7, 7, 7, 7};
				jPanel1Layout.rowHeights = new int[] {7, 7, 7, 7};
				jPanel1Layout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				jPanel1Layout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				*/
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setLayout(jPanel1Layout);
				for(int l=0;l<L;l++)
					for(int c=0;c<C;c++)
					{
						tabJButton[c][l] = new MesJButton(c,l);//colonne, ligne 
						String s="Image/StrategoBackground_"+(c+l*10+1)+".gif";
						tabJButton[c][l].setIcon(new ImageIcon(getClass().getClassLoader().getResource(s)));
						tabJButton[c][l].setBorder(BorderFactory.createCompoundBorder(null,null));
						jPanel1.add(tabJButton[c][l], new GridBagConstraints(c, l, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						//tabJButton[c][l].setText("jButton("+c+","+l+")");
						tabJButton[c][l].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jButtonActionPerformed(evt);
							}
						});
					}

			}
			pack();
			setSize(largeurJButton*C,hauteurJButton*L);
		} catch (Exception e) {
			//add your error handling code here
			e.printStackTrace();
		}
	}

	private void jButtonActionPerformed(ActionEvent evt) {
		System.out.println("jButton.actionPerformed, event="+evt);
		//TODO add your code for jButton1.actionPerformed
		int c=((MesJButton)evt.getSource()).getColonne();
		int l=((MesJButton)evt.getSource()).getLigne();
		System.out.println("on a cliqué en colonne "+c+", ligne"+l);
	}

}
