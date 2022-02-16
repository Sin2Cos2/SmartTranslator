package ClientWindow;

import Server.Translator;

import javax.swing.*;
import java.awt.*;

public class ClientWindow extends JFrame {
    private final Translator translator;
    private TextArea fromArea;
    private TextArea toArea;
    private JButton swapButton;
    private JComboBox fromButton;
    private JComboBox toButton;
    private JButton translate;
    private final int WIDTH = 700;
    private final int HEIGHT = 500;

    public ClientWindow() {
        translator = new Translator();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new FlowLayout());

        String[] languages = {"English", "Romanian", "French"};
        swapButton = new JButton("<-->");
        fromButton = new JComboBox(languages);
        toButton = new JComboBox(languages);
        toButton.setSelectedIndex(1);
        fromButton.setBounds(200, 40, 20, 10);
        toButton.setBounds(160, 40, 20, 10);
        swapButton.setBounds(130, 40, 20, 10);

        swapButton.addActionListener(e -> {
            if (!fromArea.getText().isEmpty() && toArea.getText().isEmpty())
                return;
            int index = toButton.getSelectedIndex();
            toButton.setSelectedIndex(fromButton.getSelectedIndex());
            fromButton.setSelectedIndex(index);

            String text = fromArea.getText();
            fromArea.setText(toArea.getText());
            toArea.setText(text);
        });

        fromArea = new TextArea();
        toArea = new TextArea();
        toArea.setEditable(false);

        translate = new JButton("Translate");

        translate.addActionListener(e -> {
            if(fromArea.getText().isEmpty())
                return;
            String sentence = fromArea.getText();
            String fromLanguage = getLanguage(fromButton.getSelectedIndex());
            String toLanguage = getLanguage(toButton.getSelectedIndex());

            String translatedSentence = translator.translateSentence(sentence, fromLanguage, toLanguage);
            toArea.setText(translatedSentence);
        });

        add(fromButton);
        add(swapButton);
        add(toButton);
        add(translate);
        add(fromArea);
        add(toArea);

        setVisible(true);
    }

    private String getLanguage(int index){
        if(index == 0)
            return "en";
        if(index == 1)
            return "ro";
        if(index == 2)
            return "fr";

        return "none";
    }

    public static void main(String[] args) {
        new ClientWindow();
    }
}
