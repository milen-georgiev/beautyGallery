package Project.beautyGallery.web;

import Project.beautyGallery.model.viewModel.VideoViewModel;
import Project.beautyGallery.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VideoGalleryController {

    private final VideoService videoService;

    public VideoGalleryController(VideoService videoService) {
        this.videoService = videoService;
    }


    @GetMapping("/gallery/video")
    public String video(Model model) {

        List<VideoViewModel> viewVideo = videoService.viewVideo();

        model.addAttribute("viewVideo", viewVideo);

        return "video";
    }
}

