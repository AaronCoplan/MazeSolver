/*
MIT License

Copyright (c) 2017 Aaron Coplan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

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
