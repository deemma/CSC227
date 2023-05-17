import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
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
                        KB = scanner.nextInt();
                        if (KB < 1) {
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

                char strategy = 'N';
                boolean flag = true;

                // Prompt user to enter the allocation strategy
                while(flag){
                    System.out.print("Enter the allocation strategy (F, B, or W): ");
                    try{
                        strategy = scanner.next().toUpperCase().charAt(0);
                        if(!(strategy == 'F' || strategy == 'B' || strategy == 'W'))
                        throw new Exception("Input should be f, b or w");
                        else
                        flag = false;
                    }
                    catch(Exception e){
                        System.out.println("Invalid input: " + e.getMessage());
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
                        System.out.print("Enter process ID as: ");
                        String processId = "P" + scanner.next();
                        try {
                            int num = Integer.parseInt(processId.substring(1));
                            if(num < 0)
                            throw new Exception();
                        }
                        catch(Exception e){
                            System.out.println("Process ID must be a positive integer");
                        }

                        System.out.print("Enter process size: ");
                        int processSize = scanner.nextInt();
                        if (processSize < 1) {
                            throw new Exception("Process size must be a positive integer ");
                        }
                   
                        Boolean allocated = false;
                        //Allocate memory from using the selected allocation strategy
                        switch (strategy) {
                            case 'F':
                                // allocated = firstFit(processId ,processSize);
                                break;
                            case 'B':
                                // allocated = bestFit(processId ,processSize);
                                break;
                            case 'W':
                                // allocated = worsFit(processId ,processSize);
                                break;
                        }

                        if (!allocated) {
                            System.out.println("Error: Not enough memory available to allocate process.");
                        } else {

                            // Display memory state after allocation
                            System.out.print("Memory state after allocation: [");
                            for (int i = 0; i < M; i++) {
                                if (memory[i].getStatus().equals("allocated")) {
                                    System.out.print(memory[i].getProcessNum());
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
                        String processId = "P" + scanner.next();
                        int validate = Integer.parseInt(processId.substring(1));
                        if (validate < 1 ) {
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
                                        System.out.print(memory[j].getProcessNum());
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
