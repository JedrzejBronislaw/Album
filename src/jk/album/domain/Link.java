package jk.album.domain;

import java.io.File;
import java.util.Date;

import jk.album.domain.tools.PathTool;

public class Link {

	private String basePath;
	private String path;
	private Date checkDate = null;
	private boolean correctLink = false;

	public Link() {}

//	public Link(String basePath) {
//		this.basePath = basePath;
//	}
//
//	public Link(String basePath, String path) {
//		this.basePath = basePath;
//		this.path = path;
//	}

	public Link(String path) {
		this.path = path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	public String getPath() {
		return path;
	}

	public String getAbsPath() {
		return PathTool.makeAbsolutePath(path, basePath);
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void checkFile()
	{
		File file = new File(path);
		correctLink = file.exists();
		checkDate = new Date();
	}

	public Boolean fileExists()
	{
		if (checkDate == null) return null;
		return new Boolean(correctLink);
	}
}
