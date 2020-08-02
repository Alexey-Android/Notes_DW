package com.example.notes.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class EnterActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String passwordFileName = "password";

    private Button button_del, button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private TextView tv2, tv4, tv6, tv8, tvWrongPin;

    StringBuffer sb = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        init();
    }

    private void init() {

        String savedPassword = readFromFile(passwordFileName);
        if (savedPassword.length() != 4) {
            Intent intent = new Intent(getApplicationContext(), NewPinActivity.class);
            startActivity(intent);
        }

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

        tv2 = findViewById(R.id.tv_2);
        tv4 = findViewById(R.id.tv_4);
        tv6 = findViewById(R.id.tv_6);
        tv8 = findViewById(R.id.tv_8);
        tvWrongPin = findViewById(R.id.tv_wrong_pin);

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
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn0:
                sb.append(button0.getText().toString());
                checkPin();
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn1:
                sb.append(button1.getText().toString());
                checkPin();
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                sb.append(button2.getText().toString());
                checkPin();
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn3:
                sb.append(button3.getText().toString());
                checkPin();
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn4:
                sb.append(button4.getText().toString());
                checkPin();
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn5:
                sb.append(button5.getText().toString());
                checkPin();
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn6:
                sb.append(button6.getText().toString());
                checkPin();
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn7:
                sb.append(button7.getText().toString());
                checkPin();
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn8:
                sb.append(button8.getText().toString());
                checkPin();
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn9:
                sb.append(button9.getText().toString());
                checkPin();
                Toast.makeText(EnterActivity.this, "Длина " + sb.length(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_del:
                //sb.append(button_del.getText().toString());
                sb.deleteCharAt((sb.length() - 1));
                checkPin();
                Toast.makeText(EnterActivity.this, "Длина " + sb.length() + "Значение " + sb, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void checkPin() {
        switch (sb.length()) {
            case 0:
                tv2.setBackgroundColor(Color.GRAY);
                tv4.setBackgroundColor(Color.GRAY);
                tv6.setBackgroundColor(Color.GRAY);
                tv8.setBackgroundColor(Color.GRAY);
                break;
            case 1:
                tv2.setBackgroundColor(Color.YELLOW);
                tv4.setBackgroundColor(Color.GRAY);
                tv6.setBackgroundColor(Color.GRAY);
                tv8.setBackgroundColor(Color.GRAY);
                break;
            case 2:

                tv2.setBackgroundColor(Color.YELLOW);
                tv4.setBackgroundColor(Color.YELLOW);
                tv6.setBackgroundColor(Color.GRAY);
                tv8.setBackgroundColor(Color.GRAY);
                break;
            case 3:
                tv2.setBackgroundColor(Color.YELLOW);
                tv4.setBackgroundColor(Color.YELLOW);
                tv6.setBackgroundColor(Color.YELLOW);
                tv8.setBackgroundColor(Color.GRAY);
                break;
            case 4:
                tv2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                tv4.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                tv6.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                tv8.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                String savedPassword = readFromFile(passwordFileName);
                String newPassword = sb.toString();
                if (newPassword.equals(savedPassword)) {
                    Toast.makeText(EnterActivity.this, "Пароль правильный", Toast.LENGTH_SHORT).show();
                    tvWrongPin.setText("");

                    tv2.setBackgroundColor(Color.GRAY);
                    tv4.setBackgroundColor(Color.GRAY);
                    tv6.setBackgroundColor(Color.GRAY);
                    tv8.setBackgroundColor(Color.GRAY);

                    sb.delete(0, 4);

                    Intent intent = new Intent(getApplicationContext(), ListNotesActivity.class);
                    startActivity(intent);
                } else {
                    tvWrongPin.setText("ПИН введен неверно, попробуйте еще раз " + savedPassword + newPassword);
                    tvWrongPin.setTextColor(Color.RED);

                    tv2.setBackgroundColor(Color.GRAY);
                    tv4.setBackgroundColor(Color.GRAY);
                    tv6.setBackgroundColor(Color.GRAY);
                    tv8.setBackgroundColor(Color.GRAY);

                    sb.delete(0, 4);
                }
                break;
        }
    }

    private String readFromFile(String fileName) {
        // Получим входные байты из файла которых нужно прочесть.
        // Декодируем байты в символы
        // Читаем данные из потока ввода, буферизуя символы так, чтобы обеспечить эффективную запись отдельных символов.
        StringBuilder sb2 = new StringBuilder();
        try (FileInputStream fis = openFileInput(fileName);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr);
        ) {
            String s;
            while ((s = br.readLine()) != null) {
                sb2.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb2.toString();
    }
}
