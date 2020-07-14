package Sketchpad;

import java.util.ArrayList;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;



public class FileModes extends JFrame {
    private static final long serialVersionUID = 1L;

    // Save the sketchpad to a text file
    public static void Save(Container parent, ArrayList<ObjectSketch> drawingObjects) {
        String filePath, fileName;

        // Save File Dialog Window
        final JFileChooser fc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Sketchpad File", "txt");
        fc.setFileFilter(filter);
        int response = fc.showSaveDialog(parent);
        if (response == JFileChooser.APPROVE_OPTION) {
            filePath = fc.getSelectedFile().toString();
            if(!filePath.contains(".txt")){  // If not an overwrite, add the file extension to the path
                filePath += ".txt";
            }

            fileName = fc.getSelectedFile().getName();
        }
        else {
            return;
        }

        // Write to text file
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("Sketchpad save file for: " + fileName);

            for (ObjectSketch drawing : drawingObjects) {
                bufferedWriter.write("\n");
                bufferedWriter.write(drawing.toString());
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<ObjectSketch> Load(Container parent) {
        ArrayList<ObjectSketch> shapesArray = new ArrayList<>();
        String filePath;

        // Save File Dialog Window
        final JFileChooser fc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Sketchpad Text File", "txt");
        fc.setFileFilter(filter);
        int response = fc.showOpenDialog(parent);
        if (response == JFileChooser.APPROVE_OPTION) {
            filePath = fc.getSelectedFile().toString();
        }
        else {
            return shapesArray;
        }

        // Read Text file and generate DrawingObjects list
        try {
            FileReader reader = new FileReader(filePath);

            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line and extract drawing object properties
                String[] properties = line.split(",");
                int x1 = Integer.parseInt(properties[0]);
                int y1 = Integer.parseInt(properties[1]);
                int x2 = Integer.parseInt(properties[2]);
                int y2 = Integer.parseInt(properties[3]);
                ShapeMode type = ShapeMode.valueOf(properties[4]);
                Color color = new Color(Integer.parseInt(properties[5]));

                // Create new drawing object and add it to the list
                ObjectSketch drawing = new ObjectSketch(x1, y1, x2, y2, type, color);
                shapesArray.add(drawing);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return shapesArray;
    }
}
