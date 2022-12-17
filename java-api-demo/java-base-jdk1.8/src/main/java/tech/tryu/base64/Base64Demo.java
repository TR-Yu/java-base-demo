package tech.tryu.base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * jul 下的base64编程测试
 *
 * @author YU
 * @date 2022-07-07 16:56
 */
public class Base64Demo {
    public static void main(String[] args) {
        String str = "中文字符";
        byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
        System.out.println("strBytes: " + strBytes.length);
        // 字节不对齐
        Base64.Encoder encoder1 = Base64.getEncoder().withoutPadding();
        Base64.Encoder encoder = Base64.getEncoder();

        byte[] encodeByte = encoder1.encode(strBytes);
        byte[] encodeBytes = encoder.encode(strBytes);
        System.out.println("encodeByte " + encodeByte.length + "\n" + "encodeBytes " + encodeBytes.length);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes1 = decoder.decode(encodeByte);
        byte[] bytes2 = decoder.decode(encodeBytes);

        String string1 = new String(bytes1, StandardCharsets.UTF_8);
        String string2 = new String(bytes2, StandardCharsets.UTF_8);


        System.out.println("string1: " + string1 + "\n" + "string2: " + string2);

        //

    }
}
