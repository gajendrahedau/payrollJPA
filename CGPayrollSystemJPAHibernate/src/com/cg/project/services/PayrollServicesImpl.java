package com.cg.project.services;

import java.util.ArrayList;



import com.cg.project.beans.Associate;
import com.cg.project.beans.BankDetails;
import com.cg.project.beans.Salary;
import com.cg.project.daoservices.AssociateDAO;
import com.cg.project.daoservices.AssociateDAOImpl;


public class PayrollServicesImpl implements PayrollServices{
	
	
	private AssociateDAO associateDAO=new AssociateDAOImpl();
	
	
	@Override
	public int acceptAssociateDetails( int yearlyInvestmentUnder80C,String firstName, String lastName,
			String department, String designation, String panCard,
			String emailId, int basicSalary,
			int epf, int companyPf, int accountNumber, String bankName,
			String ifscCode){
		Associate associate = new Associate(yearlyInvestmentUnder80C, firstName, lastName, department, designation, panCard, emailId,new BankDetails(accountNumber, bankName, ifscCode), new Salary(basicSalary, epf, companyPf));
		
			associate=associateDAO.save(associate);
		
		return associate.getAssociateId();
	}

	@Override
	public int calculateNetSalary(int associateId)
			{
		
		Associate associate=getAssociateDetails(associateId);
		
		associate.getSalary().setHra((30*associate.getSalary().getBasicSalary())/100);
		associate.getSalary().setConveyenceAllowance((5*associate.getSalary().getBasicSalary())/100);
		associate.getSalary().setOtherAllowance((20*associate.getSalary().getBasicSalary())/100);
		associate.getSalary().setPersonalAllowance((25*associate.getSalary().getBasicSalary())/100);
		associate.getSalary().setGrossSalary(associate.getSalary().getBasicSalary()+associate.getSalary().getHra()+associate.getSalary().getConveyenceAllowance()+associate.getSalary().getOtherAllowance()+associate.getSalary().getPersonalAllowance());
		
		int yearlySalary=(associate.getSalary().getGrossSalary()*12);
		
		int yearlyInvestment=associate.getYearlyInvestmentUnder80C();
		int investment=0;
		if(yearlyInvestment<=150000)
			investment=yearlyInvestment;
		else if(yearlyInvestment>150000)
			investment=150000;
		
		int yearlySalaryAfterInvestment=yearlySalary-investment;
		
		if(yearlySalaryAfterInvestment<=250000){
			associate.getSalary().setMonthlyTax(0);
		}
		else if(yearlySalaryAfterInvestment>250000 && yearlySalaryAfterInvestment<=500000)
			associate.getSalary().setMonthlyTax((5*(yearlySalaryAfterInvestment-250000))/(100*12));
		else if(yearlySalaryAfterInvestment>500000 && yearlySalaryAfterInvestment<=1000000){
			int taxUpto5Lakh=25000;
			int taxUpto10Lakh=(20*(1000000-yearlySalaryAfterInvestment))/100;
			associate.getSalary().setMonthlyTax((taxUpto5Lakh+taxUpto10Lakh)/12);
			}
		else if(yearlySalaryAfterInvestment>1000000){
			int taxUpto5Lakh=25000;
			int taxUpto10Lakh=100000;
			int taxAbove10Lakh=(30*(yearlySalaryAfterInvestment-1000000))/100;
			associate.getSalary().setMonthlyTax((taxUpto5Lakh+taxUpto10Lakh+taxAbove10Lakh)/12);
			}
		associate.getSalary().setNetSalary(associate.getSalary().getGrossSalary()-associate.getSalary().getMonthlyTax());
		return associate.getSalary().getNetSalary();
	}

	@Override
	public Associate getAssociateDetails(int associateId)
			{
		Associate associate = null;
		
		associate = associateDAO.findOne(associateId);
		return associate;
	}

	@Override
	public ArrayList<Associate> getAllAssociateDetails(){
			
			return associateDAO.findAll();
		
		
	}

}
