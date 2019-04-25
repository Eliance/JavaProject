package fr.upem.captcha.images.panneaux.ronds;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import fr.upem.captcha.images.Capchable;
import fr.upem.captcha.images.panneaux.Panneaux;

public class Ronds extends Panneaux  implements Capchable{
	
	public Ronds() {}
	
	@Override
	public ArrayList<String> getPhotos() {
		ArrayList<String> photos =  new ArrayList<String>();
		
		File folder = new File("src\\fr\\upem\\captcha\\images\\panneaux\\ronds");
		String[] files = folder.list();
		for(String path : files){
			if (path.endsWith(".jpeg")==true || path.endsWith(".png")==true || path.endsWith(".jpg")==true) {
				photos.add("src\\fr\\upem\\captcha\\images\\panneaux\\ronds\\"+path);
			}	
		}
		return photos;
	}

	@Override
	public ArrayList<String> getRandomPhotosURL(int nb) {
		ArrayList<String> randomPhotos =  new ArrayList<String>();
		ArrayList<String> photos =  getPhotos();
		int size = photos.size();
		for (int i=0; i< nb; i++) {
			randomPhotos.add(photos.get(ThreadLocalRandom.current().nextInt(size)));
		}
		return randomPhotos;
	}


	@Override
	public String toString() {
		String str = new String(" ronds"); 
		return super.toString()+str;
	}
}
