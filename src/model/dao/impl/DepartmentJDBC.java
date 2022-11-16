package model.dao.impl;

import db.exceptions.DBException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentJDBC implements DepartmentDao {

    private Connection conn;

    @Override
    public void insert(Department obj) {

    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(
                    ""
            );
            rs = ps.executeQuery();
            if (rs.next()) {

            }
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }
}
