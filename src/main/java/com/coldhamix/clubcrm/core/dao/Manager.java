package com.coldhamix.clubcrm.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright 2016 (c) ClubCRM.
 * Author: Vadim A. Khamzin.
 * Created on: 26-01-2016
 */
public interface Manager<T, ID extends Serializable> {
    T findById(ID id);

    void create(T object);

    void update(T object);

    void delete(T object);

    void delete(ID id);

    List<T> list();
}
