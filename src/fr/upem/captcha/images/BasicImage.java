package fr.upem.captcha.images;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class BasicImage implements Capchable{
	
	public BasicImage() {}
	
	@Override
	public ArrayList<String> getPhotos() {
		ArrayList<String> photos =  new ArrayList<String>();
		
		File folder = new File("src\\fr\\upem\\captcha\\images");
		String[] files = folder.list();
		for(String path : files){
			if (path.endsWith(".jpeg")==true) {
				photos.add("src\\fr\\upem\\captcha\\images\\"+path);
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

	//Not use their, then we can call only 1 time getPhotos() to pick several random photos
	/*
	@Override
	public String getRandomPhotoURL() {
		// TODO Auto-generated method stub
		return null;
	}
	*/

	@Override
	public boolean isPhotoCorrect(String path) {
		// TODO Auto-generated method stub
		return false;
	}

}
