package com.example.thuongky_bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TextView tvDiemNguoi = findViewById(R.id.tvDiemNguoi);
        TextView tvKQ = findViewById(R.id.tvKQ);
        TextView tvDiemMay = findViewById(R.id.tvDiemMay);
        Button btnChoiLai = findViewById(R.id.btChoiLai);
        LinearLayout layout = findViewById(R.id.layoutLi);

        int diemN = getIntent().getExtras().getInt("DiemNguoi");
        int diemM = getIntent().getExtras().getInt("DiemMay");

        if(diemN > diemM){
            tvKQ.setText("Bạn đã thắng :))");
            tvDiemNguoi.setText("Điểm của bạn là: "+diemN);
            tvDiemMay.setText("Điểm của máy là: " + diemM);
        }else if(diemN < diemM){
            tvKQ.setText("Bạn đã thua :((");
            tvDiemNguoi.setText("Điểm của bạn là: "+diemN);
            tvDiemMay.setText("Điểm của máy là: " + diemM);
        }else{
            tvKQ.setText("Hòa !!!");
            tvDiemNguoi.setText("Điểm của bạn là: "+diemN);
            tvDiemMay.setText("Điểm của máy là: " + diemM);
        }

        btnChoiLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}