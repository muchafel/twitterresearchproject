package crawler;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HashtagWriter {

	private String name;
	private File file;
	private DateFormat df;
	private Date date;
	
	public HashtagWriter(String a) {
		this.name=a.substring(1);
		//System.out.println("Crawling for "+ name+"...");
		df = new SimpleDateFormat("yyyyMMdd");

		date= new Date();
		
//		String temp1 = "23.10.2013";
//		
//		try {
//			date= df.parse(temp1);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
		String dateString = df.format(date);
		
		//System.out.println(this.name+"_"+dateString+".json");
//		file = new File(this.name+".json");
		
		file = new File(a+"/"+this.name+"_"+dateString+".json");
		System.out.println("pfad1: "+file.getAbsolutePath());
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("pfad: "+file.getAbsolutePath());
	}

	
	public String getName(){
		return this.name;
	}
	
	public void write(String a) {

		Date tempDate= new Date();
		long dateDifference = tempDate.getTime()-date.getTime();
		double dayDifference = dateDifference/(24*60*60*1000);
		
		//System.out.println("Day difference: "+String.valueOf(dayDifference));
		
		if(dayDifference>6){
			String dateString = df.format(tempDate);
			file= new File("#"+this.name+"/"+this.name+"_"+dateString+".json");
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			date=tempDate;
		}
		try {
			OutputStreamWriter writer =new OutputStreamWriter(new FileOutputStream(file,true), "UTF-8");
			//FileWriter writer = new FileWriter(file ,true);
			writer.write(a.toString());
			//System.out.println("JSON to String"+a.toString());
			writer.write(System.getProperty("line.separator"));
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
