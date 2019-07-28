
public class ThoiKhoaBieu {
	private String Lop;
	private String MaMH;
	private String TenMH;
	private String PhongHoc;
	
	public String getLop() {
		return this.Lop;
	}
	
	public void setLop(String lop) {
		if(lop != null && !lop.isEmpty()) {
			this.Lop = lop;
		}
	}
	
	public String getMaMH() {
		return this.MaMH;
	}
	
	public void setMaMH(String maMH) {
		if(maMH != null && !maMH.isEmpty()) {
			this.MaMH = maMH;
		}
	}
	
	public String getTenMH() {
		return this.TenMH;
	}
	
	public void setv(String tenMH) {
		if(tenMH != null && !tenMH.isEmpty()) {
			this.TenMH = tenMH;
		}
	}
	
	public String getPhongHoc() {
		return this.PhongHoc;
	}
	
	public void setPhongHoc(String phongHoc) {
		if(phongHoc != null && !phongHoc.isEmpty()) {
			this.PhongHoc = phongHoc;
		}
	}
}
