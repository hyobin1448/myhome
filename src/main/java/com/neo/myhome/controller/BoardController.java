package com.neo.myhome.controller;

import com.neo.myhome.model.Board;
import com.neo.myhome.model.Capo;
import com.neo.myhome.model.Lyrics;
import com.neo.myhome.model.MusicItem;
import com.neo.myhome.repository.BoardRepository;
import com.neo.myhome.service.BoardService;
import com.neo.myhome.service.MusicService;
import com.neo.myhome.validator.BoardValidator;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    public String list(Model model, MusicItem item){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        item.setUsername(username);

        List<MusicItem> musics = musicService.searchMusicList(item);
        model.addAttribute("musics",musics);
        return "board/list";
    }
    @GetMapping("/searchList")
    public String searchList(Model model, String data){
        MusicItem item = new MusicItem();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        item.setUsername(username);
        item.setTitle(data);
        List<MusicItem> musics = musicService.searchMusicList(item);
        model.addAttribute("musics",musics);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) String id){
        MusicItem item = new MusicItem();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        item.setUsername(username);
        item.setId(id);
        item = musicService.searchMusicItem(item);
        model.addAttribute("musicItem",item);
        return "board/form2";
    }
    @GetMapping("/formAdd")
    public String formAdd(Model model, String id){
        MusicItem item = new MusicItem();
        if(id==null) {
            item = new MusicItem();
            List<Lyrics> list = new ArrayList<>();
            Lyrics lyricsItem = new Lyrics();
            list.add(lyricsItem);
            item.setItems(list);
        }else{
            item.setId(id);
            item = musicService.searchMusicItem(item);
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
        MusicItem checkItem = musicService.searchMusicItem(item);
        if(checkItem == null){
            musicService.insertMusicItem(item);
        }else{
            musicService.updateMusicItem(item);
        }

        return "redirect:/board/list";
    }
    @GetMapping("/capoChange")
    @ResponseBody
    public String capoChange(String capo, String id){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        Capo item = new Capo();
        item.setCapo(capo);
        item.setMusicId(id);
        item.setUserId(a.getName());
        musicService.insertCapo(item);
        return "Y";

    }
    @GetMapping("/music")
    @ResponseBody
    public ResponseEntity<InputStreamResource> music(String id) throws IOException {
        String end ="";

        if(id.indexOf("_")==-1){
            id = id;
        }else{
            end = id.substring(id.indexOf("_"));
            id = id.substring(0,id.indexOf("_"));
        }
        id = "000".substring(id.length())+id+end;

        ClassPathResource resource = new ClassPathResource("mp3/music"+id+".mp3");
        File file = resource.getFile();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-disposition","attachment; filename=music"+id+".mp3");
        return ResponseEntity.ok().headers(headers).contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(new FileInputStream(file)));
    }
    @GetMapping("/image")
    @ResponseBody
    public byte[] image(String id) throws IOException {
        String end ="";

        if(id.indexOf("_")==-1){
            id = id;
        }else{
            end = id.substring(id.indexOf("_"));
            id = id.substring(0,id.indexOf("_"));
        }
        id = "000".substring(id.length())+id+end;

        ClassPathResource resource = new ClassPathResource("image/music"+id+".jpg");
        File file = resource.getFile();
        InputStream io = resource.getInputStream();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-disposition","attachment; filename=music"+id+".jpg");
        return IOUtils.toByteArray(io);
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
