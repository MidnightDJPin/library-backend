package library.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

public class JsonUtil {
	public static String getJsonString(HttpServletRequest request) throws IOException {
		InputStream is = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String strRead = null;
        StringBuffer sbf = new StringBuffer();
        while ((strRead = reader.readLine()) != null) {
            sbf.append(strRead);
            sbf.append("\r\n");
        }
        reader.close();
        return sbf.toString();
	}
}
