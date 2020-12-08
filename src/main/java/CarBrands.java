import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class CarBrands {
    Document doc;
    ArrayList<String> brandList;

    CarBrands(Document doc){
        this.doc = doc;
        this.brandList = setBrands();
    }

    private ArrayList<String> setBrands(){
        Element HtmlElement = doc.getAllElements().first();
        Elements carBrandsElement = HtmlElement.getElementsByClass("col-2 center");
        ArrayList<String> brandList = new ArrayList<>();
        for (Element element : carBrandsElement) {
            brandList.add(element.getAllElements().get(1).attr("title"));
        }
        return brandList;
    }
    public ArrayList<String> getAllBrands(){
        return brandList;
    }
    public void groupBrandsByLetter(char x){
        for (String element : brandList) {
            if(element.toLowerCase().charAt(0) == x){
                System.out.println(element);
            }
            else if(element.toUpperCase().charAt(0) == x){
                System.out.println(element);
            }
        }
    }
}
