import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.cars-data.com/en/car-brands-cars-logos.html").ignoreContentType(true).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc == null) throw new AssertionError();

        App app = new App(doc);
        app.Menu();
    }
}
