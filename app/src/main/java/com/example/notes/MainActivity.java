package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    public final static String passwordFileName = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pin);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setTitle("Настройки");

        init();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_pin_menu, menu);
        return true;
    }*/

    private void init() {

        Button btnSave = findViewById(R.id.save);
        final ImageButton ibtnEye = findViewById(R.id.ib_eye);
        final boolean[] flag = {true};

        final EditText newPin = findViewById(R.id.et_new_pin);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String password = newPin.getText().toString();
                if (password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Введите новый пин", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isPasswordWritten = writeToFile(password, passwordFileName);
                if (isPasswordWritten) {
                    Toast.makeText(MainActivity.this, "Пин успешно записан", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Пин не был записан", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ibtnEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // меняем изображение на кнопке
                if (flag[0]) {
                    ibtnEye.setImageResource(R.drawable.ic_baseline_visibility_24);
                    // показываем пин
                    newPin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // возвращаем первую картинку
                    ibtnEye.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    // скрываем пин
                    newPin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                flag[0] = !flag[0];
            }
        });

    }

    private boolean writeToFile(String str, String fileName) {
        // Создадим файл и откроем поток для записи данных
        // Обеспечим переход символьных потока данных к байтовым потокам.
        // Запишем текст в поток вывода данных, буферизуя символы так, чтобы обеспечить эффективную запись отдельных символов.
        // Осуществим запись данных
        // Закроем поток
        try (FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
             OutputStreamWriter osw = new OutputStreamWriter(fos);
             BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write(str);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(getApplicationContext(), NewNoteActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                //finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}