import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final String basedURL = "https://www.cgesp.org/v3/alagamentos.jsp?dataBusca=";
    private static String file = "C:\\Users\\Pichau\\Desktop\\scraping-alagamentoSP\\scraped\\ScrapingData";
    private static LocalDate getDate(String date){
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Programa de Scraping de dados de alagamento em SP: \n");
        System.out.println("Digite uma data inicial de busca:\t(dd/MM/yyyy)");
        var inicialDate = sc.nextLine();
        var currDate = getDate(inicialDate);

        System.out.println("Quantos meses deseja buscar:");
        var months = sc.nextInt();
        sc.close();

        ScrapingConfig config = new ScrapingConfig(file+".txt", currDate, basedURL);

        for(int i = 0; i < months; i++){
            config.setDate(currDate);
            currDate = currDate.minusMonths(1);

            List<Model> list = Scraping.search(config.getUrl());
            config.persistData(list);

            config.setFile(file+(i+1)+".txt");
        }
    }
}
