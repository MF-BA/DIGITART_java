package entity;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class Code128BarcodeGenerator {

    private static final int DPI = 300;
    private static final String FORMAT = "image/png";
    private static final double BAR_HEIGHT = 15.0; // Adjust this value to change the height of the bars

    public void generateBarcode(String barcodeText, OutputStream outputStream) throws IOException {
        // Create the barcode bean
        Code128Bean bean = new Code128Bean();

        // Configure the barcode generator
        bean.setModuleWidth(0.5); // Increase the width of the bars
        bean.setBarHeight(BAR_HEIGHT);
        bean.doQuietZone(true); // Add a quiet zone

        // Output the barcode as PNG image
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(outputStream, FORMAT, DPI,
                BufferedImage.TYPE_BYTE_BINARY, false, 0);

        // Generate the barcode
        bean.generateBarcode(canvas, barcodeText);

        // Signal end of generation
        canvas.finish();
    }

}
