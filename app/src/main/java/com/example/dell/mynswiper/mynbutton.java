package com.example.dell.mynswiper;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

/**
 * Created by dell on 2/3/2018.
 */

public class mynbutton  extends AppCompatButton{

int row, col;
boolean ts=true;
int data= MainActivity.empty;
    public mynbutton(Context context) {
        super(context);




    }


    public int getRow(){
        return row;
    }
    public int getCol()
    {
        return  col;
    }

    void setrowcol(int row ,int col)
    {
        this.row = row;
        this.col = col;
    }

    public void setData(int value)
    {
        this.data =value;
    }
    public int  getData()
    {
        return  data;
    }




}
