package com.poscodx.guestbook.repository.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class JdbcContext {
	private DataSource dataSource;
	
	public JdbcContext(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	

	//아이디어
	public <T> T executeQueryForObject(String sql) {
		return null;
	}
	//아이디어 
	public <T> List<T> executeQueryForObject(String sql, Object[] parameter) {
		return null;
	}
	
	// 파라미터에 콜백이 들어가있음
	public int executeUpdateWithStatementStrategy(StatementStrategy statementStrategy) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			 conn = DataSourceUtils.getConnection(dataSource);
			 pstmt = statementStrategy.makeStamtement(conn);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e); //conn.rollback 실패에서 catch해줘야되기 때문에 throw해줌 
		}finally {
			try {
				if(pstmt!=null) {
				pstmt.close();
				}
				if(conn!=null) {
					DataSourceUtils.releaseConnection(conn, dataSource);
				} 
			}catch (SQLException e) {
		
			}
			
		}
		
		return result; 
	}
	
	public <T> List<T> query(String sql, RowMapper<T> rowMapper) { //이렇게 만들어서 UserVo, GuestbookVo 모두 쓸 수 있게
		// 파라미터 맵핑은 하지 않는다는게 제약사항.
		// result만 한다. 
			return executeQueryWithStatementStrategy(new StatementStrategy() {

				@Override
				public PreparedStatement makeStamtement(Connection connection) throws SQLException {
					PreparedStatement pstmt = connection.prepareStatement(sql);
					return pstmt;
				}
				
			}, rowMapper);
		}
	
	private <E> List<E> executeQueryWithStatementStrategy(StatementStrategy statementStrategy, RowMapper<E> rowMapper) {
		List<E> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			 conn = DataSourceUtils.getConnection(dataSource);
			 pstmt = statementStrategy.makeStamtement(conn);

			rs= pstmt.executeQuery();
			while(rs.next()) {
				E t = rowMapper.mapRow(rs, rs.getRow());
				result.add(t);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e); //conn.rollback 실패에서 catch해줘야되기 때문에 throw해줌
		}finally {
			try {
				if(pstmt!=null) {
				pstmt.close();
				}
				if(conn!=null) {
					DataSourceUtils.releaseConnection(conn, dataSource);
				} 
			}catch (SQLException e) {
		
			}
			
		}
		
		return result; 
	}



	public int update(String sql, Object[] parameters) {
	
		return executeUpdateWithStatementStrategy(new StatementStrategy() {

			@Override
			public PreparedStatement makeStamtement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				for(int i=0; i<parameters.length;i++) {
					pstmt.setObject(i+1, parameters[i]);
				}
				return pstmt;
			}
			
		});
	}
	
	public int update(String sql) {
		
		return executeUpdateWithStatementStrategy(new StatementStrategy() {

			@Override
			public PreparedStatement makeStamtement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				return pstmt;
			}
			
		});
	}

}
