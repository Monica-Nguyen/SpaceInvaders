import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;

public class PreyTestForEclipse extends FormatTester {
	public static final String CLASSNAME = "Prey";

	public PreyTestForEclipse() {
		super(CLASSNAME, true);
	}
	
	private void testInterface() {
		String[] instanceVars = {"String defence"};
		assertTrue("Instance variables should be private with correct name and signature.", instanceVariablesArePrivate(instanceVars));

		assertTrue("Class should not have the default constructor.", noDefaultConstructor());
		
		assertFalse("Should not override or call getStatus.", hasMethod("getStatus"));
		assertFalse("Should not override or call getType.", hasMethod("getType"));
		assertFalse("Should not override or call setType.", hasMethod("setType"));
		assertFalse("Should not override getHealth.", hasMethod("int getHealth"));
		assertFalse("Should not override or call setHealth.", hasMethod("setHealth"));
	}
	
	
	// Testing constructors
	
	@Test
	public void test_Constructor_defence_valid(){
		testInterface();
		Prey c = new Prey('f',25,"stampede");
		assertEquals("Created prey (f,25,stampede) testing defence", "stampede", c.getDefence());
		assertEquals("Created prey with valid 25 health.", 25, c.getHealth());
		assertEquals("Created prey (f,25,stampede) testing type", 'f', c.getType());

		c = new Prey('f',25,"huddle");
		assertEquals("Created prey (f,25,huddle) testing defence", "huddle", c.getDefence());
		assertEquals("Created prey with valid 25 health.", 25, c.getHealth());
		assertEquals("Created prey (f,25,huddle) testing type", 'f', c.getType());

		c = new Prey('f',25,"hide");
		assertEquals("Created prey (f,25,hide) testing defence", "hide", c.getDefence());
		assertEquals("Created prey with valid 25 health.", 25, c.getHealth());
		assertEquals("Created prey (f,25,hide) testing type", 'f', c.getType());
	}
	
	@Test
	public void test_Constructor_defence_invalid(){
		testInterface();
		Prey c = new Prey('f',25,"camouflage");
		assertEquals("Created prey (f,25,camouflage) testing defence", "stampede", c.getDefence());
		assertEquals("Created prey with valid 25 health.", 25, c.getHealth());
		assertEquals("Created prey (f,25,camouflage) testing type", 'f', c.getType());

		c = new Prey('f',25,"nothing");
		assertEquals("Created prey (f,25,nothing) testing defence", "stampede", c.getDefence());
		assertEquals("Created prey with valid 25 health.", 25, c.getHealth());
		assertEquals("Created prey (f,25,nothing) testing type", 'f', c.getType());

		c = new Prey('f',25,"");
		assertEquals("Created prey (f,25,'') testing defence", "stampede", c.getDefence());
		assertEquals("Created prey with valid 25 health.", 25, c.getHealth());
		assertEquals("Created prey (f,25,'') testing type", 'f', c.getType());
	}
	
	@Test
	public void test_CopyConstructor() {
		testInterface();
		Prey c = new Prey('n',10,"huddle");
		Prey c2 = new Prey(c);
		assertEquals("Copied prey with defence huddle", "huddle", c2.getDefence());
		assertEquals("Copied prey with 10 health.", 10, c2.getHealth());
		assertEquals("Copied prey with 'n' type.", 'n', c2.getType());
	}
	
	@Test
	public void test_CopyConstructor2() {
		testInterface();
		Prey c = new Prey('a',45,"hide");
		Prey c2 = new Prey(c);
		assertEquals("Copied prey with defence hide", "hide", c2.getDefence());
		assertEquals("Copied prey with 45 health.", 45, c2.getHealth());
		assertEquals("Copied prey with 'a' type.", 'a', c2.getType());
	}


// Testing setter and getters

	@Test
	public void test_setter_and_getter_defence_invalid(){
		testInterface();
		Prey c = new Prey('n',50,"huddle");
		c.setDefence("run");
		assertEquals("Set defence to invalid run, should have left unchanged from huddle", "huddle", c.getDefence());
	}
	
	@Test
	public void test_setter_and_getter_defence_hide(){
		testInterface();
		Prey c = new Prey('n',50,"stampede");
		c.setDefence("hide");
		assertEquals("Changed defence from stampeded to hide", "hide", c.getDefence());
	}
	
	@Test
	public void test_setter_and_getter_defence_huddle(){
		testInterface();
		Prey c = new Prey('n',50,"hide");
		c.setDefence("huddle");
		assertEquals("Changed defence from hide to huddle", "huddle", c.getDefence());
	}
	
	@Test
	public void test_setter_and_getter_defence_stampede(){
		testInterface();
		Prey c = new Prey('n',50,"huddle");
		c.setDefence("stampede");
		assertEquals("Changed defence from huddle to stampede", "stampede", c.getDefence());
	}
	
	@Test
	public void test_setter_and_getter_herdSize_initialized(){
		testInterface();
		Prey p = new Prey('a',50,"stampede");
		assertEquals("Expected herd size to be initialized to 1.", 1, p.getHerdSize());
	}
	
	@Test
	public void test_setter_and_getter_herdSize_positive(){
		testInterface();
		Prey p = new Prey('a',50,"stampede");
		p.setHerdSize(10);
		assertEquals("Set herd size to 10..", 10, p.getHerdSize());
	}
	
	@Test
	public void test_setter_and_getter_herdSize_negative(){
		testInterface();
		Prey p = new Prey('a',50,"stampede");
		p.setHerdSize(-5);
		assertEquals("Set herd size to invalid negative, expected unchanged from 1", 1, p.getHerdSize());
	}
	
	@Test
	public void test_setter_and_getter_herdSize_positiveThenNegative(){
		testInterface();
		Prey p = new Prey('a',50,"stampede");
		p.setHerdSize(5);
		p.setHerdSize(-4);
		assertEquals("Set herd size to invalid negative, expected unchanged from 5", 5, p.getHerdSize());
	}
	
	@Test
	public void test_getRelativeHealth_stampedeAndHuddle_herdSize1(){
		testInterface();
		Prey p = new Prey('m',10,"stampede");
		assertEquals("Stampede defence, herd size 1 and health 10.", 1.0, p.getRelativeHealth(),0.000001);
		
		p = new Prey('n', 105, "huddle");
		assertEquals("Huddle defence, herd size 1 and health 105.", 10.5, p.getRelativeHealth(),0.000001);
	}
	
	@Test
	public void test_getRelativeHealth_stampedeAndHuddle_herdSizeLarge(){
		testInterface();
		Prey p = new Prey('m',10,"stampede");
		p.setHerdSize(100);
		assertEquals("Stampede defence, herd size 100 and health 10.", 100.0, p.getRelativeHealth(),0.000001);
		
		p = new Prey('n', 15, "huddle");
		p.setHerdSize(5432);
		assertEquals("Huddle defence, herd size 5432 and health 15.", 8148, p.getRelativeHealth(),0.000001);
	}
	
	@Test
	public void test_getRelativeHealth_hide(){
		testInterface();
		Prey p = new Prey('m',10,"hide");
		assertEquals("Hide defence, herd size 1 and health 10.", 10.0, p.getRelativeHealth(),0.000001);
		
		p = new Prey('n', 105, "hide");
		p.setHerdSize(10);
		assertEquals("Hide defence, herd size 10 and health 105.", 105, p.getRelativeHealth(),0.000001);
	}
	

	
	// ToString
	
	@Test
	public void test_toString() {
		testInterface();
		assertTrue("Should override toString and it should invoke parent toString (not getter methods in parent).", toStrInvokesParentToStr());
		Prey c = new Prey('b', 51, "hide");
		c.setHerdSize(50);

		assertEquals("[Prey] Type: b Health: 51.0 Defence: hide", c.toString());
	}

	@Test
	public void test_toString2() {
		testInterface();
		assertTrue("Should override toString and it should invoke parent toString (not getter methods in parent).", toStrInvokesParentToStr());
		Prey c = new Prey('b', 10, "stampede");
		c.setHerdSize(100);

		assertEquals("[Prey] Type: b Health: 100.0 Defence: stampede", c.toString());
	}

	// testing that getStatus is correctly calling overridden method getRelativeHealth
	
	@Test
	public void test_getStatusInParent_criticalAndTenuous(){
		testInterface();
		Prey c = new Prey('a', 100, "stampede");
		c.setHerdSize(1);
		String actualStatus = c.getStatus();
		assertEquals("health of 100, stampede", "critical", actualStatus);

		c = new Prey('a', 3, "huddle");
		c.setHerdSize(100);
		actualStatus = c.getStatus();
		assertEquals("health of 3, huddle", "tenuous", actualStatus);
	}
	
	@Test
	public void test_getStatusInParent_goodAndExcellent(){
		testInterface();
		Prey c = new Prey('a', 50, "hide");
		String actualStatus = c.getStatus();
		assertEquals("health of 50, hide", "good", actualStatus);

		c = new Prey('a', 100, "huddle");
		c.setHerdSize(30);
		actualStatus = c.getStatus();
		assertEquals("health of 100, huddle", "excellent", actualStatus);
	}
}
