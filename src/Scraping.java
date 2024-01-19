import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scraping {
    
    private static String getLocalName(String localText){
        return localText.substring(17);
    }

    public static List<Model> search(String url) {
        List<Model> modelList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            var zoneElements = doc.getElementsByClass("tit-bairros");
            var districtElements  = doc.getElementsByClass("bairro arial-bairros-alag linha-pontilhada");
            var localElements  = doc.getElementsByClass("arial-descr-alag col-local");

            for(Element zonetElement : zoneElements){
                var zoneName = zonetElement.text();
                for (Element districtElement : districtElements) {
                    var districtName = districtElement.text();
                    for (int j = 0; j < localElements.size(); j++) {
                        var localName = getLocalName(localElements.get(j).text());
                        modelList.add(new Model(zoneName, districtName, localName));
                    }
                }
            }
        }catch (IOException e){
            System.out.println("Request failed -> "+e.getMessage());
        }
        return modelList;
    }
}
