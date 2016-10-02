package editeur.graphique;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.*;

import editeur.modele.Editeur;

/**
 * <b>VueMenu est la vue qui d'instancier le menu de l'application.</b>
 * 
 * @author AntoineCourtil
 * @version 1.0
 */

public class VueMenu extends JMenuBar implements Vue{
	
	/**
     * L'application Editeur.
     */
	private static Editeur appli;
	
	/**
     * Je JMenu de la classe.
     */
	private JMenu menuStyle;
	
	
	//private ArrayList<Couleur> lesCouleurs;
	
	
	/**
     * La liste des Class de couleurs.
     */
	private ArrayList<Class> lesCouleurs;
	
	
	//--------------------- CONSTRUCTORS ------------------------------------------
	
	/**
     * <b>Constructeur VueMenu</b>
     * <p>Cree le Menu avec un sous-menu de gestion de fichier et un de gestion de couleur</p>
    */
	public VueMenu(Editeur e){
		this.appli = e;
		/*this.lesCouleurs = new ArrayList<Couleur>();
		this.lesCouleurs.add(new Couleur("Noir", Color.black));
		this.lesCouleurs.add(new Couleur("Rouge", Color.red));
		this.lesCouleurs.add(new Couleur("Vert", Color.green));
		this.lesCouleurs.add(new Couleur("Bleu", Color.blue));*/
		

		this.lesCouleurs = new ArrayList<Class>();

		try {
			this.lesCouleurs.add(Class.forName("styles.StyleJaune"));
			//this.lesCouleurs.add(Class.forName("styles.StyleVert"));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		e.ajouterVue(this);
		
		JMenu menuFichier = new JMenu("Fichier");

		JMenuItem jmiNouveau = new JMenuItem("Nouveau");
		JMenuItem jmiOuvrir = new JMenuItem("Ouvrir");
		JMenuItem jmiEnregistrer = new JMenuItem("Enregistrer");
		JMenuItem jmiEnregistrerSous = new JMenuItem("Enregistrer Sous");
		JMenuItem jmiQuitter = new JMenuItem("Quitter");

		menuFichier.add(jmiNouveau);
		menuFichier.add(jmiOuvrir);
		menuFichier.add(jmiEnregistrer);
		menuFichier.add(jmiEnregistrerSous);
		menuFichier.add(jmiQuitter);
		
		this.add(menuFichier);
		

		jmiNouveau.addActionListener(new newFile()) ;
		jmiOuvrir.addActionListener(new openFile()) ;
		jmiEnregistrer.addActionListener(new saveFile()) ;
		jmiEnregistrerSous.addActionListener(new saveFileAs()) ;
		jmiQuitter.addActionListener(new exitApp()) ;
		
		
		//-------------------------------------------------------------------
		
		this.menuStyle = new JMenu("Style");

		JMenuItem addColor = new JMenuItem("Ajouter une Couleur");

		this.menuStyle.add(addColor);
		
		this.add(this.menuStyle);
		
		this.mettreAJour();

		addColor.addActionListener(new openColor()) ;
		//addColor.addActionListener(new newColor()) ;

		
	}


	//--------------------- OTHERS ------------------------------------------------
	
	/**
     * Permet de mettre à jour le Menu.
     * 
     */
	public void mettreAJour() {
		this.menuStyle.removeAll();
		
		JMenuItem addColor = new JMenuItem("Ajouter une Couleur");
		this.menuStyle.add(addColor);
		
		/*for(int i=lesCouleurs.size()-1;i>=0;i--){
			final Couleur c=lesCouleurs.get(i);
			
			JMenuItem item = new JMenuItem(c.getNom());
			item.setForeground(c.getCouleur());
			this.menuStyle.add(item);
			
			item.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					appli.setCouleur(c.getCouleur());
				}
			});
		}*/
		
		for(Class c : this.lesCouleurs){
			try {
				Object o = c.newInstance();
				Method me = c.getMethod("getNom");
				Method me2 = c.getMethod("getCouleur");
				
				Object nom = me.invoke(o);
				final Object couleur = me2.invoke(o);
				
				JMenuItem item = new JMenuItem((String) nom);
				item.setForeground((Color) couleur);
				this.menuStyle.add(item);
				
				item.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						appli.setCouleur((Color) couleur);
					}
				});
				
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//addColor.addActionListener(new newColor()) ;
		addColor.addActionListener(new openColor()) ;
	}
	

	//--------------------- FILES ------------------------------------------------
	
	/**
     * ActionListener qui permet d'appeler la fonction appli.clear()
     * 
     */
	static class newFile implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            appli.clear();
        }
    }
	
	/**
     * ActionListener qui permet de choisir un fichier
     * et d'appeler la fonction appli.ouvrir(fichier)
     * 
     */
	static class openFile implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	JFileChooser explorer = new JFileChooser();
        	explorer.setCurrentDirectory(new File("."));
        	explorer.setDialogTitle("Ouvrir un fichier txt");
        	 
        	String ouvrir = new String("Ouvrir");
        	int resultatEnregistrer = explorer.showDialog(explorer, ouvrir); 
        	if (resultatEnregistrer == JFileChooser.APPROVE_OPTION){
        	    String fichier = explorer.getSelectedFile().getAbsolutePath();
        	    
        	    if (fichier.lastIndexOf(".") > 0) {
        	    	String extension=fichier.substring(fichier.lastIndexOf("."));
        	    	if(extension.equals(".editeur")){
        	    		appli.setCheminCourant(explorer.getSelectedFile().getAbsolutePath());
        	    		appli.setFichierCourant(fichier);
        	    		appli.ouvrir(fichier);
        	    	}
        	    }
        	}
        }
    }
	
	/**
     * ActionListener qui permet de sauvegarder le fichier
     * et d'appeler la fonction appli.ecrireFichier(fichier)
     * 
     */
	static class saveFile implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
    		String fichierCourant = appli.getFichierCourant();
        	if(fichierCourant!=""){
            	//System.out.println("fichierCourant non null");
        		appli.ecrireFichier(fichierCourant);
        	}
        	else{
            	//System.out.println("fichierCourant null");
        		//new VueMenu.saveFileAs();
        		JOptionPane.showMessageDialog(null, "Veuillez faire enregistrer sous avant", "Attention", JOptionPane.WARNING_MESSAGE);
        	}
        }
    }
	
	/**
     * ActionListener qui permet de sauvegarder un fichier sous un repertoire
     * et d'appeler la fonction appli.ecrireFichier(chemin, fichier)
     * 
     */
	static class saveFileAs implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	JFileChooser explorer = new JFileChooser();
        	explorer.setCurrentDirectory(new File("."));
        	explorer.setDialogTitle("Enregistrer les billets présents");
        	 
        	explorer.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        	 
        	String enregistrer = new String("Enregistrer");
        	int resultatEnregistrer = explorer.showDialog(explorer, enregistrer); 
        	if (resultatEnregistrer == JFileChooser.APPROVE_OPTION){
        	    String chemin = explorer.getSelectedFile().getAbsolutePath()+"";
        	    
        	    String nomFichier = JOptionPane.showInputDialog("Nom du fichier :") ;
        	    
        	    appli.ecrireFichier(chemin, nomFichier);
        	    
        	    //System.out.println(chemin+"/"+nomFichier);
        	}
        }   
    }
	

	
	//---------------------------- STYLES ------------------------------------------------------------------
	
	
	/*static class changeColor implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	String tmpBoutonNom = ((JButton) e.getSource()).getName();
        	
        	switch(tmpBoutonNom){
	    		case "Noir" : appli.setCouleur(Color.black);
	    		case "Rouge" : appli.setCouleur(Color.red);
        	}
        }
    }*/
	
	/*class newColor implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	Color couleur = JColorChooser.showDialog(
        		      VueMenu.this,
        		      "Choisissez une couleur",
        		      Color.black);
        		if(couleur != null){
        			String nom = JOptionPane.showInputDialog("Nom de la Couleur : ");
        			
        			lesCouleurs.add(new Couleur(nom, couleur));
        			appli.mettreAJour();
        			
        		}
        }
    }*/
	
	/**
     * ActionListener qui permet de choisir une couleur
     * dans un package deja present dans l'application
     * et de la charger dans le menu
     * 
     */
	class newColor implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	String nom = JOptionPane.showInputDialog("Nom de la Couleur : ");
        	
        	try {
				Class c = Class.forName(nom);
				lesCouleurs.add(c);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	appli.mettreAJour();
        }
    }
	
	
	/**
     * ActionListener qui permet de choisir une couleur
     * dans une archive jar
     * et de la charger dans le menu
     * 
     */
	class openColor implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	JFileChooser explorer = new JFileChooser();
        	explorer.setCurrentDirectory(new File("."));
        	explorer.setDialogTitle("Ouvrir une archive jar");
        	 
        	String ouvrir = new String("Ouvrir");
        	int resultatEnregistrer = explorer.showDialog(explorer, ouvrir); 
        	if (resultatEnregistrer == JFileChooser.APPROVE_OPTION){
        	    String archive = explorer.getSelectedFile().getAbsolutePath();
        	    
        	    if (archive.lastIndexOf(".") > 0) {
        	    	String extension=archive.substring(archive.lastIndexOf("."));
        	    	if(extension.equals(".jar")){
        	    		//System.out.println(archive);
        	    		archive="file://"+archive;
        	    		
        	    		try {
        	    			URL classUrl;
        	    	        classUrl = new URL("file://"+archive);
        	    	        URL[] classUrls = { classUrl };
        	    	        URLClassLoader ucl = new URLClassLoader(classUrls);
        	    	        
        	    	        String nomCouleur = JOptionPane.showInputDialog("Nom de la Couleur (package.class) : ");
        	    	        
        	    	        Class<?> classCouleur = ucl.loadClass(nomCouleur);
        	    	        System.out.println(classCouleur);
        	    	        lesCouleurs.add(classCouleur);
        	    		      
        	    		    } catch (MalformedURLException e1) {
        	    		      e1.printStackTrace();
        	    		    } catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
        	    		
        	    	}
        	    }
        	}
        	appli.mettreAJour();
        }
    }
	
	//---------------------------- AUTRE ------------------------------------------------------------------
	
	
	/**
     * ActionListener qui permet de quitter l'application
     * 
     */
	static class exitApp implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

}
