package com.ruoyi.suyuan.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.suyuan.tools.ImageCompressor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/suyuan/file")
public class FileController extends BaseController {
    @Value("${suyuan.uploadPath}")
    private String uploadPath;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        long MAX_FILE_SIZE = 1 << 20;
        Map<String, Object> response = new HashMap<>();
        int code = 200;
        String msg = "success";
        Map<String, Object> data = new HashMap<>();

        try {
            // 获取当前用户ID
            Long userId = getUserId();

            // 检查文件大小
            if (file.getSize() > MAX_FILE_SIZE) {
                code = 1;
                msg = "上传的图片大小不能超过1M";
                return ResponseEntity.ok(createResponse(code, msg, data));
            }
            // 获取项目根目录
            String projectDir = System.getProperty("user.dir");
            // 创建上传目录
            String userDir = projectDir + File.separator + uploadPath + File.separator + userId;
            File userDirFile = new File(userDir);
            if (!userDirFile.exists()) {
                if (!userDirFile.mkdirs()) {
                    code = 1;
                    msg = "创建目录失败";
                    return ResponseEntity.ok(createResponse(code, msg, data));
                }
            }

            // 生成新文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + extension;
            String imagePath = userDir + File.separator + newFileName;

            // 保存文件
            File destFile = new File(imagePath);
            file.transferTo(destFile);

            // 压缩图片
            try {
                String compressedPath = ImageCompressor.compressImg(imagePath, 500);
                // 移除upload/前缀
                String relativePath = compressedPath.substring(compressedPath.indexOf(uploadPath));
                data.put("filePath",  File.separator + relativePath);
            } catch (Exception e) {
                // 如果压缩失败，使用原始文件路径
                String relativePath = imagePath.substring(imagePath.indexOf(uploadPath));
                data.put("filePath", File.separator + relativePath);
            }

        } catch (Exception e) {
            code = 1;
            msg = "文件上传失败: " + e.getMessage();
        }

        return ResponseEntity.ok(createResponse(code, msg, data));
    }

    @PostMapping("/upload_voice")
    public ResponseEntity<?> uploadVoice(@RequestParam("file") MultipartFile file) {
        long MAX_FILE_SIZE = 5 << 20;
        Map<String, Object> response = new HashMap<>();
        int code = 200;
        String msg = "success";
        Map<String, Object> data = new HashMap<>();

        try {
            // 获取当前用户ID
            Long userId = getUserId();

            // 检查文件大小
            if (file.getSize() > MAX_FILE_SIZE) {
                code = 1;
                msg = "上传的视频大小不能超过5M";
                return ResponseEntity.ok(createResponse(code, msg, data));
            }
            // 获取项目根目录
            String projectDir = System.getProperty("user.dir");
            // 创建上传目录
            String userDir = projectDir + File.separator + uploadPath + File.separator + userId;
            File userDirFile = new File(userDir);
            if (!userDirFile.exists()) {
                if (!userDirFile.mkdirs()) {
                    code = 1;
                    msg = "创建目录失败";
                    return ResponseEntity.ok(createResponse(code, msg, data));
                }
            }

            // 生成新文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + extension;
            String voicePath = userDir + File.separator + newFileName;

            // 保存文件
            File destFile = new File(voicePath);
            file.transferTo(destFile);

            // 压缩图片
            try {
                String compressedPath = ImageCompressor.voiceCompression("/usr/bin/ffmpeg", voicePath);
                // 移除upload/前缀
                String relativePath = compressedPath.substring(compressedPath.indexOf(uploadPath));
                data.put("filePath",  File.separator + relativePath);
            } catch (Exception e) {
                // 如果压缩失败，使用原始文件路径
                String relativePath = voicePath.substring(voicePath.indexOf(uploadPath));
                data.put("filePath", File.separator + relativePath);
            }

        } catch (Exception e) {
            code = 1;
            msg = "文件上传失败: " + e.getMessage();
        }

        return ResponseEntity.ok(createResponse(code, msg, data));
    }

    private Map<String, Object> createResponse(int code, String msg, Map<String, Object> data) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("msg", msg);
        response.put("data", data);
        return response;
    }
}
