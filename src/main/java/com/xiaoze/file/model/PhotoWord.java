package com.xiaoze.file.model;

import com.xiaoze.file.constant.Constant;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;

/**
 * Photo
 * word导出照片类
 * 当数据为null时最好返回一个默认的数据(freeMarker导出Word)
 *
 * @author xiaoze
 * @date 2019/5/17
 */
@Data
public class PhotoWord implements Serializable {

    private static final long serialVersionUID = -2498039072478134089L;

    /**
     * 图片特定id，freeMarker用
     */
    private String id;

    /**
     * 图片名称
     */
    private String photoName;

    /**
     * 图片高度
     */
    private Integer height;

    /**
     * 图片宽度
     */
    private Integer width;

    /**
     * 图片转为字符串后的数据
     */
    private String imgStr;

    public Integer getHeight() {
        if(height != null){
            if(height < Constant.PHOTO_HEIGHT_INIT){
                return height;
            }else{
                return Constant.PHOTO_HEIGHT_INIT;
            }
        }else{
            return Constant.PHOTO_HEIGHT_INIT;
        }
    }

    public Integer getWidth() {
        if(width != null){
            if(width < Constant.PHOTO_WIDTH_INIT){
                return width;
            }else{
                return Constant.PHOTO_WIDTH_INIT;
            }
        }else{
            return Constant.PHOTO_WIDTH_INIT;
        }
    }

    public String getImgStr() {
        if(StringUtils.isNotEmpty(imgStr)){
            return imgStr;
        }
        return Constant.STRING_NONE;
    }

    public String getId() {
        if(StringUtils.isNotEmpty(id)){
            return id;
        }
        return Constant.STRING_NONE;
    }

    public String getPhotoName() {
        if(StringUtils.isNotEmpty(photoName)){
            return photoName;
        }
        return Constant.STRING_NONE;
    }
}
