package jk.album.albumfile.xml;

public interface XMLElemanetsName {

	static String rootName = "Album";
	static String atVersion = "verison";
	static String atMainFile = "mainFile";
	static String atCreateTime = "cTime";
	static String atEditTime = "eTime";

	static String elDescription = "description";
	static String atId = "id";
	static String atGlobalId = "gId";
	static String atTypeBeings = "type";

	static String elBeings = "objects";
	static String elBeing = "object";

	static String elPlaces = "places";
	static String elPlace = "place";
	static String atSuperPlace = "superPlace";

	static String elAuthors = "authors";
	static String elAuthor = "author";

	static String elDefaultPhoto = "defaultPhoto";

	static String elPhotoList = "photoList";
	static String elSize = "size";
	static String elWidth = "width";
	static String elHeight = "height";
	static String elHash = "hash";
	static String atAlgorithm = "alg";

	static String elPhotos = "photos";
	static String elPhotoListElement = "photo";
	static String elPhoto = "photo";
	static String elTitle = "title";
	static String elOccasion = "occasion";
	static String elTags = "tags";
	static String elTag = "tag";
	static String elDate = "date";
	static String elGps = "gps";
	static String atLatitude = "latitude";
	static String atLongitude = "longtude";

	static String elLinks = "links";
	static String elLink = "link";
	static String atLinkType = "type";
	static String valLinkTypeOther = "other";
	static String atCorrectLink = "correct";
	static String atCheckDate = "checkDate";

	static String elMarks = "marks";
	static String elMark = "mark";
	static String atMarkType = "type";
	static String valMarkTypeShapeless = "shapeless";
	static String valMarkTypeSpot = "point";
	static String valMarkTypeRect = "rect";
	static String valMarkTypeCircle = "circle";
	static String valMarkTypePolygon = "poly";
	static String atBeing = "being";
	static String elSpot = "point";
	static String elRect = "rect";
	static String elCircle = "circle";
	static String elPolygon = "poly";
	static String elPointOfPolygon = "point";
	static String atX = "x";
	static String atY = "y";
	static String atR = "r";
	static String atTop = "t";
	static String atBottom = "b";
	static String atLeft = "l";
	static String atRight = "r";
}
