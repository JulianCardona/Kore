package com.pixelleafs.kore.utils;

/**
 * @author Julian Cardona on 9/5/17.
 */

public class StringUtils {

    public static boolean isEmpty(CharSequence string){
        return (string == null || string.toString().trim().length() == 0);
    }

    public static String ifEmptyThenReturn(CharSequence string, CharSequence blankDefault){
        if(string == null ||
                string.toString().trim().length() == 0 ||
                    string.toString().equalsIgnoreCase("null"))
            return  blankDefault.toString();
        return string.toString();
    }

}
