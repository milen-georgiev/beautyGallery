package Project.beautyGallery.service;

import Project.beautyGallery.model.entity.CloudinaryImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    CloudinaryImageEntity upload(MultipartFile file) throws IOException;

    boolean delete(String publicId);
}
