package android.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    MaterialButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnDot,btnMultiplication,btnMinus,btnDivide,btnOpen,btnClose,btnAc,btnPlus;
    TextView txt1,txt2;
    MaterialButton btnEqual,btnC;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignId(btn0,R.id.btn_0);
        assignId(btn1,R.id.btn_1);
        assignId(btn2,R.id.btn_2);
        assignId(btn3,R.id.btn_3);
        assignId(btn4,R.id.btn_4);
        assignId(btn5,R.id.btn_5);
        assignId(btn6,R.id.btn_6);
        assignId(btn7,R.id.btn_7);
        assignId(btn8,R.id.btn_8);
        assignId(btn9,R.id.btn_9);

        assignId(btnDot,R.id.btn_dot);
        assignId(btnEqual,R.id.btn_equal);
        assignId(btnMultiplication,R.id.btn_multiplication);
        assignId(btnMinus,R.id.btn_minus);
        assignId(btnDivide,R.id.btn_divide);
        assignId(btnPlus,R.id.btn_plus);
        assignId(btnOpen,R.id.btn_start_bracket);
        assignId(btnClose,R.id.btn_end_bracket);
        assignId(btnAc,R.id.btn_ac);
        assignId(btnC,R.id.btn_c);

        txt1 = findViewById(R.id.input_text);
        txt2 = findViewById(R.id.output_text);



    }
    //setOnClickListener(new View.OnClickListener() {
    void assignId(MaterialButton btn , int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
        @Override
        public void onClick (View v){
            MaterialButton button = (MaterialButton) v;
            String buttonTxt = button.getText().toString();
            String dataToCalculate = txt1.getText().toString();


            if (buttonTxt.equals("ac")) {
                txt1.setText("");
                txt2.setText("0");
                return;
            }

            if (buttonTxt.equals("=")) {

                txt1.setText(txt2.getText());
                return;
            }
           if (buttonTxt.equals("C")) {
                if (dataToCalculate.length() > 0) {

                    dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);

                }
            }    else {
                dataToCalculate = dataToCalculate + buttonTxt;
            }
            txt1.setText(dataToCalculate);
            String finalResult = result(dataToCalculate);
            if (!finalResult.equals("Err")) {
                txt2.setText(finalResult);
            }
        }
    String result(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0"," ");
            }
            return finalResult;
        }
        catch (Exception e) {
            return "Err";

        }
    }

}
