package com.example.notes.Model.Notes;

public  enum SORT_TYPE {
    URGENT("Urgent"),
    WORK("Work"),
    DATE("Date");

    private final String label;
    SORT_TYPE(String label) {
        this.label = label;
    }


    public String getLabel() {
        return label;
    }
    //for title sorting we'll use a separate method
}
