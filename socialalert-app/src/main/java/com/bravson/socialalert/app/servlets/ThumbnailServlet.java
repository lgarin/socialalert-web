package com.bravson.socialalert.app.servlets;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.bravson.socialalert.app.infrastructure.FileHttpRequestHandler;
import com.bravson.socialalert.app.services.MediaStorageService;

@Component
public class ThumbnailServlet extends FileHttpRequestHandler {

	@Autowired
	private MediaStorageService storageService;
	
	@Override
	protected File getFile(String relativePath) throws IOException {
		return storageService.resolveThumbnailUri(URI.create(relativePath));
	}
	
	@Override
	protected MediaType getMediaType(Resource resource) {
		return MediaType.IMAGE_JPEG;
	}
}
