package ics314;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class ICSFileNameObtainer {
	@SuppressWarnings("all")
	public static void main(String[] args){
		List<String> imageFileNames = getListOfImageFileNames("");
		System.out.println(imageFileNames);
		
		
		
	}
	
	public static List<String> getListOfImageFileNames(String directory){
		File f = new File("./" + directory);
		String[] fileNames = f.list();
		
		List<String> imageFileNames = new ArrayList<String>(10);
		String r = ".*\\.ics";
		Pattern p = Pattern.compile(r);
		
		for(String s : fileNames){
			if(p.matcher(s).matches()){
				imageFileNames.add(s);
			}
		}
		return imageFileNames;
	}
}
