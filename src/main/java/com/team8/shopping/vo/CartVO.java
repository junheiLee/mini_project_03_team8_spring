package com.team8.shopping.vo;

import java.sql.Timestamp;

public class CartVO {

	private int cseq;
	private String id;
	private int pseq;
	private String mname;
	private String pname;
	private int quantity;
	private int price2;
	private Timestamp indate;

	public CartVO() {
	}

	public CartVO(int cseq, String id, int pseq, String mname, String pname, int quantity, int price2,
			Timestamp indate) {
		super();
		this.cseq = cseq;
		this.id = id;
		this.pseq = pseq;
		this.mname = mname;
		this.pname = pname;
		this.quantity = quantity;
		this.price2 = price2;
		this.indate = indate;
	}

	
	public int getCseq() {
		return cseq;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String userId) {
		this.id = userId;
	}

	public int getPseq() {
		return pseq;
	}

	public String getMname() {
		return mname;
	}

	public String getPname() {
		return pname;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getPrice2() {
		return price2;
	}

	public Timestamp getIndate() {
		return indate;
	}

	@Override
	public String toString() {
		return "CartVO [cseq=" + cseq + ", id=" + id + ", pseq=" + pseq + ", mname=" + mname + ", pname=" + pname
				+ ", quantity=" + quantity + ", price2=" + price2 + ", indate=" + indate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cseq;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((indate == null) ? 0 : indate.hashCode());
		result = prime * result + ((mname == null) ? 0 : mname.hashCode());
		result = prime * result + ((pname == null) ? 0 : pname.hashCode());
		result = prime * result + price2;
		result = prime * result + pseq;
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartVO other = (CartVO) obj;
		if (cseq != other.cseq)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (indate == null) {
			if (other.indate != null)
				return false;
		} else if (!indate.equals(other.indate))
			return false;
		if (mname == null) {
			if (other.mname != null)
				return false;
		} else if (!mname.equals(other.mname))
			return false;
		if (pname == null) {
			if (other.pname != null)
				return false;
		} else if (!pname.equals(other.pname))
			return false;
		if (price2 != other.price2)
			return false;
		if (pseq != other.pseq)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}


}
