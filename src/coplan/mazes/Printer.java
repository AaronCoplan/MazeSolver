package coplan.mazes;

import java.awt.image.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import java.io.*;
import javax.imageio.*;
import java.util.Scanner;

public class Printer {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the width of the maze:");
		String xString = sc.nextLine();
		
		System.out.println("Enter the height of the maze:");
		String yString = sc.nextLine();
		
		sc.close();
		
		int x = Integer.parseInt(xString);
		int y = Integer.parseInt(yString);
		
		Maze m = new Generator(x,y,false).getMaze();
		Display display = new Display();
		
		BufferedImage bi = display.generateMazeImage(m);
		
		try{
			printImage(bi);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void printImage(BufferedImage image) throws Exception {
		System.out.println();
		
		DocFlavor.INPUT_STREAM docFlavor = DocFlavor.INPUT_STREAM.GIF;
		
		PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
		attributes.add(new Copies(1));
		
		PrintService selectedPrintService = null;
		
		PrintService[] services = PrintServiceLookup.lookupPrintServices(docFlavor, attributes);
		for(PrintService ps : services){
			System.out.println(ps.getName());
			
			if(ps.getName().equals("HPA239E6 (HP Photosmart Plus B209a-m)")){
				selectedPrintService = ps;
				break;
			}
			
			PrintServiceAttributeSet attributeSet = ps.getAttributes();
			Attribute[] array = attributeSet.toArray();
			for(int x = 0; x < array.length; x++){
				System.out.println("\t" + array[x].getName() + ": " + array[x].toString());
			}
		}
		
		DocPrintJob job = selectedPrintService.createPrintJob();
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "gif", os);
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		
		Doc doc = new SimpleDoc(is, docFlavor, null);
		job.print(doc, attributes);
		
		is.close();
		os.close();
	}
}
