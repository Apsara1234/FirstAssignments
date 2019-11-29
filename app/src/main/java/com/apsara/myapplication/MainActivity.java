package com.apsara.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Spinner spin, spin1;
    //    private AutoCompleteTextView auto;
    private TextView chkin, chkout, country, cost, tvat, tvsc, tnettotal, roomcost, roomno, totaldays;
    private EditText room, child, adult, name;
    private Button btncal;
    int y1, y2, m1, m2, d1, d2, diff;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bindings
        spin=findViewById(R.id.spin);
        spin1=findViewById(R.id.spin1);
        // auto=findViewById(R.id.auto);
        chkin=findViewById(R.id.chkin);
        chkout=findViewById(R.id.chkout);
        country=findViewById(R.id.country);
        room=findViewById(R.id.room);
        child=findViewById(R.id.child);
        adult=findViewById(R.id.adult);
        name=findViewById(R.id.name);
        btncal=findViewById(R.id.btncal);

        //output data binding
        cost=findViewById(R.id.cost);
        tvat=findViewById(R.id.tvat);
        tvsc=findViewById(R.id.tvsc);
        tnettotal=findViewById(R.id.tnettotal);
        roomcost=findViewById(R.id.roomcost);
        roomno=findViewById(R.id.roomno);
        totaldays=findViewById(R.id.totaldays);

        //passing array to countries in spinner
        String countries[] = {"Nepal", "China", "USA", "LONDON", "QATAR", "GERMANY"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,countries);

        spin.setAdapter(arrayAdapter);

        //passing array to room type spinner
        String roomType[]={"Deluxe","Suite","King size","Double Bed"};

        final ArrayAdapter arrayAdapter1= new ArrayAdapter(this, android.R.layout.select_dialog_item,roomType);

        spin1.setAdapter(arrayAdapter1);
        // spin1.setThreshold(1);//will start working form first character


        //setting onclick event to check in date
        chkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePicker();
            }
        });



        //setting onclick to check out date
        chkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDate();
            }
        });


        //button to calculate total
        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(name.getText())){
                    name.setError("Please enter your name");
                    return;
                }else if(TextUtils.isEmpty(adult.getText()))
                {
                    adult.setText("Please enter number of adults");
                    return;
                }
                else if(TextUtils.isEmpty(child.getText()))
                {
                    child.setText("Please enter number of adults");
                    return;
                }
                else if(TextUtils.isEmpty(room.getText()))
                {
                    room.setText("Please enter number of adults");
                    return;
                }
                else if(TextUtils.isEmpty(chkin.getText())){
                    chkin.setText("Please enter check in Date");
                    return;
                }
                else if(TextUtils.isEmpty(chkout.getText())){
                    chkout.setText("Please enter check out Date");
                    return;
                }

                //calculate number of days

                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal1.set(y1,m1,d1);
                cal2.set(y2,m2,d2);
                long milis1= cal1.getTimeInMillis();
                long milis2 = cal2.getTimeInMillis();
                long diff = milis1-milis2;
                long diffDays=(diff / (86400000));

                int numRoom = Integer.parseInt(room.getText().toString());

                //calculate net total

                double roomprice, totalprice;
                double vat, servicecharge, nettotal;

                String roomtype = spin1.getSelectedItem().toString();

                if(roomtype=="Deluxe"){
                    roomprice=2500;
                    totalprice= roomprice * numRoom * diffDays;
                    vat=(0.13 * totalprice) + totalprice;
                    nettotal= servicecharge = (0.10 * vat) + vat;
                    cost.setText("Total cost is:" +totalprice);
                    tvat.setText("Price after VAT inclusion:" +vat);
                    tvsc.setText("Price after service charge inclusion:" + servicecharge);
                    tnettotal.setText("Overall Price:" +nettotal);

                }

                else if(roomtype=="Suite"){
                    roomprice=2000;
                    totalprice= roomprice * numRoom * diffDays;
                    vat=(0.13 * totalprice) + totalprice;
                    nettotal= servicecharge = (0.10 * vat) + vat;
                    cost.setText("Total cost is:" +totalprice);
                    tvat.setText("Price after VAT inclusion:" +vat);
                    tvsc.setText("Price after service charge inclusion:" + servicecharge);
                    tnettotal.setText("Overall Price:" +nettotal);
                }
                else if(roomtype=="King size"){
                    roomprice=2200;
                    totalprice= roomprice * numRoom * diffDays;
                    vat=(0.13 * totalprice) + totalprice;
                    nettotal= servicecharge = (0.10 * vat) + vat;
                    cost.setText("Total cost is:" +totalprice);
                    tvat.setText("Price after VAT inclusion:" +vat);
                    tvsc.setText("Price after service charge inclusion:" + servicecharge);
                    tnettotal.setText("Overall Price:" +nettotal);
                }

                else if(roomtype=="Double Bed"){
                    roomprice=1800;
                    totalprice= roomprice * numRoom * diffDays;
                    vat=(0.13 * totalprice) + totalprice;
                    nettotal= servicecharge = (0.10 * vat) + vat;
                    cost.setText("Total cost is:" +totalprice);
                    tvat.setText("Price after VAT inclusion:" +vat);
                    tvsc.setText("Price after service charge inclusion:" + servicecharge);
                    tnettotal.setText("Overall Price:" +nettotal);
                }
            }
        });

    }

    private void loadDatePicker()
    {
        final Calendar c = Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, day);
        datePickerDialog.show();
    }

    private void loadDate(){
        final Calendar c1= Calendar.getInstance();
        int year=c1.get(Calendar.YEAR);
        int month=c1.get(Calendar.MONTH);
        final int day = c1.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "CheckIn Date:" + (month+1) + "/" + dayOfMonth + "/" + year;
                y2= year;
                m2= month;
                d2= dayOfMonth;

                chkout.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "CheckIn Date:" + (month+1) + "/" + dayOfMonth + "/" + year;
        y1= year;
        m1= month;
        d1= dayOfMonth;

        chkin.setText(date);
    }
}

