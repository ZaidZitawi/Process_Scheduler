package com.example.os_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ArrayList<Process> p = new ArrayList<>();
        SJF sjf = new SJF();
        FCFS fcfs = new FCFS();
        RR rr = new RR();
        MFQ mfq = new MFQ();

        // Manually create 5 Process objects
        Process process1 = new Process("P1", 10, 7);
        Process process2 = new Process("P2", 8, 8);
        Process process3 = new Process("P3", 5, 3);
        Process process4 = new Process("P4", 2, 9);
        Process process5 = new Process("P5", 4, 5);


        p.add(process1);
        p.add(process2);
        p.add(process3);
        p.add(process4);
        p.add(process5);



        List<Process> pro = generateRandomProcesses(8, 100, 20); // Generate random processes


        System.out.println("=============First Come First Served====================\n");
        fcfs.runFCFS(pro);
        fcfs.printResults(pro);

        System.out.println("\n=============Shortest Job First====================\n");
//        sjf.findAvgTime(p);
        sjf.runSRTF(pro);
        sjf.printResults(pro);

        System.out.println("\n=============Round Robin====================\n");
        rr.runRoundRobin(pro, 5);
        rr.printResults(pro);

        System.out.println("\n=============Multilevel Feedback Queue====================\n");
        mfq.runMFQ(pro);
        mfq.printResults(pro);


                        //######################  IMPORTANT NOTE   #############################//


        // In the Table Step I didn't exactly understand what should I do, I had two ideas.
        // The first one is that the burst time should be from 1-100 nad then 1-1000 and so on
        //And the second idea as my classmates did which made me a wondering
        // what you really want is execute the 8 processes using the 4 Algos n times. And the times are provided in the project sheet
        // which are 100, 1000, 10000, 100000

        //the Conclusion is that I both and you can choose the one that matches the required specifications in the Project Sheet
        //I hope you understand that dr Ali, And Sorry for Confusion


                        //######################  IMPORTANT NOTE   #############################//
        System.out.println("\n===============================================================\n");



        System.out.println("\n==============================================================\n");


        int[] iterationCounts = {100, 1000, 10000, 100000};
        String[] algorithms2 = {"FCFS", "SJF", "RR", "MLFQ"};

        System.out.println("Results Summary:");
        System.out.println("Iterations\tAlgorithm\t (ATT) \t (AWT) ");

        for (int i=0;i<iterationCounts.length;i++) {
            for (int j=0;j<algorithms2.length;j++) {
                double totalAWT = 0;
                double totalATT = 0;
                for (int k = 0; k < iterationCounts[i]; k++) {

                    double[] metrics;
                    switch (algorithms2[j]) {
                        case "FCFS":
                            fcfs.runFCFS(pro);
                            metrics = fcfs.getAverageTimes(pro);
                            break;
                        case "SJF":
                            sjf.runSRTF(pro);
                            metrics = sjf.getAverageTimes(pro);
                            break;
                        case "RR":
                            rr.runRoundRobin(pro, 20);
                            metrics = rr.getAverageTimes(pro);
                            break;
                        case "MLFQ":
                            mfq.runMFQ(pro);
                            metrics = mfq.getAverageTimes(pro);
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown algorithm: " + algorithms2[j]);
                    }

                    totalAWT += metrics[0];
                    totalATT += metrics[1];
                }

                double avgAWT = totalAWT / iterationCounts[i];
                double avgATT = totalATT / iterationCounts[i];

                System.out.println(iterationCounts[i] + "\t\t\t" + algorithms2[j] + "\t\t" + String.format("%.2f", avgATT) + "\t\t" + String.format("%.2f", avgAWT));
            }
            System.out.println();
        }



        System.out.println("\n====================================================================\n");


        System.out.println("\n====================================================================\n");
        int[] burstRanges = {50, 100, 1000, 10000, 100000};
        String[] algorithms = {"FCFS", "SJF", "RR", "MFQ"};

        // Initialize data structure to store results
        Map<String, Map<Integer, double[]>> results = new HashMap<>();

        for (String algorithm : algorithms) {
            Map<Integer, double[]> burstResults = new HashMap<>();
            for (int burst : burstRanges) {
                // Generate processes
                List<Process> Processes = generateRandomProcesses(8, burst, 20);

                // Run the appropriate scheduling algorithm
                switch (algorithm) {
                    case "FCFS":
                        fcfs.runFCFS(Processes);
                        break;
                    case "SJF":
                        sjf.runSRTF(pro);
                        break;
                    case "RR":
                        rr.runRoundRobin(Processes, 20);
                        break;
                    case "MFQ":
                        mfq.runMFQ(Processes);
                        break;
                }


                double[] times = calculateAverageTimes(Processes);
                burstResults.put(burst, times);
            }
            results.put(algorithm, burstResults);
        }
        for (String algorithm : algorithms) {
            System.out.println("Results for " + algorithm + ":");
            System.out.println("Range\t\t(ATT)\t\t (AWT)");
            for (int burst : burstRanges) {
                double[] times = results.get(algorithm).get(burst);
                System.out.println(burst + "\t\t" + times[1] + "\t\t\t\t" + times[0]);
            }
            System.out.println();
        }


    }
    private static List<Process> generateRandomProcesses(int numberOfProcesses, int burst, int arrival) {
        List<Process> processes = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfProcesses; i++) {
            String processName = "P" + (i + 1);
            int arrivalTime = random.nextInt(arrival);
            int burstTime = random.nextInt(burst) + 1; //the +1 is if the burst is 0 so this makes it at least 1

            Process process = new Process(processName, arrivalTime, burstTime, 0, 0, 0, 0, burstTime, false);
            processes.add(process);
        }

        return processes;
    }

    private static double[] calculateAverageTimes(List<Process> processes) {
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        for (Process process : processes) {
            totalWaitingTime += process.getWaitingTime();
            totalTurnaroundTime += process.getTurnaroundTime();
        }

        double avgWaitingTime = totalWaitingTime / processes.size();
        double avgTurnaroundTime = totalTurnaroundTime / processes.size();

        return new double[] {avgWaitingTime, avgTurnaroundTime};
    }

    public static void main(String[] args) {
        launch();
    }
}