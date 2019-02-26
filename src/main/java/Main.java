import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Main {

	public static void main(String[] args) {
		createPDF();
	}

	private static void createPDF() {
		long start = System.currentTimeMillis();
		try (InputStream is = new FileInputStream(new File("docx/Sample medium docx.docx"))) {
			// 1) Load DOCX into XWPFDocument
			XWPFDocument document = new XWPFDocument(is);

			// 2) Prepare Pdf options
			PdfOptions options = PdfOptions.create();

			try (OutputStream out = new FileOutputStream(new File("pdf/HelloWorld.pdf"));) {
				// 3) Convert XWPFDocument to Pdf
				PdfConverter.getInstance().convert(document, out, options);
			}
			System.err.println("GenerateHelloWorld.pdf with " + (System.currentTimeMillis() - start) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
