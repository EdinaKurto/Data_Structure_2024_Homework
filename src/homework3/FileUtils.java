package homework3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class FileUtils {
    public static RedBlackTree readFile(String filePath) {
        RedBlackTree tree = new RedBlackTree();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length == 6) {
                    Entry entry = new Entry(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                    tree.put(parts[0], entry);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tree;
    }
}
