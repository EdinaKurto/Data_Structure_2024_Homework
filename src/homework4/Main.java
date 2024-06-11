package homework4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "src/homework4/social_network.csv";

        Scanner in = new Scanner(new File(filePath));
        SocialNetwork sn = new SocialNetwork(in);
        sn.printStats();
        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter name and surname, or -1 to exit:");
            String input = inputScanner.nextLine();

            if (input.equals("-1")) {
                break;
            }

            ArrayList<FriendshipRecommendation> recommendations = sn.recommendFriends(input);
            if (recommendations.isEmpty()) {
                System.out.println("No recommendations found / User does not exist.");

            } else {
                System.out.println("Total recommendations: " + recommendations.size());
                System.out.println("Top 10 recommendations based on friendship strength:");

                for (int i = 0; i < Math.min(10, recommendations.size()); i++) {
                    System.out.println(recommendations.get(i));
                }
            }
        }
        inputScanner.close();
    }
}
