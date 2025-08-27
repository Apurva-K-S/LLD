package com.example.RestaurantManagementSystem;

import com.example.RestaurantManagementSystem.Entity.*;
import com.example.RestaurantManagementSystem.Enum.OrderStatus;
import com.example.RestaurantManagementSystem.Enum.PaymentMethod;
import com.example.RestaurantManagementSystem.Enum.UserRole;
import com.example.RestaurantManagementSystem.Service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class RestaurantManagementSystemApplication {

	public static void main(String[] args) {
		// Initialize services
		UserService userService = new UserService();
		ItemService itemService = new ItemService();
		MenuService menuService = new MenuService(itemService);
		OrderService orderService = new OrderService(itemService);
		ReservationService reservationService = new ReservationService();
		InventoryService inventoryService = new InventoryService();
		PaymentService paymentService = new PaymentService();
		BillingService billingService = new BillingService();
		StaffService staffService = new StaffService();
		ReportingService reportingService = new ReportingService(orderService, itemService, staffService);

		// 1. Create staff and customers
		User staff1 = userService.createUser("Alice", "1234567890", "alice@example.com", UserRole.MANAGER);
		User staff2 = userService.createUser("Bob", "0987654321", "bob@example.com", UserRole.CHEF);
		User customer = userService.createUser("Charlie", "1112223333", "charlie@example.com", UserRole.CUSTOMER);

		System.out.println("Users:");
		System.out.println(staff1);
		System.out.println(staff2);
		System.out.println(customer);

		// 2. Add ingredients and inventory
		Ingredient tomato = inventoryService.createIngredient("ing1", "Tomato", 100);
		Ingredient cheese = inventoryService.createIngredient("ing2", "Cheese", 50);
		System.out.println("\nIngredients:");
		System.out.println(tomato);
		System.out.println(cheese);

		// 3. Create items (menu items)
		Item pizza = itemService.createItem("Pizza", 12.50, true);
		Item burger = itemService.createItem("Burger", 8.25, true);
		System.out.println("\nMenu Items:");
		System.out.println(pizza);
		System.out.println(burger);

		// 4. Create menu with items
		Menu menu = menuService.createMenu("Main Menu", "Delicious food", List.of(pizza.getId(), burger.getId()));
		System.out.println("\nMenu:");
		System.out.println(menu);

		// 5. Customer places an order
		Map<String, Integer> orderedItems = Map.of(pizza.getId(), 2, burger.getId(), 1);
		Order order = orderService.createOrder(customer.getId(), orderedItems, staff2.getId());
		System.out.println("\nOrder placed:");
		System.out.println(order);

		// 6. Update order status to Preparing
		orderService.updateOrderStatus(order.getId(), OrderStatus.PREPARING);
		System.out.println("Order status updated to PREPARING");

		// 7. Make a reservation
		Reservation reservation = reservationService.createReservation(customer.getId(), 4, 10, LocalDateTime.now().plusDays(1));
		System.out.println("\nReservation made:");
		System.out.println(reservation);

		// 8. Process payment for the order
		Payment payment = paymentService.processPayment(order.getId(), order.getTotalAmount(), PaymentMethod.CREDIT_CARD);
		System.out.println("\nPayment processed:");
		System.out.println(payment);

		// 9. Update order status to Completed
		orderService.updateOrderStatus(order.getId(), OrderStatus.COMPLETED);
		System.out.println("Order status updated to COMPLETED");

		// 10. Calculate bill with taxes and discounts
		double finalBill = billingService.calculateTotalWithTaxAndDiscount(order, true);
		System.out.println("\nFinal bill amount (with tax and discount): $" + String.format("%.2f", finalBill));

		// Generate receipt
		String receipt = billingService.generateReceipt(order, payment);
		System.out.println("\nReceipt:\n" + receipt);

		// 11. Add staff schedules and performance notes
		staffService.addOrUpdateStaff(staff1);
		staffService.addOrUpdateStaff(staff2);
		staffService.setScheduleForStaff(staff1.getId(), "Mon-Fri 9am-5pm");
		staffService.setScheduleForStaff(staff2.getId(), "Mon-Fri 6am-2pm");
		staffService.updatePerformance(staff1.getId(), "Excellent management skills");
		staffService.updatePerformance(staff2.getId(), "Great chef rating");

		System.out.println("\nStaff schedules and performance:");
		System.out.println(staff1.getName() + " schedule: " + staffService.getScheduleForStaff(staff1.getId()));
		System.out.println(staff1.getName() + " performance: " + staffService.getPerformance(staff1.getId()));
		System.out.println(staff2.getName() + " schedule: " + staffService.getScheduleForStaff(staff2.getId()));
		System.out.println(staff2.getName() + " performance: " + staffService.getPerformance(staff2.getId()));

		// 12. Generate reports
		double totalSales = reportingService.getTotalSales(null, null);
		Map<String, Integer> inventoryUsage = reportingService.getInventoryUsageReport(null, null);
		Map<String, Long> staffPerformance = reportingService.getStaffPerformanceReport(null, null);

		System.out.println("\nSales report: Total sales = $" + String.format("%.2f", totalSales));
		System.out.println("Inventory usage report: " + inventoryUsage);
		System.out.println("Staff performance report: " + staffPerformance);
	}

}
