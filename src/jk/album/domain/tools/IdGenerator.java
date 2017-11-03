package jk.album.domain.tools;

import java.util.Random;

public class IdGenerator implements IIdGenerator {

	private Random r = new Random();
	private int max = 62;//10 + 90-64 + 122-96; // 10 + 26 + 26 = 62

	@Override
	public String generate() {
		return alfanum(16);
	}

	@SuppressWarnings("unused")
	private String longToHex(){
		long id = r.nextLong();
		return String.format("%X", id);
	}

	private String alfanum(int n) {
		StringBuilder sb = new StringBuilder();

		for (int i=0; i<n; i++){
			int x = r.nextInt(max);
			x += 48; //0
			if (x > 57) x += 7;
			if (x > 90) x += 6;

			sb.append((char)x);
		}

		return sb.toString();
	}

}
