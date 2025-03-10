package com.kh.mvc.model.dto;

import java.sql.Date;

public class EmployeeDTO {
	private String EMP_ID = "";
	private String EMP_NAME = "";
	private String EMP_NO = "";
	private String EMAIL = "";
	private String PHONE = "";
	private String DEPT_CODE = "";
	private String JOB_CODE = "";
	private String SAL_LEVEL = "";
	private int SALARY = 0;
	private int BONUS = 0;
	private String MANAGER_ID = "";
	private Date HIRE_DATE;
	private Date ENT_DATE;
	private String ENT_YN;
	
	public void setField(String column, Object value) {
    if (value == null) return; // null 값 무시
    
    switch (column.toUpperCase()) {
        case "EMP_ID": this.EMP_ID = (String) value; break;
        case "EMP_NAME": this.EMP_NAME = (String) value; break;
        case "EMP_NO": this.EMP_NO = (String) value; break;
        case "EMAIL": this.EMAIL = (String) value; break;
        case "PHONE": this.PHONE = (String) value; break;
        case "DEPT_CODE": this.DEPT_CODE = (String) value; break;
        case "JOB_CODE": this.JOB_CODE = (String) value; break;
        case "SAL_LEVEL": this.SAL_LEVEL = (String) value; break;
        case "SALARY": this.SALARY = ((Number) value).intValue(); break;
        case "BONUS": this.BONUS = ((Number) value).intValue(); break;
        case "MANAGER_ID": this.MANAGER_ID = (String) value; break;
        case "HIRE_DATE": this.HIRE_DATE = (Date) value; break;
        case "ENT_DATE": this.ENT_DATE = (Date) value; break;
        case "ENT_YN": this.ENT_YN = (String) value; break;
        default:
            System.out.println("매칭되지 않은 컬럼: " + column);
    }
	}
	public Object getField(String column) {
    
    switch (column.toUpperCase()) {
        case "EMP_ID": return getEMP_ID();
        case "EMP_NAME": return getEMP_NAME();
        case "EMP_NO": return getEMP_NO();
        case "EMAIL": return getEMAIL();  
        case "PHONE": return getPHONE(); 
        case "DEPT_CODE": return getDEPT_CODE(); 
        case "JOB_CODE": return getJOB_CODE(); 
        case "SAL_LEVEL": return getSAL_LEVEL();  
        case "SALARY": return getSALARY();  
        case "BONUS": return getBONUS();  
        case "MANAGER_ID": return getMANAGER_ID();  
        case "HIRE_DATE": return getHIRE_DATE();   
        case "ENT_DATE": return getENT_DATE(); 
        case "ENT_YN": return getENT_YN(); 
        default:
        	return "잘못됨";
    }
}
	
	public Object getEMP_ID() {
		return EMP_ID;
	}
	public void setEMP_ID(String eMP_ID) {
		EMP_ID = eMP_ID;
	}
	public Object getEMP_NAME() {
		return EMP_NAME;
	}
	public void setEMP_NAME(String eMP_NAME) {
		EMP_NAME = eMP_NAME;
	}
	public Object getEMP_NO() {
		return EMP_NO;
	}
	public void setEMP_NO(String eMP_NO) {
		EMP_NO = eMP_NO;
	}
	public Object getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public Object getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public Object getDEPT_CODE() {
		return DEPT_CODE;
	}
	public void setDEPT_CODE(String dEPT_CODE) {
		DEPT_CODE = dEPT_CODE;
	}
	public Object getJOB_CODE() {
		return JOB_CODE;
	}
	public void setJOB_CODE(String jOB_CODE) {
		JOB_CODE = jOB_CODE;
	}
	public Object getSAL_LEVEL() {
		return SAL_LEVEL;
	}
	public void setSAL_LEVEL(String sAL_LEVEL) {
		SAL_LEVEL = sAL_LEVEL;
	}
	public Object getSALARY() {
		return SALARY;
	}
	public void setSALARY(int sALARY) {
		SALARY = sALARY;
	}
	public Object getBONUS() {
		return BONUS;
	}
	public void setBONUS(int bONUS) {
		BONUS = bONUS;
	}
	public Object getMANAGER_ID() {
		return MANAGER_ID;
	}
	public void setMANAGER_ID(String mANAGER_ID) {
		MANAGER_ID = mANAGER_ID;
	}
	public Object getHIRE_DATE() {
		return HIRE_DATE;
	}
	public void setHIRE_DATE(Date hIRE_DATE) {
		HIRE_DATE = hIRE_DATE;
	}
	public Object getENT_DATE() {
		return ENT_DATE;
	}
	public void setENT_DATE(Date eNT_DATE) {
		ENT_DATE = eNT_DATE;
	}
	public Object getENT_YN() {
		return ENT_YN;
	}
	public void setENT_YN(String eNT_YN) {
		ENT_YN = eNT_YN;
	}
	public EmployeeDTO(String eMP_ID, String eMP_NAME, String eMP_NO, String eMAIL, String pHONE, String dEPT_CODE,
			String jOB_CODE, String sAL_LEVEL, int sALARY, int bONUS, String mANAGER_ID, Date hIRE_DATE, Date eNT_DATE,
			String eNT_YN) {
		super();
		EMP_ID = eMP_ID;
		EMP_NAME = eMP_NAME;
		EMP_NO = eMP_NO;
		EMAIL = eMAIL;
		PHONE = pHONE;
		DEPT_CODE = dEPT_CODE;
		JOB_CODE = jOB_CODE;
		SAL_LEVEL = sAL_LEVEL;
		SALARY = sALARY;
		BONUS = bONUS;
		MANAGER_ID = mANAGER_ID;
		HIRE_DATE = hIRE_DATE;
		ENT_DATE = eNT_DATE;
		ENT_YN = eNT_YN;
	}
	public EmployeeDTO() {
		super();
	}
	
}
