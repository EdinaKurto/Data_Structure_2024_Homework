package homework3;

import java.util.ArrayList;
public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    public ArrayList<Entry> get(String searchableName) {
        Node x = root;

        while (x != null) {
            int cmp = searchableName.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.values;
            }
        }
        return null;
    }

    public void put(String searchableName, Entry entry) {
        root = put(root, searchableName, entry);
        root.color = BLACK;
    }

    private Node put(Node h, String key, Entry value) {
        if (h == null) {
            return new Node(key, value, RED);
        }

        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, value);
        } else if (cmp > 0) {
            h.right = put(h.right, key, value);
        } else {
            h.values.add(value);
        }

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);

        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);

        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        return h;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public int[] countRedAndBlackEdges() {
        int[] counts = new int[2];
        countEdges(root, counts);
        counts[0]--;
        return counts;
    }

    private void countEdges(Node x, int[] counts) {
        if (x == null) return;
        if (x.left != null) {

            if (x.left.color == RED) {
                counts[1]++;
            } else {
                counts[0]++;
            }
            countEdges(x.left, counts);
        }
        if (x.right != null) {

            if (x.right.color == RED) {
                counts[1]++;
            } else {
                counts[0]++;
            }
            countEdges(x.right, counts);
        }
    }

    public int[] countEdgesOnPath(String searchableName) {

        int[] pathCounts = new int[2];
        Node x = root;

        while (x != null) {
            int cmp = searchableName.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;

                if (x != null && x.color == RED) {
                    pathCounts[1]++;
                } else {
                    pathCounts[0]++;
                }

            } else if (cmp > 0) {
                x = x.right;

                if (x != null && x.color == RED) {
                    pathCounts[1]++;
                } else {
                    pathCounts[0]++;
                }

            } else {
                break;
            }
        }
        return pathCounts;
    }
}
