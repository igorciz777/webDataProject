import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class App {
    private static Scanner s;
    private static DBUserDAO userDAO;
    private static DBCarBrandsDAO carBrandsDAO;
    private static DBBrandModelsDAO brandModelsDAO;
    private static CarBrands carBrands;
    private boolean loggedIn;

    App(Document doc){
        s = new Scanner(System.in);
        userDAO = new DBUserDAO();
        carBrandsDAO = new DBCarBrandsDAO();
        brandModelsDAO = new DBBrandModelsDAO();
        carBrands = new CarBrands(doc);
        loggedIn = false;
    }
    public void Menu() throws IOException, SQLException {
        String login,password;
        Instant start,end;
        Duration timeElapsed;
        System.out.println("Car Database - Web Scraping Project");
        if(loggedIn){
            System.out.println("Choose option:\n1.Write to database\n2.Read from database");
            s.reset();
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
                            start = Instant.now();
                            brandModelsDAO.printAllModels();
                            end = Instant.now();
                            timeElapsed = Duration.between(start,end);
                            System.out.println("Task completed in: "+ timeElapsed.toSeconds() + " seconds");
                            break;
                        case 2:
                            System.out.print("Select ID: ");
                            start = Instant.now();
                            brandModelsDAO.printByID(s.nextInt());
                            end = Instant.now();
                            timeElapsed = Duration.between(start,end);
                            System.out.println("Task completed in: "+ timeElapsed.toSeconds() + " seconds");
                            break;
                        case 3:
                            break;
                        default:
                            System.err.println("Wrong option");
                            break;
                    }
                    break;
                default:
                    System.err.println("Wrong option");
                    break;
            }
        }
        else {
            System.out.println("Choose option:\n1.Login\n2.Register");
            s.reset();
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
                default:
                    System.err.println("Wrong option");
                    break;
            }
        }
    }
}
