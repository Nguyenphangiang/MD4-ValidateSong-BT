package com.bat.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class Song implements Validator {

    private int id;

    private String name;

    public Song(int id, String name, String singer, String category) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String singer;

    private String category;



    public Song() {
    }

    public int getId() {
        return id;
    }

    public Song(String name, String singer, String category) {
        this.name = name;
        this.singer = singer;
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Song.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Song song = (Song)target;
        String name = song.getName();
        String singer = song.getSinger();
        String category = song.getCategory();
        ValidationUtils.rejectIfEmpty(errors,"name","name.valid");
        ValidationUtils.rejectIfEmpty(errors,"singer","singer.valid");
        ValidationUtils.rejectIfEmpty(errors,"category","category.valid");
        if (!name.matches("^[A-Za-z0-9\\s]+$")){
            errors.rejectValue("name","name.pattern");
        }
        if (name.length() > 800 || name.length() < 1){
            errors.rejectValue("name","name.size");
        }
        if (!singer.matches("^[A-Za-z0-9\\s]+$")){
            errors.rejectValue("singer","singer.pattern");
        }
        if (singer.length() > 300 || singer.length() < 1){
            errors.rejectValue("singer","singer.size");
        }
        if (!category.matches("^[A-Za-z0-9\\s,]+$")){
            errors.rejectValue("category","category.pattern");
        }
        if (category.length() > 1000 || category.length() < 1){
            errors.rejectValue("category","category.size");
        }
    }
}
