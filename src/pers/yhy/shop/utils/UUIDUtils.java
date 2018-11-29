package pers.yhy.shop.utils;

import java.util.UUID;

/**
 * generate random string 
 * @author Hengyu Yue
 *
 */
public class UUIDUtils {
	
	/**
	 * get random string
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
