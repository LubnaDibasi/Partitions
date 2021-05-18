
import java.util.*;
import java.io.*;


public class OS {

	public static void main (String [] args) {
		Scanner input = new Scanner (System.in);
		
	try {
	System.out.print("Enter Number Of Partitions: ");
	int numPartitions = input.nextInt();
	Partitions [] partitions = new Partitions [numPartitions];
	int size;
	long startAddress;
	String status = "Free";
	for (int i=0; i<numPartitions; i++) {
	System.out.printf("Enter The Size Of Partition %d in KB :",i+1);
	size = input.nextInt();
	
	
	if(i==0)	{
	startAddress=0;
	Partitions p = new Partitions(status, size, startAddress);
	partitions[i] = p;
	}
	else {
	startAddress = partitions[i-1].getE_add() + 1;
	Partitions p = new Partitions(status, size, startAddress);
	partitions[i] = p;
	}
	}
	process pr = new process();
	int choice;
	 do {
	System.out.println("Enter your choice 1(request) , 2(release) , 3(status report) , 4(exit)");
    choice = input.nextInt();
    
    switch(choice){                  
        case 1: //request
            System.out.println(" Enter process name , size of process in KB , allocation strategy ");
            input.nextLine();
            String allStat= input.nextLine();
            String Splited[]= allStat.split(" ");
            String pname = Splited[0];
            for (int i =0; i<partitions.length; i++) {
        		if (partitions[i].getProcessName().equals(pname)) { 
        		System.out.print("Name isn't unique \n");
        		pname= "taken";
        		break;
        		}} 
            if (pname.equals("taken"))
            	break;
        		
            pr.setProcessName(pname);
           pr.setProcessSize(Integer.parseInt(Splited[1]));
           String strategy= Splited[2].toLowerCase();
           boolean result;
           switch(strategy){
               case "f": result = firstFit(partitions,pr);
               if (!result) {
               	System.out.print("error,insufficient memory \n");
               } else  System.out.println("Success"); 
                   break;
               case "b": result = BestFit(partitions,pr);
               if (!result) {
                  	System.out.print("error,insufficient memory \n");
                  }  else  System.out.println("Success"); 
                   break;
                   case"w": result = worstFit(partitions,pr);
                   if (!result) {
                      	System.out.print("error,insufficient memory \n");
                      }  else  System.out.println("Success"); 
                   break;
                   default: System.out.print("Invalid Input \n");
                   break;
                   
           }// inner switch 
           break;
        case 2:  
        	System.out.print("Enter process name to be released: \n");
        	input.nextLine();
        	String processname = input.nextLine();
        	boolean result_ = release(partitions, processname);
        	 if (result_) {
               	System.out.print("Released successfully \n");
               }  
        	 else System.out.print("process not found \n"); 
        	break;
        
        case 3: file(partitions);
         break;
    
    }
     
     } // outer switch	
    while(choice != 4);

           
	//output
	/*for (int i=0; i<numPartitions; i++) {
		
		System.out.printf("Partition %d : \n", i+1);
		System.out.printf("Starting Address: %d B\n",partitions[i].getS_add() );
		System.out.printf("Ending Address: %d B\n",partitions[i].getE_add() );
		
		System.out.println("Partition Status: " + partitions[i].getStatus());
		System.out.printf("Partition Size: %d KB \n", partitions[i].getSize());
		
		//if (partitions[i].getStatus().equals("Allocated")) {
			System.out.printf("The current Allocated Process: %s \n", partitions[i].getProcessName());
			System.out.printf("Internal Fragmentation Size: %d \n", partitions[i].getFragSize());  
			
			//}
	
		
	}*/ } catch (Exception e) {
		System.out.println("Invalid Input");
		}
		
	
	
	
} 
	
	
	

public static boolean BestFit(Partitions p[],process pr){

       int best= Integer.MAX_VALUE;
        int current=0;
          for (int i=0; i<p.length; i++) {
             if(p[i].getStatus().equals("Free")){
                     if(p[i].getSize() - pr.getProcessSize() < best & p[i].getSize() - pr.getProcessSize() >= 0){
                         best= p[i].getSize() - pr.getProcessSize() ;
                         current=i;
                     }
                 }
                     } 
          if (best == Integer.MAX_VALUE){
              return false;
          }
          else {
               p[current].setStatus("allocated");
               p[current].setFragSize(p[current].getSize()-pr.getProcessSize());
               p[current].setProcessName(pr.getProcessName());
               return true;
              
          }}

public static boolean firstFit(Partitions p[],process pr) {
boolean flag=false;
for(int i=0;i<p.length;i++) {
if(p[i].getSize()>=pr.getProcessSize()&&p[i].getStatus().equals("Free")) {	
	p[i].setProcessName(pr.getProcessName());
	p[i].setStatus("Allocated");
	int fragmentationSize=p[i].getSize()-pr.getProcessSize();
	p[i].setFragSize(fragmentationSize);
	flag=true;
	break;
}
}
return flag;
}


	
public static void file(Partitions p[]) {
try {	
FileWriter f = new FileWriter("Report.txt");
 
for (int i=0; i<p.length; i++) {
		
		f.write("Partition " +(i+1)+ ": \n");
		f.write("Starting Address: "+ p[i].getS_add() + " Bytes\n");
		f.write("Ending Address: "+ p[i].getE_add() + " Bytes\n");
		
		f.write("Partition Status: " + p[i].getStatus() + "\n");
		f.write("Partition Size: "+p[i].getSize()+ " KB \n");
		
	if (p[i].getStatus().equalsIgnoreCase("Allocated")) {
			f.write("The current Allocated Process: " +p[i].getProcessName()+"\n");
			f.write("Internal Fragmentation Size: "+p[i].getFragSize()+" \n");  	
		}
		f.write("------------------------------ \n");
}//end for
f.close();
}
catch(Exception e) {
	System.out.print("Error Writing on a file \n");} 
System.out.print("Writing is done \n");


}//end method


public static boolean worstFit(Partitions p[],process pr)
{

	int wst = -1;
	int index = 0;
	for (int i=0; i<p.length; i++)
	{ 
		if (p[i].getStatus().equals("Free"))
			if (p[i].getSize()> wst)
			{    wst = p[i].getSize();
				index = i;
			}} //gets the worst index
	if (pr.getProcessSize()<= wst) {
		p[index].setStatus("Allocated");
		p[index].setProcessName(pr.getProcessName());
		int fragmentationSize=p[index].getSize()-pr.getProcessSize();
		p[index].setFragSize(fragmentationSize);
		return true;
	} //if it fits
	return false;
} //end method

public static boolean release (Partitions p[], String prName) {
	
	for (int i =0; i<p.length; i++) {
		if (p[i].getProcessName().equals(prName)) {
			p[i].setStatus("Free");
			p[i].setFragSize(-1);
			p[i].setProcessName("null");
			return true;
		}	
	}
	return false;
	}
	





}
