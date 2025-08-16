package com.example.DigitalWallet.External;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class InMemoryUPIAdapter implements UPIPort {
    private final Set<UUID> validHandles = new HashSet<>();

    @Override
    public boolean verifyUPIHandle(UUID upiId) {
        return validHandles.contains(upiId);
    }

    @Override
    public boolean send(UUID upiId, float amount) {
        return verifyUPIHandle(upiId); // always "success" if valid
    }

    public void registerUPI(UUID upiId) {
        validHandles.add(upiId);
    }
}
