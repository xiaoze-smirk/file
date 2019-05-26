package com.xiaoze.file.utils;

import com.xiaoze.file.constant.Constant;
import com.xiaoze.file.model.Word;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * WordUtil
 * word工具类
 *
 * @author linzemin
 * @date 2019/5/3
 */
public class WordUtil {

    private static Logger log = LoggerFactory.getLogger(WordUtil.class);

    /**
     * 创建word的主要方法
     * @param fileTempUrl 导出文档的路径，包括.doc后缀
     */
    private void createWord(Word word, String fileTempUrl) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDefaultEncoding(Constant.ENCODING_UTF_8);
        Map<String, Object> dataMap = null;
        if(MapUtils.isNotEmpty(word.getMap())){
            dataMap = word.getMap();
        }

        Template t = null;
        try {
            // FTL文件所存在的位置
            configuration.setDirectoryForTemplateLoading(new File(word.getTemplatePath()));
            // 文件名
            t = configuration.getTemplate(word.getTemplateName());
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        File outFile = new File(fileTempUrl);
        Writer out = null;
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            fileOutputStream = new FileOutputStream(outFile);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            out = new BufferedWriter(outputStreamWriter);
            if (t != null){
                t.process(dataMap, out);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }

    }

    public void download(HttpServletResponse response, Word word){
        long currentTime=System.currentTimeMillis();
        CommonUtil.createDir(word.getFileTempPath());
        String fileTempUrl = StringUtils.join(word.getFileTempPath(), "\\", currentTime, word.getWordSuffix());

        createWord(word, fileTempUrl);
        downloadForBrowser(word, response, fileTempUrl);
        //删除临时文件
        CommonUtil.deleteFile(fileTempUrl);

    }

    /**
     * 导出word
     * @param response
     * @param filePath
     */
    private void downloadForBrowser(Word word, HttpServletResponse response, String filePath){

        OutputStream output = null;
        FileInputStream inputStream = null;
        BufferedInputStream buffInput = null;
        try {
            //清空缓存
            response.reset();
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(word.getFileName(), Constant.ENCODING_UTF_8));
            //定义下载的类型，标明是word文件
            response.setContentType("application/msword;charset=UTF-8");
            //把创建好的word写入到输出流

            inputStream = new FileInputStream(filePath);
            buffInput = new BufferedInputStream(inputStream);
            output = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = buffInput.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
        }catch (IOException e){
            log.error(e.getMessage(),e);
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (output != null) {
                    output.close();
                }
                if (buffInput != null) {
                    buffInput.close();
                }
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }
        }
    }



}
