package com.cargotransportation.constants;

public enum UserStatus {
    ACCEPT(true),
    REJECT(false);

    private boolean value;

    UserStatus(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }
}
