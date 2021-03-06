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
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keteringapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ValuePestaActivity extends AppCompatActivity {

    TextView Vnama,Vnohp,Valamat,Vsppras,Vtanggal,Vpiltam,Vtotal;
//    private static final String ACTION_UPDATE_NOTIFICATION =
//            "com.android.example.notifyme.ACTION_UPDATE_NOTIFICATION";
//    private static final String PRIMARY_CHANNEL_ID =
//            "primary_notification_channel";
//    private static final int NOTIFICATION_ID = 0;
    private Button Next;
//    private NotificationManager mNotifyManager;
//    private NotificationReceiver mReceiver = new NotificationReceiver();
    private DatabaseReference databasegetpesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_pesta);

        databasegetpesta = FirebaseDatabase.getInstance().getReference("pempesta");

        Vnama = findViewById(R.id.vnama);
        Vnohp = findViewById(R.id.vwa);
        Valamat = findViewById(R.id.valamat);
        Vsppras = findViewById(R.id.vpilpras);
        Vpiltam = findViewById(R.id.vpiltam);
        Vtanggal = findViewById(R.id.vtanggal);
        Vtotal = findViewById(R.id.vtotal);

        Intent valuekue = getIntent();
        String vnama = valuekue.getStringExtra("extraNama");
        String vnohp = valuekue.getStringExtra("extraNohp");
        String valamat = valuekue.getStringExtra("extraAlamat");
        String vsppras = valuekue.getStringExtra("extraSppes");
        String vtanggal = valuekue.getStringExtra("extraTanggal");
        String vpiltam = valuekue.getStringExtra("extraPiltam");
        String vtotal = valuekue.getStringExtra("extraTotal");

        Vnama.setText(vnama);
        Vnohp.setText(vnohp);
        Valamat.setText(valamat);
        Vsppras.setText(vsppras);
        Vtanggal.setText(vtanggal);
        Vpiltam.setText(vpiltam);
        Vtotal.setText(vtotal);


//        createNotificationChannel();
//        registerReceiver(mReceiver,
//                new IntentFilter(ACTION_UPDATE_NOTIFICATION));
        Next = findViewById(R.id.next);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendNotification();
                String VelNama = Vnama.getText().toString();
                String VelAlamat = Valamat.getText().toString();
                Intent bktpesta = new Intent(ValuePestaActivity.this, BuktiTfPesta.class);
                bktpesta.putExtra("Velnama", VelNama);
                bktpesta.putExtra("Velalamat", VelAlamat);
                startActivity(bktpesta);
                getpesta();
            }
        });
    }
    private void getpesta(){
        String Nama = Vnama.getText().toString().trim();
        String Nohp = Vnohp.getText().toString().trim();
        String Alamat = Valamat.getText().toString().trim();
        String Sppras = Vsppras.getText().toString().trim();
        String Tanggal = Vtanggal.getText().toString().trim();
        String Cbtamb = Vpiltam.getText().toString().trim();
        int Total = Integer.parseInt(Vtotal.getText().toString().trim());

        if(!TextUtils.isEmpty(Nama)){

            String id = databasegetpesta.push().getKey();

            getpesta pesta = new getpesta(id,Nama,Nohp,Alamat,Sppras,Tanggal,Cbtamb,Total);

            databasegetpesta.child(id).setValue(pesta);

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
//        Intent notificationIntent = new Intent(this, ValuePestaActivity.class);
//        PendingIntent notificationPendingIntent = PendingIntent.getActivity
//                (this, NOTIFICATION_ID, notificationIntent,
//                        PendingIntent.FLAG_UPDATE_CURRENT);
//        NotificationCompat.Builder notifyBuilder = new NotificationCompat
//                .Builder(this, PRIMARY_CHANNEL_ID)
//                .setContentTitle(getString(R.string.notification_pesta))
//                .setContentText(getString(R.string.notification_text))
//                .setSmallIcon(R.drawable.ic_android)
//                .setAutoCancel(true).setContentIntent(notificationPendingIntent)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setDefaults(NotificationCompat.DEFAULT_ALL);
//        return notifyBuilder;
//    }
}
