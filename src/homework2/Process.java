package homework2;

public class Process implements Comparable<Process> {
    private String process_name;
    private int priority;
    private int burst_time;
    private final int burstTimeConst;
    private int arrival_time;
    private int finish_time;

    public Process(String processName, int priority, int burstTime, int arrivalTime) {

        this.process_name = processName;

        this.priority = priority;

        this.burst_time = burstTime;

        this.burstTimeConst = burstTime;

        this.arrival_time = arrivalTime;
    }

    public String getProcessName() {
        return process_name;
    }

    public int getPriority() {
        return priority;
    }

    public int getBurstTime() {
        return burst_time;
    }

    public int getBurstTimeConst() {
        return burstTimeConst;
    }

    public int getArrivalTime() {
        return arrival_time;
    }

    public int getFinishTime() {
        return finish_time;
    }

    public void setFinishTime(int finishTime) {
        this.finish_time = finishTime;
    }

    public void setBurstTime(int burstTime) {
        this.burst_time = burstTime;
    }

    @Override
    public int compareTo(Process other) {

        if (this.priority > other.priority) {
            return 1;
        }
        else if (this.priority == other.priority) {
            return Integer.compare(this.arrival_time, other.arrival_time);
        }
        else {
            return -1;
        }
    }
}
