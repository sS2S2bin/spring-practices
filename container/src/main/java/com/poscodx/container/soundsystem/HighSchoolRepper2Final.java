package com.poscodx.container.soundsystem;

import org.springframework.stereotype.Component;

@Component
public class HighSchoolRepper2Final implements CompactDisc {
	private String artist = "마크";
	private String title = "Misfit";
	
	
	@Override
	public String play() {
		return "Playing "+title+" by "+artist;
	}

}
