package com.ahmed.datepicker;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahmed.datepickerlibrary.DateRangeCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String PATTERN_DISPLAY = "EEE, dd MMM yyyy";

    private Button normalPickerButton, rangePickerButton;
    private TextView normalTV, startTV, endTV;
    private Dialog dateDialog;

    private Calendar normalCal, startRangeCal, endRangeCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        normalPickerButton = (Button) findViewById(R.id.normal_picker);
        rangePickerButton = (Button) findViewById(R.id.range_picker);

        normalTV = (TextView) findViewById(R.id.normal_text_view);
        startTV = (TextView) findViewById(R.id.start_tv);
        endTV = (TextView) findViewById(R.id.end_tv);

        initListener();
    }

    private void initListener(){
        normalPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(false);
            }
        });

        rangePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(true);
            }
        });
    }


    private void showDatePicker(final Boolean isRangePicker) {
        final View bottomSheet = getLayoutInflater().inflate(R.layout.dialog_date_range_picker, null);
        dateDialog = new Dialog(this, R.style.MaterialDialogSheet);
        dateDialog.setContentView(bottomSheet);
        dateDialog.setCancelable(true);
        dateDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dateDialog.getWindow().setGravity(Gravity.BOTTOM);

        final TextView datePickerHintTV = (TextView) bottomSheet.findViewById(R.id.date_picker_hint_text_view);
        final Button saveButton = (Button) bottomSheet.findViewById(R.id.submit_picker_button);
        Button cancelButton = (Button) bottomSheet.findViewById(R.id.cancel_picker_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DISPLAY, Locale.getDefault());
                if (isRangePicker) {
                    startTV.setText(dateFormat.format(startRangeCal.getTime()));
                    endTV.setText(dateFormat.format(endRangeCal.getTime()));
                } else {
                    normalTV.setText(dateFormat.format(normalCal.getTime()));
                }
                dateDialog.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog.dismiss();
            }
        });

        // Hint Picker
        if (isRangePicker) {
            datePickerHintTV.setText(getResources().getString(R.string.start));
        } else {
            datePickerHintTV.setText(getResources().getString(R.string.select_date));
        }

        DateRangeCalendarView calendar = (DateRangeCalendarView) bottomSheet.findViewById(R.id.calendar);
        calendar.setRangePicker(isRangePicker);
        calendar.setCalendarListener(new DateRangeCalendarView.CalendarListener() {
            @Override
            public void onDateSelected(Calendar date) {
                if (isRangePicker) {
                } else {
                    saveButton.setVisibility(View.VISIBLE);
                    normalCal = date;
                }
            }

            @Override
            public void onDateRangeSelected(Calendar startDate, Calendar endDate) {
                if (isRangePicker) {
                    startRangeCal = startDate;
                    endRangeCal = endDate;
                    datePickerHintTV.setVisibility(View.INVISIBLE);
                    saveButton.setVisibility(View.VISIBLE);
                } else {
                }
            }

            @Override
            public void onCancel() {
            }
        });
        dateDialog.show();
    }
}
