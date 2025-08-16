package com.example.DigitalWallet.External;

import java.util.UUID;

public interface UPIPort {
    boolean verifyUPIHandle(UUID receiverId);
    boolean send(UUID receiverId, float amount);
}
