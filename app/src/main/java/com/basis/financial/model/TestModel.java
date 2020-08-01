package com.basis.financial.model;

import java.util.ArrayList;

public class TestModel {

    ArrayList<DataModel> data = new ArrayList<>();

    // Getter Methods
    public ArrayList<DataModel> getData() {
        return data;
    }



    public class DataModel {

        private String id;
        private String text;


        // Getter Methods

        public String getId() {
            return id;
        }

        public String getText() {
            return text;
        }

        // Setter Methods

        public void setId(String id) {
            this.id = id;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
