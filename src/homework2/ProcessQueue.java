package homework2;

public class ProcessQueue {
    private Process[] pq = new Process[2];
    public int length = 0;

    public void addProcess(Process process) {

        if (pq.length == length + 1) {
            resize(2 * pq.length);
        }

        pq[++length] = process;
        swim(length);
    }

    public Process runNextProcess() {

        if (length == 0) {
            return null;
        }

        Process min = pq[1];
        swap(1, length--);
        pq[length + 1] = null;

        if (length > 0 && length == pq.length / 4) {
            resize(pq.length / 2);
        }

        sink(1);
        return min;
    }

    public Process peekNextProcess() {

        if (length == 0) {
            return null;
        }

        return pq[1];
    }

    private void swim(int k) {

        while (k > 1 && less(k, k / 2)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {

        while (2 * k <= length) {
            int j = 2 * k;

            if (j < length && less(j + 1, j)) {
                j++;
            }

            if (!less(j, k)) {
                break;
            }

            swap(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {

        return pq[i].compareTo(pq[j]) < 0;
    }

    private void swap(int i, int j) {

        Process temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void resize(int capacity) {

        Process[] copy = new Process[capacity];

        for (int i = 1; i <= length; i++) {
            copy[i] = pq[i];
        }

        pq = copy;
    }
}
