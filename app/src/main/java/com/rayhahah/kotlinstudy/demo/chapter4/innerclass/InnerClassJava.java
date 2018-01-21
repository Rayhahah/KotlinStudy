package com.rayhahah.kotlinstudy.demo.chapter4.innerclass;

import net.println.kotlin.chapter4.innerclass.OnClickListener;
import net.println.kotlin.chapter4.innerclass.View;

/**
 * Created by benny on 4/4/17.
 */
public class InnerClassJava {
    private int a;

    public class Inner{
        public void hello(){
            System.out.println(InnerClassJava.this.a);
        }
    }

    public static void main(String... args) {
        InnerClassJava innerClassJava = new InnerClassJava();
        View view = new View();
        view.setOnClickListener(new OnClickListener() {
            public void onClick() {

            }
        });
    }
}
