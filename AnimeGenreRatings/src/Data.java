import java.util.HashMap;
import java.util.Map;


/**
 * I met some problems, that is, when I tried to run exported jar file on hadoop, it throws FileNotFoundException or NullPointerException, thus I cannot read the file "genresCount".
 * As a result, I put key-value pairs in the program as followed, instead of reading the file "genresCount". 
 * @author zijin
 *
 */
public class Data{
	public static Map<String, Integer> genresToCount;
	public static Map<String, Integer> getData(){
		genresToCount = new HashMap<>();
		
		genresToCount.put("Action", 1974);
		genresToCount.put("Adventure", 1404);
		genresToCount.put("Cars", 33);
		genresToCount.put("Comedy", 2856);
		genresToCount.put("Dementia", 49);
		genresToCount.put("Demons", 209);
		genresToCount.put("Drama", 1348);
		genresToCount.put("Ecchi", 562);
		genresToCount.put("Fantasy", 1366);
		genresToCount.put("Game", 128);
		genresToCount.put("Harem", 307);
		genresToCount.put("Hentai", 849);
		genresToCount.put("Historical", 369);
		genresToCount.put("Horror", 252);
		genresToCount.put("Josei", 47);
		genresToCount.put("Kids", 254);
		genresToCount.put("Magic", 567);
		genresToCount.put("Martial-Arts", 189);
		genresToCount.put("Mecha", 626);
		genresToCount.put("Military", 336);
		genresToCount.put("Music", 307);
		genresToCount.put("Mystery", 409);
		genresToCount.put("Parody", 238);
		genresToCount.put("Police", 157);
		genresToCount.put("Psychological", 188);
		genresToCount.put("Romance", 1241);
		genresToCount.put("Samurai", 99);
		genresToCount.put("School", 977);
		genresToCount.put("Sci-Fi", 1426);
		genresToCount.put("Seinen", 421);
		genresToCount.put("Shoujo", 425);
		genresToCount.put("Shoujo-Ai", 54);
		genresToCount.put("Shounen", 1295);
		genresToCount.put("Shounen-Ai", 58);
		genresToCount.put("Slice-of-Life", 741);
		genresToCount.put("Space", 252);
		genresToCount.put("Sports", 303);
		genresToCount.put("Super-Power", 343);
		genresToCount.put("Supernatural", 836);
		genresToCount.put("Thriller", 79);
		genresToCount.put("Vampire", 85);
		genresToCount.put("Yaoi", 38);
		genresToCount.put("Yuri", 33);
		
		return genresToCount;
	}
}
