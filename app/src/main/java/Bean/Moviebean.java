package Bean;

import java.io.Serializable;

/**
 * Created by Joker on 2017/1/19.
 */

public class Moviebean implements Serializable {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    private String title ;
    private String imgurl ;

    public String getCurrentinfo() {
        return currentinfo;
    }

    public void setCurrentinfo(String currentinfo) {
        this.currentinfo = currentinfo;
    }

    private String currentinfo ;

    public String getDowlink() {
        return dowlink;
    }

    public void setDowlink(String dowlink) {
        this.dowlink = dowlink;
    }

    private String dowlink ;

    public String[] getDowlinks() {
        return dowlinks;
    }

    public void setDowlinks(String[] dowlinks) {
        this.dowlinks = dowlinks;
    }

    private String[] dowlinks ;
}
