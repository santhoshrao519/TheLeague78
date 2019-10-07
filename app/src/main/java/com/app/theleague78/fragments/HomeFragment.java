package com.app.theleague78.fragments;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.theleague78.LoginActivity;
import com.app.theleague78.MainActivity;
import com.app.theleague78.R;
import com.app.theleague78.adapter.AllLeaguesRecyclerAdapter;
import com.app.theleague78.entity.AllLeaguesResponse;
import com.app.theleague78.entity.LoginResponse;
import com.app.theleague78.util.ApiClient;
import com.app.theleague78.util.ApiInterface;
import com.app.theleague78.util.LoadingBox;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }
    private List<AllLeaguesResponse.LeagueResponse> arrayList = new ArrayList<AllLeaguesResponse.LeagueResponse>();

    private RecyclerView recyclerView;
    private TextView noData;
    AllLeaguesRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        noData=(TextView) view.findViewById(R.id.noData);

        recyclerView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        getAllLeagues();

        return view;
    }

    private void getAllLeagues() {

        LoadingBox.showLoadingDialog(getActivity());

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);



        Call<AllLeaguesResponse> call = apiService.getAllLeagues();

        call.enqueue(new retrofit2.Callback<AllLeaguesResponse>() {
            @Override
            public void onResponse(Call<AllLeaguesResponse> call, Response<AllLeaguesResponse> response) {

                LoadingBox.dismissLoadingDialog();
                if (response.isSuccessful()){
                    AllLeaguesResponse allLeaguesResponse = response.body();

                    arrayList=allLeaguesResponse.getData();
                    if(arrayList.size()>0){
                        noData.setVisibility(View.GONE);
                        adapter=new AllLeaguesRecyclerAdapter(getActivity(), arrayList);
                        recyclerView.setAdapter(adapter);
                    }
                    else {
                        noData.setVisibility(View.VISIBLE);
                    }


                }
                else{
                    LoadingBox.dismissLoadingDialog();
                    ResponseBody errorBody = response.errorBody();
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("errors").toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }



            }

            @Override
            public void onFailure(Call<AllLeaguesResponse> call, Throwable t) {
                LoadingBox.showLoadingDialog(getActivity());

            }

        });
    }
}