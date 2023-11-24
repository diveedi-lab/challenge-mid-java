package io.test;

import io.github.ulisse1996.jaorm.BaseDao;
import io.github.ulisse1996.jaorm.annotation.Dao;
import io.github.ulisse1996.jaorm.annotation.Query;

@Dao
public class DaoFailStrategy extends BaseDao<Object> {

    @Query(sql = "NOT_VALID")
    void queryBadStrategy();
}
