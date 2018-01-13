package com.by.automate.testCases.Interface_new;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import static com.by.automate.testCases.Interface_new.interface_url.serials;

public class CommonTools {

    /*
    从serials数组里，随机抽取一台设备的serial
     */
    public String[] getRandomSerial(){
        String[] serial_new =  interface_url.serials;
        int max = 3;
        int min = 0;
        Random random = new Random();
        int random1 = random.nextInt(max);
        //随机选取一个serial，再放到新的数组里
        String device_serial = serial_new[random1];
        String[] serial = {device_serial} ;
        return serial;
    }

    public static void main(String args[]){
        CommonTools c = new CommonTools();
        System.out.println(c.getRandomSerial());
    }

}
