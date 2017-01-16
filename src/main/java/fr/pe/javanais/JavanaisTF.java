package fr.pe.javanais;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavanaisTF {

	/**
	 * création du logger
	 */
	public Logger logger = LoggerFactory.getLogger(JavanaisTF.class);
	
	/**
	 * La liste des voyelles française
	 */
	private final List<String> voyelle = new ArrayList<String>(Arrays.asList("a", "e", "i", "o", "u", "y"));

	/**
	 * Fonction permettant déterminer et traduire automatiquement
	 * du français vers le javanais et inversement
	 * 
	 * @param pLigne
	 * 			La ligne à traduire en français ou javanais
	 * @return
	 * 			La ligne traduite en français ou javanais
	 */
	public String traduireAutomatiquement(final String pLigne) {
		// Si la ligne est une phrase en français on la traduit en javanais
		if (this.isPhraseFr(pLigne)) {
			logger.info("Traduction Javanais");
			return traduireJavanais(pLigne);
			// Si la ligne ne contient pas un mot en français on la traduit en javanais
		} else if (pLigne.contains(" ") && !this.isPhraseFr(pLigne)) {
			logger.info("Traduction FR");
			return traduireFR(pLigne);
		}
		// Si il ne s'agit pas d'une phrase mains d'un mot unique qui n'est pas en français (contient des av)
		if (!this.isPhraseFr(pLigne)) {
			// On verifie si le mot peut être traduit en javanais et 
			// re-traduit en français sans "altérer" le mot d'origine
			if (pLigne.equals(this.traduireFR(this.traduireJavanais(pLigne)))) {
				logger.info("Mot ambigue traduction FR par défaut");
				return traduireFR(pLigne); 
			} else {
				logger.info("Mot ambigue traduction Javanais par défaut");
				return traduireJavanais(pLigne);
			}
			// sinon on le traduit en javanais car il n'y a pas de chaine "av" dedans
		} else {
			logger.info("Traduction Javanais");
			return traduireJavanais(pLigne);
		}
	}

	/**
	 * Fonction de traduction d'une ligne française en javanais
	 * 
	 * @param pLigne
	 * 			La ligne à traduire française
	 * @return
	 * 			La ligne traduite en javanais
	 */
	public String traduireJavanais(final String pLigne) {
		String resultat = "";
		boolean consonnePrec = true;
		for (final char caract : pLigne.toCharArray()) {
			if (!voyelle.contains(String.valueOf(caract).toLowerCase())) {
				consonnePrec = true;
			} else if (consonnePrec) {
				resultat += "av";
				consonnePrec = false;
			}
			resultat += caract;
		}
		return resultat;
	}

	/**
	 * Fonction de traduction d'une ligne javanais en français
	 * 
	 * @param pLigne
	 * 			La ligne à traduire javanais
	 * @return
	 * 			La ligne traduite en français
	 */
	public String traduireFR(final String pLigne) {
		String resultat = pLigne.replaceAll("avav", "#replace#");
		resultat = resultat.replaceAll(" av", " ");
		resultat = resultat.replaceAll("^av", "");
		resultat = resultat.replaceAll("([^aeiouy])(av)", "$1");
		return resultat.replaceAll("#replace#", "av");
	}

	/**
	 * Fonction permettant de déterminer si la ligne contient un mot en français
	 * 
	 * @param pLigne
	 * 			La ligne à tester
	 * @return
	 * 			true si la ligne contient un mot français, false sinon
	 */
	private boolean isPhraseFr(final String pLigne) {
		boolean consonnePrec = true;
		String chaineParasite = "";
		for (final String mot : pLigne.split(" ")) {
			if (!mot.contains("av")) {
				if (this.traduireJavanais(mot).contains("av")) {
					return true;
				}
			} else {
				for (final char caract : pLigne.toCharArray()) {
					if (consonnePrec) {
						chaineParasite += caract;
					}
					if (!voyelle.contains(String.valueOf(caract).toLowerCase())) {
						consonnePrec = true;
						if (2 == chaineParasite.length() && !"av".equalsIgnoreCase(chaineParasite)) {
							return true;
						} else if (2 == chaineParasite.length() && "av".equalsIgnoreCase(chaineParasite)) {
							consonnePrec = false;
						}
						chaineParasite = "";
					}
				}
			}
		}
		return false;
	}
}
