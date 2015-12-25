package com.abc.dkadmin.service.dao;

import com.abc.dkadmin.model.ConfigModel;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class ConfigDAO {

    private static final Logger log = LoggerFactory.getLogger(ConfigDAO.class);
    private JdbcTemplate jdbcTemplate;

    private static final String COLUMN_NO_ID = "containerid, name, other, modifiedtime, createdtime";

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public ConfigModel insert(ConfigModel model) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(new ConfigDAOPrepareStatement(model), key);
        long id = key.getKey().longValue();
        model.setId(id);
        return model;
    }

    public ConfigModel update(ConfigModel model) {
        String sql = "UPDATE config SET containerid=?, name=?, other=?, modifiedtime=?, createdtime=? WHERE id=?";
        jdbcTemplate.update(sql,
                            model.getContainerId(),
                            model.getName(),
                            model.getOther(),
                            model.getModifiedTime().getMillis(),
                            model.getCreatedTime().getMillis(),
                            model.getId());
        return model;
    }

    public ConfigModel findById(long id) {

        ConfigModel configModel;
        String sql = "SELECT id, " + COLUMN_NO_ID + " FROM config where id = ?";
        try {
            configModel = jdbcTemplate.queryForObject(sql, new ConfigModelMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            log.trace("Cannot find config data with id: {}", id, e);
            return null;
        }
        return configModel;
    }

    public ConfigModel findByContainerId(String containerId) {
        ConfigModel configModel;
        String sql = "SELECT id, " + COLUMN_NO_ID + " FROM config where containerid = ?";
        try {
            configModel = jdbcTemplate.queryForObject(sql, new ConfigModelMapper(), containerId);
        } catch (EmptyResultDataAccessException e) {
            log.trace("Cannot find config data with containerid: {}", containerId, e);
            return null;
        }
        return configModel;
    }

    public boolean deleteById(long id) {
        String sql = "DELETE FROM config WHERE id=?";
        int row = jdbcTemplate.update(sql, id);
        if (row > 0) {
            log.debug("Config id {} was deleted", id);
            return true;
        } else {
            log.debug("Config id {} was not found", id);
            return false;
        }
    }

    public boolean deleteByContainerId(String containerId) {
        String sql = "DELETE FROM config WHERE containerid=?";
        int row = jdbcTemplate.update(sql, containerId);
        if (row > 0) {
            log.debug("Containerid {} was deleted", containerId);
            return true;
        } else {
            log.debug("Containerid {} was not found", containerId);
            return false;
        }
    }

    public boolean deleteByName(String name) {
        String sql = "DELETE FROM config WHERE name=?";
        int row = jdbcTemplate.update(sql, name);
        if (row > 0) {
            log.debug("Name {} was deleted", name);
            return true;
        } else {
            log.debug("Name {} was not found", name);
            return false;
        }
    }


    public List<ConfigModel> findByName(String name) {

        String sql = "SELECT id, " + COLUMN_NO_ID + " FROM config where name = ?";
        try {
            return jdbcTemplate.query(sql, new ConfigModelMapper(), name);
        } catch (EmptyResultDataAccessException e) {
            log.trace("Cannot find config data with name: {}", name, e);
            return null;
        }
    }

    private static final class ConfigDAOPrepareStatement implements PreparedStatementCreator {

        private ConfigModel model;

        private ConfigDAOPrepareStatement(ConfigModel model) {
            this.model = model;
        }

        @Override
        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

            String sql = "INSERT INTO config (" + COLUMN_NO_ID + ") VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            ps.setString(index++, model.getContainerId());
            ps.setString(index++, model.getName());
            ps.setString(index++, model.getOther());
            ps.setLong(index++, model.getModifiedTime() != null ? DateTime.now().getMillis()
                    : model.getModifiedTime().getMillis());
            ps.setLong(index, model.getCreatedTime().getMillis());

            return ps;
        }
    }

    private static final class ConfigModelMapper implements RowMapper<ConfigModel> {

        @Override
        public ConfigModel mapRow(ResultSet rs, int i) throws SQLException {
            ConfigModel configModel = new ConfigModel();
            configModel.setId(rs.getLong("id"));
            configModel.setContainerId(rs.getString("containerid"));
            configModel.setName(rs.getString("name"));
            configModel.setOther(rs.getString("other"));
            configModel.setModifiedTime(new DateTime(rs.getLong("modifiedtime")));
            configModel.setCreatedTime(new DateTime(rs.getLong("createdtime")));
            return configModel;
        }
    }
}
