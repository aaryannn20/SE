import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    JFrame window;
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordWrapText = false;
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat;
    JMenuItem item_New, item_Open, item_Save, item_SaveAs, item_Exit;
    JMenuItem item_Wrap, item_FontArail, item_FontCSMS, item_FontTNR, item_FontSize8, item_FontSize12, item_FontSize16,
            item_FontSize20, item_FontSize24;
    JMenu menuFont, menuFontSize;
    JMenuItem item_undo, item_redo;

    FunctionFile file = new FunctionFile(this);
    FunctionFormat format = new FunctionFormat(this);
    FunctionEdit edit = new FunctionEdit(this);

    UndoManager um = new UndoManager();

    public static void main(String args[]) {
        new GUI();
    }

    public GUI() {
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormat();
        format.selectedFont = "Arial";
        format.createFont(16);
        format.wordWrap();
        window.setVisible(true);
    }

    public void createWindow() {
        window = new JFrame("Notepad");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createTextArea() {
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textArea.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    public void undoableEditHappened(UndoableEditEvent e) {
                        um.addEdit(e.getEdit());
                    }
                });
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);
    }

    public void createFileMenu() {
        item_New = new JMenuItem("New");
        item_New.addActionListener(this);
        item_New.setActionCommand("New");
        menuFile.add(item_New);

        item_Open = new JMenuItem("Open");
        item_Open.addActionListener(this);
        item_Open.setActionCommand("Open");
        menuFile.add(item_Open);

        item_Save = new JMenuItem("Save");
        item_Save.addActionListener(this);
        item_Save.setActionCommand("Save");
        menuFile.add(item_Save);

        item_SaveAs = new JMenuItem("Save As");
        item_SaveAs.addActionListener(this);
        item_SaveAs.setActionCommand("Save As");
        menuFile.add(item_SaveAs);

        item_Exit = new JMenuItem("Exit");
        item_Exit.addActionListener(this);
        item_Exit.setActionCommand("Exit");
        menuFile.add(item_Exit);
    }

    public void createEditMenu() {
        item_undo = new JMenuItem("Undo");
        item_undo.addActionListener(this);
        item_undo.setActionCommand("Undo");
        menuEdit.add(item_undo);

        item_redo = new JMenuItem("Redo");
        item_redo.addActionListener(this);
        item_redo.setActionCommand("Redo");
        menuEdit.add(item_redo);
    }

    public void createFormat() {
        item_Wrap = new JMenuItem("Word Wrap: OFF");
        item_Wrap.addActionListener(this);
        item_Wrap.setActionCommand("Word Wrap");
        menuFormat.add(item_Wrap);

        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        item_FontArail = new JMenuItem("Arial");
        item_FontArail.addActionListener(this);
        item_FontArail.setActionCommand("Arial");
        menuFont.add(item_FontArail);

        item_FontCSMS = new JMenuItem("Comic Sans MS");
        item_FontCSMS.addActionListener(this);
        item_FontCSMS.setActionCommand("Comic Sans MS");
        menuFont.add(item_FontCSMS);

        item_FontTNR = new JMenuItem("Times New Roman");
        item_FontTNR.addActionListener(this);
        item_FontTNR.setActionCommand("Times New Roman");
        menuFont.add(item_FontTNR);

        menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);

        item_FontSize8 = new JMenuItem("8");
        item_FontSize8.addActionListener(this);
        item_FontSize8.setActionCommand("size8");
        menuFontSize.add(item_FontSize8);

        item_FontSize12 = new JMenuItem("12");
        item_FontSize12.addActionListener(this);
        item_FontSize12.setActionCommand("size12");
        menuFontSize.add(item_FontSize12);

        item_FontSize16 = new JMenuItem("16");
        item_FontSize16.addActionListener(this);
        item_FontSize16.setActionCommand("size16");
        menuFontSize.add(item_FontSize16);

        item_FontSize20 = new JMenuItem("20");
        item_FontSize20.addActionListener(this);
        item_FontSize20.setActionCommand("size20");
        menuFontSize.add(item_FontSize20);

        item_FontSize24 = new JMenuItem("24");
        item_FontSize24.addActionListener(this);
        item_FontSize24.setActionCommand("size24");
        menuFontSize.add(item_FontSize24);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New":
                file.newFile();
                break;

            case "Open":
                file.openFile();
                break;

            case "Save":
                file.saveFile();
                break;

            case "Save As":
                file.saveAsFile();
                break;

            case "Exit":
                file.exitFile();
                break;

            case "Undo":
                edit.undo();
                break;

            case "Redo":
                edit.redo();
                break;

            case "Word Wrap":
                format.wordWrap();
                break;

            case "Arial":
                format.setFont(command);
                break;

            case "Comic Sans MS":
                format.setFont(command);
                break;

            case "Times New Roman":
                format.setFont(command);
                break;

            case "size8":
                format.createFont(8);
                break;

            case "size12":
                format.createFont(12);
                break;

            case "size16":
                format.createFont(16);
                break;

            case "size20":
                format.createFont(20);
                break;

            case "size24":
                format.createFont(24);
                break;

            default:
                break;
        }
    }
}
