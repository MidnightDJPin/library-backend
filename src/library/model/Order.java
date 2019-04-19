package library.model;

import java.util.Date;

public class Order {
	private int oid;
	private int rid;
	private int sid;
	private Date ordertime;
	private Date starttime;
	private Date endtime;
	
	public Order() {
		super();
	}

	public Order(int oid, int rid, int sid, Date ordertime, Date starttime, Date endtime) {
		super();
		this.oid = oid;
		this.rid = rid;
		this.sid = sid;
		this.ordertime = ordertime;
		this.starttime = starttime;
		this.endtime = endtime;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
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

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	
}
