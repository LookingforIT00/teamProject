package com.sist.vo;

import java.time.LocalDateTime;

public class CommentVO extends BasicVO {

	private Integer targetIdx;
	private String targetType;
	private String content;
	private String writer;
	private String delete_check;
	private LocalDateTime updateTime;
	private LocalDateTime delete_time;
	
	public Integer getTargetIdx() {
		return targetIdx;
	}
	public void setTargetIdx(Integer targetIdx) {
		this.targetIdx = targetIdx;
	}
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getDelete_check() {
		return delete_check;
	}
	public void setDelete_check(String delete_check) {
		this.delete_check = delete_check;
	}
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	public LocalDateTime getDelete_time() {
		return delete_time;
	}
	public void setDelete_time(LocalDateTime delete_time) {
		this.delete_time = delete_time;
	}
}