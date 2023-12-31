package io.test;

import io.github.ulisse1996.jaorm.annotation.*;

@Table(name = "TABLE2")
public class EntityWithCustomGeneratedWithoutId {

    @Id
    @Column(name = "COL1")
    private int id;

    @Column(name = "COL2", autoGenerated = true)
    @CustomGenerated(MyCustomGenerator.class)
    private int prog;

    public int getProg() {
        return prog;
    }

    public void setProg(int prog) {
        this.prog = prog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class MyCustomGenerator implements CustomGenerator<Integer> {

        public Integer generate(Class<?> entityClass, Class<?> columnClass, String columnName) {
            return 1;
        }
    }
}
