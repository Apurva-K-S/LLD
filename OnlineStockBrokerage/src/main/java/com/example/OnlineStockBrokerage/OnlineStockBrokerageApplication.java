package com.example.OnlineStockBrokerage;

import com.example.OnlineStockBrokerage.Entities.Order;
import com.example.OnlineStockBrokerage.Entities.Portfolio;
import com.example.OnlineStockBrokerage.Entities.TradeExecutionLog;
import com.example.OnlineStockBrokerage.Enums.OrderType;
import com.example.OnlineStockBrokerage.Enums.StockAccountType;
import com.example.OnlineStockBrokerage.Factory.StockAccountFactory;
import com.example.OnlineStockBrokerage.Service.*;
import com.example.OnlineStockBrokerage.Strategy.PriceTimePriorityExecutionStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class OnlineStockBrokerageApplication {

	public static void main(String[] args) {
		OrderManagementService orderService =
				new OrderManagementService(new PriceTimePriorityExecutionStrategy());
		StockAccountFactory accountFactory = new StockAccountFactory();
		StockAccountManagementService accountService = new StockAccountManagementService(accountFactory);
		MarketDataServiceImpl marketData = new MarketDataServiceImpl();
		UserManagementService userManagementService = new UserManagementService();
		FundManagementService fundManagementService = new FundManagementService(accountService);

		// 1. Create users
		var user1 = userManagementService.createUser("Alice", "alice@example.com", "8500809588");
		var user2 = userManagementService.createUser("Bob", "bob@example.com","9925372564");

		// 2. create Account
		var account1 = accountService.createAccount(StockAccountType.STANDARD, user1.getId(), 10000);
		var account2 = accountService.createAccount(StockAccountType.STANDARD, user2.getId(), 20000);

		// 3. deposit funds
		System.out.println("\ndeposit\n");
		fundManagementService.depositFunds(account1.getId(), 500);
		accountService.emitEvent(account1);

		// 4. withdraw funds
		System.out.println("\nwithdraw\n");
		fundManagementService.withdrawFunds(account2.getId(), 500);
		accountService.emitEvent(account2);

		// 5. create company
		var company1 = marketData.addCompany("Facebook",1500,1450);
		var company2 = marketData.addCompany("Google",2500,2450);

		// 6. getQuote
		System.out.println("quote company1 => " + marketData.getQuote(company1.getId()));

		// 7. updateQuote
		marketData.updateQuote(company2.getId(), 5000);
		System.out.println("after update, quote company2 => " + marketData.getQuote(company2.getId()));


		// 8. place order
		var orderId1 = orderService.placeOrder(user1.getId(), account1.getId(), company1.getId(), OrderType.BUY, 2, 5000);
		var orderId2 = orderService.placeOrder(user1.getId(), account1.getId(), company2.getId(), OrderType.BUY, 2, 10000);

		// 9. cancel order
		System.out.println("cancel order => "+orderService.cancelOrder(orderId1));

		// 10. execute order
		List<TradeExecutionLog> logs = orderService.executeOrders(company1.getId());

		System.out.println("\n\n");
		for(TradeExecutionLog log : logs)
		{
			System.out.println(log);
		}

		// 11. view portfolio
		Map<String, Double> latestPrices = marketData.getAllQuotes();
		Map<String, String> companyNames = marketData.getAllNames();

		List<Portfolio> portfolio = orderService
				.viewPortfolio(user1.getId(), latestPrices, companyNames);

		System.out.println("\nportfolio\n");
		for(Portfolio p: portfolio){
			System.out.println(p);
		}

		// 12. view order history
		System.out.println("\norders\n");
		List<Order> orders = orderService.viewOrderHistory(user1.getId());
		for(Order o: orders)
		{
			System.out.println(o);
		}
	}


}
