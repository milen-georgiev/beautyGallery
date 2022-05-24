package Project.beautyGallery.model.binding;

import Project.beautyGallery.model.entity.enums.ArticlesNameEnum;
import Project.beautyGallery.model.entity.enums.TypeNameEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

public class ArticlesBindingModel {

    @Size(min = 6)
    private String name;
    @Size(min = 50)
    private String description;
    private ArticlesNameEnum category;
    private MultipartFile pictures;

    public ArticlesBindingModel() {
    }

    public String getName() {
        return name;
    }

    public ArticlesBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ArticlesBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public ArticlesNameEnum getCategory() {
        return category;
    }

    public ArticlesBindingModel setCategory(ArticlesNameEnum category) {
        this.category = category;
        return this;
    }

    public MultipartFile getPictures() {
        return pictures;
    }

    public ArticlesBindingModel setPictures(MultipartFile pictures) {
        this.pictures = pictures;
        return this;
    }
}
