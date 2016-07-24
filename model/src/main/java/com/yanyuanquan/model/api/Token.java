package com.yanyuanquan.model.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guider on 16/7/14.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class Token {

    private String client_secret;
    private String note;
    private List<String> scopes;

    public Token(String client_secret, String note, List<String> scopes) {
        this.client_secret = client_secret;
        this.note = note;
        this.scopes = scopes;
    }

    public Token(String client_secret, String note, String scopes) {
        this.client_secret = client_secret;
        this.note = note;
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(scopes);
        this.scopes = arrayList;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }
}
