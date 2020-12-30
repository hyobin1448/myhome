package com.neo.myhome.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MusicItem implements Serializable {
	private int id;
    private String title;
    private String number;
    private String capo;
	private List<Lyrics> items;
}