import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FunctionFile {

    GUI gui;
    String FileName;
    String FileAddress;

    public FunctionFile(GUI gui) {
        this.gui = gui;

    }

    public void newFile() {
        gui.textArea.setText("");
        gui.window.setTitle("New");
        FileName = null;
        FileAddress = null;
    }

    public void openFile() {
        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            FileName = fd.getFile();
            FileAddress = fd.getDirectory();
            gui.window.setTitle(FileName);
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(FileAddress + FileName));

            gui.textArea.setText("");
            String line = null;

            while ((line = br.readLine()) != null) {
                gui.textArea.append(line + "\n");
            }
            br.close();
        } catch (Exception e) {
            System.out.println("File Not Opened");
        }
    }

    public void saveFile() {
        if (FileName == null) {
            saveAsFile();
        } else {
            try {
                FileWriter fw1 = new FileWriter(FileAddress + FileName);
                fw1.write(gui.textArea.getText());
                gui.window.setTitle(FileName);
                fw1.close();

            } catch (Exception e) {
                System.out.println("Something Went Wrong");
            }
        }

    }

    public void saveAsFile() {
        FileDialog fd2 = new FileDialog(gui.window, "Save As", FileDialog.SAVE);
        fd2.setVisible(true);

        if (fd2.getFile() != null) {
            FileName = fd2.getFile();
            FileAddress = fd2.getDirectory();
            gui.window.setTitle(FileName);
        }
        try {
            FileWriter fw2 = new FileWriter(FileAddress + FileName);
            fw2.write(gui.textArea.getText());
            fw2.close();
        } catch (Exception e) {
            System.out.println("Something Went Wrong");
        }
    }

    public void exitFile() {
        System.exit(0);
    }

}
