package com.sist.vo;

import java.time.LocalDateTime;
import java.util.List;

public class BoardVO extends BasicVO {

	private String title;
	private String content;
	private String writer;
	private String password;
	private Integer viewCount;
	private String noticeCheck;
	private LocalDateTime updateTime;
	private List<Integer> fileIdxs;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public String getNoticeCheck() {
		return noticeCheck;
	}

	public void setNoticeCheck(String noticeCheck) {
		this.noticeCheck = noticeCheck;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public List<Integer> getFileIdxs() {
		return fileIdxs;
	}

	public void setFileIdxs(List<Integer> fileIdxs) {
		this.fileIdxs = fileIdxs;
	}
}