package ModelGUI;

import Classes.Document;
import Classes.Path;
import Main.*;
import Classes.Query;
import Index.indexReader;
import PreProcess.DocumentCollection;
import PreProcess.ProcessText;
import Search.ExtractQuery;
import Search.QueryRetrievalModel;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SearchModelGUI {

    private JFrame mainFrame, detailFrame;
    private JPanel searchPanel, resultPanel, detailPanel;
    private JButton btnConfirm, btnReturn, btnDetailUrl;
    private JTextField jtfSearch;
    private JTextArea jtaResult, jtaDetailContent, jtaTitle;
    private JLabel jlLogo;

    private String titles;
    private int line;
    private static indexReader ixreader;
    private static QueryRetrievalModel model;
    private static ExtractQuery queries;
    private static Map<String, String> urlTitleTable = new HashMap<>();
    private static Map<String, String> urlTitleReverseTable = new HashMap<>();
    private static Map<String, String> urlContentTable = new HashMap<>();

    private static void getFile(String path, Map<String, String> result) throws IOException {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String[] strS = line.split("###");
            if (strS.length != 0) {
                String url = strS[0].replaceAll("AdvertisementSupported by","").replaceAll("Advertisement","");
                String title = strS[1].replaceAll("AdvertisementSupported by","").replaceAll("Advertisement","");
                result.put(url, title);
            }
        }
        br.close();
        fr.close();
    }

    public static void main(String[] args) throws Exception {
        // load docUrl Relation table
        getFile(Path.urlTitle, urlTitleTable);
        for (Map.Entry<String, String> entry : urlTitleTable.entrySet()) {
            urlTitleReverseTable.put(entry.getValue(), entry.getKey());
        }
        getFile(Path.urlContent, urlContentTable);

/*
        for(Map.Entry<String,String> temp : urlTitleTable.entrySet()){
            System.out.println(temp.getKey());
        }
*/

        // start GUI
        SearchModelGUI searchModelGUI = new SearchModelGUI();
    }

    private SearchModelGUI() throws IOException {
        modelFrame();
    }

    private static List<Document> retrieveData(String input) throws IOException {
        ixreader = new indexReader("txt");
        model = new QueryRetrievalModel(ixreader);

        queries = new ExtractQuery(input);
        while (queries.hasNext()) {
            Query aQuery = queries.next();
            List<Document> results = model.retrieveQuery(aQuery, 50);
            return results;
        }
        ixreader.close();
        return null;
    }

    private void modelFrame() throws IOException {
        mainFrame = new JFrame("Retrieval Model for NYTimes Psychology");
        mainFrame.setBounds(200, 100, 710, 850);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(null);

        searchPanel = new JPanel(null);
        searchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        resultPanel = new JPanel(null);
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        jtfSearch = new JTextField("Enter words", 30);
        btnConfirm = new JButton("Confirm");

        jtaResult = new JTextArea(10, 10);
        jtaResult.setEditable(false);
        jtaResult.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(jtaResult);
        scroll.setBounds(0, 0, 700, 700);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // add Logo
        int width = 600, height = 100;
        ImageIcon image = new ImageIcon("data//pics//NYTimes_LOGO.png");
        image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        jlLogo = new JLabel(image);

        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = jtfSearch.getText();
                if (input.equals("") || input == null)
                    JOptionPane.showMessageDialog(null, "Come on man, please type in!!!", "Alert", JOptionPane.ERROR_MESSAGE);
                try {
                    List<Document> out = retrieveData(input);
                    if (out == null) {
                        JOptionPane.showMessageDialog(null, "Awha! Can't find a matcher! \nWhy not try some easy one?", "Alert", JOptionPane.ERROR_MESSAGE);
                    } else {
                        StringBuilder str = new StringBuilder();
                        if (out != null) {
                            int rank = 1;
                            for (Document result : out) {
                                String temp = urlTitleTable.get(result.docno());
                                str.append(temp + "\n");
                                //str.append(result.docno() + "\n");
                                rank++;
                            }
                        }
                        jtaResult.setText(String.valueOf(str));
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getStackTrace());
                }
            }
        });

        jtaResult.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (jtaResult.getText().trim().length() == 0) return;
                int offset = e.getDot();
                try {
                    line = jtaResult.getLineOfOffset(offset);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });

        jtaResult.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    DefaultHighlighter h = (DefaultHighlighter) jtaResult.getHighlighter();
                    String[] lines = jtaResult.getText().split("\n");
                    String out = lines[line];
                    System.out.println(out);
                    titles = String.valueOf(out);
                    detailFrame(titles);
                    detailFrame.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        // Panel location
        searchPanel.setLayout(null);
        resultPanel.setLayout(null);
        searchPanel.setBounds(5, 5, 700, 230);
        resultPanel.setBounds(5, 245, 700, 600);
        // actions location
        jlLogo.setBounds(5, 5, 700, 110);
        jtfSearch.setBounds(70, 140, 400, 50);
        btnConfirm.setBounds(500, 140, 100, 50);
        jtaResult.setBounds(10, 10, 700, 500);

        // add
        searchPanel.add(jlLogo);
        searchPanel.add(jtfSearch);
        searchPanel.add(btnConfirm);
        resultPanel.add(scroll);
        mainFrame.getContentPane().add(searchPanel);
        mainFrame.getContentPane().add(resultPanel);
        mainFrame.setVisible(true);
    }

    private void detailFrame(String titles) {
        detailFrame = new JFrame("Details");
        detailFrame.setBounds(250, 150, 600, 450);
        detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        detailFrame.getContentPane().setLayout(null);

        // button
        btnReturn = new JButton("Return to Main");
        btnReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                detailFrame.setVisible(false); // close the window
            }
        });

        btnDetailUrl = new JButton(urlTitleReverseTable.get(titles));
        btnDetailUrl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWebPage(urlTitleReverseTable.get(titles));
            }
        });

        // field
        jtaTitle = new JTextArea("");
        jtaTitle.setText(titles);
        jtaTitle.setEditable(false);
        jtaTitle.setLineWrap(true);
        jtaTitle.setWrapStyleWord(true);

        jtaDetailContent = new JTextArea("");
        jtaDetailContent.setText(urlContentTable.get(urlTitleReverseTable.get(titles)));
        jtaDetailContent.setEditable(false);
        jtaDetailContent.setLineWrap(true);
        jtaDetailContent.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(jtaDetailContent);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        detailPanel = new JPanel();
        detailPanel.setLayout(null);
        detailPanel.add(btnReturn);
        detailPanel.add(btnDetailUrl);
        detailPanel.add(jtaTitle);
        detailPanel.add(scroll);
        btnReturn.setBounds(10, 10, 200, 30);
        jtaTitle.setBounds(20, 50, 540, 40);
        btnDetailUrl.setBounds(220, 10, 360, 30);
        scroll.setBounds(20, 100, 540, 300);
        detailFrame.getContentPane().add(detailPanel);
        detailPanel.setBounds(0, 0, 600, 450);
    }

    public static void openWebPage(String url) {
        try {
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URI(url));
            }
            throw new NullPointerException();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, url, "", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
