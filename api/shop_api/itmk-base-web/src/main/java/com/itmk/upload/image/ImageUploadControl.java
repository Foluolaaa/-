package com.itmk.upload.image;

import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class ImageUploadControl {
    // 图片上传的路径
    @Value("${web.uploadpath}")
    private String webUploadPath;

    // 上传参数
    @RequestMapping("/uploadImage")
    public ResultVo uploadImage(@RequestParam("file")MultipartFile file){
        String target_url = "";
        // 获取名称
        String fileName = file.getOriginalFilename();
        // 获取扩展名
        String fileExtendName = fileName.substring(fileName.indexOf("."));
        // 生成新的文件名
        String newFileName = UUID.randomUUID().toString()+fileExtendName;
        // 上传路径 自动创建该文件夹
        String path = webUploadPath;
        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.mkdirs();
            // 设置文文夹权限为可读
            fileDir.setWritable(true);
        }
        File targetFile = new File(path,newFileName);
        try{
            file.transferTo(targetFile);
            // 返回图片的路径给前端
            target_url = "/" + targetFile.getName();
        }catch (Exception e){
            return null;
        }
        return ResultUtils.success("上传成功", "/images" + target_url);
    }

}
