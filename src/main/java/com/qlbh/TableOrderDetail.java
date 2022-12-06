package com.qlbh;

public class TableOrderDetail {
        private  int stt;
        private int id = 1;
        private int orderID;
        private int productID;
        private String productName;
        private int productQuantity;
        private double productPrice;
        private double total;

        public TableOrderDetail(int orderID,int productID,  String productName, int productQuantity, double price, double total) {

            this.orderID = orderID;
            this.productID = productID;
            this.productName = productName;
            this.productQuantity = productQuantity;
            this.productPrice = price;
            this.total = total;
        }

    public TableOrderDetail(int stt, int id, int productID, String productName, int productQuantity, double productPrice, double total) {
        this.stt = stt;
        this.id = id;
        this.productID = productID;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.total = total;
    }

    public  int getStt() {

        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
            return productID;
        }

        public void setProductID(int productID) {
            this.productID = productID;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getProductQuantity() {
            return productQuantity;
        }

        public void setProductQuantity(int productQuantity) {
            this.productQuantity = productQuantity;
        }

        public double getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(double productPrice) {
            this.productPrice = productPrice;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }
}
