package com.ruoyi.suyuan.tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class ImageCompressor {

    /**
     * 压缩图片
     * @param source 源图片路径
     * @param height 目标高度
     * @return 压缩后的图片路径
     * @throws IOException 如果图片处理过程中发生错误
     */
    public static String compressImg(String source, int height) throws IOException {
        // 检查文件格式
        if (!Pattern.matches("^.*\\.((png)|(PNG)|(jpg)|(JPG))$", source)) {
            throw new IllegalArgumentException(source + " is not a .png or .jpg file");
        }

        File sourceFile = new File(source);
        if (!sourceFile.exists()) {
            throw new IOException("Source file not found: " + source);
        }

        // 读取图片
        BufferedImage img = ImageIO.read(sourceFile);
        if (img == null) {
            throw new IOException("Failed to read image: " + source);
        }

        // 获取原始宽度
        int originalWidth = img.getWidth();

        // 如果原始宽度小于目标高度，不进行压缩
        if (originalWidth <= height) {
            throw new IllegalArgumentException("尺寸太小,不压缩");
        }

        // 计算新的宽度，保持宽高比
        int newWidth = height;
        int newHeight = (int) ((double) img.getHeight() * height / originalWidth);

        // 创建新的图片
        BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, img.getType());
        Graphics2D g = resizedImg.createGraphics();

        // 设置图片质量
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 绘制调整大小后的图片
        g.drawImage(img, 0, 0, newWidth, newHeight, null);
        g.dispose();

        // 生成新文件名
        String newName = generateNewFileName(source, height);

        // 保存压缩后的图片
        File outputFile = new File(newName);
        String formatName = source.toLowerCase().endsWith(".png") ? "png" : "jpg";
        ImageIO.write(resizedImg, formatName, outputFile);

        // 获取绝对路径
        String absolutePath = outputFile.getAbsolutePath();
        System.out.println("New image successfully saved at: " + absolutePath);

        return absolutePath;
    }

    /**
     * 生成新的文件名
     * @param originalPath 原始文件路径
     * @param height 目标高度
     * @return 新的文件名
     */
    private static String generateNewFileName(String originalPath, int height) {
        File originalFile = new File(originalPath);
        String fileName = originalFile.getName();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String nameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));

        return originalFile.getParent() + File.separator +
                nameWithoutExtension + "_" + height + extension;
    }

    /**
     * 使用ffmpeg压缩/转码音视频文件
     * @param ffmpegPath ffmpeg可执行文件路径（如 /usr/bin/ffmpeg）
     * @param filePath   原始文件路径
     * @return           压缩后文件路径
     * @throws IOException          进程执行异常
     * @throws InterruptedException 进程被中断
     */
    public static String voiceCompression(String ffmpegPath, String filePath) throws IOException, InterruptedException {
        // 生成目标文件名，如 /home/222.flv -> /home/222_tmp.flv
        String tmpPath = filePath.replaceFirst("\\.(?=[^\\.]+$)", "_tmp.");

        // 构造命令
        ProcessBuilder pb = new ProcessBuilder(
                ffmpegPath, "-i", filePath, tmpPath
        );
        // 可选：设置工作目录
        pb.directory(new File(System.getProperty("user.dir")));
        // 合并错误输出到标准输出
        pb.redirectErrorStream(true);

        // 启动进程并等待结束
        Process process = pb.start();
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new IOException("ffmpeg process failed, exit code: " + exitCode);
        }
        return tmpPath;
    }

    //public static void main(String[] args) {
    //    String source = "/Users/chengang/Downloads/iShot_2025-05-12_09.45.45.png";
    //    int height = 500;
    //
    //    try {
    //        String compressedImagePath = compressImg(source, height);
    //        System.out.println("Compressed image saved at: " + compressedImagePath);
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //}
}
