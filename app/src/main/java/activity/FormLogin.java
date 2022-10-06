package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rlds.agendacontatos.R;

public class FormLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        getSupportActionBar().hide();
    }
}