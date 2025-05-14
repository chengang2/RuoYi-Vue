package com.ruoyi.suyuan.tools;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class QRCodeUtil {

    @Value("${suyuan.urlPath}")
    private String urlPath;

    public String getUrlPath(){
        return urlPath;
    }

    // 生成二维码并写入PNG
    public  void writePng(String filename, BufferedImage img) throws Exception {
        ImageIO.write(img, "png", new File(filename));
        System.out.println("Saved: " + filename);
    }

    // 生成二维码主方法
    public  String getQRCode(String id, String md5Code, String productName, String enterpriseName, int width, int height) throws Exception {
        //String urlPath = "http://slqh.test.cnqos.com/app/mobile/#productBatch/detail?id="; // 你的配置
        String url = urlPath + md5Code;

        BufferedImage qrCode = createQRCode(url, width, height);

        String filename = "upload/qrcode/" + id + "_" + productName + "_" + enterpriseName + ".png";
        writePng(filename, qrCode);

        return createImg1(filename) + "," + createImg2(filename) + "," + createImg3(filename);
    }

    // 生成二维码图片
    public  BufferedImage createQRCode(String content, int width, int height) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    // 合成水印图片1
    public  String createImg1(String filename) throws Exception {
        BufferedImage bg = ImageIO.read(new File("upload/qr1.jpg"));
        BufferedImage watermark = ImageIO.read(new File(filename));
        BufferedImage newImg = imageResize(watermark, 70, 70);

        BufferedImage combined = new BufferedImage(bg.getWidth(), bg.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = combined.createGraphics();
        g.drawImage(bg, 0, 0, null);
        g.drawImage(newImg, 30, 220, null);
        g.dispose();

        String outFile = filename.replace(".png", "1.jpg");
        ImageIO.write(combined, "jpg", new File(outFile));
        return "/" + outFile;
    }

    // 合成水印图片2
    public  String createImg2(String filename) throws Exception {
        BufferedImage bg = ImageIO.read(new File("upload/qr2.jpg"));
        BufferedImage watermark = ImageIO.read(new File(filename));
        BufferedImage newImg = imageResize(watermark, 100, 100);

        BufferedImage combined = new BufferedImage(bg.getWidth(), bg.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = combined.createGraphics();
        g.drawImage(bg, 0, 0, null);
        g.drawImage(newImg, 40, 310, null);
        g.dispose();

        String outFile = filename.replace(".png", "2.jpg");
        ImageIO.write(combined, "jpg", new File(outFile));
        return "/" + outFile;
    }

    // 合成水印图片3
    public  String createImg3(String filename) throws Exception {
        BufferedImage bg = ImageIO.read(new File("upload/qr3.jpg"));
        BufferedImage watermark = ImageIO.read(new File(filename));
        BufferedImage newImg = imageResize(watermark, 140, 140);

        BufferedImage combined = new BufferedImage(bg.getWidth(), bg.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = combined.createGraphics();
        g.drawImage(bg, 0, 0, null);
        g.drawImage(newImg, 48, 380, null);
        g.dispose();

        String outFile = filename.replace(".png", "3.jpg");
        ImageIO.write(combined, "jpg", new File(outFile));
        return "/" + outFile;
    }

    // 图片缩放
    public  BufferedImage imageResize(BufferedImage src, int w, int h) {
        Image tmp = src.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    // 测试
    public static void main(String[] args) throws Exception {
        QRCodeUtil qrCodeUtil = new QRCodeUtil();
        String result = qrCodeUtil.getQRCode("8", "md5code", "product", "enterprise", 256, 256);
        System.out.println(result);
    }
}