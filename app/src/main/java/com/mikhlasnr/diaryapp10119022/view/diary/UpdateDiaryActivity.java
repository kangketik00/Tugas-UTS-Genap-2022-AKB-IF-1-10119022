package com.mikhlasnr.diaryapp10119022.view.diary;
/*
    nim                 : 10119022
    nama                : Muhammad Ikhlas Naufalsyah Ranau
    kelas               : IF-1
*/
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mikhlasnr.diaryapp10119022.MainActivity;
import com.mikhlasnr.diaryapp10119022.R;
import com.mikhlasnr.diaryapp10119022.helper.DatabaseHelper;
import com.mikhlasnr.diaryapp10119022.models.DiaryModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateDiaryActivity extends AppCompatActivity {
    EditText title_input,category_input, note_input;
    Button update_button, delete_button;
    String date_input;
    DiaryModel diaryModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_diary);

        title_input = findViewById(R.id.title_input_update);
        category_input = findViewById(R.id.category_input_update);
        note_input = findViewById(R.id.note_input_update);
        date_input = getDateNow();
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(diaryModel.getTitle());
        }

        update_button.setOnClickListener(view -> {
            DatabaseHelper db =new DatabaseHelper(UpdateDiaryActivity.this);
            diaryModel = new DiaryModel(
                    diaryModel.getId(),
                    title_input.getText().toString().trim(),
                    category_input.getText().toString().trim(),
                    note_input.getText().toString().trim(),
                    date_input.trim()
            );

            db.updateDiary(diaryModel);

            Intent intent = new Intent(UpdateDiaryActivity.this, MainActivity.class);
//            buat clear activity sebelumnya
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    public String getDateNow(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")
                && getIntent().hasExtra("title")
                && getIntent().hasExtra("category")
                && getIntent().hasExtra("note")
                && getIntent().hasExtra("date")) {

            diaryModel = new DiaryModel(
                    getIntent().getStringExtra("id"),
                    getIntent().getStringExtra("title"),
                    getIntent().getStringExtra("category"),
                    getIntent().getStringExtra("note"),
                    getIntent().getStringExtra("date")
            );

            title_input.setText(diaryModel.getTitle());
            category_input.setText(diaryModel.getCategory());
            note_input.setText(diaryModel.getNote());

            Log.d("stev", diaryModel.getTitle() + " " + diaryModel.getCategory() + " " + diaryModel.getNote());
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + diaryModel.getTitle() + " ?");
        builder.setMessage("Are you sure you want to delete " + diaryModel.getTitle() + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper db = new DatabaseHelper(UpdateDiaryActivity.this);
                db.deleteOne(String.valueOf(diaryModel.getId()));
                finish();

                Intent intent = new Intent(UpdateDiaryActivity.this, MainActivity.class);
//            buat clear activity sebelumnya
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}