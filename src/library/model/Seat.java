package library.model;

public class Seat {
	private int sid;
	private String sname;
	private int sstate;
	
	public Seat(int sid, String sname, int sstate) {
		super();
		this.sid = sid;
		this.sname = sname;
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
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getSstate() {
		return sstate;
	}
	public void setSstate(int sstate) {
		this.sstate = sstate;
	}
}
