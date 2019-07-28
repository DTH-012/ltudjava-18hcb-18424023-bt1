
public class Diem {
	private String Lop;
	private int MSSV;
	private String HoTen;
	private float DiemGK;
	private float DiemCK;
	private float DiemKhac;
	private float DiemTong;
	public String getLop() {
		return Lop;
	}
	public void setLop(String lop) {
		Lop = lop;
	}
	public int getMSSV() {
		return MSSV;
	}
	public void setMSSV(int mSSV) {
		MSSV = mSSV;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		if(hoTen != null && hoTen.isEmpty()) {
			HoTen = hoTen;
		}
	}
	public float getDiemGK() {
		return DiemGK;
	}
	public void setDiemGK(float diemGK) {
		DiemGK = diemGK;
	}
	public float getDiemCK() {
		return DiemCK;
	}
	public void setDiemCK(float diemCK) {
		DiemCK = diemCK;
	}
	public float getDiemKhac() {
		return DiemKhac;
	}
	public void setDiemKhac(float diemKhac) {
		DiemKhac = diemKhac;
	}
	public float getDiemTong() {
		return DiemTong;
	}
	public void setDiemTong(float diemTong) {
		DiemTong = diemTong;
	}
	
}
