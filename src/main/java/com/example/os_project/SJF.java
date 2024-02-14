package com.example.os_project;

import java.util.*;

public class SJF {
    public void runSRTF(List<Process> processes) {
        int time = 0;
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getRemainingTime));
        List<Process> arrivalQueue = new ArrayList<>(processes);
        arrivalQueue.sort(Comparator.comparingInt(Process::getArrivalTime));

        Process currentProcess = null;
        while (!arrivalQueue.isEmpty() || !readyQueue.isEmpty() || currentProcess != null) {
            // Check and add new arrivals to the ready queue
            while (!arrivalQueue.isEmpty() && arrivalQueue.get(0).getArrivalTime() <= time) {
                Process arrivingProcess = arrivalQueue.remove(0);
                arrivingProcess.setRemainingTime(arrivingProcess.getBurstTime());
                readyQueue.add(arrivingProcess);
            }

            // Preempt if necessary
            if (currentProcess != null && !readyQueue.isEmpty() &&
                    readyQueue.peek().getRemainingTime() < currentProcess.getRemainingTime()) {
                readyQueue.add(currentProcess);
                currentProcess = null;
            }

            // Assign a process if CPU is idle
            if (currentProcess == null && !readyQueue.isEmpty()) {
                currentProcess = readyQueue.poll();
                if (currentProcess.getStartTime() == 0) { // Process starts for the first time
                    currentProcess.setStartTime(time);
                }
            }

            // Execute current process
            if (currentProcess != null) {
                currentProcess.setRemainingTime(currentProcess.getRemainingTime() - 1);
                if (currentProcess.getRemainingTime() == 0) {
                    // Process completed
                    currentProcess.setFinishTime(time + 1);
                    currentProcess.setTurnaroundTime(currentProcess.getFinishTime() - currentProcess.getArrivalTime());
                    currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getBurstTime());
                    currentProcess.setCompleted(true);
                    currentProcess = null;
                }
            }
            time++;
        }
    }

    public void printResults(List<Process> processes) {
        // Header
        System.out.println("Process\tArrival Time\tBurst Time\tStart Time\tFinish Time\tWaiting Time\tTurnaround Time");

        // Sort by start time for display
        processes.sort(Comparator.comparingInt(Process::getStartTime));

        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        for (Process process : processes) {
            int startTime = process.getStartTime();
            int finishTime = process.getFinishTime();
            int waitingTime = process.getWaitingTime();
            int turnaroundTime = process.getTurnaroundTime();

            // Sum up waiting time and turnaround time
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

        // Return the averages in an array
        return new double[]{avgWaitingTime, avgTurnaroundTime};
    }



}








//Second Code

//    public void findAvgTime(List<Process> processes) {
//        List<Process> readyQueue = new ArrayList<>();
//        int n = processes.size();
//        int[] waitingTime = new int[n];//to calculate avg waiting time
//        int[] turnaroundTime = new int[n];//to calculate avg turn around time
//        int totalWaitingTime = 0, totalTurnaroundTime = 0;
//
//        // Sort processes based on arrival time
//                Collections.sort(processes, (p1, p2) -> Integer.compare(p1.getArrivalTime(), p2.getArrivalTime()));
//
//        waitingTime[0] = 0; // Waiting time for the first process is always 0
//
//        for (int i = 1; i < n; i++) {
//            waitingTime[i] = processes.get(i - 1).getBurstTime() + waitingTime[i - 1];
//            totalWaitingTime += waitingTime[i];
//        }
//
//        for (int i = 0; i < n; i++) {
//            turnaroundTime[i] = processes.get(i).getBurstTime() + waitingTime[i];
//            totalTurnaroundTime += turnaroundTime[i];
//        }
//
//        System.out.println("Processes ArrivalTime BurstTime RemainingTime WaitingTime TurnaroundTime");
//        for (int i = 0; i < n; i++) {
//            Process currentProcess = processes.get(i);
//            System.out.println(currentProcess.getProcessName() + "\t\t\t\t" + currentProcess.getArrivalTime() + "\t\t\t\t" +
//                    currentProcess.getBurstTime() + "\t\t\t" + currentProcess.getRemainingTime() + "\t\t\t\t" +
//                    waitingTime[i] + "\t\t\t" +
//                    turnaroundTime[i]);
//        }
//
//        System.out.println("Average Waiting Time = " + (float) totalWaitingTime / n);
//        System.out.println("Average Turnaround Time = " + (float) totalTurnaroundTime / n);
//    }
//
//}