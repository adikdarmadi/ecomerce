package com.ecomerce.service.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindRekursif {
	public static void main(String[] args) {
		String javaFileName="Produk";
		try {
			searchFile(javaFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void searchFile(String javaFileName) throws IOException {
		System.out.println("SEARCH FILE START");
		File file = new File("E:\\workspace-7\\ecomerce-domain\\src\\main\\java\\com\\ecomerce\\medifirst2000\\entities\\"+javaFileName+".java");
			   FileInputStream fstream = new FileInputStream(file);
			   DataInputStream in = new DataInputStream(fstream);
			   BufferedReader br = new BufferedReader(new InputStreamReader(in));
			   String strLine;
			   StringBuffer stringBuffer=new StringBuffer();
			   int count=1;
			   while((strLine=br.readLine())!=null)
			   {
				   Pattern p = Pattern.compile(javaFileName, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
				   Matcher m = p.matcher(strLine);
				   if( m.find()){
				   System.out.println("COUNT "+count+" RESULT : "+strLine);
			   }
				   count++;
		   }
				System.out.println("SEARCH FILE END");

}
}