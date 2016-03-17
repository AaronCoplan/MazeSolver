package coplan.mazes;

import java.awt.image.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import java.io.*;
import javax.imageio.*;

public class Printer {

	public static void printImage(BufferedImage image) throws Exception {
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
	    pras.add(new Copies(1));
	    PrintService pss[] = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.GIF, pras);
	    if (pss.length == 0)
	      throw new RuntimeException("No printer services available.");
	    PrintService ps = pss[0];
	    System.out.println("Printing to " + ps);
	    DocPrintJob job = ps.createPrintJob();

	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    ImageIO.write(image, "gif", os);
	    InputStream is = new ByteArrayInputStream(os.toByteArray());
	    
	    Doc doc = new SimpleDoc(is, DocFlavor.INPUT_STREAM.GIF, null);
	    job.print(doc, pras);
	    is.close();
	}
}
