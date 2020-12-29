package com.neo.myhome.controller;

import com.neo.myhome.model.Board;
import com.neo.myhome.model.LyricsItem;
import com.neo.myhome.model.MusicItem;
import com.neo.myhome.repository.BoardRepository;
import com.neo.myhome.service.BoardService;
import com.neo.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards",boards);
        return "board/list";
    }
    @GetMapping("/searchList")
    public String searchList(Model model, String data){
        List<Board> boards = boardRepository.findByTitleContaining(data);
        model.addAttribute("boards",boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){
        if(id == null){
            model.addAttribute("board", new Board());
        }else{
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board",board);
        }
        MusicItem item = new MusicItem();
        List<LyricsItem> items = new ArrayList<LyricsItem>();
        LyricsItem lyrics1 = new LyricsItem();
        lyrics1.setLeftChorus("독.　");
        lyrics1.setRightChorus("독.　");
        lyrics1.setLeftCode("Em");
        lyrics1.setRightCode("Em");
        lyrics1.setLeftLyrics("울며 구하는 자에게");
        lyrics1.setRightLyrics("십자가 상에서 도둑을");
        items.add(lyrics1);
        LyricsItem lyrics2 = new LyricsItem();
        lyrics2.setLeftChorus("독.　");
        lyrics2.setRightChorus("독.　");
        lyrics2.setLeftCode("　　　　　　Am");
        lyrics2.setRightCode("　　　Am");
        lyrics2.setLeftLyrics("눈을 뜨게 해주신 당신");
        lyrics2.setRightLyrics("용서하신 당신");
        items.add(lyrics2);
        LyricsItem lyrics3 = new LyricsItem();
        lyrics3.setLeftChorus("합.　");
        lyrics3.setRightChorus("합.　");
        lyrics3.setLeftCode("　　　　B7　　　Am　　B7");
        lyrics3.setRightCode("");
        lyrics3.setLeftLyrics("이 죄인에게 자비를 베푸소서.");
        lyrics3.setRightLyrics("이 죄인에게. . .");
        items.add(lyrics3);
        item.setItems(items);
        model.addAttribute("musicItem", item);
        return "board/form";
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
}

