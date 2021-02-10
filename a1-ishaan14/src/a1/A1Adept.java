package a1;
import java.util.Scanner;

public class A1Adept {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int numAssign = scanner.nextInt();
		int maxAssignPts = 0;
		for (int o = 0; o < numAssign; o++) {
			maxAssignPts = maxAssignPts + scanner.nextInt();
		}
		int maxPartPoints = scanner.nextInt();
		int numStudents = scanner.nextInt();
		
		for (int i = 0; i <= numStudents; i++) {
			String firstName = scanner.next();
			String lastName = scanner.next();

			double partPoints = scanner.nextDouble();
			double partPercent = 0.0;
			for (int x = 0; x < numAssign; x++) {
				partPercent = 100 * (partPoints / (maxPartPoints * .8));
				if (partPercent > 100) {
					partPercent = 100;
				}
			}
			
			double assignPts= 0.0;
			double assignPercent = 0.0;
			for (int x = 0; x < numAssign; x++) {
				assignPts = assignPts + scanner.nextDouble();
				assignPercent = (assignPts / maxAssignPts) * 100;
			} if (assignPercent > 100) {
				assignPercent = 100;
			}

			
			double midTerm = scanner.nextDouble();
			double finalExam = scanner.nextDouble();
		
			double FinalGrade = ((0.15 * partPercent) + (0.20 * midTerm) + (0.25 * finalExam) + (0.40 * assignPercent));
		
		System.out.println();
		if (FinalGrade >= 94) {
			System.out.println(firstName.charAt(0) + ". " + lastName + " " + "A"); 
		} else if ((90 <= FinalGrade) && (FinalGrade < 94)) {
			System.out.println(firstName.charAt(0) + ". " + lastName + " " + "A-"); 
		} else if ((86 <= FinalGrade) && (FinalGrade < 90)) {
			System.out.println(firstName.charAt(0) + ". " + lastName + " " + "B+"); 
		} else if ((83 <= FinalGrade) && (FinalGrade < 86)) {
			System.out.println(firstName.charAt(0) + ". " + lastName + " " + "B"); 
		} else if ((80 <= FinalGrade) && (FinalGrade < 83)) {
			System.out.println(firstName.charAt(0) + ". " + lastName + " " + "B-"); 
		} else if ((76 <= FinalGrade) && (FinalGrade < 80)) {
			System.out.println(firstName.charAt(0) + ". " + lastName + " " + "C+"); 
		} else if ((73 <= FinalGrade) && (FinalGrade < 76)) {
			System.out.println(firstName.charAt(0) + ". " + lastName + " " + "C"); 
		} else if ((70 <= FinalGrade) && (FinalGrade < 73)) {
			System.out.println(firstName.charAt(0) + ". " + lastName + " " + "C-"); 
		} else if ((65 <= FinalGrade) && (FinalGrade < 70)) {
			System.out.println(firstName.charAt(0) + ". " + lastName + " " + "D+"); 
		} else if ((60 <= FinalGrade) && (FinalGrade < 65)) {
			System.out.println(firstName.charAt(0) + ". " + lastName + " " + "D"); 
		} else if (FinalGrade < 60) {
			System.out.println(firstName.charAt(0) + ". " + lastName + " " + "F"); 
			
		}	

		// Your main program code here.
		
	}
scanner.close();
	
	// Feel free to define addition methods here.
	// Be sure to declare them as "public static"
	
}}

