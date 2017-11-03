package jk.album.domain.marks;

import java.awt.Point;

import jk.album.domain.Being;

public class Shapeless extends Mark {

	public Shapeless() {
	}

	public Shapeless(Being being) {
		this.being = being;
	}

	@Override
	public boolean isHere(Point point) {
		return false;
	}

}
