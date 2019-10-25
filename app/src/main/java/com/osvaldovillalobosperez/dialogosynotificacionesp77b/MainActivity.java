package com.osvaldovillalobosperez.dialogosynotificacionesp77b;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    String[] frutas = new String[]{"Sandia", "Mango", "Fresa", "Melon"};
    String[] generos_musicales = new String[]{"Rock", "Rap", "Clásica", "Pop", "Jazz"};
    boolean[] check_list = new boolean[]{true, false, true, false, true};
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnInfo_click(View view) {
        AlertDialog dialog =
                new AlertDialog.Builder(this)
                        .setTitle("Dialogo informativo")
                        .setMessage("Deseas cerrar al APP")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Esta bien", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("Cancenlar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();

        dialog.show();
    }

    public void btnList_click(View view) {
        AlertDialog dialog =
                new AlertDialog.Builder(this)
                        .setTitle("Dialogo de listas")
                        .setItems(frutas, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,
                                        frutas[which], Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .create();

        dialog.show();
    }

    public void btnListCheckBox_click(View view) {
        AlertDialog dialog =
                new AlertDialog.Builder(this)
                        .setTitle("Dialogo de listas")
                        .setMultiChoiceItems(generos_musicales,
                                check_list,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which, boolean isChecked) {

                                        Toast.makeText(MainActivity.this,
                                                generos_musicales[which] +
                                                        (isChecked ? " Verificado" : " No verificado"),
                                                Toast.LENGTH_SHORT
                                        ).show();


                                    }
                                })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();

        dialog.show();
    }

    public void btnTimePicker_click(View view) {
        DialogFragment dialogFragment = new MiTimePickerFragment();

        dialogFragment.show(getSupportFragmentManager(), "TimePickerDialog");
    }

    public void btnDatePicker_click(View view) {
        Calendar fecha = Calendar.getInstance();
        fecha.get(Calendar.YEAR);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                /*Log.d("DATEPICKER", "Fecha seleccionada: "
                        + String.valueOf(dayOfMonth) + "/" +
                        String.valueOf(month) + "/" + String.valueOf(year));*/
                Toast.makeText(MainActivity.this,
                        "La fecha seleccionada fue: " +
                                year + "/" +
                                (month + 1) + "/" +
                                dayOfMonth,
                        Toast.LENGTH_SHORT).show();
            }
        }, fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    public void btnNoficacionBarra_click(View view) {
        Notification notificacion = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Notificacion titulo")
                .setContentText("Mi notitcacion cuerpo")
                .build();

        NotificationManagerCompat nm = NotificationManagerCompat.from(this);

        nm.notify(1001, notificacion);
    }

    public void btnNS_click(View view) {
        Intent intent = new Intent("net.ivanvega.audioenandroidcurso.CAPTURARAUDIO");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, "CHANNEL_ID")
                        .setSmallIcon(android.R.drawable.ic_notification_overlay)
                        .setContentTitle("Título de la notificación")
                        .setContentText("Cuerpo y contenido de la notificación")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(1001, mBuilder.build());
    }

    public void btnAL_click(View view) {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent receiver = new Intent(this, PlanificarAlarma.class);
        PendingIntent pi =
                PendingIntent.getBroadcast(this, 0, receiver, 0);

        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                5000,
                1000, pi);
    }
}
