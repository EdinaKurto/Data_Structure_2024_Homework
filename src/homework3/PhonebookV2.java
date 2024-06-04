package homework3;

import java.util.ArrayList;
import java.util.Scanner;

public class PhonebookV2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        RedBlackTree tree = FileUtils.readFile("src/homework3/raw_phonebook_data.csv");

        int[] edgesCount = tree.countRedAndBlackEdges();
        System.out.println("Total black edges in the tree: " + edgesCount[0] + ", Total red edges in the tree: " + edgesCount[1]);

        while (true) {
            System.out.println("Enter a name to search, or -1 to exit:");
            String input = scanner.nextLine();

            if (input.equals("-1")) {
                break;
            }

            ArrayList<Entry> entries = tree.get(input);

            if (entries != null) {
                System.out.println("\nEntries found: " + entries.size());
                for (Entry entry : entries) {
                    System.out.println("Name: " + entry.getName() + " " + entry.getSurname());
                    System.out.println("Street Address: " + entry.getStreetAddress());
                    System.out.println("City: " + entry.getCity());
                    System.out.println("Post Code: " + entry.getPostcode());
                    System.out.println("Country: " + entry.getCountry());
                    System.out.println("Phone Number: " + entry.getPhoneNumber());
                    System.out.println();
                }

                int[] pathEdges = tree.countEdgesOnPath(input);
                System.out.println("Red edges on the path: " + pathEdges[1] + ", Black edges on the path: " + pathEdges[0]);

            } else {
                System.out.println("No entries with the given name exist.");
            }
        }
        scanner.close();

    }
}
