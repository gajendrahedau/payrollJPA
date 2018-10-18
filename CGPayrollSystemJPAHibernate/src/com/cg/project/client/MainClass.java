package com.cg.project.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cg.project.beans.Associate;
import com.cg.project.beans.BankDetails;
import com.cg.project.beans.Salary;
import com.cg.project.services.PayrollServices;
import com.cg.project.services.PayrollServicesImpl;

public class MainClass {

	public static void main(String[] args) {
		PayrollServices services=new PayrollServicesImpl();
		
		services.acceptAssociateDetails(150000, "Atul", "Anand", "NFS", "Sr. Analyst", "CXP6177A", "atul@gmail.com", 17000, 2000, 2000, 5001101, "SBI", "SBI1001");
		
		/*int associateId=associate.getAssociateId();
		System.out.println("AssociateId="+associate.getAssociateId());
		
		System.out.println(dao.findOne(1));*/
		
		System.out.println("Net Salary:"+services.calculateNetSalary(1));
		
		System.out.println("Associate Details:"+services.getAssociateDetails(1));
		
		
		
		
	}

}
