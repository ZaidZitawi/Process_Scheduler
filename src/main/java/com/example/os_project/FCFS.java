package com.example.os_project;

import java.util.Collections;
import java.util.List;

public class FCFS {

    public void runFCFS(List<Process> processes) {
        int currentTime = 0;
        Collections.sort(processes, (p1, p2) -> Integer.compare(p1.getArrivalTime(), p2.getArrivalTime()));//SOOOOOOOORT

        for (int i=0;i<processes.size();i++) {
            Process process =processes.get(i);

            if(i==0){
                currentTime =process.getArrivalTime();
            }
            process.setStartTime(currentTime);
            process.setFinishTime(currentTime + process.getBurstTime());
            process.setTurnaroundTime(process.getFinishTime() - process.getArrivalTime());
            process.setWaitingTime(process.getTurnaroundTime() - process.getBurstTime());
            currentTime = process.getFinishTime();
        }
    }

        public static void printResults(List<Process> processes) {
            double totalWaitingTime = 0;
            double totalTurnaroundTime = 0;
            System.out.println("Process\tArrival Time\tBurst Time\tStart Time\tFinish Time\tWaiting Time\tTurnaround Time");
            for (Process process : processes) {
                System.out.printf("%s\t\t\t\t%d\t\t\t\t%d\t\t\t%d\t\t\t%d\t\t\t%d\t\t\t%d\n",
                        process.getProcessName(), process.getArrivalTime(), process.getBurstTime(),
                        process.getStartTime(), process.getFinishTime(), process.getWaitingTime(), process.getTurnaroundTime());
                // Sum up waiting time and turnaround time
                totalWaitingTime += process.getWaitingTime();
                totalTurnaroundTime += process.getTurnaroundTime();
            }

            double avgWaitingTime = totalWaitingTime / processes.size();
            double avgTurnaroundTime = totalTurnaroundTime / processes.size();

            System.out.println("\nAverage Waiting Time = " + avgWaitingTime);
            System.out.println("Average Turnaround Time = " + avgTurnaroundTime);
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
}
