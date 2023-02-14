package org.example.pojo;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users{
	private String name;
	private String job;
}