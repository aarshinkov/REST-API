package com.safb.rest.dao;

import com.safb.rest.dto.UserUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.Objects;

@Service
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean updateUser(UserUpdateDto userUpdateDto) {

        try {

            String sql = "UPDATE `users` SET `first_name` = ?, `last_name` = ? WHERE `public_id` = ?";
            PreparedStatement ps = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection().prepareCall(sql);
            ps.setString(1, userUpdateDto.getFirstName());
            ps.setString(2, userUpdateDto.getLastName());
            ps.setString(3, userUpdateDto.getPublicId());

            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
