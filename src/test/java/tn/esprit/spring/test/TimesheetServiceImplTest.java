package tn.esprit.spring.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.services.TimesheetServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetServiceImplTest {
	private static final Logger l = LogManager.getLogger(TimesheetServiceImplTest.class);
	@Autowired 
	TimesheetServiceImpl timesheetservice ;
	@Test
	public void ajouterMissionTest()  {
		Mission ms= new Mission(3,"app desktop", "gestion de magasin");
		try {
	int id=	timesheetservice.ajouterMission(ms);
		assertEquals(ms.getId(),id);
		l.debug("mission ajouté");
		}
		
		catch(Exception e) {
			l.error("probleme au niveau d'ajout");
		}
	}
	@Test
public void	affecterMissionADepartementTest()
{
Departement dep =new Departement(1,"vente");
int iddep=timesheetservice.affecterMissionADepartement(1,1);
assertEquals(dep.getId() ,iddep);
}
	@Test
	public void ajouterTimesheetTest() throws ParseException {
		int idemploye=1;
		int idmission=1;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		TimesheetPK tpk=timesheetservice.ajouterTimesheet(idemploye, idmission,simpleDateFormat.parse("03/11/2020"),simpleDateFormat.parse("05/11/2020"));
		
		assertEquals(tpk.getIdEmploye(),idemploye);
	}
	@Test 
	
	public void findAllMissionByEmployeJPQLTest() throws ParseException {
	int nbremission=timesheetservice.findAllMissionByEmployeJPQL(1).size();
		assertEquals(3,nbremission);
	}
	@Test
	public void getAllEmployeByMission()
	{
		int nbreemploye=timesheetservice.getAllEmployeByMission(1).size();
		assertEquals(6,nbreemploye);
	}
	@Test
	public void validerTimesheetTest() throws ParseException
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		int idemploye=2;
		int idmission=1;
		Date datedebut= simpleDateFormat.parse("02/11/2020");
		Date datefin=simpleDateFormat.parse("04/11/2020");
		
	}
	
	
	
	
	
	

}