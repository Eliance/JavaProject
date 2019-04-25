package fr.upem.captcha;

import java.io.IOException;
import java.util.ArrayList;

import fr.upem.captcha.images.Capchable;
import fr.upem.captcha.images.panneaux.Panneaux;
import fr.upem.captcha.images.panneaux.ronds.Ronds;
import fr.upem.captcha.images.panneaux.triangles.Triangles;

public class Main {
	
	static int nbImageMin = 1;
	static int difficulty = 0;
	static Capchable currentCategory;
	
	static ArrayList< ArrayList<Capchable>> categoriesByDifficulty = new ArrayList< ArrayList<Capchable>>();
	//Ici c'est les catégories type Panneaux par difficulté. 
	//On pourrait créer d'autres catégories et faire plusieurs listes #nomDeLaCategorie#ByDifficulty
	
	static ArrayList<Capchable> level1 = new ArrayList<Capchable>();
	static ArrayList<Capchable> level2 = new ArrayList<Capchable>();
	//on pourrait imaginer un lvl 3 de difficulté : panneaux Ronds/Triangles Rouges/Bleus/Verts
	
	public static void main(String[] args) throws IOException {		
		level1.add(new Panneaux());
		
		level2.add(new Ronds());
		level2.add(new Triangles());
		
		categoriesByDifficulty.add(level1);
		categoriesByDifficulty.add(level2);
		
		currentCategory = level1.get(0);
		
		Capcha capcha = new Capcha(currentCategory);
	}
	
}


