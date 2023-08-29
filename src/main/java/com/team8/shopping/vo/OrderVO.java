package com.team8.shopping.vo;

import java.sql.Timestamp;

public class OrderVO {

	private int odseq;
	private int oseq;
	private String id;
	private Timestamp indate;
	private String mname;
	private String zipNum;
	private String address;
	private String phone;
	private int pseq;
	private String pname;
	private int quantity;
	private int price2;
	private String result;

	public OrderVO() {
	}

	public OrderVO(int oseq, String userId, String result) {
		this.oseq = oseq;
		this.id = userId;
		this.result = result;
	}

	public OrderVO(int odseq, int oseq, String id, Timestamp indate, String mname, String zipNum, String address,
			String phone, int pseq, String pname, int quantity, int price2, String result) {
		super();
		this.odseq = odseq;
		this.oseq = oseq;
		this.id = id;
		this.indate = indate;
		this.mname = mname;
		this.zipNum = zipNum;
		this.address = address;
		this.phone = phone;
		this.pseq = pseq;
		this.pname = pname;
		this.quantity = quantity;
		this.price2 = price2;
		this.result = result;
	}

	public int getOdseq() {
		return odseq;
	}

	public int getOseq() {
		return oseq;
	}

	public String getId() {
		return id;
	}

	public Timestamp getIndate() {
		return indate;
	}

	public String getMname() {
		return mname;
	}

	public String getZipNum() {
		return zipNum;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public int getPseq() {
		return pseq;
	}

	public String getPname() {
		return pname;
	}
	
	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getPrice2() {
		return price2;
	}
	
	public void setPrice2(int price2) {
		this.price2 = price2;
	}

	public String getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "OrderVO [odseq=" + odseq + ", oseq=" + oseq + ", id=" + id + ", indate=" + indate + ", mname=" + mname
				+ ", zipNum=" + zipNum + ", address=" + address + ", phone=" + phone + ", pseq=" + pseq + ", pname="
				+ pname + ", quantity=" + quantity + ", price2=" + price2 + ", result=" + result + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((indate == null) ? 0 : indate.hashCode());
		result = prime * result + ((mname == null) ? 0 : mname.hashCode());
		result = prime * result + odseq;
		result = prime * result + oseq;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((pname == null) ? 0 : pname.hashCode());
		result = prime * result + price2;
		result = prime * result + pseq;
		result = prime * result + quantity;
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + ((zipNum == null) ? 0 : zipNum.hashCode());
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
		OrderVO other = (OrderVO) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
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
		if (odseq != other.odseq)
			return false;
		if (oseq != other.oseq)
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
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
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (zipNum == null) {
			if (other.zipNum != null)
				return false;
		} else if (!zipNum.equals(other.zipNum))
			return false;
		return true;
	}

}
