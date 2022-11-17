package model.dao.impl;

import db.DB;
import db.exceptions.DBException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement ps = null;
        // ResultSet rs = null;
        try {
            ps = conn.prepareStatement(
            "INSERT INTO seller " +
                "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getEmail());
            ps.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            ps.setDouble(4, obj.getBaseSalary());
            ps.setInt(5, obj.getDepartment().getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else {
                throw new DBException("Unexpected error! No rows affected!");
            }
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
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
                        "ORDER BY s.Name"
            );
            rs = ps.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            while (rs.next()){
                Department dp = instantiateDepartmentWithHashMap(rs, map);
                list.add(instantiateSeller(rs,dp));
            }
            return list;
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }
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