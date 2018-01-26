package com.alves.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alves.exception.FileException;

@Service
public class ImageService {

	protected int size = 200;

	// Método que transforma MULTIPARTFILE (SPRING) - para imagem
	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {

		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());

		if (!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Somente imagens com formatos PNG e JPG são permitidas");
		}

		try {

			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equals(ext)) {
				img = pngToJpg(img);
			}

			return img;

		} catch (IOException e) {

			throw new FileException("Erro ao ler arquivo");
		}
	}

	// Método que converte o formato PNG para JPG
	public BufferedImage pngToJpg(BufferedImage img) {

		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}

	// Converte para INPUTSTREAM formato aceito pela AWS S3
	public InputStream getInputStream(BufferedImage img, String extension) {

		try {

			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);

			return new ByteArrayInputStream(os.toByteArray());

		} catch (IOException e) {

			throw new FileException("Erro ao ler arquivo");
		}
	}

	// Recorta a imagem para quadrado
	public BufferedImage cropSquare(BufferedImage sourceImg) {
		int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
		return Scalr.crop(sourceImg, 
							(sourceImg.getWidth() / 2) - (min / 2), 	
							(sourceImg.getHeight() / 2) - (min / 2),
						  min, min);
	}

	// Redimensiona imagem
	public BufferedImage resize(BufferedImage sourceImg) {
		return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
	}
}