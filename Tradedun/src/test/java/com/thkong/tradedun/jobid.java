package com.thkong.tradedun;

import java.util.List;

import lombok.Data;

@Data
public class jobid {

	private String jobId;
	private String jobGrowId;
	private String jobName;
	private String jobGrowName;
	private List<jobid> rows;
	private jobid next;
}
