package com.poscodx.container.videosystem;

import org.springframework.context.annotation.Bean;

public class DVDPlayer {
	private DigitalVideoDisc dvd;
	

	public DigitalVideoDisc getDvd() {
		return dvd;
	}

	public void setDvd(DigitalVideoDisc dvd) {
		this.dvd = dvd;
	}

	public DVDPlayer(DigitalVideoDisc dvd) {
		this.dvd = dvd;
	}
	
	public String play() {
		return dvd.play();
	}
	
	
	

}
