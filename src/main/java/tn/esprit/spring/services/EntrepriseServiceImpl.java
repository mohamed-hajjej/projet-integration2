package tn.esprit.spring.services;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	
	private static final Logger l = LogManager.getLogger(EntrepriseServiceImpl.class);
	
	public int ajouterEntreprise(Entreprise entreprise) {
		l.info("In ajouterEntreprise() : ");
		entrepriseRepoistory.save(entreprise);
		l.info("Out ajouterEntreprise() without errors.");
		return entreprise.getId();
		
	}

	public int ajouterDepartement(Departement dep) {
		l.info("In ajouterEntreprise() : ");
		deptRepoistory.save(dep);
		l.info("Out ajouterEntreprise() without errors.");
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
	          	l.info("In affecterDepartementAEntreprise() : ");
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				Departement depManagedEntity = deptRepoistory.findById(depId).get();
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
				l.info("Out affecterDepartementAEntreprise() without errors.");
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		l.info("In getAllDepartementsNamesByEntreprise() : ");
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			l.debug("Je viens de chercher les departement dep.");
			depNames.add(dep.getName());
			
		}
		l.info("Out getAllDepartementsNamesByEntreprise() without errors.");
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		l.info("In deleteEntrepriseById() : ");
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());	
		l.info("Out deleteEntrepriseById() without errors.");
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		l.info("In deleteEntrepriseById() : ");
		deptRepoistory.delete(deptRepoistory.findById(depId).get());	
		l.info("Out deleteDepartementById() without errors.");
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		l.info("In getEntrepriseById() : ");
		Entreprise x =  entrepriseRepoistory.findById(entrepriseId).get();
		l.info("Out getEntrepriseById() without errors.");
		return x;
		

	}

}
