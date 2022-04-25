package com.bat.controller;

import com.bat.model.Song;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SongController {
    @GetMapping("/")
    public String createForm(Model model){
        model.addAttribute("song",new Song());
        return "/create";
    }
    @PostMapping("/create")
    public String checkValidation(@Validated @ModelAttribute("song") Song song, BindingResult bindingResult, Model model){
        new Song().validate(song,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "/create";
        } else {
            int id = (int) (Math.random()*10000);
            Song newSong = new Song(id,song.getName(),song.getSinger(),song.getCategory());
            model.addAttribute("message","Create Success!");
            model.addAttribute("message1","Check information");
            model.addAttribute("song",newSong);
            return "/result";
        }
    }
    @PostMapping("/edit")
    public String edit(@Validated @ModelAttribute("song")Song song,BindingResult bindingResult,Model model){
        new Song().validate(song,bindingResult);
        if (bindingResult.hasFieldErrors()){
            model.addAttribute("message","Fail");
            model.addAttribute("message1","Check information");
            return "/result";
        } else {
            Song newSong = new Song(song.getName(),song.getSinger(),song.getCategory());
            model.addAttribute("song",newSong);
            model.addAttribute("message","Edit Success!");
            model.addAttribute("message1","Check information");
            return "/result";
        }
    }
}
