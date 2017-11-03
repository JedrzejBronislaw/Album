package jk.album.domain;

import java.io.File;
import java.util.Date;

import jk.album.domain.tools.PathTool;

public class Link {

	private String basePath;
	private String rawPath;
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
		this.rawPath = path;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public String getBasePath() {
		return basePath;
	}
	
	public void setRawPath(String path) {
		this.rawPath = path;
	}
	public String getRawPath() {
		return rawPath;
	}

	public String getAbsPath() {
		return PathTool.makeAbsolutePath(rawPath, basePath);
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void checkFile()
	{
		File file = new File(rawPath);
		correctLink = file.exists();
		checkDate = new Date();
	}

	public Boolean fileExists()
	{
		if (checkDate == null) return null;
		return new Boolean(correctLink);
	}
}
