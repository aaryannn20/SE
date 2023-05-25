import java.awt.Font;

public class FunctionFormat {
    GUI gui;
    Font arial, comicSansMS, timesNewRoman;
    String selectedFont;

    public FunctionFormat(GUI gui) {
        this.gui = gui;
    }

    public void wordWrap() {
        if (gui.wordWrapText == false) {
            gui.wordWrapText = true;
            gui.textArea.setLineWrap(true);
            gui.textArea.setWrapStyleWord(true);
            gui.item_Wrap.setText("Word Wrap : ON");
        } else if (gui.wordWrapText == true) {
            gui.wordWrapText = false;
            gui.textArea.setLineWrap(false);
            gui.textArea.setWrapStyleWord(false);
            gui.item_Wrap.setText("Word Wrap : OFF");
        }
    }

    public void createFont(int FontSize) {
        arial = new Font("Arial", Font.PLAIN, FontSize);
        comicSansMS = new Font("Comic Sans MS", Font.PLAIN, FontSize);
        timesNewRoman = new Font("Times New Roman", Font.PLAIN, FontSize);

        setFont(selectedFont);
    }

    public void setFont(String font) {
        selectedFont = font;
        switch (selectedFont) {
            case "Arial":
                gui.textArea.setFont(arial);
                break;

            case "Comic Sans MS":
                gui.textArea.setFont(comicSansMS);
                break;

            case "Times New Roman":
                gui.textArea.setFont(timesNewRoman);
                break;

            default:
                break;
        }
    }
}
