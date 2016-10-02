package editeur.graphique;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import editeur.modele.Editeur;

/**
 * <b>VueSaisie est la vue qui permet d'effectuer une saisie de texte et de la valider.</b>
 * 
 * @author AntoineCourtil
 * @version 1.0
 */

public class VueSaisie extends JPanel implements Vue {
	
	/**
     * L'application Editeur.
     */
	protected Editeur appli;
	
	/**
     * Le JLabel des informations.
     */
	private JLabel infos;
	
	/**
     * Le JTextField de saisie de texte.
     */
	private JTextField saisie;
	
	/**
     * Le JButton de validation de saisie.
     */
	private JButton valider;
	
	
	//--------------------- CONSTRUCTORS ------------------------------------------
	
	
	/**
     * <b>Constructeur VueSaisie</b>
     * <p>Cree la vue qui permet d'effectuer une saisie de texte et de la valider</p>
    */
	public VueSaisie(Editeur e){
		super();
		e.ajouterVue(this);
		
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setPreferredSize(new Dimension(800, 100));
		this.add(Box.createVerticalGlue());		
		this.setBackground(Color.gray);
		
		this.appli = e;
		
		this.infos = new JLabel("Entrez votre ligne : ");
		this.saisie = new JTextField();
		this.valider = new JButton("Valider");

		this.add(this.infos);
		this.add(this.saisie);
		this.add(this.valider);
		
		this.valider.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				appli.addLine(saisie.getText());
			}
		});
		
	}

	
	
	//--------------------- OTHERS ------------------------------------------------
		
	/**
     * Permet de mettre à zero le champ de saisie.
     * 
     */
	public void mettreAJour() {
		this.saisie.setText("");
	}

}
