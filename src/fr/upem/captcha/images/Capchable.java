package fr.upem.captcha.images;

import java.util.ArrayList;

public interface Capchable {
	
	static final String msg = "";
	
	public ArrayList<String> getPhotos();
		
	public ArrayList<String> getRandomPhotosURL(int nb);
	
	
	//Not use their, then we can call only 1 time getPhotos() to pick several random photos
	/*
	public String getRandomPhotoURL();
	*/
	
	public boolean isPhotoCorrect(String path);
	
}
