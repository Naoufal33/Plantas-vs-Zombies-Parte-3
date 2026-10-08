package pvz.logic;

import java.util.Objects;

public class Position {

	private int row;
	private int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int row() {
		return row;
	}

	public int col() {
		return col;
	}

	public boolean isHorizontallyAligned(Position p) {
		return row == p.row();
	}

	public boolean isVerticallyAligned(Position p) {
		return col == p.col();
	}
	@Override
	public String toString() {
		return "(" + this.row + "," + this.col +")";  
	}
	@Override
	public boolean equals(Object o) {
		if(this == o) return true; 
		if(o == null || this.getClass() != o.getClass()) return false; 
		Position p = (Position) o; 
		return this.row == p.row && this.col == p.col; 
	}
	@Override
	public int hashCode() {
		return Objects.hash(row, col); 
	}
	public Position left() {
		return new Position(this.row(), this.col-1);
	}
	public Position right() {
		return new Position(this.row(), this.col+1);
	}

}
