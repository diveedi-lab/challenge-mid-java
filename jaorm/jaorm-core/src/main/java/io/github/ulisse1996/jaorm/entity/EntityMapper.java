package io.github.ulisse1996.jaorm.entity;

import io.github.ulisse1996.jaorm.Arguments;
import io.github.ulisse1996.jaorm.entity.sql.SqlAccessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class EntityMapper<T> {

    private final List<ColumnMapper<T>> mappers;

    private EntityMapper(List<ColumnMapper<T>> mappers) {
        this.mappers = mappers;
    }

    public static class Builder<T> {

        private final List<ColumnMapper<T>> mappers;

        public Builder() {
            this.mappers = new ArrayList<>();
        }

        public void add(String name, Class<?> type, ColumnSetter<T, Object> setter, ColumnGetter<T, Object> getter,
                        boolean key, boolean autoGenerated) {
            this.mappers.add(new ColumnMapper<>(name, type, setter, getter, key, autoGenerated));
        }

        public EntityMapper<T> build() {
            return new EntityMapper<>(this.mappers);
        }
    }

    public static class ColumnMapper<T> {
        private final String name;
        private final Class<?> type;
        private final ColumnSetter<T, Object> setter;
        private final ColumnGetter<T, Object> getter;
        private final boolean key;
        private final boolean autoGenerated;

        public ColumnMapper(String name, Class<?> type, ColumnSetter<T, Object> setter, ColumnGetter<T, Object> getter, boolean key,
                            boolean autoGenerated) {
            this.name = name;
            this.type = type;
            this.setter = setter;
            this.getter = getter;
            this.key = key;
            this.autoGenerated = autoGenerated;
        }

        public String getName() {
            return name;
        }

        public Class<?> getType() {
            return type;
        }

        public boolean isKey() {
            return key;
        }
    }

    public void setGenerated(T entity, String key, Object value) {
        mappers.stream()
                .filter(c -> c.name.equalsIgnoreCase(key))
                .findFirst()
                .ifPresent(c -> c.setter.accept(entity, value));
    }

    public Map<String, Class<?>> getAutoGenerated() {
        return mappers.stream()
                .filter(c -> c.autoGenerated)
                .collect(Collectors.toMap(c -> c.name, c -> c.type));
    }

    public Arguments getAllColumns(T entity, boolean forInsert) {
        return getAllColumns(entity, forInsert, Collections.emptyMap());
    }

    public Arguments getAllColumns(T entity, boolean forInsert, Map<String, Object> generated) {
        return Arguments.values(mappers.stream()
                .filter(c -> {
                    if (forInsert && !generated.containsKey(c.name)) {
                        return !c.autoGenerated;
                    }

                    return true;
                })
                .map(c -> c.getter.apply(entity))
                .toArray(Object[]::new));
    }

    public Arguments getValues(T updateEntity, List<ColumnMapper<T>> mappers) {
        return Arguments.values(
                mappers.stream()
                        .map(c -> c.getter.apply(updateEntity))
                        .toArray(Object[]::new)
        );
    }

    public Arguments getKeys(final T entity) {
        return Arguments.values(mappers.stream()
            .filter(c -> c.key)
            .map(c -> c.getter.apply(entity))
            .toArray(Object[]::new));
    }

    public T map(Supplier<T> entitySupplier, ResultSet rs) throws SQLException {
        T entity = entitySupplier.get();
        for (ColumnMapper<T> mapper : mappers) {
            SqlAccessor accessor = SqlAccessor.find(mapper.type);
            mapper.setter.accept(entity, accessor.getGetter().get(rs, mapper.name));
        }

        return entity;
    }

    public T mapForGraph(Supplier<T> entitySupplier, ResultSet rs, String table) throws SQLException {
        T entity = entitySupplier.get();
        for (ColumnMapper<T> mapper : mappers) {
            SqlAccessor accessor = SqlAccessor.find(mapper.type);
            mapper.setter.accept(entity, accessor.getGetter().get(rs, String.format("%s.%s", table, mapper.name)));
        }
        return entity;
    }

    public boolean containsGraphResult(ResultSet resultSet, String table) throws SQLException {
        List<ColumnMapper<T>> keysMappers = this.mappers.stream()
                .filter(m -> m.key)
                .collect(Collectors.toList());
        for (ColumnMapper<T> mapper : keysMappers) {
            String name = String.format("%s.%s", table, mapper.name);
            SqlAccessor accessor = SqlAccessor.find(mapper.type);
            accessor.getGetter().get(resultSet, name);
            if (resultSet.wasNull()) {
                return false;
            }
        }

        return true;
    }

    public List<ColumnMapper<T>> getMappers() {
        return mappers;
    }
}