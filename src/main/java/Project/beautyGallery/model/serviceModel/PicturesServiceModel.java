package Project.beautyGallery.model.serviceModel;

import Project.beautyGallery.model.entity.enums.StyleNameEnum;
import Project.beautyGallery.model.entity.enums.TypeNameEnum;
import org.springframework.web.multipart.MultipartFile;

public class PicturesServiceModel {
    private MultipartFile pictures;
    private TypeNameEnum typeNameEnum;
    private StyleNameEnum styleNameEnum;

    public PicturesServiceModel() {
    }

    public MultipartFile getPictures() {
        return pictures;
    }

    public void setPictures(MultipartFile pictures) {
        this.pictures = pictures;
    }

    public TypeNameEnum getTypeNameEnum() {
        return typeNameEnum;
    }

    public void setTypeNameEnum(TypeNameEnum typeNameEnum) {
        this.typeNameEnum = typeNameEnum;
    }

    public StyleNameEnum getStyleNameEnum() {
        return styleNameEnum;
    }

    public void setStyleNameEnum(StyleNameEnum styleNameEnum) {
        this.styleNameEnum = styleNameEnum;
    }
}
