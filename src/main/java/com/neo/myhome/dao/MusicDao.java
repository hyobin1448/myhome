package com.neo.myhome.dao;

import com.neo.myhome.model.Capo;
import com.neo.myhome.model.Lyrics;
import com.neo.myhome.model.MusicItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MusicDao {
    public List<MusicItem> searchMusicList(MusicItem item);
    public MusicItem searchMusicItem(MusicItem item);
    public void insertItem(MusicItem item);
    public void insertDetailItem(Lyrics item);
    public void updateItem(MusicItem item);
    public void deleteDetailItem(String id);
    public void insertCapo(Capo item);
}
