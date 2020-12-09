import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.cars-data.com/en/car-brands-cars-logos.html").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc == null) throw new AssertionError();

        CarBrands brands = new CarBrands(doc);
        Scanner s = new Scanner(System.in);

        Menu(brands, s);
        }
    public static void Menu(CarBrands brands, Scanner s){
        System.out.println("Car Encyclopedia\nData scraping project");
        System.out.println("Choose option:");
        System.out.println("1.View all car brands");
        System.out.println("2.View brands by letter");
        try{
            int counter;
            switch (s.nextInt()) {
                case 1 -> {
                    counter = 1;
                    for (String carBrand : brands.getAllBrands()) {
                        System.out.println(counter + ": " + carBrand);
                        counter++;
                    }
                    BrandSubMenu(brands.getAllBrands(), s);
                }
                case 2 -> {
                    System.out.println("Enter letter A-Z: ");
                    counter = 1;
                    char x = s.next().charAt(0);
                    for (String carBrand : brands.groupBrandsByLetter(x)) {
                        System.out.println(counter + ": " + carBrand);
                        counter++;
                    }
                    BrandSubMenu(brands.groupBrandsByLetter(x), s);
                }
                default -> {
                    System.err.println("Number out of range (1-2)");
                    Menu(brands, s);
                }
            }
        }catch (Exception e){
            System.err.println("Not a number " + e);
            Menu(brands, s);
        }
    }
    public static void BrandSubMenu(ArrayList<String> brands, Scanner s){
        System.out.println("Choose option:");
        System.out.println("1.View car models");
        System.out.println("2.Save to file");
        System.out.println("3.Quit program");
        try{
            int counter;
            switch(s.nextInt()){
                case 1:
                    counter = 1;
                    System.out.println("Select car brand");
                    for(String brandModel : new BrandModels(brands.get(s.nextInt() - 1)).getModels()){
                        System.out.println(counter + ": " + brandModel);
                        counter++;
                    }
                    ModelSubMenu(s);
                    break;
                case 2:
                    System.out.println("WIP");
                    //TODO
                case 3:
                    break;
                default:
                    System.err.println("Number out of range (1-2)");
                    BrandSubMenu(brands, s);
                    break;
            }
        }
        catch(Exception e){
            System.err.println("Not a number " + e);
            BrandSubMenu(brands, s);
        }
    }
    public static void ModelSubMenu(Scanner s){
        System.out.println("Choose option:");
        System.out.println("1.Save to file");
        System.out.println("2.Quit program");
        try{
            switch(s.nextInt()){
                case 1:
                    System.out.println("WIP");
                    //TODO
                    break;
                case 2:
                    break;
                default:
                    System.err.println("Number out of range (1-2)");
                    ModelSubMenu(s);
                    break;
            }
        }catch(Exception e){
            System.err.println("Not a number " + e);
            ModelSubMenu(s);
        }
    }
}
