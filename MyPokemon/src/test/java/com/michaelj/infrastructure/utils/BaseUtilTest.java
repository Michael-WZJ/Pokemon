package com.michaelj.infrastructure.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BaseUtilTest {
    @Test
    public void testComputeCode() {
        String code = "01";
        System.out.println(BaseUtils.computeCode(code, true));
        System.out.println(BaseUtils.computeCode(code, false));

        assertEquals(BaseUtils.computeCode("01", true), "02");
        assertEquals(BaseUtils.computeCode("01", false), "00");
    }
}
