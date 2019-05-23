package com.xiaoze.file.utils;

import com.xiaoze.file.constant.Constant;
import com.xiaoze.file.model.GoodsWord;
import com.xiaoze.file.model.PhotoWord;
import com.xiaoze.file.model.Word;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import java.util.*;

/**
 * ModelUtil
 * model工具类
 *
 * @author : xiaoze
 * @date : 2019/5/17
 */
public class ModelUtil {

    public static void initWord(Word word){

        GoodsWord goodsWord = new GoodsWord();
        goodsWord.setGoodsTitle("商品买卖指标");
        goodsWord.setGoodsNum("9546216763479");
        goodsWord.setGoodsName("生活用品");
        goodsWord.setCheckPerson("小泽");
        goodsWord.setCheckDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
        goodsWord.setGoodsJudge("1、合格   2、不合格");
        goodsWord.setGoodsInitPrice(20.58);
        goodsWord.setGoodsProcedure(CommonUtil.textToLines("1、验证商品信息\n2、验证商品包装\n3、验证商品安全"));
        goodsWord.setCheckResult("通过");
        goodsWord.setGoodsAmount(10000);
        goodsWord.setStandardAmount(9990);
        goodsWord.setUnStandardAmount(10);

        String resourcePath = CommonUtil.getResourcePath();

        //三张图片地址
        String boyPath = StringUtils.join(resourcePath, "static\\photo\\boy.jpg");
        String girlPath = StringUtils.join(resourcePath, "static\\photo\\girl.jpg");
        String cartoonPath = StringUtils.join(resourcePath, "static\\photo\\cartoon.jpg");

        List<GoodsWord> goodsWordList = new ArrayList<>();
        List<PhotoWord> photoWordList = new ArrayList<>();


        PhotoWord photoWord3 = getPhoto(cartoonPath);
        int t = 50;

        for(int i = 0; i < 20; i++){

            //假设每个商品都固定两张图片
            List<PhotoWord> photoWords = new ArrayList<>();
            PhotoWord photoWord1 = getPhoto(boyPath);
            photoWord1.setId(StringUtils.join("rId", ++t));
            photoWord1.setPhotoName(StringUtils.join("image", t));

            PhotoWord photoWord2 = getPhoto(girlPath);
            photoWord2.setId(StringUtils.join("rId", ++t));
            photoWord2.setPhotoName(StringUtils.join("image", t));

            photoWords.add(photoWord1);
            photoWords.add(photoWord2);

            goodsWord.setPhotoWordList(photoWords);

            goodsWordList.add(goodsWord);

            photoWordList.addAll(photoWords);

        }

        Map<String, Object> map = new HashMap<>(16);
        map.put("goodsWordList", goodsWordList);
        map.put("photoWordList", photoWordList);
        map.put("ironTytle", photoWord3);

        String templatePath = StringUtils.join(resourcePath, "freemarker");

        String templateName = "word.ftl";

        String fileName = "word.doc";

        String fileTempPath = StringUtils.join(resourcePath, "static\\word");

        word.setMap(map);
        word.setWordSuffix(Constant.WORD_DOC);
        word.setFileName(fileName);
        word.setTemplatePath(templatePath);
        word.setTemplateName(templateName);
        word.setFileTempPath(fileTempPath);

    }

    private static PhotoWord getPhoto(String imgUrl){
        PhotoWord photoWord = new PhotoWord();

        Map<String, Integer> map = CommonUtil.getImgInfo(imgUrl);
        photoWord.setWidth(map.get("width"));
        photoWord.setHeight(map.get("height"));
        photoWord.setImgStr(CommonUtil.getImgtoStr(imgUrl));
        return photoWord;
    }

}
