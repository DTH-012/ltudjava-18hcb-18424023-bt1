
public class MonHoc {
	private String MaMH;
	private String TenMH;
	private String PhongHoc;
	
	public MonHoc() {
		this.MaMH = "";
		this.TenMH = "";
		this.PhongHoc = "";
	}
	
	public MonHoc(String maMH, String tenMH, String phongHoc) {
		this.MaMH = maMH;
		this.TenMH = tenMH;
		this.PhongHoc = phongHoc;
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

	@Override
	public String toString() {
		return this.MaMH.toString() + "," + this.TenMH.toString() + "," + this.PhongHoc;
	}
}
