package com.neo.myhome.service;

import com.neo.myhome.dao.MusicDao;
import com.neo.myhome.model.MusicItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {
    @Autowired
    private MusicDao dao;

    public List<MusicItem> searchMusicList(String title){
        return dao.searchMusicList(title);
    }
    public MusicItem searchMusicItem(String id){
        return dao.searchMusicItem(id);
    }

}
