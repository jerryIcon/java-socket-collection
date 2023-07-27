package util;

import java.io.*;

/**
 * @author JerryIcon
 * @date 2023/7/25 20:20
 */
public class StreamUtil {

    /**
     * 输入的流转二进制数组
     * @param is
     * @return
     */
    public static byte[] streamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int byteRes = 0;
        while ((byteRes = is.read(bytes)) != -1){
            bos.write(bytes,0, byteRes);
        }
        byte[] array = bos.toByteArray();
        bos.close();
        return array;
    }

    /**
     * 输入流转字符串
     * @param is
     * @return
     */
    public static String streamToString(InputStream is) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line + "\r\n");
        }
        return builder.toString();
    }

}
