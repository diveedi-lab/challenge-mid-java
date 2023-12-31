package io.test;

import io.github.ulisse1996.jaorm.annotation.Column;
import io.github.ulisse1996.jaorm.annotation.Converter;
import io.github.ulisse1996.jaorm.annotation.Id;
import io.github.ulisse1996.jaorm.annotation.Table;

import java.util.Objects;

@Table(name = "TABLE")
public class EntityWithConverter {

    @Id
    @Column(name = "COL1")
    @Converter(BooleanIntConverter.class)
    private boolean col1;

    @Column(name = "COL2")
    private String col2;

    public String getCol2() {
        return col2;
    }

    public boolean getCol1() {
        return col1;
    }

    public void setCol1(boolean col1) {
        this.col1 = col1;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleEntity that = (SimpleEntity) o;
        return Objects.equals(col1, that.col1) && Objects.equals(col2, that.col2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(col1, col2);
    }

    public static class BooleanIntConverter implements ValueConverter<Integer, Boolean> {

        @Override
        public Boolean fromSql(Integer val) {
            return true;
        }

        @Override
        public Integer toSql(Boolean val) {
            return 1;
        }
    }

    public interface ValueConverter<T,R> { // We should provide all elements in test env

        R fromSql(T val);
        T toSql(R val);
    }
}
