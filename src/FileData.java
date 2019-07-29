import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileData {
	public FileData() {
		super();
	}
	
	public LopHoc readDSSV(String path) throws Exception{
		LopHoc lopHoc = new LopHoc();
		File file = new File(path);
		if(file.exists()) {
			Scanner s = new Scanner(file);
			lopHoc.setTenLop(file.getName().replaceFirst("[.][^.]+$", ""));
			while(s.hasNextLine()) {
				String line = s.nextLine();
				String[] data = line.split(",");
				SinhVien sv = new SinhVien(data[0],data[1],data[2],data[3]);
				if(!lopHoc.ThemSV(sv)) {
					return null;
				}
			}
		}
		
		return lopHoc;
	}
	
	public <T> boolean writeData(String pathFolder,String fileName, boolean isAppend, 
			ArrayList<T> DataList) throws IOException {
		FileWriter fw;
		try 
		{
			fw = new FileWriter(pathFolder+fileName+".csv", isAppend);
		}
		catch(IOException exc) 
		{
			return false;
		}
		
		for(T data : DataList) {
			String str = data.toString() + "\r\n";
			fw.write(str);
		}
		
		fw.close();
		return true;
	}
	
	public static String getFileName(String fileName) {
		return fileName.replaceFirst("[.][^.]+$", "");
		
	}
}
