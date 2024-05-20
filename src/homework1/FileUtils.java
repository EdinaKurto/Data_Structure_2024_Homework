package homework1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static Entry[] readFile(String filePath) {

        List<Entry> entries = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line = br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                entries.add(new Entry(parts[0], parts[1], parts [2], parts [3], parts[4], parts[5]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries.toArray(new Entry[0]);
    }

    public static void writeToFile(Entry[] entries, String filePath) {

        try (BufferedWriter bw= new BufferedWriter(new FileWriter(filePath))) { 
            bw.write("name; address; city; postcode; country; phone_number\n");

            for (Entry entry : entries) {
                bw.write(entry.toString() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}