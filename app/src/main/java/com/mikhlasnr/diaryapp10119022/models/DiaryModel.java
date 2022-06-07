package com.mikhlasnr.diaryapp10119022.models;
/*
    nim                 : 10119022
    nama                : Muhammad Ikhlas Naufalsyah Ranau
    kelas               : IF-1
*/
public class DiaryModel {
    private final String id;
    private final String title;
    private final String category;
    private final String note;
    private final String date;


    public DiaryModel(String id, String title, String category, String note, String date) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.note = note;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }
}
