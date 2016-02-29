package com.abhishek.eventmanager.Controller;

/**
 * Created by Abhishek on 2/28/2016.
 */
public enum Month{
    JAN(0),
    FEB(1),
    MAR(2),
    APR(3),
    MAY(4),
    JUN(5),
    JUL(6),
    AUG(7),
    SEP(8),
    OCT(9),
    NOV(10),
    DEC(11);

    private int mValue;

    Month(int value){
        this.mValue = value;
    }

}
