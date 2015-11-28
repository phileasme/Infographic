package com.phileas.infographic.model;


import java.util.ArrayList;

public class Countries {
    ArrayList<Country> countries;
    Boolean hasDoneFirstAdd;
        public Countries(){
            countries = new ArrayList<Country>();
            hasDoneFirstAdd = false;
        }
    public ArrayList<Country> getCountries (){
        return countries;
    }

    /** Method that verifies if a country with a specific name exist in the collect of Countries
     * @param name
     * @return
     */
    public boolean countrywithNameExist(String name){
        for(Country c : countries){
            if( c.getName().equals(name)){
                return  true;
            }
        }
        return false;
    }

    /** Method to add countries while reducing iteration
     * we consider that a first time we add countries
     * we do not require to iterate through a complete list.
     * @param country
     */
    public void AddCountry(Country country){
        if(hasDoneFirstAdd == false){
        countries.add(country);}
        else{
            updateCountries(country);
        }
    }

    /** Updates a country in our Arraylist of countries
     * @param country
     */
    public void updateCountries(Country country) {
        for(Country c : countries){
            if( c.getId().equals(country.getId())){
                c = country;
            }
            else if(c.equals(countries.get(countries.size()-1))){
                countries.add(country);
            }
        }
    }

}
