package Utils;

import android.content.Context;
import android.view.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import Bean.Moviebean;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Joker on 2016/12/15.
 */

public class SearchJsoup_Utils {

    private String uri;
    private String rootimgurl ;
    private OnDataRight onDataRight ;
    private ArrayList<Moviebean> moviebeens;
    private Context mContext ;
    private View view ;
    private String body;



    public SearchJsoup_Utils(String uri , Context mContext , View view){
        this.uri = uri ;
        this.mContext = mContext ;
        this.view = view ;
        moviebeens = new ArrayList<>();
        ParseUrl();
    }

    public void ParseUrl(){
        Observable.create(new Observable.OnSubscribe<ArrayList>() {


            @Override
            public void call(Subscriber<? super ArrayList> subscriber) {

                ArrayList<Moviebean> movies = aboutJsoup();
                subscriber.onNext(moviebeens);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList arrayList) {

                        onDataRight.getData(arrayList);
                    }
                });
    }

    public ArrayList<Moviebean> aboutJsoup(){
        URL url = null;
        try {
            url = new URL(uri);
            Document Root = Jsoup.parse(url, 5000);
            Elements rootbox = Root.getElementsByClass("co_content8").select("ul");
            Elements first = rootbox.select("a[href]");
            for(Element element : first){
                String link = element.attr("abs:href");
                String text = element.text();
                String title = text.substring(text.indexOf("《") + 1, text.indexOf("》"));
                Document son = Jsoup.parse(new URL(link), 3000);
                Element Sonbox = son.getElementById("Zoom");
                String imgurl = Sonbox.select("img[src]").first().attr("src");
                Element currint = Sonbox.select("p").first();
                String currintinfo = currint.text();
                String dowlink = Sonbox.select("td[style]").first().text();
                String[] dowlinks = new String[1];
                dowlinks[0] = dowlink ;
                Moviebean movie = new Moviebean();
                movie.setDowlinks(dowlinks);
                movie.setDowlink(dowlink);
                movie.setTitle(text);
                movie.setImgurl(imgurl);
                movie.setCurrentinfo(currintinfo);
                moviebeens.add(movie);
            }

            String urllink = rootbox.select("a[href]").first().attr("abs:href");


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moviebeens;
    }

    public interface OnDataRight{
        public void getData(ArrayList<Moviebean> moviebeens);
    }

    public void setOnDataRight(OnDataRight onDataRight){
        this.onDataRight = onDataRight ;

    }

}
