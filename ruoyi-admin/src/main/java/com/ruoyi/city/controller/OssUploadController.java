package com.ruoyi.city.controller;

import com.ruoyi.city.service.OssService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/city/api/oss")
public class OssUploadController {

    @Autowired
    private OssService ossService;

    @PostMapping("/upload")
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return AjaxResult.error("上传文件不能为空");
            }

            // 校验文件类型
//            String contentType = file.getContentType();
//            if (!Arrays.asList("image/jpeg", "image/png").contains(contentType)) {
//                return AjaxResult.error("仅支持JPG/PNG格式");
//            }

            // 上传到OSS
            String url = ossService.upload(file);
            return AjaxResult.success("上传成功", Collections.singletonMap("url", url));
        } catch (Exception e) {
            return AjaxResult.error("上传失败：" + e.getMessage());
        }
    }


}
