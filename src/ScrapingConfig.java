import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ScrapingConfig {
    private File file;
    private LocalDate date;
    private String url;
    private String basedURL;

    private OutputStreamWriter writer;

    public ScrapingConfig(String outputPath, LocalDate initialDate, String basedURL) {
        setFile(outputPath);
        this.basedURL = basedURL;
        date = initialDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        setUrl();
    }

    public String getUrl() {
        return url;
    }

    private void setUrl() {
        String formattedDate = getFormattedDate(this.date);
        String[] split = formattedDate.split("/");
        this.url = this.basedURL + split[0] + "%2F" + split[1] + "%2F" + split[2] + "&enviaBusca=Buscar";
    }

    public void persistData(List<Model> list) {
        try {
            this.writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            for (Model model : list) {
                writer.append(getFormattedDate(this.date)+ "," +model.getZone() + "," + model.getDistrict() + "," + model.getLocalName() + "\n");
            }
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getFormattedDate(LocalDate date) {
        var fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(fmt);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBasedURL() {
        return basedURL;
    }

    public void setBasedURL(String basedURL) {
        this.basedURL = basedURL;
    }

    public File getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = new File(file);
    }

    public OutputStreamWriter getWriter() {
        return writer;
    }

    public void setWriter(OutputStreamWriter writer) {
        this.writer = writer;
    }

}
