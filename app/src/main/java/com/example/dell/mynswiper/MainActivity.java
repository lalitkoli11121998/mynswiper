package com.example.dell.mynswiper;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    firstShow show;
    Intent intent;
    ArrayList arrayList;
   static int count = 1;
     public static String first ="ab";
   public static String first_key = "first";
    public static String  second = "ty";
    public static String second_key = "second";
    public static String third = "ad";
    public static String three_key = "three";
    public static String fourth = "rt";
    public static String fourth_key = "four";
    public static String fifth = "da";
    public static String five_key = "five";
    static  int  mainCount = 0;
    LinearLayout Rootlayout;
    public static  int myn = -1;
    public  static int empty = 0;
    mynbutton[][] board;
    mynbutton mynbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Rootlayout = findViewById(R.id.rootlayout);
        arrayList = new ArrayList(100);
         Intent intent  =getIntent();
        intigame();
    }
    public int [] i= {-1,-1,-1,0,0,1,1,1};
    public int [] j = {-1,0,1,-1,1,-1,0,1};

   public static int size = 8;
    int []a = new int[10];
    int []b= new int[10];

    public  void intigame()
    {

        board = new mynbutton[size][size];
       setupboard();
       setupmines();
    }
    void setupboard() {
        Rootlayout.removeAllViews();
        for (int i = 0; i < size; i++) {

            LinearLayout rowLayout = new LinearLayout(this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            rowLayout.setLayoutParams(params);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < size; j++) {
                mynbutton = new mynbutton(this);
                LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                mynbutton.setLayoutParams(buttonParams);
                mynbutton.setOnClickListener(this);
                mynbutton.setrowcol(i,j);
                board[i][j] = mynbutton;
                rowLayout.addView(mynbutton);

            }
            Rootlayout.addView(rowLayout);
        }
    }


    @Override
    public void onClick(View v) {
       mainCount++;
        mynbutton button = (mynbutton)v;
       int value =  button.getData();
       if(value == -1) {
           gameover();
       }
       else if(button.getData()!= -1&& button.getData()!= 0)
       {
           String s;
           s = Integer.toString(button.getData());
           button.setText(s);
       }
       else{
           int i = button.getRow();
           int j = button.getCol();
           shownighbours(i , j);
       }
       button.setEnabled(false);


    }

    private void shownighbours(int p, int q) {
        for (int x = 0; x < 8; x++) {
            if ((p + i[x] >= 0 && p + i[x] < size) && ((q+ j[x]) < size && (q+ j[x] >= 0))) {
                int first = p + i[x];
                int second = q+ j[x];
                int d = board[first][second].getData();
                if (d != 0) {
                    if (d == -1) {
                        board[first][second].setBackgroundResource(R.drawable.bomb);
                    } else {
                        String s = Integer.toString(d);
                        board[first][second].setText(s);
                        board[first][second].setEnabled(false);
                    }
                } else if (d == 0) {
                    if (board[first][second].ts != false) {
                        board[first][second].ts = false;
                        board[first][second].setBackgroundResource(R.drawable.empty);
                        shownighbours(first, second);
                    }
                }
            }
        }
    }


    void gameover()
    {

        Toast.makeText(this,"game over",Toast.LENGTH_SHORT).show();
        String S = String.valueOf(size*size);
        String t = String.valueOf(mainCount);
        String y = S +"/" + t;
        Toast.makeText(this, y, Toast.LENGTH_SHORT).show();
        if(count == 1)
        {
            first = mainCount +"";
        }
        if(count == 2)
        {
            second = mainCount + "";
        }
        if(count == 3)
        {
            third = mainCount + "";
        }
        if(count == 4)
        {
            fourth = mainCount + "";
        }
        if(count == 5) {
            fifth = mainCount + "";
        }
        mainCount = 0;

        for(int i =0;i<8;i++) {
            board[a[i]][b[i]].setBackgroundResource(R.drawable.bomb);
            board[a[i]][b[i]].setEnabled(false);
        }
        for(int k =0;k<size;k++)
        {
            for(int j =0;j<size;j++)
            {
                board[k][j].setEnabled(false);
            }
        }

    }


    public  void setnighbours(int first, int second) {

                    for (int x = 0; x < 8; x++) {

                        if ((first + i[x] >=0  && first +i[x] < size) &&((second + j[x]) <size && (second + j[x] >=0))) {
                                int ni = first + i[x];

                                int nj = second + j[x];
                                int a = board[ni][nj].getData();
                                if(a!= -1) {
                                    board[ni][nj].setData(a + 1);
                                }

                        }

                    }

        }


   public  void setupmines() {

        int min =0;
        int k =0;
        int l =0;
       Random random = new Random();
       for (int i = 0; i < 8; i++) {
           int first = random.nextInt(size);
           int second = random.nextInt(size);
           mynbutton button  =board[first][second];
           a[k] = first;
           b[l]= second;
           k++;
           l++;
           button.setData(-1);
              setnighbours(first, second);
       }
   }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id == R.id.reset){
            if(count < 6) {
                count++;
            }else{
                count =1;
            }
            intigame();
        }
        else if(id == R.id.close)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to close game");
            builder.setCancelable(true);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    firstShow show = new firstShow();
                    show.finish();

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.setTitle("Alert");
            alert.show();
        }
        else if(id == R.id.eight){
            size = 8;
            intigame();
            //doubt
            item.setChecked(true);
        }else if(id == R.id.nine){
            size = 9;
            intigame();
            item.setChecked(true);
        }
        else if(id == R.id.ten){
            size = 10;
            intigame();
            item.setChecked(true);
        }

        else if(id == R.id.see)
        {
                intent = new Intent(this, showactivity.class);
                if(first !="ab") {
                    intent.putExtra(first_key, first);
                }
                if(second != "ty") {
                    intent.putExtra(second_key, second);
                }
                if(third!= "ad") {
                    intent.putExtra(three_key, third);
                }
                if(fourth!= "rt") {
                    intent.putExtra(fourth_key, fourth);
                }
                if(fifth!= "da") {
                    intent.putExtra(five_key, fifth);
                }
                startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }
}





