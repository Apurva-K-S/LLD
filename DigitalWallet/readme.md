## P0
### User
1. Create and manage user accounts
2. Create and manage wallets per user
3. Link multiple wallets to a single user

### Transaction
5. Add funds to wallet (Bank → Wallet)
5. Transfer funds between wallets (User1 → User2)
6. View transaction history
7. Withdraw funds to bank account

### Limits & Validation
8. Balance checks before transactions
9. Daily/monthly transaction limits
10. Basic fraud detection (e.g., velocity checks)

### Notifications & Audit
11. Notify users of successful/failed transactions
12. Maintain audit logs for compliance

### Non-Functional
13. Strong consistency for wallet balance
14. Secure APIs and encrypted data
15. High availability and fault tolerance
16. Basic observability (logs, metrics)

## P1
### Payment & Merchant Support
1. Pay to merchant via QR code
2. Request money (generate payment link or QR)
3. Split payments
4. Wallet → UPI ID
5. Wallet → QR Code (generic)

### Extensibility
6. Plug-in architecture for payment providers
7. Multi-currency wallet support

### Non-Functional
8. Advanced observability (tracing, dashboards)
9. Rate limiting and abuse protection

## P2 – Future/Optional (Advanced Features)
### Rewards & Subscriptions 
1. Loyalty points or cashback
2. Scheduled payments or subscriptions

### Globalization
3. Currency conversion
4. International transfers

### Intelligence
5. ML-based fraud detection
6. Smart spending insights or budgeting tools

### Extended Transaction Types
7. Wallet → Bank Account (User1 → User2)
8. Wallet → Merchant (e.g., electricity board)
9. Wallet → Recharge (e.g., Airtel, Vodafone)