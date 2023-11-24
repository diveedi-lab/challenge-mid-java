package io.github.ulisse1996.jaorm.vendor.postgre;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PostgreLockSpecificTest {

    private final PostgreLockSpecific testSubject = new PostgreLockSpecific();

    @Test
    void should_return_lock_sql() {
        Assertions.assertEquals(
                "SELECT COLUMN FROM TABLE WHERE COL2 = ? FOR UPDATE",
                testSubject.selectWithLock("TABLE", "WHERE COL2 = ?", "COLUMN")
        );
    }
}
