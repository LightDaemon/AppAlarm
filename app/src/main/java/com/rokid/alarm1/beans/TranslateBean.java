package com.rokid.alarm1.beans;

import java.util.List;

/**
 * Created by ZY on 2016/7/29.
 * Description :翻译数据的实体类
 */
public class TranslateBean {


    private BasicEntity basic;
    private String query;
    /* 0 - 正常
    　20 - 要翻译的文本过长
    　30 - 无法进行有效的翻译
    　40 - 不支持的语言类型
    　50 - 无效的key
    　60 - 无词典结果，仅在获取词典结果生效*/
    private int errorCode;
    private List<String> translation;

    private List<WebEntity> web;

    public BasicEntity getBasic() {
        return basic;
    }

    public void setBasic(BasicEntity basic) {
        this.basic = basic;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public List<WebEntity> getWeb() {
        return web;
    }

    public void setWeb(List<WebEntity> web) {
        this.web = web;
    }

    public static class BasicEntity {
        private String phonetic;
        private List<String> explains;

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }
    }

    public static class WebEntity {
        private String key;
        private List<String> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }
    }
}
