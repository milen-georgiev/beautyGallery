package Project.beautyGallery.web;

import Project.beautyGallery.model.binding.ArticlesBindingModel;
import Project.beautyGallery.model.binding.PicturesBindingModel;
import Project.beautyGallery.model.binding.VideoBindingModel;
import Project.beautyGallery.model.serviceModel.ArticlesServiceModel;
import Project.beautyGallery.model.serviceModel.PicturesServiceModel;
import Project.beautyGallery.model.serviceModel.VideoServiceModel;
import Project.beautyGallery.service.ArticlesService;
import Project.beautyGallery.service.PicturesService;
import Project.beautyGallery.service.VideoService;
import Project.beautyGallery.service.impl.BeautyGalleryUsers;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class UploadController {

    private final PicturesService picturesService;
    private final ArticlesService articlesService;
    private final VideoService videoService;
    private final ModelMapper modelMapper;

    public UploadController(PicturesService picturesService, ArticlesService articlesService,
                            VideoService videoService, ModelMapper modelMapper) {
        this.picturesService = picturesService;
        this.articlesService = articlesService;
        this.videoService = videoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload/video")
    public String addVideo(@Valid VideoBindingModel videoBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal BeautyGalleryUsers user){

        if (bindingResult.hasErrors() ) {
            redirectAttributes
                    .addFlashAttribute("videoBindingModel", videoBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.videoBindingModel",
                            bindingResult);

            return "redirect:/upload/video";
        }

        VideoServiceModel videoModel =
                modelMapper.map(videoBindingModel, VideoServiceModel.class);

        videoService.addVideo(videoModel,user.getUserIdentified());

        return "redirect:/";
    }

    @PostMapping("/upload/pictures")
    public String addPictures(PicturesBindingModel picturesBindingModel,
                              @AuthenticationPrincipal BeautyGalleryUsers user) throws IOException {


        PicturesServiceModel picturesServiceModel =
                modelMapper.map(picturesBindingModel, PicturesServiceModel.class);

        picturesService.uploadPictures(picturesServiceModel, user.getUserIdentified());


        return "redirect:/";
    }

    @PostMapping("/upload/articles")
    public String addArticles(@Valid ArticlesBindingModel articlesBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              @AuthenticationPrincipal BeautyGalleryUsers user) throws IOException {



        if (bindingResult.hasErrors() ) {
            redirectAttributes
                    .addFlashAttribute("articlesBindingModel", articlesBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.articlesBindingModel",
                            bindingResult);

            return "redirect:/upload/articles";
        }

        ArticlesServiceModel articlesServiceModel =
                modelMapper.map(articlesBindingModel, ArticlesServiceModel.class);

        articlesService.addGrandmasSecret(articlesServiceModel,user.getUserIdentified());


        return "redirect:/";
    }

    @ModelAttribute("videoBindingModel")
    public VideoBindingModel videoBindingModel() {
        return new VideoBindingModel();
    }

    @ModelAttribute("picturesBindingModel")
    public PicturesBindingModel picturesBindingModel() {
        return new PicturesBindingModel();
    }

    @ModelAttribute("articlesBindingModel")
    public ArticlesBindingModel articlesBindingModel () {
        return new ArticlesBindingModel();
    }

}
