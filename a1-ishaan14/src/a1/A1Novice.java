package a1;
import java.util.Scanner;

public class A1Novice {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int studs = scanner.nextInt();
		String grade = "";
		for (int i = 0; i <= studs; i++) {
			String firstName = scanner.next();
			String lastName = scanner.next();
			double assignmentGrade = scanner.nextDouble();
			double partGrade = scanner.nextDouble();
			double midTerm = scanner.nextDouble();
			double finalExam = scanner.nextDouble();
						
			double weightAvg = ((0.15 * partGrade) + (0.20 * midTerm) + (0.25 * finalExam) + (0.40 * assignmentGrade));
						
			if (weightAvg >= 94) {
				grade = "A";
			} else if ((90 <= weightAvg) && (weightAvg < 94)) {
				grade = "A-";
			} else if ((86 <= weightAvg) && (weightAvg < 90)) {
				grade = "B+";
			} else if ((83 <= weightAvg) && (weightAvg < 86)) {
				grade = "B";
			} else if ((80 <= weightAvg) && (weightAvg < 83)) {
				grade = "B-";
			} else if ((76 <= weightAvg) && (weightAvg < 80)) {
				grade = "C+";
			} else if ((73 <= weightAvg) && (weightAvg < 76)) {
				grade = "C";
			} else if ((70 <= weightAvg) && (weightAvg < 73)) {
				grade = "C-";
			} else if ((65 <= weightAvg) && (weightAvg < 70)) {
				grade = "D+";
			} else if ((60 <= weightAvg) && (weightAvg < 65)) {
				grade = "D";
			} else if (weightAvg < 60) {
				grade = "F";
				
			}
						
			System.out.println();
			System.out.println(firstName.charAt(0) + ". " + lastName + " " + grade); 
			
			
			

		
		// Your main program code here.			
			
			
		
			
		
	}
		scanner.close();
		}
	}

	
	// Feel free to define addition methods here.
	// Be sure to declare them as "public static"
	
