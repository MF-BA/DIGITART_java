package entity;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QrCodeGenerator {
    static ByteMatrix byteMatrix;
    public static byte[] generateQrCode(String text) throws WriterException, IOException {
        QRCodeWriter writer = new QRCodeWriter();
             
    try {
        byteMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 200, 200);
    } catch (WriterException e) {
        e.printStackTrace();
    }
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(byteMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        pngOutputStream.close();

        return pngData;
    }
}
