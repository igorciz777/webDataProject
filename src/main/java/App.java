import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner s;
    private Document doc;
    private DBUserDAO userDAO;
    private DBCarBrandsDAO carBrandsDAO;
    private DBBrandModelsDAO brandModelsDAO;
    private CarBrands carBrands;
    private List<BrandModels> modelsList;
    private boolean loggedIn;
    private String login,password;
    private Instant start,end;
    private Duration timeElapsed;

    App(Document doc){
        s = new Scanner(System.in);
        userDAO = new DBUserDAO();
        carBrandsDAO = new DBCarBrandsDAO();
        brandModelsDAO = new DBBrandModelsDAO();
        this.doc = doc;
        carBrands = new CarBrands(doc);
        loggedIn = false;
    }
    public void Menu() throws IOException, SQLException {
        System.out.println("Car Database - Web Scraping Project");
        if(loggedIn){
            System.out.println("Choose option:\n1.Write to database\n2.Read from database");
            switch (s.nextInt()){
                case 1:
                    System.out.println("Writing...");
                    start = Instant.now();
                    brandModelsDAO.deleteAllModels();
                    carBrandsDAO.deleteAllBrands();
                    carBrands.writeToDatabase();
                    for (String brandName : carBrands.brandList) {
                        carBrandsDAO.addModelsToBrand(brandName);
                    }
                    end = Instant.now();
                    timeElapsed = Duration.between(start,end);
                    System.out.println("Task completed in: " + timeElapsed.toSeconds() + " seconds");
                    break;
                case 2:
                    System.out.println("Reading...");
                    start = Instant.now();
                    carBrandsDAO.printAllBrands();
                    end = Instant.now();
                    timeElapsed = Duration.between(start,end);
                    System.out.println("Task completed in: "+ timeElapsed.toSeconds() + " seconds");
                    System.out.println("Choose option:\n1.View all models\n2.View models by brand ID\n3.Quit");
                    switch (s.nextInt()){
                        case 1:
                            brandModelsDAO.printAllModels();
                            break;
                        case 2:
                            System.out.print("Select ID: ");
                            brandModelsDAO.printByID(s.nextInt());
                            break;
                        case 3:
                            break;
                    }
                    break;
            }
        }
        else {
            System.out.println("Choose option:\n1.Login\n2.Register");
            switch(s.nextInt()){
                case 1:
                    System.out.print("Login:");
                    login = s.next();
                    System.out.print("Password:");
                    password = s.next();
                    loggedIn = userDAO.Login(login,password);
                    Menu();
                    break;
                case 2:
                    System.out.print("Login:");
                    login = s.next();
                    System.out.print("Password:");
                    password = s.next();
                    userDAO.Register(login,password);
                    Menu();
                    break;
            }
        }
    }
}
