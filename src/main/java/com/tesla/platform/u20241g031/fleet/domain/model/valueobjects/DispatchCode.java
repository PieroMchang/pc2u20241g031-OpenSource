package com.tesla.platform.u20241g031.fleet.domain.model.valueobjects;

import java.util.regex.Pattern;

/**
 * Value Object representing a FleetDispatch code matching the strict pattern DSP-XXXXXX.
 *
 * @param code dispatch code starting with 'DSP-' followed by 6 digits
 * @author Piero Francisco Montes Chang
 */
public record DispatchCode(String code) {

    private static final Pattern DISPATCH_CODE_PATTERN = Pattern.compile("^DSP-\\d{6}$");

    public DispatchCode {
        if (code == null || code.isBlank() || !DISPATCH_CODE_PATTERN.matcher(code).matches()) {
            throw new IllegalArgumentException(
                    "Dispatch code must start with 'DSP-' followed by exactly 6 numeric digits.");
        }
    }
}