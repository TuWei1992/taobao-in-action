package com.dream.shop.model;

import java.io.Serializable;
import java.util.Date;

public class Shop implements Serializable {
	
	
	public Shop(){
		
	}
	
	
	public Shop(Long id){
		this.sid = id;
	}
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_SHOP.SID
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    private Long sid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_SHOP.CID
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    private Long cid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_SHOP.NICK
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    private String nick;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_SHOP.TITLE
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    private String title;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_SHOP.DESC
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    private String desc;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_SHOP.BULLETIN
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    private String bulletin;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_SHOP.PIC_PATH
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    private String picPath;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_SHOP.CREATED
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    private Date created;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_SHOP.MODIFIED
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    private Date modified;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table TOP_SHOP
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_SHOP.SID
     *
     * @return the value of TOP_SHOP.SID
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public Long getSid() {
        return sid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_SHOP.SID
     *
     * @param sid the value for TOP_SHOP.SID
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public void setSid(Long sid) {
        this.sid = sid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_SHOP.CID
     *
     * @return the value of TOP_SHOP.CID
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public Long getCid() {
        return cid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_SHOP.CID
     *
     * @param cid the value for TOP_SHOP.CID
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public void setCid(Long cid) {
        this.cid = cid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_SHOP.NICK
     *
     * @return the value of TOP_SHOP.NICK
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public String getNick() {
        return nick;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_SHOP.NICK
     *
     * @param nick the value for TOP_SHOP.NICK
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_SHOP.TITLE
     *
     * @return the value of TOP_SHOP.TITLE
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_SHOP.TITLE
     *
     * @param title the value for TOP_SHOP.TITLE
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_SHOP.DESC
     *
     * @return the value of TOP_SHOP.DESC
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_SHOP.DESC
     *
     * @param desc the value for TOP_SHOP.DESC
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_SHOP.BULLETIN
     *
     * @return the value of TOP_SHOP.BULLETIN
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public String getBulletin() {
        return bulletin;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_SHOP.BULLETIN
     *
     * @param bulletin the value for TOP_SHOP.BULLETIN
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public void setBulletin(String bulletin) {
        this.bulletin = bulletin == null ? null : bulletin.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_SHOP.PIC_PATH
     *
     * @return the value of TOP_SHOP.PIC_PATH
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_SHOP.PIC_PATH
     *
     * @param picPath the value for TOP_SHOP.PIC_PATH
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_SHOP.CREATED
     *
     * @return the value of TOP_SHOP.CREATED
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_SHOP.CREATED
     *
     * @param created the value for TOP_SHOP.CREATED
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_SHOP.MODIFIED
     *
     * @return the value of TOP_SHOP.MODIFIED
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public Date getModified() {
        return modified;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_SHOP.MODIFIED
     *
     * @param modified the value for TOP_SHOP.MODIFIED
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_SHOP
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof Shop)) {
            return false;
        }
        Shop other = (Shop) that;
        return this.getSid() == null ? other == null : this.getSid().equals(other.getSid())
            && this.getCid() == null ? other == null : this.getCid().equals(other.getCid())
            && this.getNick() == null ? other == null : this.getNick().equals(other.getNick())
            && this.getTitle() == null ? other == null : this.getTitle().equals(other.getTitle())
            && this.getDesc() == null ? other == null : this.getDesc().equals(other.getDesc())
            && this.getBulletin() == null ? other == null : this.getBulletin().equals(other.getBulletin())
            && this.getPicPath() == null ? other == null : this.getPicPath().equals(other.getPicPath())
            && this.getCreated() == null ? other == null : this.getCreated().equals(other.getCreated())
            && this.getModified() == null ? other == null : this.getModified().equals(other.getModified());
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_SHOP
     *
     *  Mon Sep 29 17:47:09 CST 2014
     */
    public int hashCode() {
        int hash = 23;
        if (getSid() != null) {
            hash *= getSid().hashCode();
        }
        if (getCid() != null) {
            hash *= getCid().hashCode();
        }
        if (getNick() != null) {
            hash *= getNick().hashCode();
        }
        if (getTitle() != null) {
            hash *= getTitle().hashCode();
        }
        if (getDesc() != null) {
            hash *= getDesc().hashCode();
        }
        if (getBulletin() != null) {
            hash *= getBulletin().hashCode();
        }
        if (getPicPath() != null) {
            hash *= getPicPath().hashCode();
        }
        if (getCreated() != null) {
            hash *= getCreated().hashCode();
        }
        if (getModified() != null) {
            hash *= getModified().hashCode();
        }
        return hash;
    }
}