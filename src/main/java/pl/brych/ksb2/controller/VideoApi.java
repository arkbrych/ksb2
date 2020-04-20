package pl.brych.ksb2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.brych.ksb2.model.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videos")
public class VideoApi {

    private List<Video> videoList;

    public VideoApi() {
        this.videoList = new ArrayList<>();
        videoList.add(new Video(1, "Never Gonna Give You Up", "https://youtu.be/dQw4w9WgXcQ"));
        videoList.add(new Video(2, "Bohdan Smoleń - Cepry Hej", "https://youtu.be/wiiitx8GeJs"));
    }

    @GetMapping
    public ResponseEntity<List<Video>> getVideos() {
        return new ResponseEntity<>(videoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideosById(@PathVariable long id) {
        Optional<Video> first = videoList.stream().filter(video -> video.getId() == id).findFirst();
        return first.map(video -> new ResponseEntity<>(video, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    Optional<Video> first = videoList.stream().filter(video -> video.getId() == id).findFirst();
//        if (first.isPresent()){
//        return new ResponseEntity<>(first.get(), HttpStatus.OK);
//    }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    @PostMapping
    public ResponseEntity<Video> addVideo(@RequestBody Video video) {
        boolean add = videoList.add(video);
        if (add) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<Video> modVideo(@RequestBody Video newVideo) {
        Optional<Video> first = videoList.stream().filter(video -> video.getId() == newVideo.getId()).findFirst();
        if(first.isPresent()){
            videoList.remove(first.get());
            videoList.add(newVideo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Video> deleteVIdeo(@PathVariable long id){
        Optional<Video> first = videoList.stream().filter(video -> video.getId() == id).findFirst();
        if(first.isPresent()){
            videoList.remove(first.get());
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
