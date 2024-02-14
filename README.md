# CPU Scheduling Algorithms Simulation

## Project Overview

This project, simulates various CPU scheduling algorithms to analyze and compare their performance. The primary goal is to understand how different scheduling strategies affect average turnaround time (ATT) and average waiting time (AWT) under various conditions.

### Implemented Scheduling Algorithms:
- First-Come, First-Served (FCFS)
- Shortest Remaining Time First (SRTF), a preemptive version of Shortest Job First (SJF)
- Round Robin (RR)
- Multilevel Feedback Queue (MFQ) with three queues

## Features

1. **Simulation of CPU Scheduling Algorithms:** FCFS, SRTF, RR, and MFQ.
2. **Process Generation:** Creates 8 processes in the ready queue with random CPU-burst times ranging from 5 to 100 time units, representing them with a suitable data structure akin to the Process Control Block (PCB).
3. **Arrival Times:** Assigns arrival times to processes to simulate order of arrival.
4. **Quantum Times:** Implements RR in MFQ with quantum times of 10 and 50 units for different queues, and a standard RR quantum time of 20 units.
5. **Performance Metrics:** Calculates the average turnaround time (ATT) and average waiting time (AWT) for each scheduling algorithm.
6. **Repeated Simulations:** Repeats the simulation process 100, 1000, 10000, and 100000 times to gather comprehensive performance data.
7. **Results Summary:** Presents a table summarizing ATT and AWT for all cases and scheduling algorithms.
