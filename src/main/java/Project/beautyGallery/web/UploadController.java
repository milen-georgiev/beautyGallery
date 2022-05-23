package Project.beautyGallery.web;

import Project.beautyGallery.model.binding.VideoBindingModel;
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

    @PostMapping("/add/video")
    public String addVideo(@Valid VideoBindingModel videoBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal BeautyGalleryUsers user){

        if (bindingResult.hasErrors() ) {
            redirectAttributes
                    .addFlashAttribute("videoBindingModel", videoBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.videoBindingModel",
                            bindingResult);

            return "redirect:/add/video";
        }

        VideoServiceModel videoModel =
                modelMapper.map(videoBindingModel, VideoServiceModel.class);

        videoService.addVideo(videoModel,user.getUserIdentified());

        return "redirect:/resources/video";
    }


    @ModelAttribute("videoBindingModel")
    public VideoBindingModel videoBindingModel() {
        return new VideoBindingModel();
    }

}
