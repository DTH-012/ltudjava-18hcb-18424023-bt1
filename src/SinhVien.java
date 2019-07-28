
public class SinhVien {
	private int MSSV;
	private String HoTen;
	private boolean GioiTinh;
	private String CMND;
	
	public void setMSSV(int mssv) {
		if(mssv > 0) {
			this.MSSV = mssv;
		}
	}
	
	public int getMSSV() {
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
	
	public void setGioiTinh(boolean gt) {
		this.GioiTinh = gt;
	}
	
	public boolean getGioiTinh() {
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
}
