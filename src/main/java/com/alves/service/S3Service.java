package com.alves.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 amazon;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartFile) {

		try {

			String fileName = multipartFile.getOriginalFilename();
			InputStream ins = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();

			return uploadFile(fileName, ins, contentType);

		} catch (IOException e) {
			
			throw new RuntimeException("Erro ao converter arquivo para InputStream");
		}

	}

	private URI uploadFile(String fileName, InputStream ins, String contentType) {
		
		
		try {
			
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(contentType);
			amazon.putObject(new PutObjectRequest(bucketName, fileName, ins, metadata));
			
			return amazon.getUrl(bucketName, fileName).toURI();

		} catch (URISyntaxException e) {
			
			throw new RuntimeException("Erro ao converter URL para URI");
		} 
	}

}
