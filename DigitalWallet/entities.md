## User
| Field               | Type                | Description                         |
|---------------------|---------------------|-------------------------------------|
| id                  | UUID                | Unique user identifier              |
| name                | String              | Full name                           |
| phone               | String              | Mobile number                       |
| email               | String              | Email address                       |
| userType            | Enum                | INDIVIDUAL, MERCHANT                |
| isActive            | Bool                | Account status                      |
| createdAt           | Timestamp           | Account creation time               |
| lastLoginAt         | Timestamp           | Last login time                     |
| kycStatus           | Enum                | PENDING, VERIFIED, REJECTED         |
| preferredWalletId   | UUID                | Default wallet for transactions     |
| List<BankAccount>   | List                | Linked bank accounts                |
| List<Wallet>        | List                | Linked wallets                      |

## BankAccount

| Field            | Type      | Description                   |
|------------------|-----------|-------------------------------|
| accountNumber    | String    | Bank account number           |
| userId           | UUID      | Owner of the account          |
| accountType      | Enum      | SAVINGS, CURRENT              |
| balance          | Float     | Current bank balance          |
| accountStatus    | Enum      | ACTIVE, CLOSED, BLOCKED       |
| createdAt        | Timestamp | Account creation time         |
| bankName         | String    | Name of the bank              |
| ifscCode         | String    | IFSC code                     |
| linkedWalletId   | UUID      | Optional wallet linkage       |
| isPrimary        | Bool      | Indicates default account     |

## Wallet

| Field                    | Type      | Description                     |
|--------------------------|-----------|---------------------------------|
| id                       | UUID      | Wallet ID                       |
| userId                   | UUID      | Owner of the wallet             |
| pin                      | String    | Encrypted PIN                   |
| currentBalance           | Float     | Available balance               |
| totalBalance             | Float     | Includes pending credits        |
| walletStatus             | Enum      | ACTIVE, BLOCKED, SUSPENDED      |
| currency                 | String    | e.g., INR, USD                  |
| dailyTransactionLimit    | Float     | Daily cap                       |
| monthlyTransactionLimit  | Float     | Monthly cap                     |
| isDefault                | Bool      | Default wallet flag             |

## Transaction

| Field                  | Type                  | Description                                         |
|------------------------|-----------------------|-----------------------------------------------------|
| transactionId          | UUID                  | Unique transaction ID                               |
| senderId               | UUID                  | Wallet or BankAccount ID                            |
| receiverId             | UUID                  | Wallet, BankAccount, UPI, etc.                      |
| senderType             | Enum                  | WALLET, BANK, UPI, QR, MERCHANT                     |
| receiverType           | Enum                  | Same as above                                       |
| transactionType        | Enum                  | WALLET_TO_WALLET, BANK_TO_WALLET, etc.              |
| transactionStatus      | Enum                  | PENDING, SUCCESS, FAILED, REVERSED                  |
| initiatedBy            | UUID                  | User who initiated                                  |
| amount                 | Float                 | Transaction amount                                  |
| description            | String                | Optional note                                       |
| startedAt              | Timestamp             | Initiation time                                     |
| completedAt            | Timestamp             | Completion time                                     |
| isReversed             | Bool                  | Indicates reversal                                  |
| reversalTransactionId  | UUID                  | Link to reversal transaction                        |
| metadata               | Map<String, String>   | Extensible data (e.g., UPI ID, QR payload)          |

## TransactionAudit

| Field         | Type      | Description                     |
|---------------|-----------|---------------------------------|
| id            | UUID      | Audit record ID                 |
| transactionId | UUID      | Related transaction             |
| statusChange  | String    | e.g., PENDING → SUCCESS         |
| timestamp     | Timestamp | When change occurred            |
| changedBy     | UUID      | User or system actor            |

## Notification

| Field     | Type      | Description                      |
|-----------|-----------|----------------------------------|
| id        | UUID      | Notification ID                  |
| userId    | UUID      | Recipient                        |
| message   | String    | Notification content             |
| type      | Enum      | TRANSACTION, SECURITY, PROMO     |
| status    | Enum      | DELIVERED, FAILED, READ          |
| createdAt | Timestamp | Time of creation                 |

## Merchant (P2)

| Field           | Type   | Description                       |
|-----------------|--------|-----------------------------------|
| merchantId      | UUID   | Unique merchant ID                |
| name            | String | Merchant name                     |
| merchantType    | Enum   | UTILITY, TELECOM, RETAIL           |
| linkedWalletId  | UUID   | Wallet for receiving payments     |
| isVerified      | Bool   | KYC or onboarding status          |

## SecurityLog (Optional)

| Field     | Type      | Description                    |
|-----------|-----------|--------------------------------|
| id        | UUID      | Log ID                         |
| userId    | UUID      | Affected user                  |
| action    | Enum      | PIN_CHANGE, LOGIN_FAIL, etc.   |
| timestamp | Timestamp | When it occurred               |
| details   | String    | Optional context               |
