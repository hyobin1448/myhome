package com.neo.myhome.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MusicItem implements Serializable {
	private String id;
    private String title;
    private String number;
    private String capo;
    private String username;
	private List<Lyrics> items;
}