import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.swing.text.Document;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class reader {
    private static String line;


    public static String html(String Input) throws IOException {
        URL url = new URL(Input);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            if (reader != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Something ain't working here");
            }
            String x = reader.lines().collect(Collectors.joining());
            return x;
        } catch (Exception e) {
            System.out.println("Something ain't right");
            return null;
        }
    }

    public static BufferedReader out(String input) throws MalformedURLException {
        URL x = new URL(input);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(x.openStream()));
            return reader;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    public static String key(BufferedReader x, String keyword) throws IOException {
        String b = "";
        line = "";
        int a = 0;
        while (line != null) {
            if (x.toString().contains(keyword)) {
                line = x.readLine();
                if (line.contains(keyword)) {
                    int c = line.indexOf(keyword, a);
                    int d = line.indexOf(keyword.length(), c);
                    b = b + line.substring(c, d);
                    a = d;
                }
            }
            else {
                line = null;
            }
        }
        return b;
    }
}
