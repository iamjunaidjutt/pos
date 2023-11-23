package com.scd.Data;

import java.util.List;
import java.util.UUID;

public interface DAO {
    public boolean create(Object obj);

    public List<Object> read();

    public boolean update(Object obj);

    public boolean delete(UUID id);
}
