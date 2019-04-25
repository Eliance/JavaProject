package fr.upem.captcha;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import fr.upem.captcha.images.BasicImage;
import fr.upem.captcha.images.Capchable;



public class Capcha {
	
	JFrame frame;
	private static ArrayList<String> selectedImages = new ArrayList<String>();
	
	Capcha(Capchable category) {
		System.out.println(Main.nbImageMin+" image à trouver minimum dans la catégorie "+category.toString());
		frame = new JFrame("Captcha");
		
		GridLayout layout = new GridLayout(4,3);  // Création d'un layout de type Grille avec 4 lignes et 3 colonnes
		
		frame.setLayout(layout);  // affection du layout dans la fenêtre.
		frame.setSize(1024, 768); // définition de la taille
		frame.setResizable(false);  // On définit la fenêtre comme non redimentionnable
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Lorsque l'on ferme la fenêtre on quitte le programme.
		
		BasicImage images = new BasicImage();
		
		int nbSpecificImages = ThreadLocalRandom.current().nextInt(Main.nbImageMin, 5);
		
		ArrayList<String> specificImages = category.getRandomPhotosURL(nbSpecificImages);
		ArrayList<String> basicImages = images.getRandomPhotosURL(9-nbSpecificImages);
		
		//ici on remplit les 9 premieres cases de notre fenêtre par des photos
		try {
			fillFrameRandomly(frame, specificImages, basicImages);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//puis texte d'instruction
		frame.add(new JTextArea("Veuillez selectionner les images \nmontrant des " + Main.currentCategory.toString() + "\npuis cliquez sur OK."));

		//puis bouton OK
		CheckAction check = new CheckAction(frame, specificImages, selectedImages);
		JButton okButton = new JButton(check);
		frame.add(okButton);
		
		frame.setVisible(true);
	}
	
	static void harderCapcha() {
		switch(ThreadLocalRandom.current().nextInt(2)) {
			case 0:
				if (Main.nbImageMin<4) {
					Main.nbImageMin++;
				}
				Capcha capcha0 = new Capcha(Main.currentCategory);
				break;
			
			case 1:
				if (Main.difficulty < Main.categoriesByDifficulty.size()-1) {
					Main.difficulty++;
				}
				ArrayList<Capchable> categories = Main.categoriesByDifficulty.get(Main.difficulty);
				Main.currentCategory = categories.get(ThreadLocalRandom.current().nextInt(categories.size()));
				Capcha capcha1 = new Capcha(Main.currentCategory);
				break;
				
			default:
				break;
		}
	}
	
	static void fillFrameRandomly(JFrame frame, ArrayList<String> specificImages, ArrayList<String> basicImages) throws IOException{
		int nbSpecificImg = specificImages.size();
		int specificCount = 0;
		int basicCount = 0;
		for (int i=0; i<9; i++) {
			switch(ThreadLocalRandom.current().nextInt(2)) {
				case 0:
					if (basicCount < 9-nbSpecificImg) {
						frame.add(createLabelImage(basicImages.get(basicCount)));
						basicCount++;
					}
					else {
						frame.add(createLabelImage(specificImages.get(specificCount)));
						specificCount++;
					}
					break;
				case 1:
					if (specificCount < nbSpecificImg) {
						frame.add(createLabelImage(specificImages.get(specificCount)));
						specificCount++;
					}
					else {
						frame.add(createLabelImage(basicImages.get(basicCount)));
						basicCount++;
					}
					break;
				default:
					break;
			}
		}
	}
	
	static JLabel createLabelImage(String imageLocation) throws IOException{
		File f = new File(imageLocation);
		
		
		BufferedImage img = ImageIO.read(f); //lire l'image
		Image sImage = img.getScaledInstance(1024/3,768/4, Image.SCALE_SMOOTH); //redimentionner l'image
		
		final JLabel label = new JLabel(new ImageIcon(sImage)); // créer le composant pour ajouter l'image dans la fenêtre
		
		label.addMouseListener(new MouseListener() { //Ajouter le listener d'évenement de souris
			private boolean isSelected = false;
			
			@Override
			public void mouseClicked(MouseEvent arg0) { //ce qui nous intéresse c'est lorsqu'on clique sur une image, il y a donc des choses à faire ici
				EventQueue.invokeLater(new Runnable() { 
					
					@Override
					public void run() {
						if(!isSelected){
							label.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
							isSelected = true;
							selectedImages.add(imageLocation);
							//for(String path : selectedImages) {System.out.println(path);}
							//System.out.println();
						}
						else {
							label.setBorder(BorderFactory.createEmptyBorder());
							isSelected = false;
							selectedImages.remove(imageLocation);
							//for(String path : selectedImages) {System.out.println(path);}
							//System.out.println();
						}
						
					}
				});
				
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});
		
		return label;
	}
}
