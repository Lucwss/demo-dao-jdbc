package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    // ********************************* CRUD METHODS *********************************
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
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = connection.prepareStatement("SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE seller.Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()) return instantiateSeller(rs, instantiateDepartment(rs));
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Seller> sellers = new ArrayList<>();
        Map<Integer, Department> map = new HashMap<>();

        try {
            st = connection.prepareStatement("SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE DepartmentId = ? ORDER BY Name");
            st.setInt(1, department.getId());
            rs = st.executeQuery();

            while(rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));

                if(dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                sellers.add(instantiateSeller(rs, dep));
            }
            return sellers;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    // ********************************* OTHER METHODS *********************************

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        return new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
    }

    private Seller instantiateSeller(ResultSet rs, Department dp) throws SQLException {
        return new Seller(
                rs.getInt("Id"),
                rs.getString("Name"),
                rs.getString("Email"),
                rs.getDate("BirthDate"),
                rs.getDouble("BaseSalary"),
                dp
        );
    }
}
