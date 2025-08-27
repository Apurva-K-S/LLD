package com.example.OnlineAuctionSystem;

import com.example.OnlineAuctionSystem.Entity.*;
import com.example.OnlineAuctionSystem.Enum.Category;
import com.example.OnlineAuctionSystem.Service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineAuctionSystemApplication {

	public static void main(String[] args) {
		//1.The online auction system should allow users to register and log in to their accounts.
		UserService userService = new UserService();
		User user1 = userService.createUser("user1", "user1");
		User user2 = userService.createUser("user2", "user2");

		System.out.println(user1);
		System.out.println(user2);

		System.out.println(userService.login("user1", "user2"));

		//2.Users should be able to create new auction listings with details such as item name, description, starting price, and auction duration.
		ItemService itemService = new ItemService();
		Item item1 = itemService.createItem("Iphone", Category.ELECTRONICS);
		Item item2 = itemService.createItem("H&M Bag", Category.APPAREL);
		Item item3 = itemService.createItem("Iphone 16 Pro Max", Category.ELECTRONICS);
		Item item4 = itemService.createItem("Collection of Farida Hoosenally", Category.ART);

		System.out.println(item1);
		System.out.println(item2);
		System.out.println(item3);
		System.out.println(item4);

		SearchInterface searchInterface = new ItemNameAuctionSearch(itemService);
		AuctionListingService auctionListingService = new AuctionListingService(itemService,userService,searchInterface);
		AuctionList auctionList1 = auctionListingService.createAuctionList(item1.getId(), 100000, 2, user1.getId());
		System.out.println(auctionList1);
		AuctionList auctionList2 = auctionListingService.createAuctionList(item3.getId(), 130000, 2, user2.getId());
		System.out.println(auctionList2);

		// 3. Users should be able to browse and search for auction listings based on various criteria (e.g., item name, category, price range).

		SearchCriteria iphoneSearch = new SearchCriteria("Iphone", Category.ELECTRONICS, 80000, 120000);
		System.out.println("\n");
		System.out.println("iphone search => " + auctionListingService.search(iphoneSearch));
		System.out.println("\n");


		auctionListingService.setSearchInterface(new ItemCategoryAuctionSearch(itemService));
		SearchCriteria apparelSearch = new SearchCriteria("Iphone", Category.APPAREL, 80000, 120000);
		System.out.println("apparel search => " + auctionListingService.search(apparelSearch));
		System.out.println("\n");

		auctionListingService.setSearchInterface(new PriceRangeSearch(itemService));
		SearchCriteria priceSearch = new SearchCriteria("Iphone", Category.APPAREL, 80000, 120000);
		System.out.println("price search => " + auctionListingService.search(priceSearch));
		System.out.println("\n");
		//4. Users should be able to place bids on active auction listings.
		BidService bidService = new BidService(userService, auctionListingService);
		Bid bid1 = bidService.createBid(user2.getId(), auctionList1.getId(), 110000);
		System.out.println(bid1);
		System.out.println(auctionList1);

		System.out.println("\n\n");

		Bid bid2 = bidService.createBid(user1.getId(), auctionList1.getId(), 120000);
		System.out.println(bid2);
		System.out.println(auctionList1);

	}

}
