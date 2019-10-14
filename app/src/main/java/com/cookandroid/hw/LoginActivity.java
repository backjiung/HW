package com.cookandroid.hw;
import android.content.Intent;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.FileInputStream;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button loginButton = (Button)findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_id = (EditText)findViewById(R.id.et_ID);
                String id = et_id.getText().toString();

                EditText et_pw = (EditText)findViewById(R.id.et_PW);
                String pw = et_pw.getText().toString();

                if(pw.equals(getPassword(id))){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }else{
                    if(getPassword(id) == null) {
                        Toast.makeText(getApplicationContext(), "아이디가 틀림.", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "비밀번호가 틀림.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        final Button signUpButton = (Button) findViewById(R.id.btn_signUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.cookandroid.hw.SignUpActivity.class);
                startActivity(intent);
            }
        });
    }


    public String getPassword(String id){
        String pass = null;

        try{
            FileInputStream fis = openFileInput(id);

            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            pass = reader.readLine();
        } catch (Exception e){
            e.printStackTrace();
        }
        return pass;
    }

}