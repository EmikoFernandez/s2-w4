import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Demonstrates reading data from a CSV file using Scanner
 * Creates an ArrayList of Country objects
 * Week 4: ArrayList + Scanner + File I/O
 */
public class LifeExpectancyAnalyzer {
    private ArrayList<Country> countries;

    /**
     * Constructor initializes an empty ArrayList
     */
    public LifeExpectancyAnalyzer() {
        countries = new ArrayList<Country>();
    }

    /**
     * Reads country data from a CSV file and populates the ArrayList
     * CSV format: country,region,population,income_group,life_expectancy_2010,life_expectancy_2015,life_expectancy_2020
     * 
     * @param filename path to the CSV file
     * @throws IOFoundException if the file is not found
     */
    public void readFromFile(String filename) throws IOException {
        File f = new File(filename);
        Scanner s = new Scanner(f);
        s.nextLine();
        while(s.hasNextLine()){
            String line = s.nextLine();
            String[] items = line.split(",");
            Country temp = new Country(
                items[0],
                items[1],
                Integer.parseInt(items[2]), 
                items[3], 
                Double.parseDouble(items[4]), 
                Double.parseDouble(items[5]), 
                Double.parseDouble(items[6]) 
            );
            countries.add(temp);
        }
        //System.out.println(countries.size());
        s.close();
    }

    /**
     * Display all countries in the list
     */
    public void displayAllCountries() {
        for(int i = 0; i < countries.size(); i++){
            System.out.println(countries.get(i).getName());
        }
    }

    /**
     * Display countries from a specific region
     * @param region the region to filter by
     */
    public void displayByRegion(String region) {
        ArrayList <Country> temp = new ArrayList <Country>();
        for (Country c: countries){
            if(c.getRegion().equals(region)){
                temp.add(c);
                System.out.println(c.getRegion());
            }
        }
    }

    /**
     * Find the country with the highest life expectancy in 2020
     * @return the Country with the highest life expectancy, or null if list is empty
     */
    public Country findHighestLifeExpectancy() {
       Country c = countries.get(0);
       for(int i = 1; i < countries.size(); i++){
            if(c.getAverageLifeExpectancy() < countries.get(i).getAverageLifeExpectancy()){
                c = countries.get(i);
            }
       }
       return c;
    }

    /**
     * Find the country with the lowest life expectancy in 2020
     * @return the Country with the lowest life expectancy, or null if list is empty
     */
    public Country findLowestLifeExpectancy() {
        Country c = countries.get(0);
       for(int i = 1; i < countries.size(); i++){
            if(c.getAverageLifeExpectancy() > countries.get(i).getAverageLifeExpectancy()){
                c = countries.get(i);
            }
       }
       return c;
    }

    /**
     * Calculate the average life expectancy across all countries
     * @return average life expectancy in 2020, or 0 if list is empty
     */
    public double calculateAverageLifeExpectancy() {
        double total = 0;
        for(Country c:countries){
            total+= c.getAverageLifeExpectancy();
        }
        return total/countries.size();
    }

    /**
     * Count how many countries are in a specific income group
     * @param incomeGroup the income group to count
     * @return number of countries in that income group
     */
    public int countByIncomeGroup(String incomeGroup) {
        int i = 0;
        for (Country c: countries){
            if(c.getIncomeGroup().equals(incomeGroup)){
                i++;
            }
        }
        return i;
    }

    /**
     * Find the country with the most improvement in life expectancy (2010 to 2020)
     * @return the Country with the largest improvement, or null if list is empty
     */
    public Country findMostImprovement() {
        Country country = countries.get(0);
        for(Country c:countries){
            if(country.getChange2010To2020() < c.getChange2010To2020()){
                country = c;
            }
        }
        return country;
        
    }

    /**
     * Display summary statistics
     */
    public void displayStatistics() {
        // System.out.println(findHighestLifeExpectancy().getName());
        // System.out.println(findLowestLifeExpectancy().getName());
        // System.out.println(calculateAverageLifeExpectancy());
        System.out.println(findMostImprovement().getName());
    }

}
