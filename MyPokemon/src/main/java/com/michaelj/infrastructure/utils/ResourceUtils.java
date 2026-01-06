package com.michaelj.infrastructure.utils;

import cn.hutool.core.util.StrUtil;
import com.michaelj.infrastructure.constant.PokeExceptionEnum;
import com.michaelj.infrastructure.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class ResourceUtils {

    /**
     * 安全地创建 UrlResource
     */
    public static Resource loadAsUrlResource(String fileDir, String fileName) {
        if (StrUtil.isBlank(fileDir) || StrUtil.isBlank(fileName)) {
            throw new BusinessException(PokeExceptionEnum.FILE_PATH_BLANK_ERR);
        }

        // 安全校验
        Path safeBasePath = Paths.get(fileDir).normalize();
        Path filePath = safeBasePath.resolve(fileName).normalize();
        if (!filePath.startsWith(safeBasePath)) {
            log.error("【ERROR 读取文件】 路径遍历攻击：{}", fileDir + fileName);
            throw new BusinessException(PokeExceptionEnum.RESOURCE_ATTACH_ERR);
        }

        Path path = filePath.toAbsolutePath();
        log.info("【INFO 读取文件】 路径: {}", path);

        // 检查文件是否存在
//        if (!Files.exists(path)) {
//            log.error("【ERROR 读取图片】 图片不存在");
//            throw new BusinessException(PokeExceptionEnum.RESOURCE_ATTACH_ERR);
//        }

        String uriString = normalizeFileUri(path.toUri().toString());

        UrlResource resource = null;
        try {
            // 创建Resource对象
            resource = new UrlResource(uriString);
            validateResource(resource);
        } catch (MalformedURLException e) {
            log.error("【ERROR 读取文件】 访问资源异常", e);
            throw new BusinessException(PokeExceptionEnum.RESOURCE_ATTACH_ERR);
        }

        return resource;
    }


    /**
     * 读取资源内容为字节数组
     */
    public byte[] readResourceAsBytes(String fileDir, String fileName)  {
        Resource resource = loadAsUrlResource(fileDir, fileName);

        try (InputStream inputStream = resource.getInputStream()) {
            return StreamUtils.copyToByteArray(inputStream);
        } catch (IOException e) {
            log.error("【ERROR 读取文件】 读取资源内容为字节数组异常", e);
            throw new BusinessException(PokeExceptionEnum.RESOURCE_ATTACH_ERR);
        }
    }




    /**
     * 标准化文件URI（处理Windows/Linux差异）
     */
    private static String normalizeFileUri(String uri) {
        if (uri.startsWith("file:/") && !uri.startsWith("file:///")) {
            return "file:///" + uri.substring(6);
        }
        return uri;
    }


    /**
     * 验证资源可用性
     */
    private static void validateResource(UrlResource resource) throws MalformedURLException {
        try {
            if (!resource.exists()) {
                throw new MalformedURLException("文件不存在: " + resource.getFilename());
            }
            if (!resource.isReadable()) {
                throw new MalformedURLException("文件不可读: " + resource.getFilename());
            }
        } catch (IOException e) {
            throw new MalformedURLException("无法访问资源: " + e.getMessage());
        }
    }






}
