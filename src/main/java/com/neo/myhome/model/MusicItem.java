package com.neo.myhome.model;

import java.util.List;

public class MusicItem {
    private String title;
    private String number;
    private String capo;
    private List<LyricsItem> items;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCapo() {
		return capo;
	}
	public void setCapo(String capo) {
		this.capo = capo;
	}
	public List<LyricsItem> getItems() {
		return items;
	}
	public void setItems(List<LyricsItem> items) {
		this.items = items;
	}
    
}