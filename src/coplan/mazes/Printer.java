package coplan.mazes;

import java.awt.PrintJob;
import java.awt.image.*;
import java.awt.print.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import java.io.*;
import javax.imageio.*;

public class Printer {

	public static void main(String[] args){
		Maze m = new Generator(10,10,false).getMaze();
		Display display = new Display();
		
		BufferedImage bi = display.generateMazeImage(m);
		
		try{
			printImage(bi);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void printImage(BufferedImage image) throws Exception {
		DocFlavor.INPUT_STREAM docFlavor = DocFlavor.INPUT_STREAM.GIF;
		
		PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
		attributes.add(new Copies(1));
		
		PrintService[] services = PrintServiceLookup.lookupPrintServices(docFlavor, attributes);
		for(PrintService ps : services){
			System.out.println(ps.getName());
		}
	   
	}
}
