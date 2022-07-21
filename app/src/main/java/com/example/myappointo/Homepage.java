package com.example.myappointo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Homepage extends AppCompatActivity {
    EditText editTopic, editDesc;
    Button editDate, editTime;
    Button calendarView, timeView;

    final Calendar myCalendar= Calendar.getInstance();

    ArrayList<Integer> taskIds = new ArrayList<>();
    ArrayList<Integer> editTaskIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        getIntent();
        Toast.makeText(Homepage.this, "Welcome User!", Toast.LENGTH_SHORT).show();

    }



    public void createTask(View view){
        View child, edit_child;
        LinearLayout cardsContainerLL = (LinearLayout) ((ViewGroup) ((LinearLayout) ((LinearLayout) view.getParent()).getParent()).getChildAt(1)).getChildAt(0);
//        int childCount = cardsContainerLL.getChildCount();
        child = getLayoutInflater().inflate(R.layout.appointment_cards, cardsContainerLL, false);
        edit_child = getLayoutInflater().inflate(R.layout.edit_appointment_card, cardsContainerLL, false);

        cardsContainerLL.addView(child);
        cardsContainerLL.addView(edit_child);

        child.setId((View.generateViewId()));
        taskIds.add(child.getId());
        edit_child.setId(View.generateViewId());
        editTaskIds.add(edit_child.getId());

        edit_child.setVisibility(View.GONE);

        }

    public void deleteTask(View view){
//        CardView parent = (CardView)((RelativeLayout)((LinearLayout)((RelativeLayout) view.getParent()).getParent()).getParent()).getParent();
        ViewGroup parent = (ViewGroup) view.getRootView();
        ((ViewManager) parent.getParent()).removeView(parent);
//        ViewGroup grandParent = (ViewGroup) parent.getParent();
        System.out.println("///////////////////////" + parent.getRootView());
//        if(parent.getChildCount() == 0){
//            CardView cv = (CardView)((RelativeLayout)((LinearLayout)((RelativeLayout) parent.getParent()).getParent()).getParent()).getParent();
//            ((ViewManager) cv.getParent()).removeView(cv);
//        }
    }

    public void createNewDateTask(View view){
//        LinearLayout dateCardsLL = (LinearLayout) view.getParent(); //OUTPUT: 2
        LinearLayout dateCardsLL = (LinearLayout) ((LinearLayout) view.getParent()).getParent(); // OUTPUT: 1
//        numberOfTaskDates = dateCardsLL.getChildCount();

        LinearLayout parentLL = findViewById(R.id.parentLL);
        View child = getLayoutInflater().inflate(R.layout.recycler_cards, dateCardsLL, false); // inflate(Linerar Layout, XML, false);
        parentLL.addView(child);

//        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////            dateCardsLL.addView(vi.inflate(R.layout.recycler_cards, dateCardsLL, true));
//        vi.inflate(R.layout.recycler_cards, dateCardsLL, true);
//        Toast.makeText(Homepage.this, "no. of newDateTasks " + Integer.toString(numberOfTaskDates), Toast.LENGTH_SHORT).show();
    }

    public void openCalendarView(View view){
        calendarView = (Button) findViewById(R.id.edit_date);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        calendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Homepage.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        calendarView.setText(dateFormat.format(myCalendar.getTime()));
    }

    public void openClock(View view){
        timeView = (Button) findViewById(R.id.edit_time);
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        String maridian;

        if (mcurrentTime.get(Calendar.AM_PM) == Calendar.PM) {
            maridian = "PM";
        }
        else
            maridian = "AM";

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Homepage.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                timeView.setText( selectedHour + ":" + selectedMinute + " " + maridian);
            }
        }, hour, minute, false);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    public void convertToEditText(View view){
        CardView card = (CardView) ((RelativeLayout) ((LinearLayout)((RelativeLayout) view.getParent()).getParent()).getParent()).getParent();
//        ViewGroup cardsContainer = (ViewGroup) thisCard.getParent();
//        int childCount =  cardsContainer.getChildCount();
        int card_id = card.getId();
        int card_index = taskIds.indexOf(card_id);
        int edit_card_id = editTaskIds.get(card_index);
        CardView editCard = findViewById(edit_card_id);
        
        card.setVisibility(View.GONE);
        editCard.setVisibility(View.VISIBLE);

    }

    public void convertToTextView(View view){
        CardView edit_card = (CardView) ((RelativeLayout) ((LinearLayout)((RelativeLayout) view.getParent()).getParent()).getParent()).getParent();
        int edit_card_id = edit_card.getId();
        int card_index = editTaskIds.indexOf(edit_card_id);
        int card_id = taskIds.get(card_index);
        
        CardView card = findViewById(card_id);
        
        edit_card.setVisibility(View.GONE);

        RelativeLayout dateTimeRL = (RelativeLayout) card.getChildAt(0);
        RelativeLayout edit_dateTimeRL = (RelativeLayout) edit_card.getChildAt(0);
        LinearLayout textLL = (LinearLayout) dateTimeRL.getChildAt(2);
        LinearLayout edit_textLL = (LinearLayout) edit_dateTimeRL.getChildAt(2);

        editTopic = (EditText) edit_textLL.getChildAt(0);
        ((TextView)textLL.getChildAt(0)).setText(editTopic.getText().toString()); //topic set

        editDesc = (EditText) edit_textLL.getChildAt(1);
        ((TextView)textLL.getChildAt(1)).setText(editDesc.getText().toString()); //Description set

        editDate = (Button) edit_dateTimeRL.getChildAt(0);
        ((TextView)dateTimeRL.getChildAt(0)).setText(editDate.getText().toString());

        editTime = (Button) edit_dateTimeRL.getChildAt(1);
        ((TextView)dateTimeRL.getChildAt(1)).setText(editTime.getText().toString());


        edit_card.setVisibility(View.GONE);
        card.setVisibility(View.VISIBLE);
    }


}