package com.osvaldovillalobosperez.dialogosynotificacionesp77b;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MiTimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    TimePickerDialog dialog;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        dialog = new TimePickerDialog(getActivity(), this, hour, minute, true);

        return dialog;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        Log.d("TIMEPICKER", "Hora seleccionada: " + String.valueOf(hourOfDay) + ":" +
                String.valueOf(minute));
    }
}
