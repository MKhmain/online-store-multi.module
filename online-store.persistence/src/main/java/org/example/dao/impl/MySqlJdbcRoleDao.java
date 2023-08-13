package org.example.dao.impl;

import org.example.dao.RoleDao;
import org.example.dto.RoleDto;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlJdbcRoleDao implements RoleDao {
    @Override
    public RoleDto getRoleById(int id) {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn_it_db","root","test");
             var ps = conn.prepareStatement("SELECT * FROM role WHERE id = ?")) {
            ps.setInt(1, id);

            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    RoleDto role = new RoleDto();
                    role.setId(rs.getInt("id"));
                    role.setRoleName(rs.getString("role_name"));
                    return role;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
