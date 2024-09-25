package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private static class Marche{
		private Etal[] etals;
		private Marche(int nbEtal) {
			etals = new Etal[nbEtal];
			for(int i=0; i<nbEtal;i++) {
				etals[i] = new Etal();
			}
		}
		
		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
		}
		
		int trouverEtalLibre() {
			int i  = 0;
			while(i<etals.length && etals[i].isEtalOccupe()) {
				i++;
			}
			if (i == etals.length) {
				return -1;
			}
			return i;
		}
		
		 private Etal[] trouverEtals(String produit) {
			 Etal[] etalProduit;
			 int j = 0;
			 for(int i = 0; i< etals.length; i++) {
				 if (etals[i].contientProduit(produit)) {
					 j++;
				 }
			 }
			 etalProduit = new Etal[j];
			 int k = 0;
			 for(int i =0; i<etals.length;i++) {
				 if (etals[i].contientProduit(produit)) {
					 etalProduit[k] = etals[i];
					 k++;
				 }
			 }
			 return etalProduit;
		 }
		 
		 private Etal trouverVendeur(Gaulois gaulois) {
			 for(int i = 0;  i< etals.length; i++) {
				 if (etals[i].getVendeur().equals(gaulois)){
					 return etals[i];
				 }		
			 }
			 return null;
		 }
		 
		 private String afficherMarche() {
			 int nbEtalVide = 0;
			 StringBuilder chaine = new StringBuilder();
			 for(int i = 0;i<etals.length;i++) {
				 if(!etals[i].isEtalOccupe()) {
					 nbEtalVide++;
				 }else {
					 chaine.append(etals[i].afficherEtal());
				 }
			 }
			 if (nbEtalVide > 0) {
				 return  "Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n."; 
			 }
		 }
	}
	
}