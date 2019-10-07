package com.app.theleague78.adapter;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.app.theleague78.R;
import com.app.theleague78.entity.AllLeaguesResponse;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;


public class AllLeaguesRecyclerAdapter extends RecyclerView.Adapter<AllLeaguesRecyclerAdapter.ViewHolder> {
    //   private List<CelebrityRequestsEntity> list;
    private Context mContext;
    private List<AllLeaguesResponse.LeagueResponse> list;
    private ArrayList<AllLeaguesResponse.LeagueResponse> arraylist;
    private static final int SHARED_IMAGE_QUALITY = 100;
    private static final String SHARED_DIRECTORY = "sharing";
    private static final String SHARED_IMAGE_FILE = "app_logo.png";
    private static final String FILE_PROVIDER_AUTHORITY = "com.app.celebvm.fileprovider";

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            name = (TextView) v.findViewById(R.id.leagueName);

        }
    }

    public AllLeaguesRecyclerAdapter(Context context, List<AllLeaguesResponse.LeagueResponse> list) {
        this.mContext = context;
        this.list = list;
        this.arraylist = new ArrayList<AllLeaguesResponse.LeagueResponse>();
        this.arraylist.addAll(list);
    }

    @Override
    public AllLeaguesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                        int viewType) {

        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.list_item_allleagues, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final AllLeaguesResponse.LeagueResponse allLeaguesResponse = list.get(position);



        holder.name.setText(allLeaguesResponse.getLeagueName());




        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
