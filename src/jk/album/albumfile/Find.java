package jk.album.albumfile;

import java.util.List;

import jk.album.domain.IdentifiedById;

public class Find {


	/**
	 *
	 * @param <T>
	 * @param list
	 * @param id
	 * @param adding if there is no photo with this id and adding is true then photo will be add
	 * @return Object of Photo or null
	 */
	public static <T extends IdentifiedById> T inList(List<T> list, String id, Class<T> clazz, boolean adding){

//		System.out.println(clazz.getName());
//		System.out.println("id: "+id);
//		System.out.println("size of list: "+list.size());
//		System.out.println();

		for (int i=0; i<list.size(); i++) {
			T p = list.get(i);
			if (p.getId().equals(id))
				return p;
		}

		if (adding) {
			try {
				T photo = clazz.newInstance();
				photo.setId(id);
				list.add(photo);
				return photo;
			} catch (InstantiationException|IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

}
