import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;


public class Main {

    private static int M;
    private static int KB;
    private static Partition [] memory ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

                // Prompt user to enter the number of partitions
                System.out.print("Enter the number of partitions: ");
                try {
                    M = scanner.nextInt();
                    if (M < 1 ) {
                        throw new Exception("Number of partitions must be a positive integer between 1 and ");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input: " + e.getMessage());
                    return;
                }
        
                // Prompt user to enter the size of each partition
                System.out.println("Enter the size of each partition in KB:");
                memory = new Partition[M];
                int start = 0;
		        int end = 0;

                for (int i = 0; i < M; i++) {
                    System.out.print("Partition " + (i+1) + ": ");
                    try {
                        int size = scanner.nextInt();
                        if (size < 1) {
                            throw new Exception("Partition size must be a positive integer.");
                        }
                        end = start + (KB * 1024) - 1;
                        memory[i] = new Partition(start, end, KB);
                        start = memory[i].getEndAddress() + 1;
                    } catch (Exception e) {
                        System.out.println("Invalid input: " + e.getMessage());
                        return;
                    }
                }


                

        // Loop until user chooses to exit
        int choice = 0;
        while (choice != 4) {
            // Display menu and prompt user for choice
            System.out.println("\nMemory Management Menu:");
            System.out.println("1. Allocate a block of memory");
            System.out.println("2. De-allocate a block of memory");
            System.out.println("3. Report detailed information about memory blocks");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Allocate memory block
                    try {
                        System.out.print("Enter process ID: ");
                        String processId = scanner.next();
                        System.out.print("Enter process size: ");
                        int processSize = scanner.nextInt();
                        if (processSize < 1) {
                            throw new Exception("Process size must be a positive integer ");
                        }

                        char strategy = 'N';
        
                        // Prompt user to enter the allocation strategy
                        System.out.print("Enter the allocation strategy (F, B, or W): ");
                        strategy = scanner.next().toUpperCase().charAt(0);
                        if(!(strategy == 'F' || strategy == 'B' || strategy == 'W'))
                        throw new Exception("Input should be f, b or w");
                   
                        // Find a partition to allocate memory from using the selected allocation strategy
                        int partitionIndex = -1;
                        switch (strategy) {
                            case 'F':
                                // partitionIndex = findFirstFitPartition(processSize);
                                break;
                            case 'B':
                                // partitionIndex = findBestFitPartition(processSize);
                                break;
                            case 'W':
                                // partitionIndex = findWorstFitPartition(processSize);
                                break;
                        }

                        if (partitionIndex == -1) {
                            System.out.println("Error: Not enough memory available to allocate process.");
                        } else {
                            // Allocate memory to the process 
                            memory[partitionIndex].setStatus("allocated");
                            memory[partitionIndex].setProcessNum(processId);
                            memory[partitionIndex].setFragmentSize(memory[partitionIndex].getPartitionSize()-processSize);

                            // Display memory state after allocation
                            System.out.print("Memory state after allocation: [");
                            for (int i = 0; i < M; i++) {
                                if (memory[i].getStatus().equals("allocated")) {
                                    System.out.print("P" + memory[i].getProcessNum());
                                } else {
                                    System.out.print("H");
                                }
                                if (i < M-1) {
                                    System.out.print(" | ");
                                }
                            }
                            System.out.println("]");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input: " + e.getMessage());
                    }
                    break;
                case 2:
                    // De-allocate memory block
                    try {
                        System.out.print("Enter process ID to release): ");
                        int processId = scanner.nextInt();
                        if (processId < 1 ) {
                            throw new Exception("Process ID must be a positive integer");
                        }

                        // Find the partition that has been allocated to the process and de-allocate it
                        boolean found = false;
                        for (int i = 0; i < M; i++) {
                            if (memory[i].getStatus().equals("allocated") && memory[i].getProcessNum().equals(processId)) {
                                memory[i].setStatus("free");
                                memory[i].setProcessNum("-1");
                                memory[i].setFragmentSize(-1);
                                found = true;

                                // Display memory state after de-allocation
                                System.out.print("Memory state after de-allocation: [");
                                for (int j = 0; j < M; j++) {
                                    if (memory[j].getStatus().equals("allocated")) {
                                        System.out.print("P" + memory[j].getProcessNum());
                                    } else {
                                        System.out.print("H");
                                    }
                                    if (j < M-1) {
                                        System.out.print(" | ");
                                    }
                                }
                                System.out.println("]");

                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Error: Process ID not found or not allocated.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input: " + e.getMessage());
                    }
                    break;
                case 3:
                    // Print detailed information about memory blocks to console and write to file
                    System.out.println("Memory block information:");
                    for (int i = 0; i < M; i++) {
                        System.out.println(memory[i].toString());
                    }
                    try {
                        File file = new File("Report.txt");
                        FileWriter writer = new FileWriter(file);
                        for (int i = 0; i < M; i++) {
                            writer.write(memory[i].toString() + "\n");
                        }
                        writer.close();
                        System.out.println("Memory block information written to file: Report.txt");
                    } catch (IOException e) {
                        System.out.println("Error writing to file: " + e.getMessage());
                    }
                    break;
                case 4:
                    // Exit loop
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid input: Please enter a number between 1 and 4.");
            }
        }
        
        
    }

    //  findFirstFitPartition

    // findBestFitPartition

    // findWorstFitPartition

}
