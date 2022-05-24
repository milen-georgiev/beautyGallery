package Project.beautyGallery.service.impl;

import Project.beautyGallery.model.entity.CloudinaryImageEntity;
import Project.beautyGallery.service.CloudinaryService;
import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public CloudinaryImageEntity upload(MultipartFile multipartFile) throws IOException {

        File tempFile = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);

        try {
            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = cloudinary
                    .uploader()
                    .upload(tempFile, Map.of());

            String url = uploadResult.getOrDefault(URL, "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/04e7ca72768897.5bf2f7bd522bd.png");
            String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");

            return new CloudinaryImageEntity()
                    .setUrl(url)
                    .setPublicId(publicId);

        } finally {
            tempFile.delete();
        }

    }

    @Override
    public boolean delete(String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId,Map.of());
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}