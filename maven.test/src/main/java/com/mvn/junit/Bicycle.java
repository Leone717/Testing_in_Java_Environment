package com.mvn.junit;

public class Bicycle {

	DatabaseConnection dbCon;
	
	Bicycle() {
		
	}
	
	Bicycle(DatabaseConnection dbCon) {
		this.dbCon = dbCon;
		
	}
	
	public Integer sum(String user, String password, int a, int b, int c) {
		//it asks the Database, does user exist in the Database? 
		
		boolean checkUser = dbCon.checkUserPass(user, password);
		
		if (checkUser)
			return a+b+c;
		
		return null;
	}
}
