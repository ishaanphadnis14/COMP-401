package a1;
import java.util.Scanner;

public class A1Jedi {

	public static void main(String[] args) {
		
			Scanner scanner = new Scanner(System.in);		
		    
		    int aCount = 0;
		    int aMinusCount = 0;
		    int bPlusCount = 0;
		    int bCount = 0;
		    int bMinusCount = 0;
		    int cPlusCount = 0;
		    int cCount = 0;
		    int cMinusCount = 0;
		    int dPlusCount = 0;
		    int dCount = 0;
		    int fCount = 0;

		    
		    int numAssign = scanner.nextInt();   
		   

		    int maxAssignPoints = 0;
		    for (int i = 0; i < numAssign; i++) {
				maxAssignPoints = maxAssignPoints + scanner.nextInt();
			}
		  
		    int maxPartPoints = scanner.nextInt();
		   
		
		    int numStudents = scanner.nextInt();
		 
		    double [] assignmentGrades = new double [numStudents];
		    double [] participationGrades = new double [numStudents];
		    double [] midtermExamArray = new double [numStudents];
		    double [] finalExamArray = new double [numStudents];

		    for (int i = 0; i < numStudents; i++) {
		        
		    	String firstName = scanner.next();
		    	
		    	
		        String lastName = scanner.next();
		       
		        
		        double partPoints = scanner.nextDouble();
		   	        
		        
				double assignPoints = 0;
		        
		        for (double x = 0; x < numAssign; x++) {
					assignPoints += scanner.nextDouble();
		        }
				double assignGrade = (assignPoints / maxAssignPoints) * 100;
				
				assignmentGrades[i] = assignGrade;
				
		        
		        double partGrade = 100 * (partPoints / (maxPartPoints * .8));
				
		        if (partGrade > 100) {
						partGrade = 100;
					}
		        
		        participationGrades[i] = partGrade;
		       

		        int Midterm = scanner.nextInt();
		        
		        
		        midtermExamArray [i] = Midterm;
		        
		        
		        int finalExam = scanner.nextInt();
		        
		        finalExamArray [i] = finalExam;
		        
		    }

		        
		        double midtermAverage = 0;
		        for (int i = 0; i < midtermExamArray.length; i++) {
		            midtermAverage += midtermExamArray[i];
		            
		        }
		        midtermAverage = midtermAverage / numStudents;
		        
		        double standdev = 0;
		        for (int i = 0; i < midtermExamArray.length; i++) {
		        	standdev += Math.pow((midtermExamArray[i] - midtermAverage) , 2);
		        }
		        
		        double midtermStanddev = Math.sqrt((standdev / midtermExamArray.length));
		        
		    	double [] normalMidterm = new double[numStudents];
		        for (int i = 0; i < midtermExamArray.length; i++) {
		        	normalMidterm[i] = ((midtermExamArray[i] - midtermAverage) / midtermStanddev);
		        }
		      
		        
		        
		        double curvedMidtermPercentage = 0;
		        double[] curvedMidtermPercentages = new double [numStudents];
		      
		        for (int i = 0; i < normalMidterm.length; i++) {
		        	if (normalMidterm[i] >= 2.0) {
		        		curvedMidtermPercentage = 100;
		        	} else if (normalMidterm[i] >= 1.0) {
		        		curvedMidtermPercentage = ((normalMidterm[i] - 1.0) / (2.0 - 1.0) * (100-94) + 94);
		        	} else if  (normalMidterm[i] >= 0) {
		        		curvedMidtermPercentage = ((normalMidterm[i] - 0.0) / (1.0 - 0.0) * (94-85) + 85);
		        	} else if (normalMidterm[i] >= -1.0) {
		        		curvedMidtermPercentage = ((normalMidterm[i] - -1.0) / (0.0 - -1.0) * (85-75) + 75);
		        	} else if (normalMidterm[i] >= -1.5) {
		        		curvedMidtermPercentage = ((normalMidterm[i] - -1.5) / (-1.0 - -1.5) * (75-65) + 65);
		        	} else if  (normalMidterm[i] >= -2.0) {
		        		curvedMidtermPercentage = ((normalMidterm[i] - -2.0) / (-1.5 - -2.0) * (65-55) + 55);
		        	} else if  (normalMidterm[i] >= -3.0) {
		        		curvedMidtermPercentage = ((normalMidterm[i] - -3.0) / (-2.0 - -3.0) * (55-30) + 30);
		        	} else if (normalMidterm[i] >= -4.0) {
		        		curvedMidtermPercentage = ((normalMidterm[i] - -4.0) / (-3.0 - -4.0) * (30-0) + 0);
		        	} else {
		        		curvedMidtermPercentage = 0;
		        	} 
		        	curvedMidtermPercentages[i] = curvedMidtermPercentage; 
		        	
		        }
		        
		                

		        
		       

		        double finalAverage = 0;
		        for (int i = 0; i < finalExamArray.length; i++) {
		            finalAverage += finalExamArray[i];
		        }
		        
		        finalAverage = finalAverage / finalExamArray.length;
		        
		        
		        double standev= 0;
		        for (int i = 0; i < midtermExamArray.length; i++) {
		        	standev += Math.pow((finalExamArray[i] - finalAverage) , 2);
		        }
		        
		        double finalSD = Math.sqrt(standev / finalExamArray.length);
		    	double [] normalFinal = new double[numStudents];
		        for (int i = 0; i < finalExamArray.length; i++) {
		        	normalFinal[i] = ((finalExamArray[i] - finalAverage) / finalSD);
		        }
		        
		        
		        double curvedFinal = 0;
		        double[] curvedFinals = new double [numStudents];
		      
		        for (int i = 0; i < normalFinal.length; i++) {
		        	if (normalFinal[i] >= 2.0) {
		        		curvedFinal = 100;
		        	} else if (normalFinal[i] >= 1.0) {
		        		curvedFinal = ((normalFinal[i] - 1.0) / (2.0 - 1.0) * (100-94) + 94);
		        	} else if  (normalFinal[i] >= 0) {
		        		curvedFinal = ((normalFinal[i] - 0.0) / (1.0 - 0.0) * (94-85) + 85);
		        	} else if (normalFinal[i] >= -1.0) {
		        		curvedFinal = ((normalFinal[i] - -1.0) / (0.0 - -1.0) * (85-75) + 75);
		        	} else if (normalFinal[i] >= -1.5) {
		        		curvedFinal = ((normalFinal[i] - -1.5) / (-1.0 - -1.5) * (75-65) + 65);
		        	} else if  (normalFinal[i] >= -2.0) {
		        		curvedFinal = ((normalFinal[i] - -2.0) / (-1.5 - -2.0) * (65-55) + 55);
		        	} else if  (normalFinal[i] >= -3.0) {
		        		curvedFinal = ((normalFinal[i] - -3.0) / (-2.0 - -3.0) * (55-30) + 30);
		        	} else if (normalFinal[i] >= -4.0) {
		        		curvedFinal = ((normalFinal[i] - -4.0) / (-3.0 - -4.0) * (30-0) + 0);
		        	} else {
		        		curvedFinal = 0;
		        	} 
		        	
		        	 curvedFinals[i] = curvedFinal;
		        } 
		        
		        
	
		        for (int i = 0; i < numStudents; i++) {
				     			
		        double weightedAverage =  ((0.15 *  participationGrades[i]) + (0.20 * curvedMidtermPercentages[i]) + (0.25 * curvedFinals[i]) + (0.40 * assignmentGrades[i]));

		        if (weightedAverage >= 94) {

		            aCount++;
		        }
		        else if (weightedAverage >= 90 && weightedAverage < 94) {

		            aMinusCount++;
		        }
		        else if (weightedAverage >= 86 && weightedAverage < 90) {

		            bPlusCount++;

		        }
		        else if (weightedAverage >= 83 && weightedAverage < 86) {

		            bCount++;
		        }
		        else if (weightedAverage >= 80 && weightedAverage < 83) {

		            bMinusCount++;
		        }
		        else if (weightedAverage >= 76 && weightedAverage < 80) {

		            cPlusCount++;
		        }
		        else if (weightedAverage >= 73 && weightedAverage < 76) {

		            cCount++;
		        }
		        else if (weightedAverage >= 70 && weightedAverage < 73) {

		            cMinusCount++;
		        }
		        else if (weightedAverage >= 65 && weightedAverage < 70) {

		            dPlusCount++;
		        }
		        else if (weightedAverage >= 60 && weightedAverage < 65) {

		            dCount++;
		        }
		        else {

		            fCount++;
		        }
		    }

		    scanner.close();  

		    System.out.println();
		    System.out.println("A : " + aCount);
		    System.out.println("A-: " + aMinusCount);
		    System.out.println("B+: " + bPlusCount);
		    System.out.println("B : " + bCount);
		    System.out.println("B-: " + bMinusCount);
		    System.out.println("C+: " + cPlusCount);
		    System.out.println("C : " + cCount);
		    System.out.println("C-: " + cMinusCount);
		    System.out.println("D+: " + dPlusCount);
		    System.out.println("D : " + dCount);
		    System.out.println("F : " + fCount);
		    
		    
		    


		// Your main program code here.
		
	}
	
	
	// Feel free to define addition methods here.
	// Be sure to declare them as "public static"
}
