package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadWebPage {

    public void printFromWebPage() throws IOException {

        BufferedReader br = null;

        try {
            String web = "file:///C:/Users/Eric%20Kuo/Documents/UBC%202018%20SUMMER/CPSC%20210/libraryVersion2.html";
            URL url = new URL(web);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }
}