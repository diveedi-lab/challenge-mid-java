package com.diveedi.storage.datasource;

import io.github.ulisse1996.jaorm.entity.sql.DataSourceProvider;
import io.github.ulisse1996.jaorm.schema.TableInfo;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import javax.sql.DataSource;

@ApplicationScoped
@Named
public class DefaultDataSourceImpl extends DataSourceProvider {
    @Resource(lookup = "jdbc/defaultDB")
    DataSource dataSource;

    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }

    @Override
    public DataSource getDataSource(TableInfo tableInfo) {
        return this.dataSource;
    }
}
