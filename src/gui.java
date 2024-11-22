import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import static java.awt.BorderLayout.*;

public class gui {
    public static JTextField urlInput;
    public static JTextField keyword;
    private static JButton search;
    private static JButton print;
    private static JButton clear;
    private static JScrollPane keywordOutput;
    private static JScrollPane keywordLinks;
    private static JTextArea output;
    private static JTextArea keyOutput;
    private String display;

    public static int getScreenSizeX() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) screenSize.getWidth();
    }
    public static int getScreenSizeY () {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) screenSize.getHeight();
    }
    public void userGui() {
        output = new JTextArea();
        keyOutput = new JTextArea();

        JPanel input = new JPanel();
        input.add(urlInput = new JTextField(20));
        input.add(keyword = new JTextField(20));
        keyword.setEditable(true);
        keyword.setToolTipText("Keyword");
        urlInput.setEditable(true);
        urlInput.setToolTipText("URL");


        JPanel buttons = new JPanel();
        buttons.add(search = new JButton());
        buttons.add(print = new JButton());
        search.setText("search");
        print.setText("print");
        search.addActionListener(new SEARCH());
        print.addActionListener(new PRINT());


        JFrame main = new JFrame();
        main.setLayout(new GridLayout(3, 2));
        main.add(input, NORTH);
        main.add(buttons, CENTER);
        main.setVisible(true);
        main.setSize(getScreenSizeX()/4, getScreenSizeY()/4);
        main.add(keywordOutput = new JScrollPane(output));
        main.add(keywordLinks = new JScrollPane(keyOutput));
        output.setEditable(false);
        keyOutput.setEditable(false);
        keywordLinks.setToolTipText("Keyword");
        keywordOutput.setToolTipText("Raw Output");
        keywordLinks.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        keywordOutput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    class SEARCH implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display = urlInput.getText();
            try {
                String x = reader.html(display);
                output.setText(x);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class PRINT implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                BufferedReader x = reader.out(urlInput.getText());
                System.out.println(x);
                String c = (reader.key(x, keyword.getText()));
                System.out.println(c);
                keyOutput.setText(c);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
