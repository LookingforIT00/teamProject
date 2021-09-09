package com.sist.serialize;

import java.io.Serializable;

public class FileObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer boardIdx;
	private String originalName;
	private String saveFile;
	private Long size;

	public Integer getBoardIdx() {
		return boardIdx;
	}

	public void setBoardIdx(Integer boardIdx) {
		this.boardIdx = boardIdx;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(String saveFile) {
		this.saveFile = saveFile;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public FileObject(Integer boardIdx, String originalName, String saveFile, Long size) {
		this.boardIdx = boardIdx;
		this.originalName = originalName;
		this.saveFile = saveFile;
		this.size = size;
	}
}