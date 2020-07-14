package com.example.keteringapp.ui.pesan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keteringapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ValueKueActivity extends AppCompatActivity {

    TextView Vnama,Vnohp,Valamat,Vpil,Vtanggal,Vporsi,Vpaket,Vhartot;
//    private static final String ACTION_UPDATE_NOTIFICATION =
//            "com.android.example.notifyme.ACTION_UPDATE_NOTIFICATION";
//    private static final String PRIMARY_CHANNEL_ID =
//            "primary_notification_channel";
//    private static final int NOTIFICATION_ID = 0;
    private Button Submit;
    private DatabaseReference databasegetpeskue;
//    private NotificationManager mNotifyManager;
//    private NotificationReceiver mReceiver = new NotificationReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_kue);

        databasegetpeskue = FirebaseDatabase.getInstance().getReference("pemkue");

        Vnama = findViewById(R.id.vnama);
        Vnohp = findViewById(R.id.vwa);
        Valamat = findViewById(R.id.valamat);
        Vpil = findViewById(R.id.vpil);
        Vtanggal = findViewById(R.id.vtanggal);
        Vpaket = findViewById(R.id.vpilcat);
        Vporsi = findViewById(R.id.vporsi);
        Vhartot = findViewById(R.id.vhartot);

        Intent valuekue = getIntent();
        String vnama = valuekue.getStringExtra("extraNama");
        String vnohp = valuekue.getStringExtra("extraNohp");
        String valamat = valuekue.getStringExtra("extraAlamat");
        String Vspkue = valuekue.getStringExtra("extraSpkue");
        String vtanggal = valuekue.getStringExtra("extraTanggal");
        String vpilkue = valuekue.getStringExtra("extraPilkue");
        String vporsi = valuekue.getStringExtra("extraPorsi");
        String vhartot = valuekue.getStringExtra("extraTotal");

        Vnama.setText(vnama);
        Vnohp.setText(vnohp);
        Valamat.setText(valamat);
        Vpil.setText(Vspkue);
        Vtanggal.setText(vtanggal);
        Vpaket.setText(vpilkue);
        Vporsi.setText(vporsi);
        Vhartot.setText(vhartot);

//        createNotificationChannel();
//        registerReceiver(mReceiver,
//                new IntentFilter(ACTION_UPDATE_NOTIFICATION));
        Submit = findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendNotification();
                getpeskue();
            }
        });
    }
    private void getpeskue(){
        String Nama = Vnama.getText().toString().trim();
        String Nohp = Vnohp.getText().toString().trim();
        String Alamat = Valamat.getText().toString().trim();
        String Sppil = Vpil.getText().toString().trim();
        String Tanggal = Vtanggal.getText().toString().trim();
        String Paket = Vpaket.getText().toString().trim();
        String Porsi = Vporsi.getText().toString().trim();
        int Total = Integer.parseInt(Vhartot.getText().toString().trim());

        if(!TextUtils.isEmpty(Nama)){

            String id = databasegetpeskue.push().getKey();

            getpeskue peskue = new getpeskue(id,Nama,Nohp,Alamat,Sppil,Tanggal,Paket,Porsi,Total);

            databasegetpeskue.child(id).setValue(peskue);

            Toast.makeText(this, "Pemesanan Vegetarian", Toast.LENGTH_LONG).show();


        }else {
            Toast.makeText(this,"Anda Belum Mengisi Pemesanan", Toast.LENGTH_LONG).show();
        }
    }
//    private class NotificationReceiver extends BroadcastReceiver {
//        public NotificationReceiver() {
//        }
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//        }
//    }
//    @Override
//    protected void onDestroy() {
//        unregisterReceiver(mReceiver);
//        super.onDestroy();
//    }
//
//    private void createNotificationChannel() {
//        mNotifyManager =
//                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        if (android.os.Build.VERSION.SDK_INT >=
//                android.os.Build.VERSION_CODES.O) {
//        }
//    }
//    private void sendNotification() {
//        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
//        PendingIntent updatePendingIntent = PendingIntent.getBroadcast(this,
//                NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);
//        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
//        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
//
//    }
//
//    private NotificationCompat.Builder getNotificationBuilder() {
//        Intent notificationIntent = new Intent(this, ValueKueActivity.class);
//        PendingIntent notificationPendingIntent = PendingIntent.getActivity
//                (this, NOTIFICATION_ID, notificationIntent,
//                        PendingIntent.FLAG_UPDATE_CURRENT);
//        NotificationCompat.Builder notifyBuilder = new NotificationCompat
//                .Builder(this, PRIMARY_CHANNEL_ID)
//                .setContentTitle(getString(R.string.notification_kue))
//                .setContentText(getString(R.string.notification_text))
//                .setSmallIcon(R.drawable.ic_android)
//                .setAutoCancel(true).setContentIntent(notificationPendingIntent)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setDefaults(NotificationCompat.DEFAULT_ALL);
//        return notifyBuilder;
//    }
}
