package com.neo.myhome.controller;

import com.neo.myhome.model.Board;
import com.neo.myhome.model.Lyrics;
import com.neo.myhome.model.MusicItem;
import com.neo.myhome.repository.BoardRepository;
import com.neo.myhome.service.BoardService;
import com.neo.myhome.service.MusicService;
import com.neo.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;


    @Autowired
    private MusicService musicService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model){
        List<MusicItem> musics = musicService.searchMusicList(null);
        model.addAttribute("musics",musics);
        return "board/list";
    }
    @GetMapping("/searchList")
    public String searchList(Model model, String data){
        List<MusicItem> musics = musicService.searchMusicList(data);
        model.addAttribute("musics",musics);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) String id){
        MusicItem item = musicService.searchMusicItem(id);
        model.addAttribute("musicItem",item);
        return "board/form2";
    }
    @GetMapping("/formAdd")
    public String formAdd(Model model, String id){
        MusicItem item;
        if(id==null) {
            item = new MusicItem();
            List<Lyrics> list = new ArrayList<>();
            Lyrics lyricsItem = new Lyrics();
            list.add(lyricsItem);
            item.setItems(list);
        }else{
            item = musicService.searchMusicItem(id);
        }

        model.addAttribute("item", item);
        return "board/formAdd";
    }
    @PostMapping("/form")
    public String formSubmit(@Valid Board board, BindingResult bindingResult, Authentication authentication){
        boardValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/form";

        }
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        boardService.save(username, board);
        return "redirect:/board/list";
    }
    @PostMapping("/formAdd")
    public String formAddSubmit(@Valid MusicItem item, BindingResult bindingResult, Authentication authentication){

        if (bindingResult.hasErrors()) {
            return "board/form";

        }
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();


        return "redirect:/board/list";
    }
}
//        MusicItem item = new MusicItem();
//        List<Lyrics> items = new ArrayList<Lyrics>();
//        Lyrics lyrics1 = new Lyrics();
//        lyrics1.setLeftChorus("독.　");
//        lyrics1.setRightChorus("독.　");
//        lyrics1.setLeftCode("Em");
//        lyrics1.setRightCode("Em");
//        lyrics1.setLeftLyrics("울며 구하는 자에게");
//        lyrics1.setRightLyrics("십자가 상에서 도둑을");
//        items.add(lyrics1);
//        Lyrics lyrics2 = new Lyrics();
//        lyrics2.setLeftChorus("독.　");
//        lyrics2.setRightChorus("독.　");
//        lyrics2.setLeftCode("　　　　　　Am");
//        lyrics2.setRightCode("　　　Am");
//        lyrics2.setLeftLyrics("눈을 뜨게 해주신 당신");
//        lyrics2.setRightLyrics("용서하신 당신");
//        items.add(lyrics2);
//        Lyrics lyrics3 = new Lyrics();
//        lyrics3.setLeftChorus("합.　");
//        lyrics3.setRightChorus("합.　");
//        lyrics3.setLeftCode("　　　　B7　　　Am　　B7");
//        lyrics3.setRightCode("");
//        lyrics3.setLeftLyrics("이 죄인에게 자비를 베푸소서.");
//        lyrics3.setRightLyrics("이 죄인에게. . .");
//        items.add(lyrics3);
//        item.setItems(items);
//        model.addAttribute("musicItem", item);
