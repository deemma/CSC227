public class Partition {
    
    private boolean status; // true if available false if allocated
    private int startAddress;
    private int endAddress;
    private int fragmentSize;
    private int partitionSize;
    private String processNum;
    private int processSize;

    public Partition(boolean status, int startAddress, int endAddress, int partitionSize, String processNum) {
        this.status = false;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.fragmentSize = -1;
        this.partitionSize = partitionSize;
        this.processNum = "null"; // or should we actually make it null?;
    }

    public void calculateFragment() {
        if (!this.processNum.equals("null") && this.status == false) {
            int fragment = this.partitionSize - this.processSize;
            this.setFragmentSize(fragment);
        }
    }

    public boolean isStatus() {
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

    public int getPartition() {
        return partitionSize;
    }

    public String getProcessNum() {
        return processNum;
    }

    public int getProcessSize() {
        return processSize;
    }

    public void setStatus(boolean status) {
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
