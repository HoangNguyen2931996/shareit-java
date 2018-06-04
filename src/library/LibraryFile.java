package library;

import javax.servlet.http.Part;

public class LibraryFile {
	
	public static String rename(String fileName){
		String[] arrImg =  fileName.split("\\.");
		String duoiFileImg = arrImg[arrImg.length - 1];
		String nameFile = "";
		for (int i  = 0;i< (arrImg.length - 1) ; i++) {
			if(i == 0){
				nameFile = arrImg[i];
			}else{
				nameFile += "-"+arrImg[i];
			}
		}
		nameFile = nameFile + "-"+System.nanoTime() +"."+duoiFileImg;
		return nameFile;
	}
	
	public static String getName(final Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
}
