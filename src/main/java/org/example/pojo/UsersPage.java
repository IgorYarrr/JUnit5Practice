package org.example.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsersPage{
	private int per_page;
	private int total;
	private List<DataItem> data;
	private int page;
	private int total_pages;
	private Support support;
}