package com.michaelj.domain.dto;

import jakarta.validation.groups.Default;

public interface ValidateConfig {
    interface add extends Default {}
    interface update extends Default {}
}
