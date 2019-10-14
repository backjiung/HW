package com.cookandroid.hw;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    String now = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        final TextView display_view = (TextView)findViewById(R.id.textView);
        String user_id = intent.getExtras().getString("id");
        display_view.setText(getName(user_id) + "님 가입되었습니다.");

        Button n1 = (Button)findViewById(R.id.n1);
        n1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_view.setText(now.concat("1"));
                now = display_view.getText().toString();
            }
        });
        Button n2 = (Button)findViewById(R.id.n2);
        n2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_view.setText(now.concat("2"));
                now = display_view.getText().toString();
            }
        });
        Button n3 = (Button)findViewById(R.id.n3);
        n3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_view.setText(now.concat("3"));
                now = display_view.getText().toString();
            }
        });
        Button n4 = (Button)findViewById(R.id.n4);
        n4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_view.setText(now.concat("4"));
                now = display_view.getText().toString();
            }
        });
        Button n5 = (Button)findViewById(R.id.n5);
        n5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_view.setText(now.concat("5"));
                now = display_view.getText().toString();
            }
        });
        Button n6 = (Button)findViewById(R.id.n6);
        n6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_view.setText(now.concat("6"));
                now = display_view.getText().toString();
            }
        });
        Button n7 = (Button)findViewById(R.id.n7);
        n7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_view.setText(now.concat("7"));
                now = display_view.getText().toString();
            }
        });
        Button n8 = (Button)findViewById(R.id.n8);
        n8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_view.setText(now.concat("8"));
                now = display_view.getText().toString();
            }
        });
        Button n9 = (Button)findViewById(R.id.n9);
        n9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_view.setText(now.concat("9"));
                now = display_view.getText().toString();
            }
        });
        Button n0 = (Button)findViewById(R.id.n0);
        n0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_view.setText(now.concat("0"));
                now = display_view.getText().toString();
            }
        });


        Button add = (Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_view.setText(now.concat("+"));
                now = display_view.getText().toString();
            }
        });
        Button sub = (Button)findViewById(R.id.sub);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_view.setText(now.concat("-"));
                now = display_view.getText().toString();
            }
        });
        Button mult = (Button)findViewById(R.id.mult);
        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_view.setText(now.concat("*"));
                now = display_view.getText().toString();
            }
        });
        Button dis = (Button)findViewById(R.id.dis);
        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_view.setText(now.concat("/"));
                now = display_view.getText().toString();
            }
        });

        Button calc = (Button)findViewById(R.id.calc);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double result = eval(now);
                    display_view.setText(Double.toString(result));
                    now = "";
                }catch (Exception e) {
                    display_view.setText("식이 틀렸음");
                    now = "";
                }
            }
        });
        Button clear = (Button)findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_view.setText("");
                now = "";
            }
        });


    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                return x;
            }
        }.parse();
    }

    public String getName(String id){
        String name = null;

        try{
            FileInputStream fis = openFileInput(id);

            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            reader.readLine();
            name = reader.readLine();
        } catch (Exception e){
            e.printStackTrace();
        }
        return name;
    }
}