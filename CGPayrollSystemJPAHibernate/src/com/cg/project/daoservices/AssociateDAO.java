package com.cg.project.daoservices;
import com.cg.project.beans.Associate;
import java.sql.SQLException;
import java.util.ArrayList;


public interface AssociateDAO {
	Associate save(Associate associate);
	Associate findOne(int associateId);
	ArrayList<Associate> findAll();
	boolean update(Associate associate);
}
