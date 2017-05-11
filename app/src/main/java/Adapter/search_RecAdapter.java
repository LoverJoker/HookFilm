package Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joker.hookfilm.R;

import java.util.ArrayList;

import Bean.Moviebean;
import Utils.Glide_Utils;

/**
 * Created by Joker on 2016/12/28.
 */

public class search_RecAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<Moviebean> moviebeens ;
    private OnitemClick onitemClick ;

    public interface OnitemClick{
        void onClick(View view, int position);
    }
    public void setOnitemClick(OnitemClick onitemClick){
        this.onitemClick = onitemClick ;
    }
    public search_RecAdapter(Context mContext , ArrayList<Moviebean> moviebeens){
        this.mContext = mContext ;
        this.moviebeens = moviebeens ;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Normal_Holder normal_holder = new Normal_Holder(LayoutInflater.from(mContext).inflate(R.layout.search_item, parent, false));

        return normal_holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Normal_Holder normal_holder = (Normal_Holder) holder;
        normal_holder.search_txt.setText(moviebeens.get(position).getTitle());
        Glide_Utils.SetGlide(mContext,moviebeens.get(position).getImgurl(),
                normal_holder.search_img);
        normal_holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onitemClick.onClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviebeens.size();
    }

    class Normal_Holder extends RecyclerView.ViewHolder{
        ImageView search_img ;
        CardView cardView ;
        TextView search_txt;
        public Normal_Holder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.search_Card);
            search_img = (ImageView) itemView.findViewById(R.id.search_img);
            search_txt = (TextView) itemView.findViewById(R.id.search_txt);
        }
    }
}
