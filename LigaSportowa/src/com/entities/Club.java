package com.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Club extends Entity{

	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	private String name;
	
}
