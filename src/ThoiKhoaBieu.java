import java.util.ArrayList;

public class ThoiKhoaBieu {
	private String TenLop;
	private ArrayList<MonHoc> DSMH;
	
	public ThoiKhoaBieu() {
		super();
		DSMH = new ArrayList<MonHoc>();
	}
	
	public String getTenLop() {
		return TenLop;
	}
	public void setTenLop(String tenLop) {
		TenLop = tenLop;
	}
	public ArrayList<MonHoc> getDSMH() {
		return DSMH;
	}
	public void setDSMH(ArrayList<MonHoc> dSMH) {
		DSMH = dSMH;
	}
}
