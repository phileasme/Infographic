package com.phileas.infographic.model;


import java.util.ArrayList;

/**
 * Class definition of containment of countries.
 * @Author : Phileas Hocquard
 */
public class Countries {
    ArrayList<Country> countries;
    Boolean hasDoneFirstAdd;
        public Countries(){
            countries = new ArrayList<Country>();
            hasDoneFirstAdd = false;
        }

    /** Method that verifies if a country with a specific name exist in the collect of Countries
     * @param name
     * @return existence of a country with a specific name
     */
    public boolean countryWithNameExist(String name){
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
    public void add(Country country){
        if(hasDoneFirstAdd == false){
        countries.add(country);}
        else{
            updateCountries(country);
        }
    }

    /**
     * Method that allows us to retrieve the collection of countries
     * @return the collection of countries.
     */
    public ArrayList<Country> getCountries(){
        return countries;
    }

    /** Updates a country in our ArrayList of countries
     * @param country
     */
    public void updateCountries(Country country) {
        for(Country c : countries){
            if( c.getName().equals(country.getName())){
                c = country;
            }
            else if(c.equals(countries.get(countries.size()-1))){
                countries.add(country);
            }
        }
    }

    /**
     * Changes the value of hasDoneFirstAdd to true
     * Showing that we have already Added all the local json files once.
     */
    public void HasDoneFirst(){
        hasDoneFirstAdd = true;
    }

}
