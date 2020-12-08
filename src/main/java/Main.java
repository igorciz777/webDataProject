import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
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

    }
    public static int Menu(){
        System.out.println("Car Encyclopedia\nData scraping project");
        System.out.println("Choose option:");
        System.out.println("1.View all car brands");
        System.out.println("2.View brands by letter");
        System.out.println("3.Export data to file");
        Scanner s = new Scanner(System.in);
        try{
            return s.nextInt();
        }catch (Exception e){
            System.out.println("Wrong number " + e);
            return 0;
        }
    }
}
