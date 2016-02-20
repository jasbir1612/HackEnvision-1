package com.cereal_killers.hackenvsion;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by rajdeep1008 on 21/10/15.
 */
public class Toaster {

    public static void toast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
