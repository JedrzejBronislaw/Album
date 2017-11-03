package jk.album.domain.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

	private byte[] hash;
	public Hash() {

	}

	public byte[] returnTabByte(){
		return hash;
	}

	public String returnString(){
		if (hash == null) return null;

		StringBuffer sb = new StringBuffer();
		for(byte b : hash)
			sb.append(String.format("%X", b));

		return sb.toString();
	}

	public Hash generate(File file){

		MessageDigest ms;
		InputStream is = null;
		try {
			ms = MessageDigest.getInstance("SHA-256");
			is = new FileInputStream(file);
			byte[] b = new byte[1024];
			int bufSize = 0;

			bufSize = is.read(b);
			while (bufSize > 0){
				ms.update(b, 0, bufSize);
				bufSize = is.read(b);
			}

			hash = ms.digest();

			is.close();
		} catch (NoSuchAlgorithmException | IOException e) {
			hash = null;
		}

		return this;
	}
}
