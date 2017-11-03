package jk.album.domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jk.album.domain.tools.Hash;

public class PhotoFile {

	private String path = null;
	private long size = 0;
	private int width = 0;
	private int height = 0;
	private String hash = null;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}

	public PhotoFile() {
	}

	public PhotoFile(String path) {
		fill(new File(path));
	}

	public PhotoFile(File file) {
		fill(file);
	}

	private void fill(File file){
		if (!file.exists()) return;

		
//		path = file.getAbsolutePath();
		try {
			path = file.getCanonicalPath();
		} catch (IOException e1) {
			path = file.getAbsolutePath();
		}
		size = file.length();

		BufferedImage image;
		try {
			image = ImageIO.read(file);
			width  = image.getWidth();
			height = image.getHeight();
		} catch (IOException e) {
			width = height = 0;
		}

		hash = new Hash().generate(file).returnString();

	}


}
