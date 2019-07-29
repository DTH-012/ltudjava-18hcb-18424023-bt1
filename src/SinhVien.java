
public class SinhVien {
	private String MSSV;
	private String HoTen;
	private String GioiTinh;
	private String CMND;
	
	public SinhVien(String mssv, String ht, String gt, String cmnd) {
		this.MSSV = mssv;
		this.HoTen = ht;
		this.GioiTinh = gt;
		this.CMND = cmnd;
	}
	
	public void setMSSV(String mssv) {
		if(mssv != null && !mssv.isEmpty()) {
			this.MSSV = mssv;
		}
	}
	
	public String getMSSV() {
		return this.MSSV;
	}
	
	public void setHoTen(String hoTen) {
		if(hoTen != null && !hoTen.isEmpty()) {
			this.HoTen = hoTen;
		}
	}
	
	public String getHoTen() {
		return this.HoTen;
	}
	
	public void setGioiTinh(String gt) {
		this.GioiTinh = gt;
	}
	
	public String getGioiTinh() {
		return this.GioiTinh;
	}
	
	public void setCMND(String cmnd) {
		if(cmnd != null && !cmnd.isEmpty()) {
			this.CMND = cmnd;
		}
	}
	
	public String getCMND() {
		return this.CMND;
	}
	
	@Override
	public String toString() {
		return this.MSSV+","+this.HoTen+","+this.GioiTinh+","+this.CMND;
	}
}
