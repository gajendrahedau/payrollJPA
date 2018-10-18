package com.cg.project.services;
import java.util.ArrayList;
import com.cg.project.beans.Associate;

public interface PayrollServices {
	int acceptAssociateDetails(int yearlyInvestmentUnder80C,String firstName,String lastName,
			String department,String designation,String panCard ,String emailId,
			int basicSalary, int epf, int companyPf,
			int accountNumber, 
			String bankName, String ifscCode);
	
	int calculateNetSalary(int associateId);
	
	Associate getAssociateDetails(int associateId);
	
	ArrayList<Associate> getAllAssociateDetails();
}
