package com.web.billim.common.infra;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
	String upload(MultipartFile image, String path) throws IOException;
}
