public class Partition {

    private String status; 
    private int startAddress;
    private int endAddress;
    private int fragmentSize;
    private int partitionSize;
    private String processNum;
    private int processSize;



    public Partition(int startAddress, int endAddress, int partitionSize) {
        this.status = "free";
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.fragmentSize = -1;
        this.partitionSize = partitionSize;
        this.processNum = "Null"; 
    }

    // public void calculateInternalFragment() {
    //     if (!this.processNum.equals("Null") && this.status.equals("allocated")) {
    //         int fragment = this.partitionSize - this.processSize;
    //         this.setFragmentSize(fragment);
    //     }
    // }

    public String toString() {
        String partitionDetails = String.format(" | The Size of Partition: %-5d", this.partitionSize);
        partitionDetails += String.format(" | The Partition Status:%-5s", this.status);
        partitionDetails += String.format(" | The Process Number: %-5s", this.processNum);
        partitionDetails += String.format(" | The Fragmentation Size: %-5s", this.fragmentSize);
        partitionDetails += String.format(" | Starting Address:%-9d (bytes)", this.startAddress);
        partitionDetails += String.format(" | Ending Address:%-8d (bytes) \n", this.endAddress);
        return partitionDetails;
    }

    public String getStatus() {
        return status;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public int getEndAddress() {
        return endAddress;
    }

    public int getFragmentSize() {
        return fragmentSize;
    }

    public int getPartitionSize() {
        return partitionSize;
    }

    public String getProcessNum() {
        return processNum;
    }

    public int getProcessSize() {
        return processSize;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }

    public void setEndAddress(int endAddress) {
        this.endAddress = endAddress;
    }

    public void setFragmentSize(int fragmentSize) {
        this.fragmentSize = fragmentSize;
    }

    public void setPartition(int partition) {
        this.partitionSize = partition;
    }

    public void setProcessNum(String processNum) {
        this.processNum = processNum;
    }

    public void setProcessSize(int processSize) {
        this.processSize = processSize;
    }
}
