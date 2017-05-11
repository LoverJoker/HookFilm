package Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.joker.hookfilm.R;

import java.util.ArrayList;

import Bean.Moviebean;
import Utils.Glide_Utils;
import Utils.ZuixinJsoup_Utils;
import Utils.guonei_JsoupUtils;

/**
 * Created by Joker on 2017/1/19.
 */

public class Main_RecycleView_Adapter extends RecyclerView.Adapter {
    private int NORMAL = 0;
    private int FOOT = 1;
    private int NOW_NUM = 2 ;
    private Context mContext ;
    private ArrayList<Moviebean> mMoviebeens;
    private RecyclerView mRecycleView ;
    private int lastVisibleItemPosition ;
    private int FilmType ; //1为最新电影。
    private String nextUrl ;
    private onFilmClick onfilmClick;

    public interface onFilmClick{
        void onClick(View view,int position);
    }

    public void setOnFilmClick(onFilmClick onfilmClick){
        this.onfilmClick = onfilmClick ;
    }

    public Main_RecycleView_Adapter(Context mContext ,ArrayList<Moviebean> mMoviebeens ,RecyclerView mRecycleView ,int filmType ,String  nextUrl){
        this.mContext = mContext ;
        this.mMoviebeens = mMoviebeens ;
        this.mRecycleView = mRecycleView ;
        this.FilmType = filmType ;
        this.nextUrl = nextUrl ;
        if (filmType == 1){
            refreshzuixin();
        }else {
            refreshguonei();
        }
    }

    public void refreshzuixin(){
        mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItemPosition+1==Main_RecycleView_Adapter.this.getItemCount()){
                    ZuixinJsoup_Utils zuixinJsoup_utils = new ZuixinJsoup_Utils("http://www.ygdy8.com/html/gndy/dyzz/list_23_"+NOW_NUM+".html",mContext,mRecycleView);
                    NOW_NUM++;
                    zuixinJsoup_utils.setOnDataRight(new ZuixinJsoup_Utils.OnDataRight() {
                        @Override
                        public void getData(ArrayList<Moviebean> moviebeens) {
                            Main_RecycleView_Adapter.this.addAll(moviebeens);
                            Main_RecycleView_Adapter.this.notifyDataSetChanged();
                        }
                    });
                }
                LinearLayoutManager lm = (LinearLayoutManager) mRecycleView.getLayoutManager();
                lastVisibleItemPosition = lm.findLastVisibleItemPosition();
            }
        });
    }

    public void refreshguonei(){
        mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItemPosition+1==Main_RecycleView_Adapter.this.getItemCount()){
                    guonei_JsoupUtils guonei_jsoupUtils = new guonei_JsoupUtils(nextUrl+ NOW_NUM + ".html", mContext, mRecycleView);
                    NOW_NUM++;
                    guonei_jsoupUtils.setOnDataRight(new guonei_JsoupUtils.OnDataRight() {
                        @Override
                        public void getData(ArrayList<Moviebean> moviebeens) {
                            Main_RecycleView_Adapter.this.addAll(moviebeens);
                            Main_RecycleView_Adapter.this.notifyDataSetChanged();
                        }
                    });
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager lm = (LinearLayoutManager) mRecycleView.getLayoutManager();
                lastVisibleItemPosition = lm.findLastVisibleItemPosition();
            }
        });
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder =  null ;
        switch (viewType){
            case 0 :
                viewHolder = new Normal_Holder(LayoutInflater.from(mContext).inflate(R.layout.main_item_normal, parent,false));
                break;
            case 1 :
                viewHolder = new Emplty_Holder(LayoutInflater.from(mContext).inflate(R.layout.main_item_foot,parent,false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof Normal_Holder){
            Normal_Holder normal_holder = (Normal_Holder) holder;
            normal_holder.title.setText(mMoviebeens.get(position).getTitle());
            Glide_Utils.SetGlide(mContext,mMoviebeens.get(position).getImgurl(),normal_holder.imgv);
            normal_holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onfilmClick.onClick(v,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mMoviebeens.size()>0?mMoviebeens.size()+1 : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position+1 == getItemCount()){
            return FOOT;
        }else{
            return NORMAL ;
        }
    }

    public void addAll(ArrayList<Moviebean> moviebeens){
        for (int i=0; i<moviebeens.size();i++){
            mMoviebeens.add(moviebeens.get(i));
        }
    }

    class Normal_Holder extends RecyclerView.ViewHolder{
        ImageView imgv ;
        TextView title ;
        public Normal_Holder(View itemView) {
            super(itemView);
            imgv = (ImageView) itemView.findViewById(R.id.imgv);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
    class Emplty_Holder extends RecyclerView.ViewHolder{
        TextView tex_emplty ;
        public Emplty_Holder(View itemView) {
            super(itemView);
            tex_emplty = (TextView) itemView.findViewById(R.id.tex_emplty);
        }
    }

    class footholder extends RecyclerView.ViewHolder{
        TextView foot_text;
        LinearLayout footFather ;
        public footholder(View itemView) {
            super(itemView);
            footFather = (LinearLayout) itemView.findViewById(R.id.footFather);
            foot_text = (TextView) itemView.findViewById(R.id.foot_text);
        }
    }

}
