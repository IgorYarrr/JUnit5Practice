package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataItem{
	private String last_name;
	private int id;
	private String avatar;
	private String first_name;
	private String email;
}