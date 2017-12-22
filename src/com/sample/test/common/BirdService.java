package com.sample.test.common;

import java.util.List;

public interface BirdService {
	public List<String> getBirdList();
	public String getBirdDetails(String birdID);
	public boolean removeBird(String birdId); 
	public String addBird(Bird b);
	
	
	
}
