package com.mikhlasnr.diaryapp10119022.view.diary;
/*
    nim                 : 10119022
    nama                : Muhammad Ikhlas Naufalsyah Ranau
    kelas               : IF-1
*/
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.mikhlasnr.diaryapp10119022.MainActivity;
import com.mikhlasnr.diaryapp10119022.R;
import com.mikhlasnr.diaryapp10119022.helper.DatabaseHelper;
import com.mikhlasnr.diaryapp10119022.models.DiaryModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddDiaryActivity extends AppCompatActivity {
    EditText title_input,category_input, note_input;
    String date_input;
    Button addButton;


//    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);

        title_input = findViewById(R.id.title_input);
        category_input = findViewById(R.id.category_input);
        note_input = findViewById(R.id.note_input);
        date_input = getDateNow();

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(view -> {
            DatabaseHelper db = new DatabaseHelper(AddDiaryActivity.this);
            DiaryModel diary = new DiaryModel("id",
                    title_input.getText().toString().trim(),
                    category_input.getText().toString().trim(),
                    note_input.getText().toString().trim(),
                    date_input.trim());

            db.addDiary(diary);

            Intent intent = new Intent(AddDiaryActivity.this, MainActivity.class);
            // buat clear activity sebelumnya
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

    }

    public String getDateNow(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
}