package library.model;

public class Seat {
	private int sid;
	private int rid;
	private int sstate;
	
	public Seat(int sid, int rid, int sstate) {
		super();
		this.sid = sid;
		this.rid = rid;
		this.sstate = sstate;
	}
	public Seat() {
		super();
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getSstate() {
		return sstate;
	}
	public void setSstate(int sstate) {
		this.sstate = sstate;
	}
}
