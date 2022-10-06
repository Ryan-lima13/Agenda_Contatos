package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.firebase.ktx.Firebase;
import com.rlds.agendacontatos.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                abrirTelaLogin();

            }
        },3000);
    }
    private  void abrirTelaLogin(){
        Intent intent = new Intent(MainActivity.this, FormLogin.class);
        startActivity(intent);
    }
}