package com.xiaoze.file.model;

import com.xiaoze.file.constant.Constant;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;
import java.util.List;

/**
 * TestCase
 * word导出专用类
 * 当数据为null时最好返回一个默认的数据(freeMarker导出Word)
 *
 * @author xiaoze
 * @date 2019/5/17
 */
@Data
public class GoodsWord implements Serializable {

    private static final long serialVersionUID = -6637748716886681296L;

    /**
     * 商品小标题
     */
    private String goodsTitle;

    /**
     * 商品编号
     */
    private String goodsNum;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品检测员
     */
    private String checkPerson;

    /**
     * 检测日期
     */
    private String checkDate;

    /**
     * 商品评判标准
     */
    private String goodsJudge;

    /**
     * 商品初始价格
     */
    private Double goodsInitPrice;

    /**
     * 商品检测步骤
     */
    private String goodsProcedure;

    /**
     * 商品检测结果
     */
    private String checkResult;

    /**
     * 检测商品的总数量
     */
    private Integer goodsAmount;

    /**
     * 合格数量
     */
    private Integer standardAmount;

    /**
     * 不合格数量
     */
    private Integer unStandardAmount;

    /**
     * 图片类集合
     */
    private  List<PhotoWord> photoWordList;

    public String getGoodsTitle() {
        if(StringUtils.isNotEmpty(goodsTitle)){
            return goodsTitle;
        }
        return Constant.STRING_NONE;
    }

    public String getGoodsNum() {
        if(StringUtils.isNotEmpty(goodsNum)){
            return goodsNum;
        }
        return Constant.STRING_NONE;
    }

    public String getGoodsName() {
        if(StringUtils.isNotEmpty(goodsName)){
            return goodsName;
        }
        return Constant.STRING_NONE;
    }

    public String getCheckPerson() {
        if(StringUtils.isNotEmpty(checkPerson)){
            return checkPerson;
        }
        return Constant.STRING_NONE;
    }

    public String getCheckDate() {
        if(StringUtils.isNotEmpty(checkDate)){
            return checkDate;
        }
        return Constant.STRING_NONE;
    }

    public String getGoodsJudge() {
        if(StringUtils.isNotEmpty(goodsJudge)){
            return goodsJudge;
        }
        return Constant.STRING_NONE;
    }

    public Double getGoodsInitPrice() {
        if(goodsInitPrice != null){
            return goodsInitPrice;
        }
        return Constant.DOUBLE_ZERO;
    }

    public String getGoodsProcedure() {
        if(StringUtils.isNotEmpty(goodsProcedure)){
            return goodsProcedure;
        }
        return Constant.STRING_NONE;
    }

    public String getCheckResult() {
        if(StringUtils.isNotEmpty(checkResult)){
            return checkResult;
        }
        return Constant.STRING_NONE;
    }

    public Integer getGoodsAmount() {
        if(goodsAmount != null){
            return goodsAmount;
        }
        return Constant.Int_ZERO;
    }

    public Integer getStandardAmount() {
        if(standardAmount != null){
            return standardAmount;
        }
        return Constant.Int_ZERO;
    }

    public Integer getUnStandardAmount() {
        if(unStandardAmount != null){
            return unStandardAmount;
        }
        return Constant.Int_ZERO;
    }
}
