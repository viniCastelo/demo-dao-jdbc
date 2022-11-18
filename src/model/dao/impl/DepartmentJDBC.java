package model.dao.impl;

import db.DB;
import db.exceptions.DBException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                    "INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, obj.getName());
            int rowAffected = ps.executeUpdate();
            if (rowAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    obj.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            }
            else {
                throw new DBException("Unexpected error. No rows affected!");
            }
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM department WHERE (Id = ?)");
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(
                "SELECT * FROM department WHERE (Id = ?)"
            );
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Department obj = instantiateDepartment(rs);
                return obj;
            }
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Department> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement("SELECT * FROM department");
            rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Department(rs.getInt("Id"), rs.getString("Name")));
            }
            return list;
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException{
        Department obj = new Department();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        return obj;
    }

}