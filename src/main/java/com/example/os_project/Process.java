package com.example.os_project;

public class Process {
    private String processName;
    private int arrivalTime;
    private int burstTime;
    private int startTime;//service start time
    private int finishTime;//service finish time
    private int waitingTime;
    private int turnaroundTime;
    private int RemainingTime;
    private boolean completed;

    public Process(String processName, int arrivalTime, int burstTime) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    public Process(String processName, int arrivalTime, int burstTime, int startTime, int finishTime, int waitingTime, int turnaroundTime, int remainingTime, boolean completed) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.waitingTime = waitingTime;
        this.turnaroundTime = turnaroundTime;
        RemainingTime = burstTime;
        this.completed=completed;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getRemainingTime() {
        return RemainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        RemainingTime = remainingTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Process{" +
                "processName='" + processName + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", waitingTime=" + waitingTime +
                ", turnaroundTime=" + turnaroundTime +
                ", RemainingTime=" + RemainingTime +
                '}';
    }
}
