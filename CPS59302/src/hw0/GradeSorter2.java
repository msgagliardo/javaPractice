/* Marc Gagliardo						September 3, 2015 
   CPS593.02										hw0*/


package hw0;
import java.util.Scanner;

public class GradeSorter2 {
	private static int a, b, c, d, f;

	public static void main(String[] args) {
		
		System.out.print("Enter all of the grades: ");		
		Scanner sc = new Scanner(System.in);
			
		while (sc.hasNextInt()) {
			int grade = sc.nextInt();
			if (90 <= grade && grade <= 100)
				a++;
			else if (80 <= grade && grade <= 89)
				b++;
			else if (70 <= grade && grade <= 79)
				c++;
			else if (60 <= grade && grade <= 69)
				d++;
			else if (0 <= grade && grade <= 59)
				f++;
			else if (grade == -1)
				break;
			else
				System.err.println("An invalid grade was entered.");
		}
		System.out.println("The total number of grades = "
			+ (a+b+c+d+f));
		System.out.println("Number of A's = " + a);
		System.out.println("Number of B's = " + b);
		System.out.println("Number of C's = " + c);
		System.out.println("Number of D's = " + d);
		System.out.println("Number of F's = " + f);
		
		sc.close(); 
	}
}		
	
