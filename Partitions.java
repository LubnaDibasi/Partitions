
public class Partitions {

	private String status;
	private int size;
	private long S_add;
	private long E_add;
	private String processName;
	private int fragSize;
	private int processSize;
	
	
	public Partitions(String status, int size, long S_add){ 
	this.status = status;
	this.size = size;
	this.S_add = S_add;
	E_add = S_add +(size*1024) -1;
			
	if (status.equals("Free"))
		processName = "null";
		
	fragSize = calcFragSize(S_add, E_add, size);
	
	
		
	}
	
	public int calcFragSize (long start, long end, int size) {
		if (status.equals("Free")) 
	return 	(((int)end-(int)start)/1024) - size; 
		
		//else if (status.equals("Allocated"))    //to be done in phase 2
	 
		else return 0;
		
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getS_add() {
		return S_add;
	}

	public void setS_add(long s_add) {
		S_add = s_add;
	}

	public long getE_add() {
		return E_add;
	}

	public void setE_add(long e_add) {
		E_add = e_add;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public int getFragSize() {
		return fragSize;
	}

	public void setFragSize(int fragSize) {
		this.fragSize = fragSize;
	}

	
	
	
	
	
}
