package com.sist.vo;

import java.time.LocalDateTime;

public class BasicVO {

	private Integer idx;
	private LocalDateTime insertTime;

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public LocalDateTime getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(LocalDateTime insertTime) {
		this.insertTime = insertTime;
	}
}