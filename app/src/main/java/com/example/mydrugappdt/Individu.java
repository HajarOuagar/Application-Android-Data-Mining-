package com.example.mydrugappdt;

public class Individu {

        private int age;
        private String sex;
        private String BP;
        private String Cholesterol;
        private Double Na;
        private Double K;
        private String Drug;

        public Individu(int i, String m, String high, String high1, Double v, Double v1, String d) {
            this.setAge(i);
            this.setSex(m);
            this.setBP(high);
            this.setCholesterol(high1);
            this.setNa(v);
            this.setK(v1);
            this.setDrug(d);
        }


        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBP() {
            return BP;
        }

        public void setBP(String BP) {
            this.BP = BP;
        }

        public String getCholesterol() {
            return Cholesterol;
        }

        public void setCholesterol(String cholesterol) {
            this.Cholesterol = cholesterol;
        }

        public Double getNa() {
            return Na;
        }

        public void setNa(Double na) {
            this.Na = na;
        }

        public Double getK() {
            return K;
        }

        public void setK(Double k) {
            this.K = k;
        }

        public String getDrug() {
            return Drug;
        }

        public void setDrug(String drug) {
            this.Drug = drug;
        }
    }



