package jk.album.albumfile;

public class AlbumFileFactory {

//	public enum Verion {v1}

//	public static AlbumFile build(Verion verion){
	public static AlbumFile build(){
		return build(1);
	}
	public static AlbumFile build(int verion){

		AlbumFile albumFile = new AlbumFile(verion);


		return albumFile;
	}
}
