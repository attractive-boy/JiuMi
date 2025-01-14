package com.miniapp.talks.bean;

public class LabelBean {
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "LabelBean{" +
                "label='" + label + '\'' +
                '}';
    }
}
