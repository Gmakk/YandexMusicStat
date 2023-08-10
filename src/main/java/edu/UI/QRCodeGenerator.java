package edu.UI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {
    private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";

    public static void generateQRCodeImage(String text, int width, int height, String fileName)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath("src/main/resources/" + fileName + ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
