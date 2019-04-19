package library.model;

public class Use {
	private int uid;
	private int rid;
	private int sid;
	private int ustate;
	
	public Use() {
		super();
	}

	public Use(int uid, int rid, int sid, int ustate) {
		super();
		this.uid = uid;
		this.rid = rid;
		this.sid = sid;
		this.ustate = ustate;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getUstate() {
		return ustate;
	}

	public void setUstate(int ustate) {
		this.ustate = ustate;
	}
	
}
