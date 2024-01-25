package com.duru.project.domain;

public class InsertBasketData {

    private int productAmount;
    private int productSeq;
    private int userSeq;

    // 기본 생성자와 getter, setter 메소드는 생략하였습니다.

    public InsertBasketData() {
    }

    public InsertBasketData(int productAmount, int productSeq, int userSeq) {
        this.productAmount = productAmount;
        this.productSeq = productSeq;
        this.userSeq = userSeq;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public long getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(int productSeq) {
        this.productSeq = productSeq;
    }

    public long getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }
}