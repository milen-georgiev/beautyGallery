package Project.beautyGallery.model.serviceModel;

import Project.beautyGallery.model.entity.enums.ArticlesNameEnum;
import org.springframework.web.multipart.MultipartFile;

public class ArticlesServiceModel {
    private String name;
    private String description;
    private ArticlesNameEnum category;
    private MultipartFile pictures;

    public ArticlesServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArticlesNameEnum getCategory() {
        return category;
    }

    public void setCategory(ArticlesNameEnum category) {
        this.category = category;
    }

    public MultipartFile getPictures() {
        return pictures;
    }

    public void setPictures(MultipartFile pictures) {
        this.pictures = pictures;
    }
}
