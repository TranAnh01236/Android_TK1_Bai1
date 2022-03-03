package com.example.thuongky_bai1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int manghinhbai[]={
            R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,
            R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,
            R.drawable.cj,R.drawable.cq,R.drawable.ck,
            R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,
            R.drawable.d6,R.drawable.d7,R.drawable.d8,R.drawable.d9,R.drawable.d10,
            R.drawable.dj,R.drawable.dq,R.drawable.dk,
            R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,
            R.drawable.h6,R.drawable.h7,R.drawable.h8,R.drawable.h9,R.drawable.h10,
            R.drawable.hj,R.drawable.hq,R.drawable.hk,
            R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,
            R.drawable.s6,R.drawable.s7,R.drawable.s8,R.drawable.s9,R.drawable.s10,
            R.drawable.sj,R.drawable.sq,R.drawable.sk};

    private Button btnRutBai;
    private ImageView imv1, imv2, imv3, imv4, imv5, imv6;
    private TextView tvDiem_Nguoi, tvKetqua_Nguoi, tvDiem_May, tvKetQua_May, tvVan;

    int diemNguoi = 0, diemMay = 0;
    int van = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        imv1 = findViewById(R.id.imageView1);
        imv2 = findViewById(R.id.imageView2);
        imv3 = findViewById(R.id.imageView3);
        imv4 = findViewById(R.id.imageView4);
        imv5 = findViewById(R.id.imageView5);
        imv6 = findViewById(R.id.imageView6);

        tvDiem_May = findViewById(R.id.tv_Diem_May);
        tvKetQua_May = findViewById(R.id.tv_KetQua_May);
        tvDiem_Nguoi = findViewById(R.id.tv_Diem_Nguoi);
        tvKetqua_Nguoi = findViewById(R.id.tv_KetQua_Nguoi);

        tvVan = findViewById(R.id.tv_Van);

        btnRutBai = findViewById(R.id.bt_RutBai);

        btnRutBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] saulabai = laySauSoNgauNhien(0,51);
                int[] baso_Nguoi = new int[3];
                int[] baso_May = new int[3];
                for (int i = 0; i < 3; i++)
                    baso_Nguoi[i] = saulabai[i];
                for (int i = 0; i < 3; i++)
                    baso_May[i] = saulabai[i+3];

                imv1.setImageResource(manghinhbai[baso_Nguoi[0]]);
                imv2.setImageResource(manghinhbai[baso_Nguoi[1]]);
                imv3.setImageResource(manghinhbai[baso_Nguoi[2]]);
                tvKetqua_Nguoi.setText(tinhKetQua(baso_Nguoi));

                imv4.setImageResource(manghinhbai[baso_May[0]]);
                imv5.setImageResource(manghinhbai[baso_May[1]]);
                imv6.setImageResource(manghinhbai[baso_May[2]]);
                tvKetQua_May.setText(tinhKetQua(baso_May));

                int diemN = tinhSoNut(baso_Nguoi);
                int diemM = tinhSoNut(baso_May);

                tinhDiem(diemN, diemM);

                if(van == 10){
                    Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                    intent.putExtra("DiemNguoi", diemNguoi);
                    intent.putExtra("DiemMay", diemMay);
                    startActivityForResult(intent, 999);
                }

            }
        });
    }
    //---------------------------------------------------------------------
    private int[] laySauSoNgauNhien(int min, int max){
        int[] baso = new int[6];
        int i = 0;
        baso[i++] = random(min, max);
        do{
            int k = random(min, max);
            if(!kiemTraTrung(k, baso))
                baso[i++] = k;
        }while(i<6);
        return baso;
    }
    //---------------------------------------------------------------------
    private boolean kiemTraTrung(int k, int[]arr){
        for(int i = 0; i < arr.length; i++)
            if(arr[i] == k)
                return true;
        return false;
    }
    //---------------------------------------------------------------------
    private int random(int min, int max){
        return min + (int)(Math.random()*((max-min) + 1));
    }
    //---------------------------------------------------------------------
    private  int tinhSoTay(int[]arr){
        int k = 0;
        for(int i = 0; i < arr.length; i++)
            if(arr[i] % 13 >=10 && arr[i] >= 10)
                k++;
        return k;
    }
    //---------------------------------------------------------------------
    private String tinhKetQua(int[]arr){
        String ketqua = "";
        if(tinhSoTay(arr) == 3)
            ketqua = "3 tây";
        else{
            int tong = 0;
            for(int i =0; i< arr.length; i++)
                if(arr[i] % 13 < 10)
                    tong += arr[i] % 13 + 1;
            if(tong % 10 == 0)
                ketqua = "bù, số tây là " + tinhSoTay(arr);
            else
                ketqua = (tong%10) + " nút, số tây là " + tinhSoTay(arr);

        }
        return ketqua;
    }

    //-------------------------------------------------------------------
    private int tinhSoNut(int[]arr){
        int soNut = 0;
        if(tinhSoTay(arr) == 3)
            soNut = 10;
        else{
            int tong = 0;
            for(int i =0; i< arr.length; i++)
                if(arr[i] % 13 < 10)
                    tong += arr[i] % 13 + 1;
            if(tong % 10 == 0)
                soNut = 0;
            else
                soNut = (tong%10);
        }
        return soNut;
    }
    private void tinhDiem(int nguoi, int may) {
        if (nguoi > may){
            Toast toast = Toast.makeText(this,"Người chơi đã thắng !!!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.TOP, 0, 0);
            toast.show();
            diemNguoi++;
            tvDiem_Nguoi.setText("Điểm: " + diemNguoi);
        }else if(nguoi < may){
            Toast toast = Toast.makeText(this,"Máy đã thắng !!!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.TOP, 0, 0);
            toast.show();
            diemMay++;
            tvDiem_May.setText("Điểm: " + diemMay);
        }else{
            Toast toast = Toast.makeText(this,"Hòa !!!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.TOP, 0, 0);
            toast.show();
        }
        van++;
        tvVan.setText("Ván: " + van);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999 && resultCode == RESULT_OK){
            van = 0;
            diemNguoi = 0;
            diemMay = 0;

            tvVan.setText("Ván: ");
            tvDiem_Nguoi.setText("Điểm: 0");
            tvDiem_May.setText("Điểm: 0");

            tvKetqua_Nguoi.setText("");
            tvKetQua_May.setText("");

            imv1.setImageResource(0);
            imv2.setImageResource(0);
            imv3.setImageResource(0);
            imv4.setImageResource(0);
            imv5.setImageResource(0);
            imv6.setImageResource(0);
        }
    }
}