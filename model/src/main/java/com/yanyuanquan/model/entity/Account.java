package com.yanyuanquan.model.entity;

import java.util.List;

/**
 * Created by guider on 16/7/13.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class Account  {

    /**
     * id : 40309236
     * url : https://api.github.com/authorizations/40309236
     * app : {"name":"GitHub","url":"https://github.com/guider/GitHub-mvpentication.html","client_id":"689dd44e2f6914201fb4"}
     * token :
     * hashed_token : 9842a4c22725b4425f63460ad345bbb041e3ad51d706a9cf67e9ef566d76caf0
     * token_last_eight : 0c05145a
     * note : admin script
     * note_url : null
     * created_at : 2016-07-13T15:08:08Z
     * updated_at : 2016-07-13T15:08:08Z
     * scopes : ["public_repo"]
     * fingerprint : null
     */

    private int id;
    private String url;
    /**
     * name : GitHub
     * url : https://github.com/guider/GitHub-mvpentication.html
     * client_id : 689dd44e2f6914201fb4
     */

    private AppEntity app;
    private String token;
    private String hashed_token;
    private String token_last_eight;
    private String note;
    private Object note_url;
    private String created_at;
    private String updated_at;
    private String fingerprint;
    private List<String> scopes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AppEntity getApp() {
        return app;
    }

    public void setApp(AppEntity app) {
        this.app = app;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHashed_token() {
        return hashed_token;
    }

    public void setHashed_token(String hashed_token) {
        this.hashed_token = hashed_token;
    }

    public String getToken_last_eight() {
        return token_last_eight;
    }

    public void setToken_last_eight(String token_last_eight) {
        this.token_last_eight = token_last_eight;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Object getNote_url() {
        return note_url;
    }

    public void setNote_url(Object note_url) {
        this.note_url = note_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public static class AppEntity {
        private String name;
        private String url;
        private String client_id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getClient_id() {
            return client_id;
        }

        public void setClient_id(String client_id) {
            this.client_id = client_id;
        }

        @Override
        public String toString() {
            return "AppEntity{" +
                    "name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    ", client_id='" + client_id + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", app=" + app +
                ", token='" + token + '\'' +
                ", hashed_token='" + hashed_token + '\'' +
                ", token_last_eight='" + token_last_eight + '\'' +
                ", note='" + note + '\'' +
                ", note_url=" + note_url +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                ", scopes=" + scopes +
                '}';
    }
}
