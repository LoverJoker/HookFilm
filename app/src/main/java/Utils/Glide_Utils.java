package Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.joker.hookfilm.R;

/**
 * Created by Joker on 2016/12/14.
 */

public class Glide_Utils {

    public static final void SetGlide(Context context , String url , ImageView imageView){
        Glide.with(context)
                .load(url)
                .crossFade()
                .placeholder(R.drawable.error)
                .thumbnail(0.3f)
                .error(R.drawable.error)
                .into(imageView);
    }

}
