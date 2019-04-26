package me.leig.task.base.aes;

public interface AESUtils {

    /**
     * 解密
     *
     * @param content
     * @return
     */
    String decode(String content);

    /**
     * 加密
     *
     * @param content
     * @return
     */
    String encrypt(String content);

}
