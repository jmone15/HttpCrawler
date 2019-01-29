/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.media24.httpcrawler.model;

/**
 *
 * @author Jurgen Mone <jmone@24media.gr>
 */
public class Link {
    
    private int typeId;
    private String typeName;
    private String Url;
    private String articleId;
    
    

    public Link(int typeId, String typeName, String Url, String articleId) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.Url = Url;
        this.articleId = articleId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

}
