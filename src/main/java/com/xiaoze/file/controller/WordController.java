package com.xiaoze.file.controller;

import com.xiaoze.file.model.Word;
import com.xiaoze.file.utils.ModelUtil;
import com.xiaoze.file.utils.WordUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * WordController
 * word控制层
 *
 * @author : xiaoze
 * @date : 2019/5/17
 */
@RestController
@RequestMapping("/word")
public class WordController {

    /**
     * http://localhost:9090/file/word/download
     * @param response
     */
    @GetMapping("download")
    public void download(HttpServletResponse response){
        Word word = new Word();
        ModelUtil.initWord(word);
        WordUtil wordUtil = new WordUtil();
        wordUtil.download(response, word);
    }

}
