package model.dao.impl;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    // ********************************* CRUD METHODS *********************************
    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;

        try {
            connection.setAutoCommit(false);
            st = connection.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else {
                throw new DbException("Unexpected Error! No rows Affected!");
            }
            connection.commit();
        } catch(SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DbException("Error trying to rollback!!!, caused by :" + e.getMessage());
            }
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement("SELECT * FROM department WHERE department.Id = ?", Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) return instantiateDepartment(rs);
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Department> departments = new ArrayList<>();


        try {
            st = connection.prepareStatement("SELECT * FROM department");
            rs = st.executeQuery();

            while(rs.next()) departments.add(instantiateDepartment(rs));


            return departments;

        } catch (SQLException e) {
            throw new DbException((e.getMessage()));
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    // ********************************* OTHER METHODS *********************************

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        return new Department(rs.getInt("Id"), rs.getString("Name"));
    }
}
