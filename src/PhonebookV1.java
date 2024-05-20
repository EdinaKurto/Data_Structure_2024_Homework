import java.util.Scanner;

public class PhonebookV1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputFilePath = "src/raw_phonebook_data.csv";
        String outputFilePath = "src/output_phonebook_data.csv";


        System.out.println("Loading the entries.."); 
        Entry[] entries = FileUtils.readFile(inputFilePath);

        System.out.println("Sorting the entries.."); 
        MergeSort.sort(entries);

        System.out.println("Saving file..."); 
        FileUtils.writeToFile(entries, outputFilePath);

        System.out.println("=========================================");
        System.out.println("System is ready.");

        while (true) {
            System.out.print("Enter the name you're searching for, or -1 to exit: "); 
            String name = scanner.nextLine();

            if (name.equals("-1")) {
                break;
            }

            int[] result = BinarySearch.search(entries, name);

            if (result[0] == -1) {
                System.out.println("No entries with the given name exist in the phonebook.");

            } else {
                System.out.println("Entries found: " + (result[1] - result[0] + 1)); 

                for (int i = result[0]; i <= result[1]; i++) {
                    System.out.println(entries[i]);
                }
            }
        }

        scanner.close();
        System.out.println("Thank you for using the phonebook.");
    }
}