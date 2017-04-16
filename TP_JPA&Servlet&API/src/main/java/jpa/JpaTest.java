package jpa;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Appareil;
import domain.Chauffage;
import domain.Maison;
import domain.Personne;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager manager = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = manager.getTransaction();

		tx.begin();

		try {
			create(manager);
		} catch (Exception e) {
			e.printStackTrace();
		}

		tx.commit();

		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
	}

	private static void create(EntityManager manager) {
		Long nbPersonne = (Long) manager.createQuery("select count(p) FROM Personne p").getSingleResult();

		if (nbPersonne == 0) {

			Collection<Appareil> appList1 = new ArrayList<Appareil>();
			Collection<Appareil> appList2 = new ArrayList<Appareil>();
			Collection<Appareil> appList3 = new ArrayList<Appareil>();

			Personne HTSA = new Personne();
			Personne Yvann = new Personne();
			Personne Thomas = new Personne();

			Appareil tv = new Appareil();
			tv.setNom("TV");
			tv.setConso(120);
			tv.setProprio(HTSA);

			Appareil lDVD = new Appareil();
			lDVD.setNom("lecteurDVD");
			lDVD.setConso(19);
			lDVD.setProprio(HTSA);

			Appareil hc = new Appareil();
			hc.setNom("homecinema");
			hc.setConso(200);
			hc.setProprio(HTSA);

			Appareil mo = new Appareil();
			mo.setNom("micro_ondes");
			mo.setConso(200);
			mo.setProprio(Thomas);

			Appareil fo = new Appareil();
			fo.setNom("four");
			fo.setConso(200);
			fo.setProprio(Thomas);

			Appareil tel = new Appareil();
			tel.setNom("smart_phone");
			tel.setConso(100);
			tel.setProprio(Thomas);

			Appareil table = new Appareil();
			table.setNom("tablette");
			table.setConso(80);
			table.setProprio(Yvann);

			Appareil impr = new Appareil();
			impr.setNom("imprimante");
			impr.setConso(120);
			impr.setProprio(Yvann);

			appList1.add(tv);
			appList1.add(lDVD);
			appList1.add(hc);

			appList2.add(mo);
			appList2.add(fo);
			appList2.add(tel);

			appList3.add(table);
			appList3.add(impr);

			Chauffage ch1 = new Chauffage();
			Chauffage ch2 = new Chauffage();
			Chauffage ch3 = new Chauffage();
			Chauffage ch4 = new Chauffage();
			Chauffage ch5 = new Chauffage();
			Chauffage ch6 = new Chauffage();
			Chauffage ch7 = new Chauffage();

			Collection<Chauffage> chList1 = new ArrayList<Chauffage>();
			Collection<Chauffage> chList2 = new ArrayList<Chauffage>();
			Collection<Chauffage> chList3 = new ArrayList<Chauffage>();

			Maison maison1 = new Maison();
			maison1.setAdresse("5 square Dr Guerin");
			maison1.setChauffages(chList1);
			maison1.setNbPieces(2);
			maison1.setTaille(50);
			maison1.setProprio(Yvann);

			Maison maison2 = new Maison();
			maison2.setAdresse("64 boulevard Raymond Poincarré");
			maison2.setChauffages(chList2);
			maison2.setNbPieces(3);
			maison2.setTaille(80);
			maison2.setProprio(HTSA);

			Maison maison3 = new Maison();
			maison3.setAdresse("4 boulevard Solferino");
			maison3.setChauffages(chList3);
			maison3.setNbPieces(2);
			maison3.setTaille(60);
			maison3.setProprio(Thomas);

			ch1.setConso(50);
			ch2.setConso(60);
			ch3.setConso(70);
			ch4.setConso(60);
			ch5.setConso(50);
			ch6.setConso(80);
			ch7.setConso(80);

			ch1.setMaison(maison1);
			ch2.setMaison(maison1);
			ch7.setMaison(maison1);

			ch3.setMaison(maison2);
			ch4.setMaison(maison2);

			ch6.setMaison(maison3);
			ch5.setMaison(maison3);

			chList1.add(ch1);
			chList1.add(ch2);
			chList1.add(ch7);

			chList2.add(ch3);
			chList2.add(ch4);

			chList3.add(ch5);
			chList3.add(ch6);

			maison1.setChauffages(chList1);
			maison2.setChauffages(chList2);
			maison3.setChauffages(chList3);

			Collection<Personne> poteHtsa = new ArrayList<Personne>();
			Collection<Personne> poteYvann = new ArrayList<Personne>();
			Collection<Personne> poteThomas = new ArrayList<Personne>();

			Collection<Maison> maisonHtsa = new ArrayList<Maison>();
			Collection<Maison> maisonYvann = new ArrayList<Maison>();
			Collection<Maison> maisonThomas = new ArrayList<Maison>();

			maisonHtsa.add(maison3);
			maisonThomas.add(maison2);
			maisonYvann.add(maison1);

			HTSA.setPrenom("Hedi Theo");
			HTSA.setNom("Sahraoui");
			HTSA.setAppareils(appList1);
			HTSA.setMaisons(maisonHtsa);
			HTSA.setAge(16);

			Yvann.setPrenom("Yvann");
			Yvann.setNom("Josso");
			Yvann.setAppareils(appList2);
			Yvann.setMaisons(maisonYvann);
			Yvann.setAge(21);

			Thomas.setPrenom("Thomas");
			Thomas.setNom("Gregoire");
			Thomas.setAppareils(appList3);
			Thomas.setMaisons(maisonThomas);
			Thomas.setAge(22);

			poteHtsa.add(Yvann);
			poteHtsa.add(Thomas);

			poteYvann.add(Thomas);
			poteYvann.add(HTSA);

			poteThomas.add(Yvann);
			poteThomas.add(HTSA);

			HTSA.setAmis(poteHtsa);
			Yvann.setAmis(poteYvann);
			Thomas.setAmis(poteThomas);

			manager.persist(HTSA);
			manager.persist(Yvann);
			manager.persist(Thomas);

		}
	}

}
