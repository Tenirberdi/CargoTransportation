package com.cargotransportation.constants;

public enum DetailCodeEnum {

    SUCCESS(0),
    UNDOCUMENTED_ERROR(1),
    DUPLICATE_ENTRY(2),
    FILE_LOAD_ERROR(3),
    INVALID_STATUS(4),
    NOT_FOUND(5);

    private final int code;

    DetailCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
