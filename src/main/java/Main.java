import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Document doc = null;
        try{
            doc = Jsoup.connect("https://www.autoevolution.com/cars/").get(); //encyklopedia aut
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc == null) throw new AssertionError();
        Element element1 = doc.getAllElements().first();
        int size = element1.getElementsByClass("col2width fl bcol-white carman").size();
        for(int i=0; i<size; i++){
            System.out.println(element1.getElementsByClass("col2width fl bcol-white carman").get(i).getAllElements().get(1).attr("title"));
            System.out.println("#######################################");
        }
    }
}
