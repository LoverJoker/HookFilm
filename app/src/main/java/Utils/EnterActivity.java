package Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.joker.hookfilm.current_Activity;

import java.util.ArrayList;

import Adapter.Main_RecycleView_Adapter;
import Bean.Moviebean;


/**
 * Created by Joker on 2016/12/21.
 */

public class EnterActivity {

    public void enterCurrent(Main_RecycleView_Adapter film_recAdapter , final ArrayList<Moviebean> moviebeens , final Activity mActivity){
        film_recAdapter.setOnFilmClick(new Main_RecycleView_Adapter.onFilmClick() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(mActivity, current_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("moviebeens",moviebeens);
                bundle.putInt("position",position);
                intent.putExtras(bundle);
                mActivity.startActivity(intent);
            }
        });
    }
}
