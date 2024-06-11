package homework4;

import java.util.*;

public class SocialNetwork {
    private int V;
    private int E;
    private HashMap<String, ArrayList<Friendship>> adj;

    public SocialNetwork() {

        this.V = 0;
        this.E = 0;
        this.adj = new HashMap<>();

    }

    public SocialNetwork(Scanner in) {

        this();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.equals("friend1;friend2;friendship_strength")) {
                continue;
            }

            String[] parts = line.split(";");
            String friend1 = parts[0];
            String friend2 = parts[1];
            int strength = Integer.parseInt(parts[2]);
            addFriendship(new Friendship(friend1, friend2, strength));

        }
    }

    public void addUser(String user) {

        if (!adj.containsKey(user)) {
            adj.put(user, new ArrayList<>());
            V++;
        }
    }

    public void addFriendship(Friendship f) {

        addUser(f.getFriend1());
        addUser(f.getFriend2());
        adj.get(f.getFriend1()).add(f);
        adj.get(f.getFriend2()).add(f);
        E++;

    }

    public ArrayList<FriendshipRecommendation> recommendFriends(String user) {

        if (!adj.containsKey(user)) {
            return new ArrayList<>();
        }

        Map<String, Integer> potentialFriends = new HashMap<>();
        List<Friendship> friends = adj.get(user);

        for (Friendship f : friends) {

            String friend = f.getFriend1().equals(user) ? f.getFriend2() : f.getFriend1();
            List<Friendship> friendsOfFriend = adj.get(friend);
            for (Friendship fof : friendsOfFriend) {

                String potentialFriend = fof.getFriend1().equals(friend) ? fof.getFriend2() : fof.getFriend1();
                if (potentialFriend.equals(user) || adj.get(user).stream().anyMatch(fu -> fu.getFriend1().equals(potentialFriend) || fu.getFriend2().equals(potentialFriend))) {
                    continue;
                }

                int weight = Math.min(f.getFriendshipStrength(), fof.getFriendshipStrength());
                potentialFriends.put(potentialFriend, potentialFriends.getOrDefault(potentialFriend, 0) + weight);
            }
        }

        ArrayList<FriendshipRecommendation> recommendations = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : potentialFriends.entrySet()) {
            recommendations.add(new FriendshipRecommendation(entry.getKey(), entry.getValue()));
        }

        Collections.sort(recommendations);
        return recommendations;
    }

    public void printStats() {
        System.out.println("Total users: " + V);
        System.out.println("Total friendships: " + E);
    }
}
