package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListSearch extends AppCompatActivity {

    EditText etName, etMobile, etEmail;
    SearchView searchView;
    Button btnUpload;
    ListView listView;
    LottieAnimationView progressBar;
    HashMap <String,String> hashMap;
    ArrayList <HashMap <String,String>> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_search);


        etName = findViewById(R.id.etName);
        etMobile = findViewById(R.id.etMobile);
        etEmail = findViewById(R.id.etEmail);
        btnUpload = findViewById(R.id.btnUpload);
        progressBar=findViewById(R.id.progressBar);
        listView = findViewById(R.id.listView);
        loadData();

        searchView = (SearchView) findViewById(R.id.searchView);



        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mysql Database Store");
        toolbar.setTitleTextColor(Color.WHITE);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // don't forget click listener for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        //-------------------------------------------------------------------------------------------------

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String url = "https://www.kartikworld.com/apps/phpMyAdmin/my_data.php?a=" + name +
                        "&b=" + mobile + "&c=" + email;
                //checking validity
                if (name.isEmpty()) {
                    etName.setError("আগে লিখুন");
                    etName.requestFocus();
                    return;
                }

                if (mobile.isEmpty()) {
                    etMobile.setError("আগে লিখুন");
                    etMobile.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    etEmail.setError("আগে লিখুন");
                    etEmail.requestFocus();
                    return;
                } else {
                    progressBar.setVisibility(View.VISIBLE);

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressBar.setVisibility(View.GONE);

                            new AlertDialog.Builder(ListSearch.this)
                                    .setTitle("It's working very good")
                                    .setMessage(response)
                                    .setPositiveButton("OK",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(ListSearch.this, "Ok,Upload Done\nServer Received", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                    .show();

                            loadData();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressBar.setVisibility(View.GONE);
                            //NameMobileEmail.super.onBackPressed();

                        }

                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(ListSearch.this);
                    requestQueue.add(stringRequest);

                    // String Add and Clear Text
                    etName.getText().clear();
                    etMobile.getText().clear();
                    etEmail.getText().clear();

                }
            }
        });
    }

    //************************************

    public class MyAdapter extends BaseAdapter {

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
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.item_data, null);
            TextView tvId = myView.findViewById(R.id.tvId);
            TextView tvName = myView.findViewById(R.id.tvName);
            TextView tvMobile = myView.findViewById(R.id.tvMobile);
            TextView tvEmail = myView.findViewById(R.id.tvEmail);
            Button btUpdate = myView.findViewById(R.id.btUpdate);
            Button btDelete = myView.findViewById(R.id.btDelete);

            hashMap = arrayList.get(position);
            String id = hashMap.get("id");
            String name = hashMap.get("name");
            String mobile = hashMap.get("mobile");
            String email = hashMap.get("email");

            tvId.setText(id);
            tvName.setText(name);
            tvMobile.setText(mobile);
            tvEmail.setText(email);

            //++++++++++++++++++++++++++++
            btUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name = etName.getText().toString();
                    String mobile = etMobile.getText().toString();
                    String email = etEmail.getText().toString();
                    String url = "https://kartikworld.com/apps/phpMyAdmin/id_update.php?id="+ id+"&name="+name+"&mobile="+mobile
                            +"&email="+email;

                    progressBar.setVisibility(View.VISIBLE);

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressBar.setVisibility(View.GONE);

                            new AlertDialog.Builder(ListSearch.this)
                                    .setTitle("It's working very nice")
                                    .setMessage(response)
                                    .setPositiveButton("OK",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(ListSearch.this, "Update & Save Success", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                    .show();

                            loadData();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressBar.setVisibility(View.VISIBLE);
                            //NameMobileEmail.super.onBackPressed();

                        }

                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(ListSearch.this);
                    requestQueue.add(request);

                    // String Add and Clear Text
                    etName.getText().clear();
                    etMobile.getText().clear();
                    etEmail.getText().clear();


                }
            });
            //+++++++++++++++++++++++++++++
            btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(ListSearch.this)
                            .setTitle("Are you Confirm to Delete")
                            .setMessage("Select Yes to Confirm.")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {


                                            String url = "https://kartikworld.com/apps/phpMyAdmin/id_delete.php?id="+ id;

                                            progressBar.setVisibility(View.VISIBLE);

                                            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    progressBar.setVisibility(View.GONE);

                                                    Toast.makeText(ListSearch.this, "Successfully Delete", Toast.LENGTH_SHORT).show();

                                                    loadData();

                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    progressBar.setVisibility(View.VISIBLE);
                                                    //NameMobileEmail.super.onBackPressed();

                                                }

                                            });

                                            RequestQueue requestQueue = Volley.newRequestQueue(ListSearch.this);
                                            requestQueue.add(request);

                                        }
                                    })

                            .show();


                }
            });



            return myView;

        }
    }
    //+++++++++++++++++++++++++++++++++++++++
    public void loadData(){
        arrayList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(ListSearch.this);

        progressBar.setVisibility(View.VISIBLE);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://www.kartikworld.com/apps/phpMyAdmin/view.php",
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                for (int x = 0; x<response.length();x++){

                    try {
                        JSONObject jsonObject = response.getJSONObject(x);

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String mobile = jsonObject.getString("mobile");
                        String email = jsonObject.getString("email");

                        hashMap=new HashMap<>();
                        hashMap.put("id",id);
                        hashMap.put("name",name);
                        hashMap.put("mobile",mobile);
                        hashMap.put("email",email);
                        arrayList.add(hashMap);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //***************************
                if (arrayList.size()>0){
                    ListSearch.MyAdapter myAdapter = new ListSearch.MyAdapter();
                    listView.setAdapter(myAdapter);

                    //====
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            myAdapter.notifyDataSetChanged();
                            return false;
                        }
                    });
                    //****
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ServerResponseCheck", error.toString());

            }
        });
        requestQueue.add(jsonArrayRequest);

    }



}