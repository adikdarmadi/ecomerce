package com.ecomerce.service.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import org.apache.commons.io.FileUtils;

public class CopyFilesExample {
	public static void main(String[] args) throws InterruptedException,
			IOException {
		// domain
		String folderWorkspace="ecomerce-domain";
		String folderChange="entities";
		String fromFileName="Bedah";
		String toFileName="Gizi";
		String endFileName="";
		//domain File
		copyAndReplace(folderWorkspace, folderChange, fromFileName, toFileName, endFileName);
		
		//vo
		folderChange="vo";
		endFileName="VO";
		//vo File
		copyAndReplace(folderWorkspace, folderChange, fromFileName, toFileName, endFileName);
		
		// converter -dao -service -service impl
		folderWorkspace="ecomerce-business";
		folderChange="converter";
		endFileName="Converter";
		//Converter File
		copyAndReplace(folderWorkspace, folderChange, fromFileName, toFileName, endFileName);

		folderChange="dao";
		endFileName="Dao";
		//Dao File
		copyAndReplace(folderWorkspace, folderChange, fromFileName, toFileName, endFileName);

		folderChange="service";
		endFileName="Service";
		//Service File
		copyAndReplace(folderWorkspace, folderChange, fromFileName, toFileName, endFileName);

		folderChange="service\\impl";
		endFileName="ServiceImpl";
		//ServiceImpl File
		copyAndReplace(folderWorkspace, folderChange, fromFileName, toFileName, endFileName);

		// controller
		folderWorkspace="ecomerce-web";
		folderChange="controller";
		endFileName="Controller";
		//Controller File
		copyAndReplace(folderWorkspace, folderChange, fromFileName, toFileName, endFileName);
	}

	private static void copyAndReplace(String folderWorkspace, String folderChange, String fromFileName,
			String toFileName, String endFileName) throws IOException, InterruptedException {
		copyFile(fromFileName,toFileName,folderWorkspace,folderChange,endFileName);
		Thread.sleep(3000);
		replaceFile(fromFileName,toFileName,folderWorkspace,folderChange,endFileName);
	}

	private static void replaceFile(String fromFileName,String toFileName,String folderWorkspace,String folderChange,String endFileName) {
		System.out.println("REPLACE FILE " +folderChange+ " START");
		File destFileConverter = new File("E:\\workspace-7\\"+folderWorkspace+"\\src\\main\\java\\com\\ecomerce\\medifirst2000\\"+folderChange+"\\Pap"+toFileName+endFileName+".java");
		   try{
			   FileInputStream fstream = new FileInputStream(destFileConverter);
			   DataInputStream in = new DataInputStream(fstream);
			   BufferedReader br = new BufferedReader(new InputStreamReader(in));
			   String strLine;
			   String result = null;
			   StringBuffer stringBuffer=new StringBuffer();
			   while((strLine=br.readLine())!=null)
			   {
				   System.out.println("LINE : "+strLine);
				   Pattern p = Pattern.compile(fromFileName, Pattern.CASE_INSENSITIVE);
				   Matcher m = p.matcher(strLine);
				   result = m.replaceAll(toFileName);
				   stringBuffer.append(result+"\n");
				   System.out.println("RESULT : "+result);
			   }
		        FileWriter fw = new FileWriter(destFileConverter);
		        fw.write(stringBuffer.toString());
		        fw.close();			   
		        in.close();
			    }catch (Exception e){
			      e.printStackTrace();
			    }
		   System.out.println("REPLACE FILE " +folderChange+ " END");
		   }
		

	private static void copyFile(String fromFileName,String toFileName,String folderWorkspace,String folderChange,String endFileName) throws IOException {
		System.out.println("COPY FILE " +folderChange+ " START");
		File source = new File("E:\\workspace-7\\"+folderWorkspace+"\\src\\main\\java\\com\\ecomerce\\medifirst2000\\"+folderChange+"\\Pap"+fromFileName+endFileName+".java");
		File destFile = new File("E:\\workspace-7\\"+folderWorkspace+"\\src\\main\\java\\com\\ecomerce\\medifirst2000\\"+folderChange+"\\Pap"+toFileName+endFileName+".java");

		// copy file using FileStreams
		long start = System.nanoTime();
		long end;
		copyFileUsingFileStreams(source, destFile);
		System.out.println("Time taken by FileStreams Copy = "
				+ (System.nanoTime() - start));
		System.out.println("COPY FILE " +folderChange+ " END");
	}

	private static void copyFileUsingFileStreams(File source, File dest)
			throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		} finally {
			input.close();
			output.close();
		}
	}

}