package homework2;

import java.util.ArrayList;

public class Scheduler {

    public static void scheduleAndRun(ArrayList<Process> processes) {

        int currentTime = 0;
        ProcessQueue pq = new ProcessQueue();
        Process currentProcess = null;
        int totalWaitingTime = 0;
        int numOfProcesses = processes.size();

        while (!processes.isEmpty() || currentProcess != null || pq.length > 0) {

            ArrayList<Process> arrivedProcesses = new ArrayList<>();
            for (Process process : processes) {

                if (process.getArrivalTime() == currentTime) {
                    pq.addProcess(process);
                    arrivedProcesses.add(process);
                }
            }
            processes.removeAll(arrivedProcesses);

            if (currentProcess != null && pq.length > 0 && pq.peekNextProcess().getPriority() < currentProcess.getPriority()) {
                pq.addProcess(currentProcess);
                currentProcess = pq.runNextProcess();

            }

            if (currentProcess == null && pq.length > 0) {
                currentProcess = pq.runNextProcess();
            }


            if (currentProcess != null) {
                System.out.println("t: " + currentTime + "    " + currentProcess.getProcessName());
                currentProcess.setBurstTime(currentProcess.getBurstTime() - 1);

                if (currentProcess.getBurstTime() == 0) {
                    currentProcess.setFinishTime(currentTime + 1);

                    int waitingTime = currentProcess.getFinishTime() - currentProcess.getArrivalTime() - currentProcess.getBurstTimeConst();
                    totalWaitingTime += waitingTime;
                    currentProcess = null;
                }
            }
            else {
                System.out.println("t: " + currentTime + "no process");
            }

            currentTime++;
        }

        System.out.println("Total time: " + currentTime);
        double averageWaitingTime = (double) totalWaitingTime / numOfProcesses;
        System.out.println("Average waiting time: " + averageWaitingTime);
    }

    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 1, 4, 0));
        processes.add(new Process("P2", 2, 3, 0));
        processes.add(new Process("P3", 1, 7, 6));
        processes.add(new Process("P4", 3, 4, 11));
        processes.add(new Process("P5", 2, 2, 12));
        scheduleAndRun(processes);
    }
}
