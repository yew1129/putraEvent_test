package com.example.myapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarTest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_test);

        MaterialButton pick_time_btn = findViewById(R.id.pick_time_btn);
        MaterialButton pick_date_btn = findViewById(R.id.pick_date_btn);
        TextView show_time = findViewById(R.id.time);
        TextView show_date = findViewById(R.id.date);

        pick_time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(12)
                        .setMinute(0)
                        .setInputMode(com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK)
                        .setTitleText("Pick Time")
                        // Set the theme
                        .setTheme(R.style.TimePickerTheme)
                        .build();
                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show_time.setText(MessageFormat.format("Time: {0}:{1}",
                                String.format(Locale.getDefault(), "%02d", timePicker.getHour()),
                                String.format(Locale.getDefault(), "%02d", timePicker.getMinute())));
                    }
                });
                timePicker.show(getSupportFragmentManager(), "TimePicker");
            }
        });

        pick_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Pick Date");
                // Set the initial selection to today
                builder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());
                // Restrict past dates
                CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
                constraintsBuilder.setStart(MaterialDatePicker.todayInUtcMilliseconds());
                builder.setCalendarConstraints(constraintsBuilder.build());
                // Set the theme
                builder.setTheme(R.style.DatePickerTheme);

                MaterialDatePicker<Long> materialDatePicker = builder.build();
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
                materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(selection);
                    show_date.setText(MessageFormat.format("Date: {0}/{1}/{2}",
                            String.format(Locale.getDefault(), "%02d", calendar.get(Calendar.DAY_OF_MONTH)),
                            String.format(Locale.getDefault(), "%02d", calendar.get(Calendar.MONTH) + 1),
                            String.format(Locale.getDefault(), "%02d", calendar.get(Calendar.YEAR))));
                });

            }
        });
    }




}