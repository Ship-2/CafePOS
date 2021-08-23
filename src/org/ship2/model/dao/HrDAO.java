package org.ship2.model.dao;

import static org.ship2.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HrDAO {
	private Properties prop = new Properties();
	
	public HrDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/hr-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
