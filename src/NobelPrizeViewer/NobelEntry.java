/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NobelPrizeViewer;

/**
 *
 * @author WinstonK
 */
public class NobelEntry {
    private String name = "";
    private String gender = "";
    private String year = "";
    private String country = "";
    private String affiliation = "";
    private String prize = "";
    private String birthYear = "";
    private String deathYear = "";
    private String profileLink = null;
    
    public NobelEntry (String name, String gender, String year, String country, String affiliation, String prize, String birthYear, String deathYear, String profileLink) {
        this.name = name;
        this.gender = gender;
        this.year = year;
        this.country = country;
        this.affiliation = affiliation;
        this.prize = prize;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.profileLink = profileLink;
    }
    
    public String getName() {
        return name;
    }
    
    public String getGender() {
        return gender;
    }
    
    public String getYear() {
        return year;
    }
    
    public String getCountry() {
        return country;
    }
    
    public String getAffiliation() {
       return affiliation; 
    }
    
    public String getPrize() {
        return prize;
    }
    
    public String getProfileLink() {
        return profileLink;
    }
    
    public String getBirthYear() {
        return birthYear;
    }
    
    public String getDeathYear() {
        return deathYear;
    }
    
    public String getLifeLine() {
        return birthYear + " - " + deathYear;
    }
    
    public String getEntryTitle() {
        return getPrize().toUpperCase() + " (" + getYear() + ")";
    }
    
    public Object[] getEntryObject() {
        Object[] entryObj = {year, prize, name};
        return entryObj;
    }
}
