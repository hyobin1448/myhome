package com.neo.myhome.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Lyrics implements Serializable {
    private String id;
	private int seq;
	private String leftChorus;
    private String leftCode;
    private String leftLyrics;
    private String rightChorus;
    private String rightCode;
    private String rightLyrics;
}
