package com.neo.myhome.dao;

import com.neo.myhome.model.Lyrics;
import com.neo.myhome.model.MusicItem;

import java.util.List;

public interface MusicDao {
    public List<MusicItem> searchMusicList(String title);
    public MusicItem searchMusicItem(String id);
    public void insertItem(MusicItem item);
    public void insertDetailItem(Lyrics item);
//    public void updateItem(MusicItem item);
//    public void updateDetailItem(Lyrics item);
}
