package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class Grid_Home extends AppCompatActivity {

    
    
    GridView mainGrid;
    HashMap<String, String> hashMap = new HashMap<>();
    ArrayList < HashMap<String,String>>arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_home);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++

        mainGrid = findViewById(R.id.mainGrid);
        createTable();
        MyAdapter customAdapter = new MyAdapter();
        mainGrid.setAdapter(customAdapter);


    }



    public class  MyAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater  inflater = (LayoutInflater) getSystemService (Context.LAYOUT_INFLATER_SERVICE);

            View mytView = inflater.inflate(R.layout.grid_item, parent,false);

            TextView tvTitle = mytView.findViewById(R.id.tvTitle);
            ImageView imgIcon = mytView.findViewById(R.id.imgIcon);
            LinearLayout layItem = mytView.findViewById(R.id.layItem);

            HashMap<String, String> hashMap = arrayList.get(position);
            String title = hashMap.get("title");
            String image = hashMap.get("image");

            tvTitle.setText(title);

            Picasso.get().load(image)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imgIcon);


            if (layItem != null) {

              layItem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                if (title != null && title.contains("SearchData")) {
                                Intent intent = new Intent(Grid_Home.this, MainActivity.class);
                                startActivity(intent);
                                }
                                //----------------------------------------------------------------

                                //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

                                else if (title != null && title.contains("My Info")) {
                                    Intent intent = new Intent(Grid_Home.this, NameMobileEmail.class);
                                    startActivity(intent);
                                }
                                //----------------------------------------------------------------


                                //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                else if (title != null && title.contains("List")) {
                                    Intent intent = new Intent(Grid_Home.this, ListSearch.class);
                                    startActivity(intent);
                                }
                                //----------------------------------------------------------------

                                //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                else {
                                    Intent intent = new Intent(Grid_Home.this, Grid_Home.class);
                                    startActivity(intent);
                                }
                                //----------------------------------------------------------------

                            }
                        });


            }

            return mytView;
        }
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private void createTable() {

        hashMap=new HashMap<>();
        hashMap.put("title","SearchData");
        hashMap.put("image","https://img.icons8.com/external-others-rabbit-jes/512/external-lineal-mobile-filled-outline-others-rabbit-jes-24.png");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","My Info");
        hashMap.put("image","https://img.icons8.com/color/512/broken-phone.png");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","List");
        hashMap.put("image","https://img.icons8.com/ios-glyphs/512/search.png");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","SearchData");
        hashMap.put("image","https://img.icons8.com/external-others-rabbit-jes/512/external-lineal-mobile-filled-outline-others-rabbit-jes-24.png");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","My Info");
        hashMap.put("image","https://img.icons8.com/color/512/broken-phone.png");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","List");
        hashMap.put("image","https://img.icons8.com/ios-glyphs/512/search.png");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","SearchData");
        hashMap.put("image","https://img.icons8.com/external-others-rabbit-jes/512/external-lineal-mobile-filled-outline-others-rabbit-jes-24.png");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","My Info");
        hashMap.put("image","https://img.icons8.com/color/512/broken-phone.png");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","List");
        hashMap.put("image","https://img.icons8.com/ios-glyphs/512/search.png");
        arrayList.add(hashMap);
        hashMap=new HashMap<>();
        hashMap.put("title","SearchData");
        hashMap.put("image","https://img.icons8.com/external-others-rabbit-jes/512/external-lineal-mobile-filled-outline-others-rabbit-jes-24.png");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","My Info");
        hashMap.put("image","https://img.icons8.com/color/512/broken-phone.png");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","List");
        hashMap.put("image","https://img.icons8.com/ios-glyphs/512/search.png");
        arrayList.add(hashMap);


    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}