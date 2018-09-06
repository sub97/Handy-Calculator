package com.aravind.android.calculator;

import android.app.ActionBar;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.aravind.android.calculator.R;

import android.support.v7.widget.LinearLayoutCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static android.R.attr.screenOrientation;
import static android.R.attr.x;
import static android.R.id.edit;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.provider.Telephony.Carriers.PORT;
import static com.aravind.android.calculator.R.id.bcosh;
import static com.aravind.android.calculator.R.id.bsinh;
import static com.aravind.android.calculator.R.id.btanh;
import static java.lang.Double.doubleToLongBits;
import static java.lang.Double.parseDouble;
import static java.lang.Math.pow;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onWindowFocusChanged(true);
        setContentView(R.layout.activity_main);
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT)
            Toast.makeText(this, "Rotate your screen for Scientific Calculator", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    String t[]=new String[15];
    int snum=0,num=0,i=0,calchkr=0,decchk=0,dci=0,decchkb=0,ic=0,ineg=0,irad=1,iarc=0;
    double res=0.0, mynum=0.0000000000001,aftreqlchkr,pi=3.141592653,e=2.718281828,powtmp=0.0,torial=0.0;
    double mem=0.0;
    float txsz=27,otxz=28;

    public void farc(View view)
    {
        try {
            Button bsinh = (Button) findViewById(R.id.bsinh);
            Button bcosh = (Button) findViewById(R.id.bcosh);
            Button btanh = (Button) findViewById(R.id.btanh);
            Button barc = (Button) findViewById(R.id.barc);
            if (iarc == 0) {
                iarc = 1;
                bsinh.setText("" + "sin⁻¹");
                bcosh.setText("" + "cos⁻¹");
                btanh.setText("" + "tan⁻¹");
                barc.setText("" + "hyp");
            } else {
                bsinh.setText("" + "sinh");
                bcosh.setText("" + "cosh");
                btanh.setText("" + "tanh");
                barc.setText("" + "arc");
                iarc = 0;
            }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void fmpl(View view)
    {
        try {
            TextView editor = (TextView) findViewById(R.id.tv);
            if (editor.getText().length() == 0)
                Toast.makeText(this, "Enter number first", Toast.LENGTH_SHORT).show();
            else {
                mem += Double.parseDouble(editor.getText().toString());
                TextView tmv = (TextView) findViewById(R.id.tm);
                tmv.setText("" + "M+");
            }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }
     public void fcosh(View view)
    {
        try{
        TextView editor = (TextView) findViewById(R.id.tv);
        if(iarc==0)
        {
            if (editor.getText().length() == 0 && snum < 1) {
                Toast.makeText(this, "Enter number to calculate its hyperbolic cos value", Toast.LENGTH_SHORT).show();
            } else if (snum < 1) {
                double ypxi;
                powtmp = Double.parseDouble(editor.getText().toString());
                if (ineg == 1) {
                    powtmp = 0 - powtmp;
                    ineg = 0;
                    TextView negr = (TextView) findViewById(R.id.tneg);
                    negr.setText(null);
                }
                ypxi = powtmp;
                TextView symr = (TextView) findViewById(R.id.tsym);
                String symtxtr = symr.getText().toString();
                if (symtxtr.contains("=")) {
                    symtxtr = "";
                }
                if (powtmp > 999999999999999.9 || powtmp < (-999999999999999.9)) {
                    DecimalFormat df = new DecimalFormat("###############.#########E0");
                    symr.setText("" + symtxtr + "cosh(" + df.format(ypxi) + ")");
                } else {
                    DecimalFormat df = new DecimalFormat("###############.###################");
                    symr.setText("" + symtxtr + "cosh(" + df.format(ypxi) + ")");
                }
                snum = 14;
            }
        }
        else
        {
                if (editor.getText().length() == 0 && snum < 1) {
                    Toast.makeText(this, "Enter number to calculate its inverse cos value", Toast.LENGTH_SHORT).show();
                } else if (snum < 1)
                {
                    double ypxi;
                    powtmp = Double.parseDouble(editor.getText().toString());
                    if (ineg == 1) {
                        powtmp = 0 - powtmp;
                        ineg = 0;
                        TextView negr = (TextView) findViewById(R.id.tneg);
                        negr.setText(null);
                    }
                    ypxi = powtmp;
                    TextView symr = (TextView) findViewById(R.id.tsym);
                    String symtxtr = symr.getText().toString();
                    if (symtxtr.contains("=")) {
                        symtxtr = "";
                    }
                    if (powtmp > 999999999999999.9 || powtmp < (-999999999999999.9)) {
                        DecimalFormat df = new DecimalFormat("###############.#########E0");
                        symr.setText("" + symtxtr + "cos⁻¹(" + df.format(ypxi) + ")");
                    } else {
                        DecimalFormat df = new DecimalFormat("###############.###################");
                        symr.setText("" + symtxtr + "cos⁻¹(" + df.format(ypxi) + ")");
                    }
                    snum = 18;
                }
        }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void fmsub(View view)
    {
        try {
            TextView editor = (TextView) findViewById(R.id.tv);
            if (editor.getText().length() == 0)
                Toast.makeText(this, "Enter number first", Toast.LENGTH_SHORT).show();
            else {
                mem -= Double.parseDouble(editor.getText().toString());
                TextView tmv = (TextView) findViewById(R.id.tm);
                tmv.setText("" + "M-");
            }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void fmc(View view)
    {
        try {
            mem = 0.0;
            TextView tmv = (TextView) findViewById(R.id.tm);
            tmv.setText(null);
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void fmr(View view)
    {
        try {
            TextView editor = (TextView) findViewById(R.id.tv);
            if (mem > 999999999999999.9 || mem < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mem));
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mem));
            }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void fi(View view)
    {
        try {
            if (snum < 1 || snum == 1 || snum == 3 || snum == 16)
                display("1");
            else
                Toast.makeText(this, "Enter operator to continue", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, "Content Invalid", Toast.LENGTH_SHORT).show();
        }
    }

    public void fii(View view)
    {
        try {
            if (snum < 1 || snum == 1 || snum == 3 || snum == 16)
                display("2");
            else
                Toast.makeText(this, "Enter operator to continue", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
        Toast.makeText(this, "Content Invalid", Toast.LENGTH_SHORT).show(); }
    }

    public void fiii(View view)
    {
        try {
            if (snum < 1 || snum == 1 || snum == 3 || snum == 16)
                display("3");
            else
                Toast.makeText(this, "Enter operator to continue", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, "Content Invalid", Toast.LENGTH_SHORT).show();
        }
    }

    public void fiv(View view)
    {
        try {
            if (snum < 1 || snum == 1 || snum == 3 || snum == 16)
                display("4");
            else
                Toast.makeText(this, "Enter operator to continue", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, "Content Invalid", Toast.LENGTH_SHORT).show();
        }
    }

    public void fv(View view)
    {
        try {
            if (snum < 1 || snum == 1 || snum == 3 || snum == 16)
                display("5");
            else
                Toast.makeText(this, "Enter operator to continue", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, "Content Invalid", Toast.LENGTH_SHORT).show();
        }
    }

    public void fvi(View view)
    {
        try {
            if (snum < 1 || snum == 1 || snum == 3 || snum == 16)
                display("6");
            else
                Toast.makeText(this, "Enter operator to continue", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, "Content Invalid", Toast.LENGTH_SHORT).show();
        }
    }

    public void fvii(View view)
    {
        try {
            if (snum < 1 || snum == 1 || snum == 3 || snum == 16)
                display("7");
            else
                Toast.makeText(this, "Enter operator to continue", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, "Content Invalid", Toast.LENGTH_SHORT).show();
        }
    }

    public void fviii(View view)
    {
        try {
            if (snum < 1 || snum == 1 || snum == 3 || snum == 16)
                display("8");
            else
                Toast.makeText(this, "Enter operator to continue", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, "Content Invalid", Toast.LENGTH_SHORT).show();
        }
    }

    public void fix(View view)
    {
        try {
            if (snum < 1 || snum == 1 || snum == 3 || snum == 16)
                display("9");
            else
                Toast.makeText(this, "Enter operator to continue", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, "Content Invalid", Toast.LENGTH_SHORT).show();
        }
    }

    public void fzer(View view)
    {
        try {
            if (snum < 1 || snum == 1 || snum == 3 || snum == 16)
                display("0");
            else
                Toast.makeText(this, "Enter operator to continue", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, "Content Invalid", Toast.LENGTH_SHORT).show();
        }
    }

    public void fcent(View view)
    {
        try {
            TextView editor = (TextView) findViewById(R.id.tv);
            if (editor.getText().length() == 0 && snum < 1) {
                Toast.makeText(this, "Enter value to calculate its percent", Toast.LENGTH_SHORT).show();
            } else if (snum < 1) {
                double ypxi;
                powtmp = Double.parseDouble(editor.getText().toString());
                if (ineg == 1) {
                    powtmp = 0 - powtmp;
                    ineg = 0;
                    TextView negr = (TextView) findViewById(R.id.tneg);
                    negr.setText(null);
                }
                ypxi = powtmp;
                TextView symr = (TextView) findViewById(R.id.tsym);
                String symtxtr = symr.getText().toString();
                if (symtxtr.contains("=")) {
                    symtxtr = "";
                }
                if (powtmp > 999999999999999.9 || powtmp < (-999999999999999.9)) {
                    DecimalFormat df = new DecimalFormat("###############.#########E0");
                    symr.setText("" + symtxtr + df.format(ypxi) + "%");
                } else {
                    DecimalFormat df = new DecimalFormat("###############.###################");
                    symr.setText("" + symtxtr + df.format(ypxi) + "%");
                }
                snum = 5;
            }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void fscrotl (View view)
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void fscrotp (View view)
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void fsin(View view)
    {
        try {
            TextView editor = (TextView) findViewById(R.id.tv);
            if (editor.getText().length() == 0 && snum < 1) {
                Toast.makeText(this, "Enter number to calculate its sine value", Toast.LENGTH_SHORT).show();
            } else if (snum < 1) {
                double ypxi;
                powtmp = Double.parseDouble(editor.getText().toString());
                if (ineg == 1) {
                    powtmp = 0 - powtmp;
                    ineg = 0;
                    TextView negr = (TextView) findViewById(R.id.tneg);
                    negr.setText(null);
                }
                ypxi = powtmp;
                TextView symr = (TextView) findViewById(R.id.tsym);
                String symtxtr = symr.getText().toString();
                if (symtxtr.contains("=")) {
                    symtxtr = "";
                }
                if (powtmp > 999999999999999.9 || powtmp < (-999999999999999.9)) {
                    DecimalFormat df = new DecimalFormat("###############.#########E0");
                    symr.setText("" + symtxtr + "sin(" + df.format(ypxi) + ")");
                } else {
                    DecimalFormat df = new DecimalFormat("###############.###################");
                    symr.setText("" + symtxtr + "sin(" + df.format(ypxi) + ")");
                }
                snum = 10;
            }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    



    public void fpi(View view)
    {
        try{
        TextView editor = (TextView) findViewById(R.id.tv);
        editor.setTextSize(txsz);
        if(editor.getText().length()==0)
            editor.setText(""+pi);
        else
            Toast.makeText(this, "Enter operator perform operation with π", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void fmod(View view)
    {
        try{
        TextView editor = (TextView) findViewById(R.id.tv);
        double temmod;
        if(editor.getText().length()==0)
            Toast.makeText(this, "Enter number to make mod operation", Toast.LENGTH_SHORT).show();
        else
            {

                temmod=Double.parseDouble(editor.getText().toString());
                if(ineg==1)
                {
                    powtmp=0-powtmp;
                    ineg=0;
                    TextView negr=(TextView) findViewById(R.id.tneg);
                    negr.setText(null);
                }
                if(temmod<0)
                {
                    temmod = 0 - temmod;
                    if (temmod >999999999999999.9 || temmod <(-999999999999999.9))
                    {
                        DecimalFormat df = new DecimalFormat("###############.#########E0");
                        editor.setText("" + df.format(temmod));
                    } else
                    {
                        DecimalFormat df = new DecimalFormat("###############.###################");
                        editor.setText("" + df.format(temmod));
                    }
                }
            }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void fe(View view)
    {
        try{
        TextView editor = (TextView) findViewById(R.id.tv);
        editor.setTextSize(txsz);
        if(editor.getText().length()==0)
            editor.setText(""+e);
        else
            Toast.makeText(this, "Enter operator perform operation with e", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void frad(View view)
    {
        try{
        TextView radr=(TextView) findViewById(R.id.trad);
        Button bradr=(Button) findViewById(R.id.brad);
        if(irad==1)
        {
            radr.setText(""+"RAD");
            bradr.setText(""+"DEG");
            irad = 0;
        }
        else
        {
            radr.setText(""+"DEG");
            bradr.setText(""+"RAD");
            irad = 1;
        }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }



    public void fneg(View view)
    {
        try{
        TextView negr=(TextView) findViewById(R.id.tneg);
        if(ineg==0)
        {
            negr.setText(""+"(-)");
            ineg = 1;
        }
        else
        {
            negr.setText(null);
            ineg = 0;
        }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void fc(View view)
    {
        try{
        TextView editor = (TextView) findViewById(R.id.tv);
        TextView negr=(TextView) findViewById(R.id.tneg);
        TextView symr=(TextView) findViewById(R.id.tsym);
        symr.setText(null);
        editor.setText(null);
        num=0;i=0;decchk=0;dci=0;decchkb=0;
        mynum=0.0000000000001;
        aftreqlchkr=0;
        calchkr=0;
        ineg=0;
        snum=0;
        powtmp=0.0;
        editor.setTextSize(otxz);
        negr.setText(null);
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void fdec(View view)
    {
        try{
        String chkr;
        TextView editor = (TextView) findViewById(R.id.tv);
        chkr=editor.getText().toString();

        if (chkr.contains("."))
        {
            Toast.makeText(this, "Only one decimal point can be added", Toast.LENGTH_SHORT).show();
        }

        if(snum<1 || snum==1 || snum==3 || snum==16)
        if(decchk!=1)
        {
            if(i==0)
                display("0.");
            else
                display(".");
        }
        else
            Toast.makeText(this, "Enter operator to continue", Toast.LENGTH_SHORT).show();
        decchk = 1;
        decchkb=1;
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void fbsp(View view)
    {
        try{
            TextView symr=(TextView) findViewById(R.id.tsym);
        TextView editor=(TextView) findViewById(R.id.tv);
        if (editor.getText().toString().length()==0)
            Toast.makeText(this, "Nothing to Erase", Toast.LENGTH_SHORT).show();
        else if(symr.getText().toString().contains("="))
        {
            TextView negr=(TextView) findViewById(R.id.tneg);
            symr.setText(null);
            editor.setText(null);
            num=0;i=0;decchk=0;dci=0;decchkb=0;
            mynum=0.0000000000001;
            aftreqlchkr=0;
            calchkr=0;
            ineg=0;
            snum=0;
            powtmp=0.0;
            editor.setTextSize(otxz);
            negr.setText(null);
        }
        else
        {
            i--;
            editor.setText(t[i]);
            if(i<8)
                editor.setTextSize(otxz);
        }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    private void display(String n)
    {
        try{
        TextView sizer=(TextView) findViewById(R.id.tv);
        sizer.setTextColor(Color.parseColor("#000000"));

        if(i==8)
        {
            sizer.setTextSize(txsz);
            ic=1;
        }
        else if (i<8 && ic==1)
        {
            sizer.setTextSize(otxz);
        }

        if(i==15)
            Toast.makeText(this, "Maximum number of digits (15) exceeded", Toast.LENGTH_SHORT).show();
        else if (decchk==1)
        {
            if(dci<10)
            {
                TextView editor = (TextView) findViewById(R.id.tv);
                t[i] = editor.getText().toString();
                editor.setText(t[i] + n);
                i++;
                dci++;
            }
            else
                Toast.makeText(this, "Maximum number of digits after decimal point is 10", Toast.LENGTH_SHORT).show();

        }
        else {
            TextView editor = (TextView) findViewById(R.id.tv);
            t[i] = editor.getText().toString();
            editor.setText(t[i] + n);
            i++;
        }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void fadd(View view)
    {
        try{
        TextView editor = (TextView) findViewById(R.id.tv);
        TextView negr=(TextView) findViewById(R.id.tneg);
        TextView symr=(TextView) findViewById(R.id.tsym);
        i=0;
        dci=0;
        decchk=0;

        if (editor.getText().toString().length()==0)
        {
            if(ineg==1)
            {
                ineg=0;
                negr.setText(null);
            }
            else
                Toast.makeText(this, "Enter number to add", Toast.LENGTH_SHORT).show();
        }
        else if(snum==1)
        {

            double v;
            v = Double.parseDouble(editor.getText().toString());
            powtmp = pow(powtmp, v);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }
        else if(snum==2)
        {

            powtmp = pow(powtmp, 2);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }
        else if(snum==4)
        {
            powtmp = pow(powtmp, 0.5);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }
        else if(snum==8)
        {
            powtmp = Math.log(powtmp);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }
        else if(snum==9)
        {
            powtmp = Math.log10(powtmp);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }
        else if(snum==5)
        {
            powtmp/=100;
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }

        else if(snum==10)
        {
            if(irad==0)
                powtmp=Math.sin((powtmp));
            else
                powtmp=Math.sin(Math.toRadians(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }

        else if(snum==11)
        {
            if(irad==0)
                powtmp=Math.cos((powtmp));
            else
                powtmp=Math.cos(Math.toRadians(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }
        else if(snum==12)
        {
            if(irad==0)
                powtmp=Math.tan((powtmp));
            else
                powtmp=Math.tan(Math.toRadians(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }

        else if(snum==13)
        {
            powtmp=Math.sinh((powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }
        else if(snum==17)
        {
            if(irad==0)
                powtmp = Math.asin(powtmp);
            else
                powtmp=Math.toDegrees(Math.asin(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }

        else if(snum==14)
        {
           powtmp=Math.cosh((powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }
        else if(snum==18)
        {
            if(irad==0)
                powtmp = Math.acos(powtmp);
            else
                powtmp=Math.toDegrees(Math.acos(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }
        else if(snum==15)
        {
            powtmp=Math.tanh((powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }
        else if(snum==19)
        {
            if(irad==0)
                powtmp = Math.atan(powtmp);
            else
                powtmp=Math.toDegrees(Math.atan(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }
        else if(snum==6)
        {
            powtmp = 1/powtmp;
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }
        else if(snum==7)
        {
            powtmp = factori(powtmp);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "+");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }
        else if(snum==3)
        {
            Double v;
            v = Double.parseDouble(editor.getText().toString());
            powtmp = pow(powtmp, v);
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "+");
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }
        else if(snum==16)
        {
            Double v;
            v = Double.parseDouble(editor.getText().toString());
            powtmp = pow(powtmp, v);
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum+=powtmp;
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "+");
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 1;
        }
        else if(mynum!=0.0000000000001 && calchkr==0)
        {
            double v;
            String symtxtr1=symr.getText().toString();
            String symtxtr2=editor.getText().toString();
            symr.setText(""+symtxtr1+symtxtr2+("+"));
            v=Double.parseDouble(editor.getText().toString());
            if (ineg==1)
            {
                v=0-v;
                ineg=0;
                negr.setText(null);
            }
            if(num==1)
                mynum+=v;
            else if(num==2)
                mynum-=v;
            else if(num==3)
                mynum*=v;
            else if(num==4)
                mynum/=v;
            editor.setText(null);
            num=1;
        }

        else
        {
            if(num==0)
             mynum = Double.parseDouble(editor.getText().toString());
            else if(num==1)
            {
                aftreqlchkr= Double.parseDouble(editor.getText().toString());
                mynum+=aftreqlchkr;
            }
            else if(num==2)
            {
                aftreqlchkr=Double.parseDouble(editor.getText().toString());
                mynum-=aftreqlchkr;
            }
            else if(num==3)
            {
                aftreqlchkr= Double.parseDouble(editor.getText().toString());
                mynum*=aftreqlchkr;
            }
            else if(num==4)
            {
                aftreqlchkr=Double.parseDouble(editor.getText().toString());
                mynum/=aftreqlchkr;
            }
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText(""+symtxtr+symtxtr2+("+"));
            editor.setText(null);
            if (ineg==1)
            {
                mynum=0-mynum;
                ineg=0;
                negr.setText(null);
            }
            num = 1;
        }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void fsub(View view)
    {
        try{
        TextView editor = (TextView) findViewById(R.id.tv);
        TextView negr=(TextView) findViewById(R.id.tneg);
        i=0;
        dci=0;
        decchk=0;
        TextView symr=(TextView) findViewById(R.id.tsym);
        if (editor.getText().toString().length()==0)
        {
            if(ineg==0)
            {
                negr.setText(""+"(-)");
                ineg = 1;
            }
            else
                Toast.makeText(this, "Enter number to subtract", Toast.LENGTH_SHORT).show();
        }
        else if(snum==1)
        {
            double v;
            v = Double.parseDouble(editor.getText().toString());
            powtmp = pow(powtmp, v);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(snum==2)
        {
            powtmp = pow(powtmp, 2);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(snum==4)
        {
            powtmp = pow(powtmp, 0.5);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(snum==8)
        {
            powtmp = Math.log(powtmp);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(snum==9)
        {
            powtmp = Math.log10(powtmp);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(snum==5)
        {
            powtmp /=100;
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(snum==10)
        {
            if(irad==0)
                powtmp=Math.sin((powtmp));
            else
                powtmp=Math.sin(Math.toRadians(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }

        else if(snum==11)
        {
            if(irad==0)
                powtmp=Math.cos((powtmp));
            else
                powtmp=Math.cos(Math.toRadians(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(snum==12)
        {
            if(irad==0)
                powtmp=Math.tan((powtmp));
            else
                powtmp=Math.tan(Math.toRadians(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }

        else if(snum==13)
        {
           powtmp=Math.sinh((powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(snum==17)
        {
            if(irad==0)
                powtmp = Math.asin(powtmp);
            else
                powtmp=Math.toDegrees(Math.asin(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }

        else if(snum==14)
        {
           powtmp=Math.cosh((powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(snum==18)
        {
            if(irad==0)
                powtmp = Math.acos(powtmp);
            else
                powtmp=Math.toDegrees(Math.acos(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(snum==15)
        {
           powtmp=Math.tanh((powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(snum==19)
        {
            if(irad==0)
                powtmp = Math.atan(powtmp);
            else
                powtmp=Math.toDegrees(Math.atan(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(snum==6)
        {
            powtmp = 1/powtmp;
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(snum==7)
        {
            powtmp = factori(powtmp);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(snum==3)
        {
            double v;
            v = Double.parseDouble(editor.getText().toString());
            powtmp = pow(powtmp, v);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(snum==16)
        {
            double v;
            v = Double.parseDouble(editor.getText().toString());
            powtmp = pow(powtmp, v);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "-");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum-=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 2;
        }
        else if(mynum!=0.0000000000001 && calchkr==0)
        {
            double v;
            String symtxtr1=symr.getText().toString();
            String symtxtr2=editor.getText().toString();
            symr.setText(""+symtxtr1+symtxtr2+("-"));
            v=Double.parseDouble(editor.getText().toString());
            if (ineg==1)
            {
                v=0-v;
                ineg=0;
                negr.setText(null);
            }
            if(num==1)
                mynum+=v;
            else if(num==2)
                mynum-=v;
            else if(num==3)
                mynum*=v;
            else if(num==4)
                mynum/=v;
            num=2;
            editor.setText(null);
        }
        else
        {
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText(""+symtxtr+symtxtr2+("-"));
            if(num==0)
                mynum = Double.parseDouble(editor.getText().toString());
            else if(num==1)
            {
                aftreqlchkr= Double.parseDouble(editor.getText().toString());
                mynum+=aftreqlchkr;
            }
            else if(num==2)
            {
                aftreqlchkr=Double.parseDouble(editor.getText().toString());
                mynum-=aftreqlchkr;
            }
            else if(num==3)
            {
                aftreqlchkr= Double.parseDouble(editor.getText().toString());
                mynum*=aftreqlchkr;
            }
            else if(num==4)
            {
                aftreqlchkr=Double.parseDouble(editor.getText().toString());
                mynum/=aftreqlchkr;
            }
            editor.setText(null);
            if (ineg==1)
            {
                mynum=0-mynum;
                ineg=0;
                negr.setText(null);
            }
            num = 2;
        }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }


    public void fmul(View view)
    {
        try{
        TextView editor = (TextView) findViewById(R.id.tv);
        TextView negr=(TextView) findViewById(R.id.tneg);
        i=0;
        dci=0;
        decchk=0;
        TextView symr=(TextView) findViewById(R.id.tsym);

        if (editor.getText().toString().length()==0)
            Toast.makeText(this, "Enter number to multiply", Toast.LENGTH_SHORT).show();

        else if(snum==1)
        {

            double v;
            v = Double.parseDouble(editor.getText().toString());
            powtmp = pow(powtmp, v);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }

        else if(snum==2)
        {

            powtmp = pow(powtmp, 2);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==4)
        {
            powtmp = pow(powtmp, 0.5);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==8)
        {
            powtmp = Math.log(powtmp);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==9)
        {
            powtmp = Math.log10(powtmp);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==5)
        {
            powtmp/=100;
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==10)
        {
            if(irad==0)
                powtmp=Math.sin((powtmp));
            else
                powtmp=Math.sin(Math.toRadians(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==11)
        {
            if(irad==0)
                powtmp=Math.cos((powtmp));
            else
                powtmp=Math.cos(Math.toRadians(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==12)
        {
            if(irad==0)
                powtmp=Math.tan((powtmp));
            else
                powtmp=Math.tan(Math.toRadians(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==13)
        {
            powtmp=Math.sinh((powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==17)
        {
            if(irad==0)
                powtmp = Math.asin(powtmp);
            else
                powtmp=Math.toDegrees(Math.asin(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==14)
        {
             powtmp=Math.cosh((powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==18)
        {
            if(irad==0)
                powtmp = Math.acos(powtmp);
            else
                powtmp=Math.toDegrees(Math.acos(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==15)
        {
           powtmp=Math.tanh((powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==19)
        {
            if(irad==0)
                powtmp = Math.atan(powtmp);
            else
                powtmp=Math.toDegrees(Math.atan(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==6)
        {
            powtmp = 1/powtmp;
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==7)
        {
            powtmp = factori(powtmp);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==3)
        {

            double v;
            v = Double.parseDouble(editor.getText().toString());
            powtmp = pow(powtmp, v);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(snum==16)
        {

            double v;
            v = Double.parseDouble(editor.getText().toString());
            powtmp = pow(powtmp, v);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "x");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 3;
        }
        else if(mynum!=0.0000000000001 && calchkr==0)
        {
            String symtxtr1=symr.getText().toString();
            String symtxtr2=editor.getText().toString();
            symr.setText(""+symtxtr1+symtxtr2+("x"));
            double v;
            v=Double.parseDouble(editor.getText().toString());
            if (ineg==1)
            {
                v=0-v;
                ineg=0;
                negr.setText(null);
            }
            if(num==1)
                mynum+=v;
            else if(num==2)
                mynum-=v;
            else if(num==3)
                mynum*=v;
            else if(num==4)
                mynum/=v;
            num=3;
            editor.setText(null);
        }
        else {
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText(""+symtxtr+symtxtr2+("x"));
            if(num==0)
                mynum = Double.parseDouble(editor.getText().toString());
            else if(num==1)
            {
                aftreqlchkr= Double.parseDouble(editor.getText().toString());
                mynum+=aftreqlchkr;
            }
            else if(num==2)
            {
                aftreqlchkr=Double.parseDouble(editor.getText().toString());
                mynum-=aftreqlchkr;
            }
            else if(num==3)
            {
                aftreqlchkr= Double.parseDouble(editor.getText().toString());
                mynum*=aftreqlchkr;
            }
            else if(num==4)
            {
                aftreqlchkr=Double.parseDouble(editor.getText().toString());
                mynum/=aftreqlchkr;
            }
            editor.setText(null);
            if (ineg==1)
            {
                mynum=0-mynum;
                ineg=0;
                negr.setText(null);
            }
            num = 3;
        }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void fdiv(View view)
    {
        try{
        TextView editor = (TextView) findViewById(R.id.tv);
        TextView negr=(TextView) findViewById(R.id.tneg);
        i=0;
        dci=0;
        decchk=0;
        TextView symr=(TextView) findViewById(R.id.tsym);

        if (editor.getText().toString().length()==0)
            Toast.makeText(this, "Enter number to divide", Toast.LENGTH_SHORT).show();

        else if(snum==1)
        {

            double v;
            v = Double.parseDouble(editor.getText().toString());
            powtmp = pow(powtmp, v);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==2)
        {

            powtmp = pow(powtmp, 2);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==4)
        {
            powtmp = pow(powtmp, 0.5);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==8)
        {
            powtmp = Math.log(powtmp);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==9)
        {
            powtmp = Math.log10(powtmp);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum*=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==5)
        {
            powtmp/=100;
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==10)
        {
            if(irad==0)
                powtmp=Math.sin((powtmp));
            else
                powtmp=Math.sin(Math.toRadians(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==11)
        {
            if(irad==0)
                powtmp=Math.cos((powtmp));
            else
                powtmp=Math.cos(Math.toRadians(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==12)
        {
            if(irad==0)
                powtmp=Math.tan((powtmp));
            else
                powtmp=Math.tan(Math.toRadians(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }

        else if(snum==13)
        {
          powtmp=Math.sinh((powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==17)
        {
            if(irad==0)
                powtmp = Math.asin(powtmp);
            else
                powtmp=Math.toDegrees(Math.asin(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==14)
        {
           powtmp=Math.cosh((powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==18)
        {
            if(irad==0)
                powtmp = Math.acos(powtmp);
            else
                powtmp=Math.toDegrees(Math.acos(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==15)
        {
           powtmp=Math.tanh((powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==19)
        {
            if(irad==0)
                powtmp = Math.atan(powtmp);
            else
                powtmp=Math.toDegrees(Math.atan(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==6)
        {
            powtmp = 1/powtmp;
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==7)
        {
            powtmp = factori(powtmp);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==3)
        {

            double v;
            v = Double.parseDouble(editor.getText().toString());
            powtmp = pow(powtmp, v);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(snum==16)
        {

            double v;
            v = Double.parseDouble(editor.getText().toString());
            powtmp = pow(powtmp, v);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "÷");
            if(mynum==0.0000000000001)
                mynum=powtmp;
            else
                mynum/=powtmp;
            if (mynum > 999999999999999.9 || mynum < (-999999999999999.9)) {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                df.format(mynum);
            } else {
                DecimalFormat df = new DecimalFormat("###############.###################");
                df.format(mynum);
            }
            editor.setText(null);
            snum=0;
            num = 4;
        }
        else if(mynum!=0.0000000000001 && calchkr==0)
        {

            String symtxtr1=symr.getText().toString();
            String symtxtr2=editor.getText().toString();
            symr.setText(""+symtxtr1+symtxtr2+("÷"));
            double v;
            v=Double.parseDouble(editor.getText().toString());
            if (ineg==1)
            {
                v=0-v;
                ineg=0;
                negr.setText(null);
            }
            if(num==1)
                mynum+=v;
            else if(num==2)
                mynum-=v;
            else if(num==3)
                mynum*=v;
            else if(num==4)
                mynum/=v;
            num=4;
            editor.setText(null);
        }
        else {
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText(""+symtxtr+symtxtr2+("÷"));
            if(num==0)
                mynum = Double.parseDouble(editor.getText().toString());
            else if(num==1)
            {
                aftreqlchkr= Double.parseDouble(editor.getText().toString());
                mynum+=aftreqlchkr;
            }
            else if(num==2)
            {
                aftreqlchkr=Double.parseDouble(editor.getText().toString());
                mynum-=aftreqlchkr;
            }
            else if(num==3)
            {
                aftreqlchkr= Double.parseDouble(editor.getText().toString());
                mynum*=aftreqlchkr;
            }
            else if(num==4)
            {
                aftreqlchkr=Double.parseDouble(editor.getText().toString());
                mynum/=aftreqlchkr;
            }
            editor.setText(null);
            if (ineg==1)
            {
                mynum=0-mynum;
                ineg=0;
                negr.setText(null);
            }
            num = 4;
        }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void feql(View view)
    {
        try{
        TextView editor = (TextView) findViewById(R.id.tv);
        Double v;
        calchkr=1;
        editor.setTextSize(otxz);
        TextView symr=(TextView) findViewById(R.id.tsym);

        if(ineg==1)
        {
            if(editor.getText().length()!=0)
            {
                double negtemp;
                TextView negr=(TextView) findViewById(R.id.tneg);
                negtemp=Double.parseDouble(editor.getText().toString());
                ineg=0;
                negr.setText(null);
                negtemp=0-negtemp;
                editor.setTextColor(Color.parseColor("#08a52d"));
                editor.setText(""+negtemp);
            }
        }

        if(editor.getText().length()==0 && mynum==0.0000000000001)
        {
            Toast.makeText(this, "Enter number to calculate", Toast.LENGTH_SHORT).show();
        }
        else if(editor.getText().toString().length()==0)
        {
            symr.setText(""+("="));

            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum>99999999.9 || mynum<(-99999999.9))
                editor.setTextSize(txsz);
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
        }
        else if(snum==5)
        {
            powtmp/=100;
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }

        else if(snum==10)
        {
            if(irad==0)
                powtmp=Math.sin((powtmp));
            else
                powtmp=Math.sin(Math.toRadians(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }
        else if(snum==11)
        {
            if(irad==0)
                powtmp=Math.cos((powtmp));
            else
                powtmp=Math.cos(Math.toRadians(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }
        else if(snum==12)
        {
            if(irad==0)
                powtmp=Math.tan((powtmp));
            else
                powtmp=Math.tan(Math.toRadians(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }
        else if(snum==13)
        {
            powtmp=Math.sinh((powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }
        else if(snum==17)
        {
            if(irad==0)
                powtmp = Math.asin(powtmp);
            else
                powtmp=Math.toDegrees(Math.asin(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }
        else if(snum==14)
        {
           powtmp=Math.cosh((powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }
        else if(snum==18)
        {
            if(irad==0)
                powtmp = Math.acos(powtmp);
            else
                powtmp=Math.toDegrees(Math.acos(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }
        else if(snum==15)
        {
           powtmp=Math.tanh((powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }
        else if(snum==19)
        {
            if(irad==0)
                powtmp = Math.atan(powtmp);
            else
                powtmp=Math.toDegrees(Math.atan(powtmp));
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }
        else if(snum==6)
        {
            powtmp=1/powtmp;
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }
        else if(snum==7)
        {
            powtmp=factori(powtmp);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }

        else if(snum==1)
        {
            v=Double.parseDouble(editor.getText().toString());
            powtmp=pow(powtmp,v);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }
        else if(snum==3)
        {
            v=Double.parseDouble(editor.getText().toString());
            powtmp=pow(powtmp,v);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }
        else if(snum==16)
        {
            v=Double.parseDouble(editor.getText().toString());
            powtmp=pow(powtmp,v);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }

        else if(snum==2)
        {
            powtmp=pow(powtmp,2);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }

        else if(snum==4)
        {
            powtmp=pow(powtmp,0.5);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }

        else if(snum==8)
        {
            powtmp=Math.log(powtmp);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }

        else if(snum==9)
        {
            powtmp=Math.log10(powtmp);
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            symr.setText("" +symtxtr+ "=");
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(mynum==0.0000000000001 || res!=0.0)
                mynum=powtmp;
            else if(num==1)
                mynum+=powtmp;
            else if(num==2)
                mynum-=powtmp;
            else if(num==3)
                mynum*=powtmp;
            else if(num==4)
                mynum/=powtmp;
            if (mynum >999999999999999.9 || mynum <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(mynum));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(mynum));
            }
            snum=0;
            num=0;
        }

        else if(num==0)
        {
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText("" +symtxtr+symtxtr2+ "=");
            if(editor.getText().length()==0)
                Toast.makeText(this, "Enter number to calculate", Toast.LENGTH_SHORT).show();
            else {
                double eqlm;
                eqlm = Double.parseDouble(editor.getText().toString());
                if (eqlm > 99999999.9 || eqlm < (-99999999.9)) {
                    editor.setTextSize(txsz);
                    DecimalFormat df = new DecimalFormat("###############.#########E0");
                    editor.setText("" + df.format(eqlm));
                }
                else {
                    DecimalFormat df = new DecimalFormat("###############.###################");
                    editor.setText("" + df.format(eqlm));
                }
            }
        }

        else {
            String symtxtr=symr.getText().toString();
            if(symtxtr.contains("="))
            {
                symtxtr="";
            }
            String symtxtr2=editor.getText().toString();
            symr.setText(""+symtxtr+symtxtr2+("="));
            v = Double.parseDouble(editor.getText().toString());
            TextView negr=(TextView) findViewById(R.id.tneg);

            if (num == 1) {
                res = mynum + v;
            } else if (num == 2) {
                res = mynum - v;
            } else if (num == 3) {
                res = mynum * v;
            } else if (num == 4) {
                res = mynum / v;
            }
            double len;
            num=0;
            if(ineg==1)
            {
                res=0-res;
                ineg=0;
                negr.setText(null);
            }
            editor.setText(""+res);
            len=Double.parseDouble(editor.getText().toString());
            editor.setTextColor(Color.parseColor("#08a52d"));
            if(len>99999999.9 || len<(-99999999.9))
                editor.setTextSize(txsz);
            if (len >999999999999999.9 || len <(-999999999999999.9))
            {
                DecimalFormat df = new DecimalFormat("###############.#########E0");
                editor.setText("" + df.format(res));
            } else
            {
                DecimalFormat df = new DecimalFormat("###############.###################");
                editor.setText("" + df.format(res));
            }
        }
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

}
