import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Document doc;
        try {
            doc = Jsoup.connect("https://ru.wikipedia.org/wiki/Java").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements el = doc.getElementsByTag("img");
        ArrayList<String> imgList = new ArrayList<>();
        el.forEach(e -> imgList.add(e.absUrl("src")));
        int i = 1;

        for(String s : imgList) {
                try {
                    String folder = "data/img/";
                    File d = new File(folder + i + ".jpg");
                    System.out.println(s);
                    i++;
                    URL url = new URL(s);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream is = conn.getInputStream();
                    OutputStream os = new FileOutputStream(d);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) > 0) {
                        os.write(buffer, 0, length);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
//    public static String getText(String path){
//        StringBuilder sb = new StringBuilder(path);
//        try {
//            List<String> lines = Files.readAllLines(Path.of(path));
//            lines.forEach(l -> sb.append(l).append("\n"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return sb.toString();
//    }
}
