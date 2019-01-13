package openweather;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;



public class ClasePrincipal {

	private static String provincia;
	
	public static void main(String[] args) {
		
		ParsearJSON pj = new ParsearJSON();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Introduce una provincia: ");
		provincia = sc.nextLine();
		
		String json = pj.getJson(provincia);
		pj.getDatos(json);
		
		
		
		
	}
}
