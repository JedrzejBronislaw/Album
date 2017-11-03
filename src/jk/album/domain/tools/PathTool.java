package jk.album.domain.tools;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class PathTool {

	public final static String separator = File.separator;


	public static String makeRelativePath(String filePath, String placePath){
		File file  = new File(filePath);
		File place = new File(placePath);

//		if (!file.isFile()) return null;
//		if (!file.isDirectory()) return null;
		if (!file.isAbsolute() || !place.isAbsolute()) return null;


		String[] fileList  = filePath.split(Pattern.quote(separator));
		String[] placeList = placePath.split(Pattern.quote(separator));

		if (!placeList[0].equals(fileList[0])) return filePath; //another discs

		if (place.isFile()) placeList = shortPath(placeList);

		int rootSize = rootSize(fileList, placeList);

		StringBuffer relPath = new StringBuffer();

		if (rootSize < placeList.length)
			for(int i=0; i<placeList.length-rootSize; i++)
				relPath.append("..\\");

		for(int i=rootSize; i<fileList.length-1; i++){
			relPath.append(fileList[i]);
			relPath.append(separator);
		}

		relPath.append(fileList[fileList.length-1]);

		return relPath.toString();
	}

	private static String[] shortPath(String[] path) {
		String[] result = new String[path.length-1];
		for(int i=0; i<result.length; i++)
			result[i] = path[i];
		return result;
	}

	private static int rootSize(String[] fileList, String[] placeList){
		int min = Math.min(fileList.length-1, placeList.length);
		int rootSize = min;
		for (int i=0; i<min; i++)
			if (!placeList[i].equals(fileList[i])){
				rootSize = i;
				break;
			}

		return rootSize;
	}

	public static String makeAbsolutePath(String relFilePath, String absPlacePath){
		File file  = new File(relFilePath);
		File place = new File(absPlacePath);

		if (file.isAbsolute()) return relFilePath;
		if (!place.isAbsolute()) return null;

		String[] fileList  = relFilePath.split(Pattern.quote(separator));
		String[] placeList = absPlacePath.split(Pattern.quote(separator));

		int nBacks = 0;
		for(String s : fileList)
			if (s.equals("..")) nBacks++; else
				break;

		if (place.isFile()) placeList = shortPath(placeList);


		int lenFPath = fileList.length;
		int lenPPath = placeList.length;

		if (nBacks > lenPPath-1) return null;

		StringBuffer absPath = new StringBuffer();
		for (int i=0; i<lenPPath-nBacks; i++)
			absPath.append(placeList[i]).append(separator);
		for (int i=nBacks; i<lenFPath-1; i++)
			absPath.append(fileList[i]).append(separator);
		absPath.append(fileList[fileList.length-1]);


		return absPath.toString();
	}

	public static String tryCanonicalPath(String path){
		String resultPath;
		File f = new File(path);

		try {
			resultPath = f.getCanonicalPath();
		} catch (IOException e) {
			resultPath = f.getAbsolutePath();
		}

		return resultPath;
	}
}
