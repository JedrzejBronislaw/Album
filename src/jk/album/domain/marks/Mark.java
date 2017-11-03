package jk.album.domain.marks;

import java.awt.Point;

import jk.album.domain.Being;

public abstract class Mark {

	protected Being being = null;

	public abstract boolean isHere(Point point);

	public void setBeing(Being being) {
		this.being = being;
	}
	public Being getBeing() {
		return being;
	}
}
