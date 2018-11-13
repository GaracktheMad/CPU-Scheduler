# CPU_Scheduler
By Brandon Ruiz and Peter Vukas
## Usage
1. Select the algorithm to use. Default is FIFO.
2. Select the number of processes. Default is 1.
3. If applicable, select Quantum value.
4. If applicable, click the randomize button to generate random values. This will also calculate averages and create the Gantt Chart.
5. Click the calculate button.

## Assumptions:
1. Times do not exceed the maximum value of a double in Java.
2. Burst times do not go below 0
3. When switching the algorithm used or the number of processes to calculate, the list will be purged.
4. The maximum number of processes and quantum times are specified in the associated combo boxes, and cannot be increased
5. Priority can be negative. Smallest values have the highest priority. Priorities should not exceed the max value of an integer.
6. Arrival times are ALWAYS given, even if they're all the same.
7. The user hitting random also wants the randomized values calculated. The user can however edit these values after and recalculate.
8. If invalid data is inputted, or a field is left blank, no calculations will be done.

## Quirks and Bugs
1. The Gantt Chart boxes may not connect to other boxes properly if the given burst time value is too small
2. The Gantt Chart and calculated averages will not disappear on switching algorithms, processes, or data. The chart and values will update on clicking the calculate button.
