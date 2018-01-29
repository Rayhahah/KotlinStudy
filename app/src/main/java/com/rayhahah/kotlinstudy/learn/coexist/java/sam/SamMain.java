package com.rayhahah.kotlinstudy.learn.coexist.java.sam;

import cn.kotliner.kotlin.sam.SAMInKotlin;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class SamMain {
    public static void main(String... args) {
        SAMInKotlin samInKotlin = new SAMInKotlin();
        samInKotlin.addTask(new Function0<Unit>() {
            public Unit invoke() {
                return null;
            }
        });
    }
}
