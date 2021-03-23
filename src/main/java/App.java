import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private Scanner s;
    private DBUserDAO userDAO;
    private boolean loggedIn;
    private String login,password;
    App(){
        s = new Scanner(System.in);
        userDAO = new DBUserDAO();
        loggedIn = false;
    }
    public void Menu(){
        System.out.println("Car Database - Web Scraping Project\nChoose option:\n1.Login\n2.Register");
        switch(s.nextInt()){
            case 1:
                System.out.print("Login:");
                login = s.next();
                System.out.print("Password:");
                password = s.next();
                loggedIn = userDAO.Login(login,password);
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
