package com.sds.cafeshop.model.product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sds.cafeshop.domain.Product;
import com.sds.cafeshop.exception.ImageException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageService {
	
	@Value("${upload.location}")
	private String uploadLocation;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	//저장 처리 
	public void save(Product product) throws ImageException{
		MultipartFile file = product.getFile();
		
//		log.debug("title is "+product.getTitle());
		log.debug("original filename is "+file.getOriginalFilename());	
		
		try {
			File directory = resourceLoader.getResource(uploadLocation).getFile();
			log.debug("파일을 저장할 경로는 "+directory.getAbsolutePath());
			
			Path path =Paths.get(directory.getAbsolutePath());
			
			String filename = file.getOriginalFilename();
			String ext = getExt(filename);
			String newname = createFilename(ext);
			
			Path savePath = path.resolve(newname);	
			log.debug("savePath = "+savePath.toString());
			
			product.setFilename(newname);//파일명
			
			Files.copy(file.getInputStream(), savePath);
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new ImageException("업로드 실패", e);
		}
	}
	private String getExt(String path) {
        return path.substring(path.lastIndexOf(".") + 1, path.length());
    }

    private String createFilename(String ext) {
        long time = System.currentTimeMillis();
        return time + "." + ext;
    }
}



