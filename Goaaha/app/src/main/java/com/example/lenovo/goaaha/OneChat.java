package com.example.lenovo.goaaha;

/**
 * Created by 小妖精停一下 on 2016/9/13.
 */
public class OneChat {
    private Long id;
    private String Name;
    private String Content;
    private String Time;
    private int Img;

    public OneChat(Long id, int img, String name, String content, String time) {
        this.id = id;
        Img = img;
        Name = name;
        Content = content;
        Time = time;
    }

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


}
