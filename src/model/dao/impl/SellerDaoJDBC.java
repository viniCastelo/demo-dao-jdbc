package model.dao.impl;

import db.DB;
import db.exceptions.DBException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(
            "SELECT " +
                "s.*, " +
                "d.Name AS DepName " +
                "FROM seller s " +
                "INNER JOIN department d " +
                "ON s.DepartmentId = d.Id " +
                "WHERE (s.Id = ?)"
            );
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                Department department = instantiateDepartment(rs);
                Seller seller = instantiateSeller(rs, department);
                return seller;
            }
            return null;
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(
                    "SELECT " +
                            "s.*, " +
                            "d.Name AS DepName " +
                            "FROM seller s " +
                            "INNER JOIN department d ON s.DepartmentId = d.Id " +
                            "WHERE s.DepartmentId = ? " +
                            "ORDER BY s.Name"
            );
            ps.setInt(1, department.getId());
            rs = ps.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            while (rs.next()){
                Department dp = instantiateDepartmentWithHashMap(rs, map);
                list.add(instantiateSeller(rs,dp));
            }
            return list;
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    private Seller instantiateSeller(ResultSet rs, Department dp) throws SQLException{
            Seller obj = new Seller();
            obj.setId(rs.getInt("Id"));
            obj.setName(rs.getString("Name"));
            obj.setEmail(rs.getString("Email"));
            obj.setBirthDate(rs.getDate("BirthDate"));
            obj.setBaseSalary(rs.getDouble("BaseSalary"));
            obj.setDepartment(dp);
            return obj;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
            Department obj = new Department();
            obj.setId(rs.getInt("DepartmentId"));
            obj.setName(rs.getString("DepName"));
            return obj;
    }

    private Department instantiateDepartmentWithHashMap(ResultSet rs, Map<Integer, Department> map) throws SQLException {
        Department obj = map.get(rs.getInt("DepartmentId"));
        if (obj == null){
            obj = instantiateDepartment(rs);
            map.put(rs.getInt("DepartmentId"), obj);
            return obj;
        }
        return obj;
    }

}