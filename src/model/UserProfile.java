package model;

import java.time.LocalDate;

public class UserProfile {
	private int id;
    private String name;
    private String gender;
    private LocalDate dateOfBirth;
    private double heightCm;
    private double weightKg;
  

    // Constructor

    public UserProfile(String name, String gender, LocalDate dob, double height, double weight) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dob;
        this.heightCm = height;
        this.weightKg = weight;
       
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getGender() {
    	return this.gender;
    }
    
    public LocalDate getDateOfBirth() {
    	return this.dateOfBirth;
    }
    
    public double getHeightCm() {
    	return this.heightCm;
    }
    
    public double getWeightKg() {
    	return this.weightKg;
    }
    
    

}
