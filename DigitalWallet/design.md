### User Service
#### Responsibilities

| Responsibility                | Description                                                         |
|-------------------------------|---------------------------------------------------------------------|
| CreateUser                    | Create a new user based on type (INDIVIDUAL, MERCHANT)              |
| UpdateUser                    | Modify user details (name, contact info, preferences)               |
| ActivateUser / DeactivateUser | Enable or disable user access                                       |
| SetPreferredWallet            | Assign default wallet for transactions                              |
| SetKYCStatus                  | Update KYC verification status                                      |
| EmitUserEvents (optional)     | Publish domain events (e.g., USER_CREATED, KYC_VERIFIED)             |
| GetUserById                   | Fetch user details                                                  |
| ListUsers                     | Admin or internal listing                                           |
| DeleteUser (optional)         | Soft delete or deactivate user                                      |

#### Design Patterns Applied

| Pattern                     | Purpose                                             | Application in UserService                                                                 |
|-----------------------------|-----------------------------------------------------|---------------------------------------------------------------------------------------------|
| Factory Pattern             | Create users based on type (Individual or merchant) | `UserFactory.CreateUser(UserType)`                                                          |
| Builder Pattern (optional)  | Construct user with optional fields                 | `UserBuilder.SetName().SetEmail().Build()`                                                  |
| Observer Pattern (optional) | Notify other services on user events                | `UserCreated → NotificationService`, `KYCVerified → AuditService`                           |

### Bank Account Service
#### Responsibilities

| Responsibility            | Description                                                 |
|---------------------------|-------------------------------------------------------------|
| LinkBankAccount           | Associate a bank account with a user                        |
| UnlinkBankAccount         | Remove or deactivate a linked account                       |
| ValidateBankDetails       | Verify IFSC, account number format, etc.                     |
| GetBankAccountsByUser     | Fetch all accounts linked to a user                          |
| SetPrimaryAccount         | Mark one account as default for fund transfers               |
| UpdateBankAccountStatus   | Change status (ACTIVE, CLOSED, etc.)                         |

#### Design Patterns

| Pattern                     | Purpose                                                      | Application in BankAccountService                                               |
|-----------------------------|--------------------------------------------------------------|----------------------------------------------------------------------------------|
| Factory Pattern             | Create bank account objects based on type (SAVINGS, CURRENT) | `BankAccountFactory.Create(AccountType)`                                         |
| Strategy Pattern            | Handle different validation rules                            | `ValidationStrategy` for SAVINGS, CURRENT, etc.                                 |
| Observer Pattern (optional) | Notify services on account events                            | `BankLinked`, `BankUnlinked`, `StatusChanged`                                   |
| Builder Pattern (optional)  | Construct bank account with optional metadata                | `BankAccountBuilder.SetIFSC().SetName().Build()`                                 |

### Wallet Service
#### Responsibilities

| Responsibility        | Description                                                   |
|-----------------------|---------------------------------------------------------------|
| CreateWallet          | Initialize wallet for a user                                  |
| GetBalance            | Return current wallet balance                                 |
| CreditWallet          | Add funds (from bank, refund, etc.)                           |
| DebitWallet           | Deduct funds (for payment, transfer)                          |
| GetTransactionHistory | List wallet transactions                                      |

#### Design Patterns

| Pattern                     | Purpose                                               | Application in WalletService                      |
|-----------------------------|-------------------------------------------------------|---------------------------------------------------|
| Strategy Pattern            | Handle different credit/debit sources                 | `CreditWallet / debit wallet`                       |
| Observer Pattern (optional) | Notify other services on wallet changes               | `WalletCredited`, `WalletDebited`, `BalanceLow`   |
| Builder Pattern (optional)  | Construct wallet transaction objects                  | `WalletTxnBuilder.SetAmount().SetSource().Build()` |

### Transaction Service
#### Responsibilities

| Responsibility         | Description                                                       |
|------------------------|-------------------------------------------------------------------|
| StartTransaction       | Start a transaction based on type and source/destination          |
| ValidateTransaction    | Check limits, balance, fraud rules                                |
| ExecuteTransaction     | Perform debit/credit operations via Wallet/Bank services          |
| GetTransactionById     | Fetch transaction details                                         |
| ListTransactionsByUser | View user’s transaction history                                   |
| TrackStatus            | Monitor transaction lifecycle (`PENDING`, `SUCCESS`, etc.)        |

#### Design Patterns

| Pattern                     | Purpose                             | Application in TransactionService                                                                                                                     |
|-----------------------------|-------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| Strategy Pattern            | Handle different transaction types  | `TransactionStrategy` for:<br>• WALLET_TO_WALLET<br>• BANK_TO_WALLET<br>• WALLET_TO_BANK<br>• WALLET_TO_UPI<br>• WALLET_TO_QR<br>• WALLET_TO_MERCHANT |
| Factory Pattern             | Select appropriate strategy         | `TransactionStrategyFactory.Get(type)`: to select different Transaction Strategies                                                                    |

## Not implementing now, not P0
### Notification Service 
#### Responsibilities

| Responsibility         | Description                                                       |
|------------------------|-------------------------------------------------------------------|
| SendNotification       | Deliver messages via SMS, email, push, in-app                     |
| HandleTransactionEvents| React to transaction lifecycle events                             |
| Template Rendering     | Format messages based on type and context                         |
| Channel Routing        | Choose delivery channel(s) per user preferences                   |
| Retry & Failure Handling | Ensure delivery or log failure gracefully                       |
| Audit & Logging        | Track notification attempts and outcomes                          |

#### Design Patterns

| Pattern            | Purpose                           | Application                                                                            |
|--------------------|-----------------------------------|----------------------------------------------------------------------------------------|
| Observer Pattern   | React to domain events            | `TransactionCompleted → NotificationService`                                           |
| Strategy Pattern   | Channel-specific delivery logic   | `NotificationStrategy` for SMS, Email, Push                                            |
| Factory Pattern    | Select strategy based on channel  | `NotificationStrategyFactory.Get(channel)` : to select different notification strategy |

