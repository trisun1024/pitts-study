package PreProcess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Classes.Path;

public class ProcessText implements DocumentCollection {

    private FileReader freader;
    BufferedReader wreader;
    File files = new File(Path.textInput);


    public ProcessText() throws IOException {

        freader = new FileReader(files);
        wreader = new BufferedReader(freader);
    }


    public Map<String, Map<Object, Map<Object, Object>>> nextDoc() throws IOException {

        Map<String, Map<Object, Map<Object, Object>>> wholeMap = new HashMap<>();
        Map<Object, Map<Object, Object>> content = new HashMap<>();
        Map<Object, Object> titleText = new HashMap<>();

        String line = "";
        String url = "";
        String title = "";
        String text = "";
        String docno;

        if ((line = wreader.readLine()) != null) {
            while (line.substring(0,1).equals(" ")) {
                line=wreader.readLine();
            }
            if (line.contains("http://") || line.contains("https://")) {
                url = line;
            }
            while (!line.contains("The New York Times")) {
                line = wreader.readLine();
            }
            if (line.contains("The New York Times")) {
                title = line.split(" - ")[0];
            }
            line = wreader.readLine();
            line = wreader.readLine();
            docno = line;
            line = wreader.readLine();
            text += line + " ";
            //titleText.put(title.replaceAll("[^a-zA-Z\\s]", " ").toCharArray(), text.replaceAll("[^a-zA-Z\\s]", " ").toCharArray());
            titleText.put(title.toCharArray(),text.toCharArray());
            content.put(url, titleText);
            wholeMap.put(String.valueOf(docno), content);
            return wholeMap;
        }
        if (wreader != null)
            wreader.close();
        return null;


    }


}
