package com.example.cargarimageninternet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<imagen> personajes = new ArrayList<>();
    RecyclerView recyclerView;
    PersonajesAdapter personajesAdapter;
    Button btnshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargar();
        btnshow = findViewById(R.id.btnmostrar);
    }

        public void btnshow_onclick(View v){
            recyclerView = findViewById(R.id.listRecyclerView);
            personajesAdapter = new PersonajesAdapter(personajes);
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(personajesAdapter);
        }

        public void cargar(){
            String urlpersonajes = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
            StringRequest request = new StringRequest(Request.Method.GET, urlpersonajes,
                    response ->  {
                            try {
                                JSONObject object = new JSONObject(response);
                                JSONArray array = object.getJSONArray("heroes");
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject heroeObj = array.getJSONObject(i);
                                    String objeto = "";
                                    imagen heroe = new imagen(heroeObj.getString("name"), heroeObj.getString("imageurl"));
                                    personajes.add(heroe);
                                    objeto =
                                            heroeObj.getString("name");
                                    objeto += "\n" + heroeObj.getString("imageurl");

                                    Toast.makeText(this,"objeto" + objeto,Toast.LENGTH_LONG).show();
                                    Log.d("objeto",objeto);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                    }, error -> {
                    //Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this,"NO HAY CONEXIÓN",Toast.LENGTH_LONG).show();
            });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);
        }
}