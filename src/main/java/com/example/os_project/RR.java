package com.example.os_project;

import java.util.*;

public class RR {
    public void runRoundRobin(List<Process> processes, int quantum) {
        //Note that the processes are sorted in the list processes because it was sorted twice
        //in the FCFS and in the SJF using the Collection library @_@
        int time = 0;
        Queue<Process> queue = new LinkedList<>();

        // Initialize processes and add them to the queue
        for (Process process : processes) {
            //when add the processes to the queue we set the remaining time to cpu burst time
            //and set the complete attribute with false because the processes didn't excute yet
            process.setRemainingTime(process.getBurstTime());
            process.setCompleted(false);
            queue.add(process);//add them to the queue
        }

        while (!queue.isEmpty()) {
            Process current = queue.poll();//the first process entered the queue "FIFO"

            // Set the start time for the first run of this process
            if (!current.isCompleted() && current.getStartTime() == 0) {
                current.setStartTime(Math.max(time, current.getArrivalTime()));
                //the start time for the first process is either 0 or the arrival time for the process
            }

            // Update the time considering the arrival time of the process
            time = Math.max(time, current.getArrivalTime());

            // Calculate the time slice for the current process
            int timeSlice = Math.min(current.getRemainingTime(), quantum);//determining if the process will take the quantum time or the or it needs less than the quantum
            time += timeSlice;
            current.setRemainingTime(current.getRemainingTime() - timeSlice);

            if (current.getRemainingTime() > 0) {
                // Process not yet finished, add it back to the queue
                queue.add(current);
            } else {
                // this condition means the process is finished
                current.setFinishTime(time);
                current.setTurnaroundTime(current.getFinishTime() - current.getArrivalTime());
                current.setWaitingTime(current.getTurnaroundTime() - current.getBurstTime());
                current.setCompleted(true);
            }
        }
    }
//    public void runRoundRobinSingleStep(Process process, int quantum) {
//        if (!process.isCompleted() && process.getStartTime() == 0) {
//            process.setStartTime(process.getArrivalTime());
//

    public static void printResults(List<Process> processes) {
        // Header
        System.out.println("Process\tArrival Time\tBurst Time\tStart Time\tFinish Time\tWaiting Time\tTurnaround Time");


        processes.sort(Comparator.comparingInt(Process::getStartTime));

        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        for (Process process : processes) {
            int startTime = process.getStartTime();
            int finishTime = process.getFinishTime();
            int waitingTime = process.getWaitingTime();
            int turnaroundTime = process.getTurnaroundTime();

            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;

            System.out.println(process.getProcessName() + "\t\t\t" + process.getArrivalTime() + "\t\t\t\t" + process.getBurstTime() + "\t\t\t" +
                    startTime + "\t\t\t" + finishTime + "\t\t\t\t" + waitingTime + "\t\t\t\t" + turnaroundTime);
        }

        // Calculate and print average waiting time and turnaround time
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

      //return an array of double have ATT and AWT
        return new double[]{avgWaitingTime, avgTurnaroundTime};
    }
}
