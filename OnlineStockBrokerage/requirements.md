### Account Management
1. CreateAccount(name, email) → returns User
2. ViewAccount(userId) → shows balance, portfolio summary

### Fund Management
3. DepositFunds(userId, amount) → updates cash balance
4. WithdrawFunds(userId, amount) → validates balance, updates

### Market Data
5. GetQuote(symbol) → returns current price
6. UpdateQuote(symbol, price) → admin-only, simulates market

### Order Lifecycle
#### PlaceOrder(userId, companyId, type, qty, price)
8. Validates balance (BUY) or holdings (SELL)
9. Creates Order with PENDING status

#### CancelOrder(orderId)
10. Allowed only if PENDING

#### ExecuteOrders(symbol)
11. Matches BUY/SELL orders by price-time priority
12. Updates holdings, balances, order status

### Portfolio & History
13. ViewPortfolio(userId) → shows holdings with avg price, value
14. ViewOrderHistory(userId) → all orders with status