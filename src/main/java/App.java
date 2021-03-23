import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.SQLException;
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
                    brandModelsDAO.deleteAllModels();
                    carBrandsDAO.deleteAllBrands();
                    carBrands.writeToDatabase();
                    for (String brandName : carBrands.brandList) {
                        carBrandsDAO.addModelsToBrand(brandName);
                    }
                    break;
                case 2:
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
