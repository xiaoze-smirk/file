package com.xiaoze.file.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * Word导出配置类
 *
 * @author : xiaoze
 * @date : 2019/5/17
 */
@Data
public class Word implements Serializable {

    private static final long serialVersionUID = 7779296783876629483L;

    /**
     * 模板路径（绝对路径，不包括模板名称,结尾没有“\”）
     */
    private String templatePath;

    /**
     * 模板名称（包括后缀，不包括模板路径）
     */
    private String templateName;

    /**
     * 导出word的名称（包括后缀，不包括路径）
     */
    private String fileName;

    /**
     * 存放已生成word到本地的临时路径（不包括word名称,结尾没有“\”）
     */
    private String fileTempPath;

    /**
     * word后缀
     */
    String wordSuffix;

    /**
     * 数据
     */
    Map<String, Object> map;

}
