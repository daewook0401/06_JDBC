package test;

import java.util.ArrayList;
import java.util.List;

public class testString {
	public static void main(String[] args) {
		int asdf = 0;
		String b = "w";
		List<Object> lst = new ArrayList<Object>();
		lst.add(asdf);
		lst.add(b);
		for(Object l : lst) {
			System.out.println(l);
		}
		asdf = 123;
		b = "222";
		for(Object l : lst) {
			System.out.println(l);
		}
		
	}
}
