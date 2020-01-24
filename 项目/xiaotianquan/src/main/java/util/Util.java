package util;




import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Util {
    public static final String DATA_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String[] SIZE_NAMES = {"B","KB","MB","GB"};

    public static DateFormat DATE_FORMAT = new SimpleDateFormat(DATA_PATTERN);


    public static String parseSize(Long size) {
        int n = 0;
        while(size >= 1024){
            size = size/1024;
            n++;
        }
        return size + SIZE_NAMES[n];
    }

    public static String parseDate(Long lastModified) {
        return DATE_FORMAT.format(new Date(lastModified));

    }

    public static void main(String[] args) {
        System.out.println(parseSize(1024L));
    }
}
