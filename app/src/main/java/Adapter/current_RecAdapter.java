package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joker.hookfilm.R;

/**
 * Created by Joker on 2016/12/19.
 */

public class current_RecAdapter extends RecyclerView.Adapter {
    private Context mContext ;
    private String[] info ;

    public current_RecAdapter(Context mContext ,String[] info){
        this.mContext = mContext ;
        this.info = info ;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NormalHolder normalHolder = new NormalHolder(LayoutInflater.from(mContext).inflate(R.layout.current_item, parent, false));
        return normalHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NormalHolder normalHold  = (NormalHolder) holder;
        normalHold.textView.setText(info[position]);
    }

    @Override
    public int getItemCount() {
        return info.length;
    }
    class NormalHolder extends RecyclerView.ViewHolder{
        TextView textView ;
        public NormalHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.current_item_text);
        }
    }
}
