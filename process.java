
public class process {
	private String processName;
	private int processSize;
	
	public process(String processName, int processSize) {
		this.processName = processName;
		this.processSize = processSize;
	}
	public process() {
		this.processName = null;
		this.processSize = -1;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public int getProcessSize() {
		return processSize;
	}

	public void setProcessSize(int processSize) {
		this.processSize = processSize;
	}
	
	
	
	
}
