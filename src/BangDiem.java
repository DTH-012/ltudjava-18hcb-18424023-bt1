import java.util.ArrayList;

public class BangDiem {
	private String TenLop;
	private ArrayList<Diem> DSDiem;
	
	public BangDiem() {
		super();
		DSDiem = new ArrayList<Diem>();
	}
	
	public String getTenLop() {
		return TenLop;
	}
	public void setTenLop(String tenLop) {
		TenLop = tenLop;
	}
	public ArrayList<Diem> getDSDiem() {
		return DSDiem;
	}
	public void setDSDiem(ArrayList<Diem> dSDiem) {
		DSDiem = dSDiem;
	}
}
