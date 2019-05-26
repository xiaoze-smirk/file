package com.xiaoze.file.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;
import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * CommonUtils
 * 常用工具类
 *
 * @author : xiaoze
 * @date : 2019/5/17
 */
public class CommonUtil {

    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    private CommonUtil() {
    }

    /**
     * 将图片转为字符串
     * @param imgUrl 图片路径
     * @return String
     */
    public static String getImgtoStr(String imgUrl){

        String img;
        InputStream in = null;
        byte[] picdata = null;
        try {
            in = new FileInputStream(imgUrl);
            picdata = new byte[in.available()];
            in.read(picdata);

        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }

        BASE64Encoder encoder = new BASE64Encoder();
        img = encoder.encode(picdata);

        if(StringUtils.isNotEmpty(img)){
            return img;
        }
        return "";
    }


    /**
     * 删除单个文件
     * @param filePath 文件路径（包括文件本身名称）
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (!file.delete()) {
                log.info("删除单个文件失败！");
            } else {
                log.info("删除单个文件成功！");
            }
        } else {
            log.info("删除的单个文件不存在！");
        }
    }

    /**
     * 创建文件夹
     * @param filePath
     */
    public static void createDir(String filePath){
        File upload = new File(filePath);
        if(!upload.exists()){
            upload.mkdirs();
            log.info("创建文件夹成功！");
        }
    }

    /**
     * 将文本回车换行成转为freeMarker换行
     * 若是前端已换行传到数据库，再从数据库取出来已经是换行的，不需要此方法了
     *
     * @param text
     * @return String
     */
    public static String textToLines(String text){
        return text.replaceAll("\n", "<w:br/>");
    }

    /**
     * 获取uuid
     * uuid本身就是32位的
     *
     * @return String
     */
    public static String getUUID(){
        //转化为String对象
        String uuid = UUID.randomUUID().toString();

        //因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可
        uuid = uuid.replace("-", "");

        return uuid;

    }

    /**
     * 获取图片的宽度和高度
     *
     * @param filePath 图片路径（包括图片自己的名称）
     * @return Map<String, Integer>
     */
    public static Map<String, Integer> getImgInfo(String filePath){

        Map<String, Integer> map = new HashMap<>(16);

        ImageIcon imageIcon = new ImageIcon(filePath);
        //图片宽度
        map.put("width", imageIcon.getIconWidth());
        //图片高度
        map.put("height", imageIcon.getIconHeight());

        return map;

    }

}
