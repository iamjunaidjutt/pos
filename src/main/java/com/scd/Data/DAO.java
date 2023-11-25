package com.scd.Data;

import java.util.Collection;

public interface DAO {
    public boolean save(Object obj);

    public Collection<Object> getAll();

    public boolean update(Object obj);

    public boolean delete(int id);
}
