package test;

public class testString {
	public static void main(String[] args) {
		String a = "aBcDEfg";
		String b = "";
		for (char t : a.toCharArray()) {
			if(t<=90) {
				b=b+(char)(t+32);
			} else {
				b=b+(char)(t-32);
			}
		}
		System.out.println(b);
	}
}
