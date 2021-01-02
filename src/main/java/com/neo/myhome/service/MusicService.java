package com.neo.myhome.service;

import com.neo.myhome.dao.MusicDao;
import com.neo.myhome.model.Capo;
import com.neo.myhome.model.Lyrics;
import com.neo.myhome.model.MusicItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {
    @Autowired
    private MusicDao dao;


    public List<MusicItem> searchMusicList(MusicItem item){
        return dao.searchMusicList(item);
    }
    public MusicItem searchMusicItem(MusicItem item){
        return dao.searchMusicItem(item);
    }
    public void insertMusicItem(MusicItem item){
        dao.insertItem(item);

        for(int i=0; i<item.getItems().size();i++){
            Lyrics detailItem = item.getItems().get(i);
            detailItem.setId(item.getId());
            detailItem.setSeq(i);
            dao.insertDetailItem(detailItem);
        }
    }
    public void updateMusicItem(MusicItem item){
        dao.deleteDetailItem(item.getId());
        dao.updateItem(item);
        for(int i=0; i<item.getItems().size();i++){
            Lyrics detailItem = item.getItems().get(i);
            detailItem.setId(item.getId());
            detailItem.setSeq(i);
            dao.insertDetailItem(detailItem);
        }
    }

    public void insertCapo(Capo item){
        dao.insertCapo(item);
    }
}
