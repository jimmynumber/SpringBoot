package com.want.amap.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.want.amap.util.PropertiesUtil;
import com.want.amap.vo.PoiListQueryStringVO;

public class OracUtil {
	static String host = PropertiesUtil.getOracleProperties().getProperty("host");
	static String account = PropertiesUtil.getOracleProperties().getProperty("account");
	static String passwd = PropertiesUtil.getOracleProperties().getProperty("passwd");
	static String sid = PropertiesUtil.getOracleProperties().getProperty("sid");
	static String url = "jdbc:oracle:thin:@"+ host + ":1521:" + sid;
	
//	static String url = "jdbc:oracle:thin:@"+ "10.0.0.42" + ":1521:" + "SDH";
	
	static Connection conDB = null;

	public static void main(String[] args) {
		getConn();
		System.out.println(getDefaultPoiType());
	}
	
	public static void close() {
		if (conDB != null) {
			try {
				conDB.close();
				conDB = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static List<PoiListQueryStringVO> getDefaultPoiType() {
		List<PoiListQueryStringVO> list = new ArrayList<>();
		String sql = "select * from POI_List";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conDB.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PoiListQueryStringVO vo = new PoiListQueryStringVO();
				vo.setLV1(rs.getString("LV1"));
				vo.setLV2(rs.getString("LV2"));
				vo.setLV3(rs.getString("LV3"));
				vo.setQueryString(".*" + vo.getLV3( ).replace("/", "\\/").replace("(", "\\(").replace(")", "\\)") + ".*");
				list.add(vo);
			}
		} catch (SQLException e) {
			list = null;
			System.out.println("Get default poi type failed");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public static Connection getConn(){
        try{
            // Oracle 8i not pooling connection
            Class.forName("oracle.jdbc.driver.OracleDriver");            
            //取得連線
            conDB = DriverManager.getConnection(url, account, passwd);  
//            conDB = DriverManager.getConnection(url, "amap", "amapuser123");  
            System.out.println("DataBase connected  ");
//            conDB.setAutoCommit(false);
        }
        catch(ClassNotFoundException cnfe){
            System.out.println("Driver didn't be load : " + cnfe.toString());
        }
        catch(SQLException sqle){
            System.out.println("DataBase didn't connected : " + sqle.toString());
        }
        finally{
            if(conDB == null){
                try{
                    conDB.close(); //關閉資料庫連結
                }
                catch(SQLException sqle){ }
            }
        }    
        
        return conDB;
    }

}
