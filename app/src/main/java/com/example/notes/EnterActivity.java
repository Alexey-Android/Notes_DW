package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EnterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_del, button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    String code = "";
    StringBuffer sb = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        init();
    }

    private void init() {
        button0 = findViewById(R.id.btn0);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);
        button_del = findViewById(R.id.btn_del);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button_del.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enter_menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn0:
                sb.append(button0.getText().toString());
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn1:
                sb.append(button1.getText().toString());
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                sb.append(button2.getText().toString());
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn3:
                sb.append(button3.getText().toString());
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn4:
                sb.append(button4.getText().toString());
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn5:
                sb.append(button5.getText().toString());
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn6:
                sb.append(button6.getText().toString());
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn7:
                sb.append(button7.getText().toString());
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn8:
                sb.append(button8.getText().toString());
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn9:
                sb.append(button9.getText().toString());
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_del:
                sb.append(button_del.getText().toString());
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
