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
import java.util.Date;
import java.util.List;

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
                        "seller.*, " +
                        "department.Name AS DepName " +
                        "FROM seller " +
                        "INNER JOIN department " +
                        "ON seller.DepartmentId = department.Id " +
                        "WHERE (seller.Id = ?)"
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

    @Override
    public List<Seller> findAll() {
        return null;
    }
}