package com.huateng.xhcp.db;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

/**
 * Created by sam.pan on 2017/3/28.
 */
public class IntegerNullValueHandler implements TypeHandler<String> {
    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex);
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex);
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, String value, JdbcType jdbcType) throws SQLException {
        if(StringUtils.isBlank(value) && jdbcType == JdbcType.INTEGER){
            ps.setNull(i, Types.INTEGER);
        }else{
            ps.setInt(i, Integer.parseInt(value));
        }
    }
}
