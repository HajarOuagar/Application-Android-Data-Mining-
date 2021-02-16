package com.example.mydrugappdt;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import static java.lang.Double.compare;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class MainActivity extends Activity {
    private final String defaultString = "Clicker sur calculer pour obtenir le type de drug";
    Button calculate = null;
    Button raz    = null;
    EditText age = null;
    EditText K = null;
    EditText Na = null;
    RadioGroup groupSex = null;
    RadioGroup groupBP = null;
    RadioGroup groupCholesterol = null;
    TextView result = null;
    public static String medicament = null;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        // Getting items
        calculate = (Button)findViewById(R.id.calculate);
        raz    = (Button)findViewById(R.id.raz);
        age = (EditText)findViewById(R.id.age);
        K  = (EditText)findViewById(R.id.K);
        Na  = (EditText)findViewById(R.id.Na);
        groupSex  = (RadioGroup)findViewById(R.id.groupSex);
        groupBP  = (RadioGroup)findViewById(R.id.groupBP);
        groupCholesterol  = (RadioGroup)findViewById(R.id.groupCholesterol);
        result = (TextView)findViewById(R.id.result);

        // Attribute listners and assign actions
        calculate.setOnClickListener(calculateListener);
        raz.setOnClickListener(razListener);
        age.addTextChangedListener(textWatcher);
        K.addTextChangedListener(textWatcher);
        Na.addTextChangedListener(textWatcher);
    }

    private OnClickListener razListener = new OnClickListener() {
        @Override
        public void onClick(View v){
            age.getText().clear();
            K.getText().clear();
            Na.getText().clear();
            result.setText("");
        }
    };


    private OnClickListener calculateListener = new OnClickListener() {

        public void onClick (View v){


            int ageval = Integer.parseInt(age.getText().toString());
            int selectedId1 = groupSex.getCheckedRadioButtonId();
            RadioButton sexBtn = (RadioButton)findViewById(selectedId1);
            String sexValue = sexBtn.getText().toString();

            int selectedId2 = groupBP.getCheckedRadioButtonId();
            RadioButton bpBtn = (RadioButton)findViewById(selectedId2);
            String bpValue = bpBtn.getText().toString();

            int selectedId3 = groupCholesterol.getCheckedRadioButtonId();
            RadioButton bcBtn = (RadioButton)findViewById(selectedId3);
            String bcValue = bcBtn.getText().toString();

            Log.d("MyActivity", "bcValue => " + bcValue);
            Log.d("MyActivity", "bpValue => " + bpValue);

            double naValue = parseDouble(Na.getText().toString());
            double Kvalue = parseDouble(K.getText().toString());

            if(Kvalue <=0.0448315742){
                if(naValue <=0.633256883){
                    if(ageval <=34){
                        medicament = "DrugY";
                    }
                    else if(34<ageval && ageval<=54){
                        if(sexValue.equals("M")) {
                            if(bcValue.equals("HIGH")){
                                medicament = "DrugY";
                            }
                            if(bcValue.equals("NORMAL")){
                                medicament = "DrugX";
                            }
                        }
                        else if(sexValue.equals("F")){
                            medicament = "DrugA";
                        }
                    }
                    else if(54<ageval){
                        medicament = "DrugY";
                    }
                }
                else if(0.633256883<naValue && naValue<=0.7599290783){
                    medicament = "DrugY";
                }
                else if(0.7599290783<=naValue && naValue<=0.896056){
                    medicament = "DrugY";
                }
            }
            else if (0.0448315742<Kvalue && Kvalue<=0.0615165104){
                if(naValue<=0.633256883){
                    if(bpValue.equals("HIGH")){
                        medicament = "DrugA";
                    }
                    if(bpValue.equals("LOW")){
                        medicament = "DrugX";
                    }
                    if(bpValue.equals("NORMAL")){
                        medicament = "DrugX";
                    }
                }
                else if(0.633256883<naValue && naValue<=0.7599290783){
                    if(bpValue.equals("HIGH")){
                        medicament = "DrugA";
                    }
                    if(bpValue.equals("LOW")){
                        medicament = "DrugC";
                    }
                    if(bpValue.equals("NORMAL")){
                        if(bcValue.equals("HIGH")){
                            medicament = "DrugX";
                        }
                        else if(bcValue.equals("NORMAL")){
                            medicament = "DrugY";
                        }
                    }
                }
                else if(0.7599290783<naValue && naValue<=0.896056){
                    if(bpValue.equals("HIGH")){
                        if(ageval <=34){
                            medicament = "DrugY";
                        }
                        else if(34<ageval && ageval<=54){
                            medicament = "DrugA";
                        }
                        else if(54<ageval && ageval<=74){
                            medicament = "DrugB";
                        }
                    }
                    }
                    //medicament = bpValue == "Low" ? "DrugY" : "DrugY";
                    if(bpValue.equals("LOW")){
                        medicament = "DrugY";
                    }
                    if(bpValue.equals("NORMAL")){
                        medicament = "DrugY";
                    }
            }
            else if(0.0615165104<=Kvalue){
                if(bpValue.equals("HIGH")){
                    medicament = "DrugB";
                }
                else if(bpValue.equals("NORMAL")){
                    medicament = "DrugX";
                }
                else if(bpValue.equals("LOW")){
                    if(bcValue.equals("HIGH")){
                        medicament = "DrugC";
                    }
                    else if(bcValue.equals("NORMAL")){
                        medicament = "DrugX";
                    }
                }
            }
            Individu patient = new Individu(ageval, sexValue, bpValue, bcValue, naValue, Kvalue, medicament);
            Log.d("MyActivity","Drugs => " + medicament);
            result.setText(patient.getDrug());
    }
    };

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {result.setText(defaultString);}
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,int after){}
        @Override
        public void afterTextChanged(Editable s) {}
    };
}

