package com.miniapp.talks.bean;

public class CommGridTpoicBean {
    private String A;
    private String B;
    private int C;

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public int getC() {
        return C;
    }

    public void setC(int c) {
        C = c;
    }

    @Override
    public String toString() {
        return "CommGridTpoicBean{" +
                "A='" + A + '\'' +
                ", B='" + B + '\'' +
                ", C=" + C +
                '}';
    }
}
