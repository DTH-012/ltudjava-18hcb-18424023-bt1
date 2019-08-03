
public class Diem {
	private String MSSV;
	private String HoTen;
	private float DiemGK;
	private float DiemCK;
	private float DiemKhac;
	private float DiemTong;
	
	public Diem() {
		
	}
	
	public Diem(String mssv, String hoTen, float diemGK, 
			float diemCK, float diemKhac, float diemTong) {
		this.MSSV = mssv;
		this.HoTen = hoTen;
		this.DiemGK = diemGK;
		this.DiemCK = diemCK;
		this.DiemKhac = diemKhac;
		this.DiemTong = diemTong;
	}
	
	public String getMSSV() {
		return MSSV;
	}
	public void setMSSV(String mssv) {
		MSSV = mssv;
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

	@Override
	public String toString() {
		return this.MSSV+","+this.HoTen+","+this.DiemGK+","+this.DiemCK+","+this.DiemKhac+","+this.DiemTong;
	}
	
}
