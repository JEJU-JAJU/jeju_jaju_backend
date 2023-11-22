package com.jejujaju.event.model.dto;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FileHandler {

    public List<EventImg> parseFileInfo(Long eventId, List<MultipartFile> multipartFiles) throws IOException {
        List<EventImg> fileList = new ArrayList<>();

        if(multipartFiles == null || multipartFiles.isEmpty()) return fileList;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        String absolutePath = new File("").getAbsolutePath() + "\\";

        String path = "images/" + current_date;
        File file = new File(path);

        if(!file.exists()){
            file.mkdirs();
        }

        for(MultipartFile multipartFile : multipartFiles){
            if(!multipartFile.isEmpty()){
                String contentType = multipartFile.getContentType();
                String originalFileExtension;

                if (ObjectUtils.isEmpty(contentType)) {
                    break;
                }
                else {
                    if(contentType.contains("image/jpeg")){
                        originalFileExtension = ".jpg";
                    }
                    else if(contentType.contains("image/png")){
                        originalFileExtension = ".png";
                    }
                    else if(contentType.contains("image/gif")){
                        originalFileExtension = ".gif";
                    }
                    else {
                        break;
                    }
                }

                String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
                EventImg eventImg = EventImg.builder()
                        .eventId(eventId)
                        .originalFileName(multipartFile.getOriginalFilename())
                        .storedFilePath(path + "/" + new_file_name)
                        .fileSize(multipartFile.getSize())
                        .build();
                fileList.add(eventImg);

                file = new File(absolutePath + path + "/" + new_file_name);
                multipartFile.transferTo(file);
            }
        }

        return fileList;
    }
}
