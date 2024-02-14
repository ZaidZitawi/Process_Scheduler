package com.example.os_project;

import java.util.*;

public class MFQ {

    private RR rr;
    private FCFS fcfs;

    public MFQ() {
        this.rr = new RR();
        this.fcfs = new FCFS();
    }

    public void runMFQ(List<Process> processes) {
        Queue<Process> q1 = new LinkedList<>();
        Queue<Process> q2 = new LinkedList<>();
        Queue<Process> q3 = new LinkedList<>();

        //  add processes to the first queue
        for (Process process : processes) {
            q1.add(process);
        }


        while (!q1.isEmpty() || !q2.isEmpty() || !q3.isEmpty()) {
            List<Process> temp = new ArrayList<>();


            while (!q1.isEmpty()) {
                Process process = q1.poll();
                rr.runRoundRobin(processes, 10);
                if (process.getRemainingTime() > 0) {
                    q2.add(process);
                } else {
                    temp.add(process);
                }
            }


            while (!q2.isEmpty()) {
                Process process = q2.poll();
                rr.runRoundRobin(processes, 50);
                if (process.getRemainingTime() > 0) {
                    q3.add(process);
                } else {
                    temp.add(process);
                }
            }

            while (!q3.isEmpty()) {
                Process process = q3.poll();
                fcfs.runFCFS(processes);
                temp.add(process);
            }

            // Print results of this round
//            rr.printResults(temp);
            //OR
//            fcfs.printResults(temp);
        }
    }
    public double[] getAverageTimes(List<Process> processes) {
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        for (Process process : processes) {
            totalWaitingTime += process.getWaitingTime();
            totalTurnaroundTime += process.getTurnaroundTime();
        }

        double avgWaitingTime = totalWaitingTime / processes.size();
        double avgTurnaroundTime = totalTurnaroundTime / processes.size();

        return new double[]{avgWaitingTime, avgTurnaroundTime};
    }



    public void printResults(List<Process> processes) {
        System.out.println("Process\tArrival Time\tBurst Time\tStart Time\tFinish Time\tWaiting Time\tTurnaround Time");
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        for (Process process : processes) {
            System.out.printf("%s\t\t\t%d\t\t\t%d\t\t\t%d\t\t\t%d\t\t\t%d\t\t\t%d\n",
                    process.getProcessName(),
                    process.getArrivalTime(),
                    process.getBurstTime(),
                    process.getStartTime(),
                    process.getFinishTime(),
                    process.getWaitingTime(),
                    process.getTurnaroundTime());
            totalWaitingTime += process.getWaitingTime();
            totalTurnaroundTime += process.getTurnaroundTime();
        }

        double avgWaitingTime = totalWaitingTime / processes.size();
        double avgTurnaroundTime = totalTurnaroundTime / processes.size();

        System.out.println("\nAverage Waiting Time = " + avgWaitingTime);
        System.out.println("Average Turnaround Time = " + avgTurnaroundTime);
    }
}