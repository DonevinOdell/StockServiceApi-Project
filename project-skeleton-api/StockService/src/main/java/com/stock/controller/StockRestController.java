package com.stock.controller;

import com.stock.DividendStock;
import com.stock.Stock;
import com.stock.StockAccount;
import com.stock.StockException;
import com.stock.StockFileManager;
import com.stock.valueobjects.AccountInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/stocks")
public class StockRestController{

  static final String JSON = "application/json";

  // Create account
  @PostMapping(path = "/account", consumes = JSON, produces = JSON)
  @ResponseBody
  public AccountInfo createAccount(@RequestBody() AccountInfo accountInfo,HttpServletResponse response) {
	System.out.println("In create account!");
    // we only allow creation of one account but we'll follow
    // follow RESTful convention and return the Location header
    response.setHeader("Location", "/stocks/account");
    response.setStatus(HttpServletResponse.SC_CREATED);
    return accountInfo;
  }

  // Get account
  @GetMapping(path = "/account", produces = JSON)
  @ResponseBody
  public StockAccount getAccount(HttpServletResponse response) throws Exception {
	response.setStatus(HttpServletResponse.SC_OK);
	StockAccount account = StockFileManager.getStoredAccount();
    return account;
    
  }
  
  // Buy stock
  @PostMapping(path = "/account/buy", consumes = JSON, produces = JSON)
  @ResponseBody
  public StockAccount buyStock(@RequestBody Stock toBuy, HttpServletResponse response) throws Exception {
	  response.setStatus(HttpServletResponse.SC_OK);
	  StockAccount account = StockFileManager.getStoredAccount();
	  System.out.println("In buy dividend: " + toBuy);
	  account.buyStock(toBuy);
	  StockFileManager.storeAccountInfo(account);
	  return account;
  }
  
  // Buy dividend stock
  @PostMapping(path = "/account/buydividend", consumes = JSON, produces = JSON)
  @ResponseBody
  public StockAccount buyStock(@RequestBody DividendStock toBuy, HttpServletResponse response) throws Exception {
	  response.setStatus(HttpServletResponse.SC_OK);
	  StockAccount account = StockFileManager.getStoredAccount();
	  System.out.println("In buy: " + toBuy);
	  account.buyStock(toBuy);
	  StockFileManager.storeAccountInfo(account);
	  return account;

  }
  
  // Sell stock
  @PostMapping(path = "/account/sell", consumes = JSON, produces = JSON) 
  @ResponseBody
  public StockAccount sellStock(@RequestBody Stock toSell, HttpServletResponse response) throws Exception {
	  response.setStatus(HttpServletResponse.SC_OK);
	  StockAccount account = StockFileManager.getStoredAccount();
	  System.out.println("In buy: " + toSell);
	  account.sellStock(toSell);
	  return account;
  }
  
  // Buy dividend stock
  @PostMapping(path = "/account/selldividend", consumes = JSON, produces = JSON)
  @ResponseBody
  public StockAccount sellStock(@RequestBody DividendStock toSell, HttpServletResponse response) throws Exception {
	  response.setStatus(HttpServletResponse.SC_OK);
	  StockAccount account = StockFileManager.getStoredAccount();
	  System.out.println("In buy: " + toSell);
	  account.sellStock(toSell);
	  StockFileManager.storeAccountInfo(account);
	  return account;

  }
  
  // Get account name
  @GetMapping(path = "/account/name", produces = JSON) 
  @ResponseBody
  public String getName(@RequestBody Stock getName, HttpServletResponse response) throws Exception {
	  response.setStatus(HttpServletResponse.SC_OK);
	  return StockFileManager.getStoredAccount().getName();
  }
  
  // Get account balance
  @GetMapping(path = "/account/balance", produces = JSON)
  @ResponseBody
  public double getBalance(@RequestBody Stock getBalance, HttpServletResponse response) throws Exception {
	  response.setStatus(HttpServletResponse.SC_OK);
	  return StockFileManager.getStoredAccount().getBalance();
  }
  
  
  // Get held stocks
  @GetMapping(path = "/account/heldstocks", produces = JSON)
  @ResponseBody
  public Collection<Stock> getHeldStocks(@RequestBody HttpServletResponse response) throws Exception {
	  response.setStatus(HttpServletResponse.SC_OK);
	  return StockFileManager.getStoredAccount().getHeldStocks();
  }

}
