package com.neo.myhome.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Capo implements Serializable {
	private String userId;
	private String musicId;
    private String capo;
}