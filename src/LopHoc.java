import java.util.ArrayList;

public class LopHoc {
	private String TenLop;
	private ArrayList<SinhVien> DSSV;
	
	public LopHoc() {
		super();
		DSSV = new ArrayList<SinhVien>();
	}
	
	public String getTenLop() {
		return TenLop;
	}
	public void setTenLop(String tenLop) {
		TenLop = tenLop;
	}
	public ArrayList<SinhVien> getDSSV() {
		return DSSV;
	}
	public void setDSSV(ArrayList<SinhVien> dSSV) {
		DSSV = dSSV;
	}
	
	public boolean ThemSV(SinhVien newSV) {
		SinhVien sv = DSSV.stream().filter(x -> x.getMSSV().equals(newSV.getMSSV())).findFirst().orElse(null);
		if(sv == null) {
			DSSV.add(newSV);
			return true;
		}
		
		return false;
	}
}
