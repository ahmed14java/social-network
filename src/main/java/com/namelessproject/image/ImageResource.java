package com.namelessproject.image;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.UUID;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = { "*" })
public class ImageResource {

	private static long MAX_SIZE = 120 * 1024; // 120 KB
	
	/*
	 * TODO drop below method and let the server handle the transfer 
	 */
	@RequestMapping(path = "/{imageKey}", method = RequestMethod.GET, produces="image/*")
	public ResponseEntity<InputStreamResource> retrieveImage(@PathVariable("imageKey") String imageKey) {
		try {
			File img = new File("C:/SW/imgs/" + imageKey);
			InputStream inputStream = new FileInputStream(img);
			InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
			return ResponseEntity.ok(inputStreamResource);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, produces="text/html; charset=utf-8")
	public String storeImage(@RequestParam("image") MultipartFile file) {
		try {
			String imgKey = UUID.randomUUID().toString().replaceAll("-", "");

			System.out.println("\n\n Max Size: " + MAX_SIZE + "\n\n");
			System.out.println("\n\n Size: " + file.getSize() + "\n\n");
			
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("C:/SW/imgs/" + imgKey)));
			FileCopyUtils.copy(file.getInputStream(), stream);
			stream.close();

			return "{"
					+ "\"link\":\"http://localhost:50003/image/" + imgKey + "\"" 
					+ ", "
					+ "\"key\":\"" + imgKey + "\"" 
					+ "}";
		} catch (Exception e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR.toString();
		}
	}
}
