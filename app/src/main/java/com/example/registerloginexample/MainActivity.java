package com.example.registerloginexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    public String id;
    private EditText et_id;
    private Button btn_rental;
    private Button btn_bluetooth;
    fragment_home homefragment;
    fragment_rain rainfragment;
    fragment_user userfragment;

    private final long finishtimeed = 1000;
    private long presstime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homefragment = new fragment_home();
        rainfragment = new fragment_rain();
        userfragment = new fragment_user();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homefragment).commit();
        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigationview);

        

        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homefragment).commit();

                        return true;
                    case R.id.rain:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, rainfragment).commit();
                        return true;
                    case R.id.btn_user:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, userfragment).commit();
                        return true;
                }
                return false;
            }

        });
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        homefragment.setArguments(bundle);

       /* btn_rental = findViewById(R.id.buttonRental);
        btn_bluetooth = findViewById(R.id.buttonBluetooth);*/



       /* btn_rental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RentalActivity.class);
                intent.putExtra("userID",userID);s
                startActivity(intent);
            }
        });


        btn_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,BluetoothActivity.class);
                startActivity(intent);
            }
        });*/
    }


    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - presstime;

        if (0 <= intervalTime && finishtimeed >= intervalTime)
        {
            finish();
        }
        else
        {
            presstime = tempTime;
            Toast.makeText(getApplicationContext(), "한 번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }

}