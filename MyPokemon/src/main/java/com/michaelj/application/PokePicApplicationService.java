package com.michaelj.application;

import com.michaelj.infrastructure.constant.BaseConst;
import com.michaelj.infrastructure.enums.FileSuffixEnum;
import com.michaelj.infrastructure.utils.ResourceUtils;
import com.michaelj.service.ConfigPokeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
public class PokePicApplicationService {
    @Autowired
    private ConfigPokeService configPokeService;


    /**
     * inline: 表示在浏览器中直接显示内容（而不是下载）
     * filename: 指定文件名，当用户选择"另存为"时会使用此名称
     * public: 允许所有缓存（CDN、代理服务器、浏览器）
     * max-age=86400: 缓存有效期为86400秒（24小时） 这能显著提升性能，减少服务器压力
     */
    public ResponseEntity<Resource> getBasePicByCode(String pokeCode) {
        String baseDir = configPokeService.getValueByCode(BaseConst.PIC_BASE_DIR);
        String subDir = configPokeService.getValueByCode(BaseConst.BASE_PIC);
        FileSuffixEnum pngEnum = FileSuffixEnum.PNG;
        String imgName = pngEnum.getFileName(pokeCode);

        Resource resource = ResourceUtils.loadAsUrlResource(baseDir + subDir, imgName);

        // 获取图片MIME类型
        String contentType = MediaType.IMAGE_PNG_VALUE;

        // 返回字节流响应
        //
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + imgName + "\"")
                .header(HttpHeaders.CACHE_CONTROL, "public, max-age=86400") // 缓存一天
                .body(resource);
    }



    /**
     * 自动检测MIME类型
     */
    public String getContentType(Path filePath, String fileName) throws IOException {
        // 1. 尝试通过Files.probeContentType获取
        String mimeType = Files.probeContentType(filePath);

        if (mimeType != null) {
            return mimeType;
        }

        // 2. 根据文件扩展名判断
        String extension = getFileExtension(fileName).toLowerCase();
        return switch (extension) {
            case "jpg", "jpeg" -> "image/jpeg";
            case "png" -> "image/png";
            case "gif" -> "image/gif";
            case "bmp" -> "image/bmp";
            case "webp" -> "image/webp";
            case "ico" -> "image/x-icon";
            case "svg" -> "image/svg+xml";
            default -> "application/octet-stream";
        };


        // 设置缓存和CORS
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(mimeType))
//                .header(HttpHeaders.CACHE_CONTROL, "public, max-age=86400")
//                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
//                .body(resource);
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(BaseConst.SPLIT_DOT);
        return (dotIndex == -1) ? BaseConst.BLANK : fileName.substring(dotIndex + 1);
    }








}
