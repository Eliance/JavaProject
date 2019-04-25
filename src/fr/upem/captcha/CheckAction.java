package fr.upem.captcha;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.upem.captcha.images.panneaux.Panneaux;
import fr.upem.captcha.images.panneaux.ronds.Ronds;
import fr.upem.captcha.images.panneaux.triangles.Triangles;

class CheckAction extends AbstractAction {
	ArrayList<String> specificImages;
	ArrayList<String> selectedImages;
	JFrame frame;
	
	CheckAction(JFrame frame, ArrayList<String> specificImages, ArrayList<String> selectedImages){
		this.specificImages = specificImages;
		this.selectedImages = selectedImages;
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Collections.sort(specificImages);
				Collections.sort(selectedImages);
				//System.out.println(specificImages.toString());
				//System.out.println(selectedImages.toString());
				
				if (specificImages.equals(selectedImages)) {
					JOptionPane.showMessageDialog(null,
						    "Vous avez réalisé le capcha avec succès !",
						    " Capcha",
						    JOptionPane.WARNING_MESSAGE);
					frame.setVisible(false);
					return;
				}
				else {
					JOptionPane.showMessageDialog(null,
							"FAUX ! Veuillez réessayer de résoudre un nouveau capcha",
						    " Capcha",
						    JOptionPane.WARNING_MESSAGE);
					frame.setVisible(false);
					selectedImages.clear();
					Capcha.harderCapcha();
					return;
				}
			}
		});
	}

}
