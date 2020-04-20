package pl.brych.ksb2.model;

import lombok.Data;

@Data
public class Video {

    private long id;
    private String title;
    private String url;

    public Video(long id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public Video() {
    }
}
