package com.ruoyi.suyuan.tools;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class PdfUtil {
    /**
     * 将 PDF 每一页转为 JPG 图片，返回所有图片的相对路径（逗号分隔）
     * @param pdfPath 形如 "/upload/8/test.pdf"
     * @return 形如 "/upload/8/fitz_xxx/test001.jpg,/upload/8/fitz_xxx/test002.jpg"
     */
    public static String pdf2Jpg(String pdfPath) throws Exception {

        // 去掉开头的斜杠
        String realPdfPath = pdfPath.startsWith("/") ? pdfPath.substring(1) : pdfPath;
        // 目录和文件名
        int lastSlash = realPdfPath.lastIndexOf("/");
        String dir = realPdfPath.substring(0, lastSlash);
        String baseName = realPdfPath.substring(lastSlash + 1, realPdfPath.lastIndexOf("."));

        // 创建临时目录
        File tmpDir = Files.createTempDirectory(new File(dir).toPath(), "fitz").toFile();

        // 读取 PDF
        PDDocument document = PDDocument.load(new File(realPdfPath));
        PDFRenderer pdfRenderer = new PDFRenderer(document);

        List<String> filePaths = new ArrayList<>();
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 200); // 200 DPI
            String jpgFileName = String.format("%s%03d.jpg", baseName, page);
            File jpgFile = new File(tmpDir, jpgFileName);
            ImageIO.write(bim, "jpg", jpgFile);
            // 返回路径格式和你的 Go 代码一致，加上前缀斜杠
            filePaths.add("/" + tmpDir.getPath().replace("\\", "/") + "/" + jpgFileName);
        }
        document.close();

        // 返回所有图片路径，逗号分隔
        return String.join(",", filePaths);
    }

    // 测试
    public static void main(String[] args) throws Exception {
        String result = pdf2Jpg("/upload/31/5d4dd3e7-5daa-4973-8e90-ab639e28de52.pdf");
        System.out.println(result);
    }
}
