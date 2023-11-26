package com.scd.Data;

import java.util.List;

public interface DAO {
    public boolean save(Object obj);

    public List<Object> getAll();

    public boolean update(Object obj);

    public boolean delete(int id);
}
