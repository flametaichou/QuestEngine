package ru.flametaichou.quest.core.dto;


public class ImageDto {

    private String data;
    private String format;

    public ImageDto() {

    }

    public ImageDto(String data, String format) {
        this.data = data;
        this.format = format;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
