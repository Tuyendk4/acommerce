package com.acommerce.pojo;

public class Product {

  private int qty;
  private String sku;
  private String updatedTime;

  public int getQty() {
    return qty;
  }

  public void setQty(int qty) {
    this.qty = qty;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public String getUpdatedTime() {
    return updatedTime;
  }

  public void setUpdatedTime(String updatedTime) {
    this.updatedTime = updatedTime;
  }

  public String toString() {
    return "|     SKU     |   Qty   |       updatedTime         |\n|   " + this.sku +"   |    " + this.qty + "    |" + this.updatedTime + "|";
  }
}
