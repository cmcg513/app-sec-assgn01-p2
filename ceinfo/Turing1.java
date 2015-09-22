import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.math.BigInteger;


//Store the instructions from the file
class Instruction {
	String instruction;
	String[] args;
	
	public Instruction(String instruction, String[] args) {
		this.instruction = instruction;
		this.args = args;
	}
	void setInstruction(String instr) {
		this.instruction = instr;
	}
	String getInstruction() {
		return (this.instruction);
	}
	void setArgs(String[] args) {
		this.args = args;
	}
	String[] getArgs() {
		return this.args;
	}
	void printInstruction() {
		String str = "\tPrinting instruction.  line=" + this.getInstruction() + "\t\tArg=";
		for (int x=0; x<this.getArgs().length; x++) {
			str += this.getArgs()[x] + "_";
		}
		System.out.println(str);
	}
}

class TuringTest
{  
	static int mainCounter = 0;
	static int MAX_STORAGE_SIZE = 2001;
	
	static ArrayList<Instruction> instructions = new ArrayList<Instruction>();
	//static int[] storage = new int[MAX_STORAGE_SIZE];  -- orig started with this, but too small for pwrs
	static BigInteger[] bigstorage = new BigInteger[MAX_STORAGE_SIZE];
	//Only write to storage from storage[0-2000];
	
	
	public static void main(String args[])
    {
		
		System.out.println("Output for Turing Machine Program:");       

		initializeStorage();
		/***
		for (int x=0; x<args.length; x++) {
			System.out.println("args[" + x + "]=" + args[x]);
		}
		****/
		//open input file for processing data 	
		if (args.length < 1) {
			shutdown("ERROR: must provide an input file on, exiting.  Usage: TuringTest <file>", 1);
		}
		String fileName = args[0];
		/***
		fileName = ",/test.dat";
		fileName = "fakeNonExistantFile";
		fileName = "/Users/ceng/Documents/workspace/ApplicationSec_HW1_Eclipse/src/fibonacci.dat";
		fileName = "/Users/ceng/Documents/workspace/ApplicationSec_HW1_Eclipse/src/testEmpty.dat";
		fileName = "/Users/ceng/Documents/workspace/ApplicationSec_HW1_Eclipse/src/testErrors.dat";
		fileName = "/Users/ceng/Documents/workspace/ApplicationSec_HW1_Eclipse/src/test.dat";
		fileName = "/Users/ceng/Documents/workspace/ApplicationSec_HW1_Eclipse/src/addSub.dat";
		fileName = "/Users/ceng/Documents/workspace/ApplicationSec_HW1_Eclipse/src/fibonacci.dat";
		fileName = "/Users/ceng/Documents/workspace/ApplicationSec_HW1_Eclipse/src/powersOfTwo.dat";
		**/
		readFile(fileName);
    }

    
	
    //************************************************************************
    // Read the file input file and add to the instructions list
    //************************************************************************
	static void readFile(String fileName) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		 
			String line = null;
			while ((line = br.readLine()) != null) {
				if (addValidInstructionLine(line)) {
					//instructions.get(mainCounter).printInstruction();
					mainCounter++;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			shutdown("ERROR: failed to find input file [" + fileName + "], exiting.", 1);
		} catch (IOException e) {
			//e.printStackTrace();
			shutdown("ERROR: failed for IO, exiting.", 1);
		}
	}
	
	
    //************************************************************************
	// Check if the line contains valid instructions
    //************************************************************************
	static boolean addValidInstructionLine(String line) {     
		boolean errFound = true;
		
		if (line.trim().isEmpty() || line.startsWith("#")) { 
			return false;
		}
		
	     String[] lineSplit = line.split(",");
	     /***
	     System.out.println("========================");
	     for (int x=0; x<lineSplit.length; x++) {
	    	 System.out.println("\t" + lineSplit[x]);
	     }
	     **/
	     
	     //verify each instruction for validity
	     String instrType = lineSplit[0];
	     argExitIfFail(lineSplit, lineSplit[0]);
	     
	     try {
		     if (instrType.equals("SET")) {
		    	 BigInteger a = new BigInteger(lineSplit[1]);
		    	 BigInteger b = new BigInteger(lineSplit[2]);
	
		    	 //Check 1 to see if it's within the storage space
		    	 // Check 2 to see if it's in the int size
		    	 if (isInStorageRange(a) && isInMaxMinDatatype(b)) {
			    		 bigstorage[a.intValue()] = b;
				    	 System.out.println("\tSET bigstorage[" + a + "]=" + bigstorage[a.intValue()]);
		    	 } else {
		    		 	shutdown("ERROR: fail on SET [" + line + "]", 1);
		    	 }
		     }
		     else if (instrType.equals("ADD") || instrType.equals("SUB")) {
		    	 BigInteger a = new BigInteger(lineSplit[1]);
		    	 BigInteger b = new BigInteger(lineSplit[2]);
		    	 BigInteger c = new BigInteger(lineSplit[3]);
		    	 		    	 
		    	 // Check 1,2,3 if it's within the address zone
		    	 // Check value of 3 within MAX_SIZE
		    	 if (isInStorageRange(a) && isInStorageRange(b) && isInStorageRange(c)) {
		    		 		bigstorage[c.intValue()] = (instrType.equals("ADD") ? 
		    		 					bigstorage[a.intValue()].add(bigstorage[b.intValue()]) :
		    		 					bigstorage[a.intValue()].subtract(bigstorage[b.intValue()]));
		    		 		System.out.println("\t" + instrType + " bigstorage[" + c.intValue() + "]=" + bigstorage[c.intValue()]);
		    	 }else {
		    		 shutdown("ERROR: fail on ADD [" + line + "]", 1);
		    	 }
		     }
		     else if (instrType.equals("MUL")) {
		    	 BigInteger a = new BigInteger(lineSplit[1]);
		    	 BigInteger b = new BigInteger(lineSplit[2]);
		    	 BigInteger c = new BigInteger(lineSplit[3]);
		    	  
		    	 // Check 1,2,3 if it's within the address zone
		    	 // Check value of 3 within MAX_SIZE
		    	 if (isInStorageRange(a) && isInStorageRange(b) && isInStorageRange(c)) {
			    		 	bigstorage[c.intValue()] = bigstorage[a.intValue()].multiply(bigstorage[b.intValue()]);
			    		 	System.out.println("\tMUL bigstorage[" + c + "]=" + bigstorage[c.intValue()]);
			    		 	//System.out.println("\t\t count=" + mainCounter);
		    	 } else {
		    		 shutdown("ERROR: fail on MUL [" + line + "]", 1);
		    	 }
		     }
		     else if (instrType.equals("SUB")) {
		    	 System.out.println("Cat, SUB");
		     }
		     else if (instrType.equals("DISP")) {
		    	 BigInteger a = new BigInteger(lineSplit[1]);
		    	 // Check 1 if it's within the address zone
		    	 if (isInStorageRange(a)) {	    
		    		 	System.out.println("\tDISP bigstorage[" + a.intValue() + "]=" + bigstorage[a.intValue()]);
		    	 } else {
		    		 shutdown("ERROR: fail on DISP [" + line + "]", 1);
		    	 }
		     }
		     else if (instrType.equals("HALT")) {
	    		 shutdown("Halting program.", 0);
		     } else {
	    		 shutdown("ERROR: unrecognized instruction, program terminating.", 1);
		     }
	     } catch (NumberFormatException e) {
    		 shutdown("ERROR: fail on " + instrType + " for number format error.", 1);
	     }
	     Instruction instruction = new Instruction(line, lineSplit);
	     instructions.add(instruction);	     
	     //printStorage();
	     //instructions.get(mainCounter).printInstruction();

		return errFound;
	}

	
    //************************************************************************
	// Checks the instructions and length of arguments and fails if
	//  does not match
    //************************************************************************
	static void argExitIfFail(String[] args, String type) {
		if ((type.equals("SET") && args.length==3) 
				|| (type.equals("ADD") && args.length==4)
				|| (type.equals("SUB") && args.length==4)
				|| (type.equals("MUL") && args.length==4)
				|| (type.equals("DISP") && args.length==2)
				|| (type.equals("HALT"))) {
			//it's good, do nothing
		} else {
			shutdown("ERROR: fail on argument check for " + type  
					+ ", len=" + args.length,  1);
		}
	}

    //************************************************************************
	// isInMaxMinDatatype - 
	//   limit your inputs so that they can only be the max or min integer
    //************************************************************************
	static boolean isInMaxMinDatatype(BigInteger a) {
		return (a.intValue()<=Integer.MAX_VALUE && a.intValue()>=Integer.MIN_VALUE);
	}
	
    //************************************************************************
	// isInStorageRange
    //************************************************************************
	static boolean isInStorageRange(BigInteger range) {
		int intRange = range.intValue();
		return (intRange<=MAX_STORAGE_SIZE && intRange>=0);
	}

	
    //************************************************************************
	// shutdown - exit on major errors
    //************************************************************************
	static void shutdown(String str, int retCode) {
		System.out.println(str);
		System.exit(retCode);
	}
	
    //************************************************************************
	// printStorage
    //************************************************************************
	static void printStorage() {
		String str = "   printStorage=[";
		for (int x=0; x<MAX_STORAGE_SIZE; x++) {
			str += bigstorage[x] + "|";
		}
		str += "]";
		System.out.println(str);
	}
	
	
    //************************************************************************
	// initializeStorage
    //************************************************************************
	static void initializeStorage() {
		for (int x=0; x<MAX_STORAGE_SIZE; x++) {
			bigstorage[x] = new BigInteger("0");
		}
	}
	
	
	
}

