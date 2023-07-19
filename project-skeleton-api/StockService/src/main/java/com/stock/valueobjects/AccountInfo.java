package com.stock.valueobjects;

import java.io.Serializable;

public class AccountInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;
  private double balance;

  public AccountInfo() {}

  public AccountInfo(String name, double balance) {
    super();
    this.name = name;
    this.balance = balance;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public double getBalance() {
    return balance;
  }
  public void setBalance(double balance) {
    this.balance = balance;
  }


}