package com.example.notes.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class NewPinActivity extends AppCompatActivity {

    public final static String passwordFileName = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pin);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        init();
    }

    private void init() {

        Button btnSave = findViewById(R.id.save);
        final TextView fourSymbols = findViewById(R.id.tv_four_symbol);
        final ImageButton iBtnEye = findViewById(R.id.ib_eye);
        final boolean[] flag = {true};
        final EditText newPin = findViewById(R.id.et_new_pin);

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String password = newPin.getText().toString();
                if (password.length() != 4) {
                    fourSymbols.setTextColor(getResources().getColor(R.color.colorRed));
                    Toast.makeText(NewPinActivity.this, "Введите новый пин из 4-х цифр", Toast.LENGTH_SHORT).show();
                } else {
                    fourSymbols.setTextColor(getResources().getColor(R.color.colorGrey));
                    boolean isPasswordWritten = writeToFile(password, passwordFileName);
                    if (isPasswordWritten) {
                        String savedPassword = readFromFile(passwordFileName);
                        Toast.makeText(NewPinActivity.this, "Новый пин успешно записан " + savedPassword, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ListNotesActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
        iBtnEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // меняем изображение на кнопке
                if (flag[0]) {
                    iBtnEye.setImageResource(R.drawable.ic_baseline_visibility_24);
                    // показываем пин
                    newPin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // возвращаем первую картинку
                    iBtnEye.setImageResource(R.drawable.ic_baseline_visibility_off_24);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(getApplicationContext(), ListNotesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}