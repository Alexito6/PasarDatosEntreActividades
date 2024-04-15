package com.example.mycustomview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class Preferencias extends AppCompatActivity {
    private int colorSeleccionadoRadioGroup;
    private String colorSeleccionadoSpinner;
    private EditText gradeSize;

    private EditText textSize;

    private RadioGroup colores;
    private Spinner coloresTexto;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferencias);
        ArrayList<String> opcionesColores=new ArrayList<>(Arrays.asList("black", "white", "blue", "yellow", "red", "green"));
        gradeSize =findViewById(R.id.gradeSize);
        textSize=findViewById(R.id.textSize);
        colores=findViewById(R.id.Colores);
        coloresTexto=findViewById(R.id.ColoresTexto);
        back=findViewById(R.id.atras);
        ArrayAdapter<String> misColores=new ArrayAdapter<>(this,R.layout.preferencias,opcionesColores);
        coloresTexto.setAdapter(misColores);
        coloresTexto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                colorSeleccionadoSpinner=opcionesColores.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                colorSeleccionadoSpinner=opcionesColores.get(0);
            }
        });
        colores.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.negro){
                    colorSeleccionadoRadioGroup =1;
                }
                if (checkedId==R.id.blanco){
                    colorSeleccionadoRadioGroup =2;
                }
                if (checkedId==R.id.azul){
                    colorSeleccionadoRadioGroup =3;
                }
                if (checkedId==R.id.amarillo){
                    colorSeleccionadoRadioGroup =4;
                }
                if (checkedId==R.id.rojo){
                    colorSeleccionadoRadioGroup =5;
                }
                if (checkedId==R.id.verde){
                    colorSeleccionadoRadioGroup =6;
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarDeVuelta(colorSeleccionadoRadioGroup,colorSeleccionadoSpinner,Integer.parseInt(gradeSize.getText().toString()),Integer.parseInt(textSize.getText().toString()));
            }
        });
    }
    private void lanzarDeVuelta(int colorSeleccionadoRadioGroup,String colorSeleccionadoSpinner,int tamanyoBarra,int tamanyoTexto){
        Intent intent=new Intent(this, MainActivity.class);
        intent.putExtra("barColor",colorSeleccionadoRadioGroup);
        intent.putExtra("textColor",colorSeleccionadoSpinner);
        intent.putExtra("gradeSize",tamanyoBarra);
        intent.putExtra("textSize",tamanyoTexto);
        finish();
    }
}
