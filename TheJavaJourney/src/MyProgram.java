import java.util.Scanner;

//Classes contain members
//Members are either methods or properties
//Methods do something
//Properties store something
//access modifier (public) - Who can use this?
//static --> No instance of a class needed
//object --> instance of a class

//literals - the value given by me (no expression)
//expression - evaluates multiple literals to a value
//primitive types
//objects - instance of a class (class is the data type)

//unary operators, e.g. (double) x (works only on the value directly to the right

//Convert string to integer: Integer.valueOf (returns an Object), Integer.parseInt (returns a primitive)


public class MyProgram {  //class
	
	public static void main(String[] args) {
		String x = "da ba da doep da ba";
		System.out.println(x.charAt(x.length()-1));
		System.out.println(x.concat(", jaja, geen mop"));
		System.out.println(x);
		
		System.out.println(x.contains("doep"));
		System.out.println(x.indexOf("da", x.indexOf("da") + 1));
		System.out.println(x.lastIndexOf("doep"));
		}
}
