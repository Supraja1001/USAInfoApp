package com.example.supraja.usainfoapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Supraja on 6/12/2017.
 */

public class StatesList {

    private String title1;
    private String title2;
    private int imageID;

    public String getFirstTitle() {
        return title1;
    }

    public void setFirstTitle(String title1) {
        this.title1 = title1;
    }

    public String getSecondTitle() {
        return title2;
    }

    public void setSecondTitle(String title2) {
        this.title2 = title2;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public static List<StatesList> getData()
    {
        List<StatesList> dataList = new ArrayList<>();

        int[] imageIds = getImages();
        String[] firstTitles = getFirstTitles();
        String[] secondTitles = getSecondTitles();



        for(int i=0;i<firstTitles.length;i++){

            StatesList item = new StatesList();
            item.setFirstTitle(firstTitles[i]);
            item.setSecondTitle(secondTitles[i]);
            item.setImageID(imageIds[i]);
            dataList.add(item);
        }

        return  dataList;
    }

    private static int[] getImages(){
        return new int[]{
                android.R.drawable.ic_dialog_email, android.R.drawable.btn_star, android.R.drawable.ic_delete,
                android.R.drawable.btn_default_small,  android.R.drawable.ic_btn_speak_now, android.R.drawable.ic_input_add,
                android.R.drawable.ic_menu_send
        };
    }

    private static  String[] getFirstTitles(){
        return new String[]{
                "Row 0","Row 1","Row 2","Row 3","Row 4", "Row 5", "Row 6"
        };
    }

    private static  String[] getSecondTitles(){
        return new String[]{
                "RowSubTitle 0","RowSubTitle 1","RowSubTitle 2","RowSubTitle 3","RowSubTitle 4",
                "RowSubTitle 5", "RowSubTitle 6"
        };
    }
}
