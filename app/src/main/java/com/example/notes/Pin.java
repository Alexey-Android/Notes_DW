package com.example.notes;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Pin implements KeyStore{
    private Context context;
    public Pin(Context context) {
        this.context = context;
    }

    public boolean writeToFile(String str, String fileName) {
        // Создадим файл и откроем поток для записи данных
        // Обеспечим переход символьных потока данных к байтовым потокам.
        // Запишем текст в поток вывода данных, буферизуя символы так, чтобы обеспечить эффективную запись отдельных символов.
        // Осуществим запись данных
        // Закроем поток
        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
             OutputStreamWriter osw = new OutputStreamWriter(fos);
             BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write(str);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String readFromFile(String fileName) {
        // Получим входные байты из файла которых нужно прочесть.
        // Декодируем байты в символы
        // Читаем данные из потока ввода, буферизуя символы так, чтобы обеспечить эффективную запись отдельных символов.
        StringBuilder sb2 = new StringBuilder();
        try (FileInputStream fis = context.openFileInput(fileName);
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