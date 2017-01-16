package fr.pe.javanais;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
public class JavanaisTFTest {

	@Inject
	JavanaisTF traducteur;
	
	/**
	 * Test de la traduction en français
	 */
	@Test
	public void testTraductionMotFr() {
		Assert.assertEquals("bavonjavour", this.traducteur.traduireJavanais("bonjour"));
		Assert.assertEquals("chavantave", this.traducteur.traduireJavanais("chante"));
		Assert.assertEquals("mavoyen", this.traducteur.traduireJavanais("moyen"));
		Assert.assertEquals("avexavemplave", this.traducteur.traduireJavanais("exemple"));
		Assert.assertEquals("avau", this.traducteur.traduireJavanais("au"));
		Assert.assertEquals("avais", this.traducteur.traduireJavanais("ais"));
		Assert.assertEquals("bavoir", this.traducteur.traduireJavanais("boir"));
	}

	/**
	 * Test de la traduction en javanais
	 */
	@Test
	public void testTraductionMotJavanais() {
		Assert.assertEquals("bonjour", this.traducteur.traduireFR("bavonjavour"));
		Assert.assertEquals("chante", this.traducteur.traduireFR("chavantave"));
		Assert.assertEquals("moyen", this.traducteur.traduireFR("mavoyen"));
		Assert.assertEquals("exemple", this.traducteur.traduireFR("avexavemplave"));
		Assert.assertEquals("au", this.traducteur.traduireFR("avau"));
		Assert.assertEquals("avais", this.traducteur.traduireFR("avavavais"));
		Assert.assertEquals("ais", this.traducteur.traduireFR("avais"));
		Assert.assertEquals("bavoir", this.traducteur.traduireFR("bavavavoir"));
		Assert.assertEquals("boir", this.traducteur.traduireFR("bavoir"));
	}
	
	/**
	 * Test de la traduction en automatique
	 */
	@Test
	public void testTraductionAutomatique() {
		Assert.assertEquals("bavonjavour", this.traducteur.traduireAutomatiquement("bonjour"));
		Assert.assertEquals("chavantave", this.traducteur.traduireAutomatiquement("chante"));
		Assert.assertEquals("mavoyen", this.traducteur.traduireAutomatiquement("moyen"));
		Assert.assertEquals("avexavemplave", this.traducteur.traduireAutomatiquement("exemple"));
		Assert.assertEquals("avau", this.traducteur.traduireAutomatiquement("au"));
		Assert.assertEquals("avais", this.traducteur.traduireAutomatiquement("ais"));
		Assert.assertEquals("bavoir", this.traducteur.traduireAutomatiquement("boir"));
		Assert.assertEquals("bonjour", this.traducteur.traduireAutomatiquement("bavonjavour"));
		Assert.assertEquals("chante", this.traducteur.traduireAutomatiquement("chavantave"));
		Assert.assertEquals("moyen", this.traducteur.traduireAutomatiquement("mavoyen"));
		Assert.assertEquals("exemple", this.traducteur.traduireAutomatiquement("avexavemplave"));
		Assert.assertEquals("au", this.traducteur.traduireAutomatiquement("avau"));
		Assert.assertEquals("avais", this.traducteur.traduireAutomatiquement("avavavais"));
		Assert.assertEquals("ais", this.traducteur.traduireAutomatiquement("avais"));
		Assert.assertEquals("bavoir", this.traducteur.traduireAutomatiquement("bavavavoir"));
		Assert.assertEquals("boir", this.traducteur.traduireAutomatiquement("bavoir"));
	}
	
	/**
	 * Test de la traduction automatique sur les mots ambigue
	 */
	@Test
	public void testTraductionAmbigue() {
		Assert.assertNotSame("avavavoir", this.traducteur.traduireAutomatiquement("avoir"));
		Assert.assertNotSame("avavavais", this.traducteur.traduireAutomatiquement("avais"));
		Assert.assertNotSame("bavavavoir", this.traducteur.traduireAutomatiquement("bavoir"));
	}
	
}
