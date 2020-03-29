import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;

public class PredatorTestForEclipse extends FormatTester{
	public static final String CLASSNAME = "Predator";

	public PredatorTestForEclipse() {
		super(CLASSNAME, true);
	}
	
	private void testInterface() {
		String[] instanceVars = {"String huntStyle"};
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
	public void test_Constructor_huntStyle_valid(){
		testInterface();
		Predator c = new Predator('f',25,"ambush");
		assertEquals("Created prey (f,25,ambush) testing huntStyle", "ambush", c.getHuntStyle());
		assertEquals("Created prey with valid 25 health.", 25, c.getHealth());
		assertEquals("Created prey (f,25,ambush) testing type", 'f', c.getType());

		c = new Predator('f',25,"stalk");
		assertEquals("Created prey (f,25,stalk) testing huntStyle", "stalk", c.getHuntStyle());
		assertEquals("Created prey with valid 25 health.", 25, c.getHealth());
		assertEquals("Created prey (f,25,stalk) testing type", 'f', c.getType());
	}
	
	@Test
	public void test_Constructor_huntStyle_invalid(){
		testInterface();
		Predator c = new Predator('f',25,"camouflage");
		assertEquals("Created prey (f,25,camouflage) testing huntStyle", "ambush", c.getHuntStyle());
		assertEquals("Created prey with valid 25 health.", 25, c.getHealth());
		assertEquals("Created prey (f,25,camouflage) testing type", 'f', c.getType());

		c = new Predator('f',25,"nothing");
		assertEquals("Created prey (f,25,nothing) testing huntStyle", "ambush", c.getHuntStyle());
		assertEquals("Created prey with valid 25 health.", 25, c.getHealth());
		assertEquals("Created prey (f,25,nothing) testing type", 'f', c.getType());

		c = new Predator('f',25,"");
		assertEquals("Created prey (f,25,'') testing huntStyle", "ambush", c.getHuntStyle());
		assertEquals("Created prey with valid 25 health.", 25, c.getHealth());
		assertEquals("Created prey (f,25,'') testing type", 'f', c.getType());
	}
	
	@Test
	public void test_CopyConstructor() {
		testInterface();
		Predator c = new Predator('n',10,"ambush");
		Predator c2 = new Predator(c);
		assertEquals("Copied prey with huntStyle stalk", "ambush", c2.getHuntStyle());
		assertEquals("Copied prey with 10 health.", 10, c2.getHealth());
		assertEquals("Copied prey with 'n' type.", 'n', c2.getType());
	}
	
	@Test
	public void test_CopyConstructor2() {
		testInterface();
		Predator c = new Predator('a',45,"stalk");
		Predator c2 = new Predator(c);
		assertEquals("Copied prey with huntStyle stalk", "stalk", c2.getHuntStyle());
		assertEquals("Copied prey with 45 health.", 45, c2.getHealth());
		assertEquals("Copied prey with 'a' type.", 'a', c2.getType());
	}


// Testing setter and getters

	@Test
	public void test_setter_and_getter_huntStyle_invalid(){
		testInterface();
		Predator c = new Predator('n',50,"ambush");
		c.setHuntStyle("run");
		assertEquals("Set huntStyle to invalid run, should have left unchanged from ambush", "ambush", c.getHuntStyle());
	}
	
	@Test
	public void test_setter_and_getter_huntStyle_stalk(){
		testInterface();
		Predator c = new Predator('n',50,"ambush");
		c.setHuntStyle("stalk");
		assertEquals("Changed huntStyle from ambushd to stalk", "stalk", c.getHuntStyle());
	}
	
	@Test
	public void test_setter_and_getter_huntStyle_huddle(){
		testInterface();
		Predator c = new Predator('n',50,"stalk");
		c.setHuntStyle("ambush");
		assertEquals("Changed huntStyle from stalk to ambush", "ambush", c.getHuntStyle());
	}
	
	@Test
	public void test_getRelativeHealth_ambush(){
		testInterface();
		Predator p = new Predator('m',10,"ambush");
		assertEquals("Ambush huntStyle and health 10.", 15.0, p.getRelativeHealth(),0.000001);
	}
	
	@Test
	public void test_getRelativeHealth_ambush2(){
		testInterface();
		Predator p = new Predator('m',123,"ambush");
		assertEquals("Ambush huntStyle and health 123.", 184.5, p.getRelativeHealth(),0.000001);
	}
	
	@Test
	public void test_getRelativeHealth_stalk(){
		testInterface();
		Predator p = new Predator('m',10,"stalk");
		assertEquals("Hide huntStyle, herd size 1 and health 10.", 7.5, p.getRelativeHealth(),0.000001);
	}
	
	@Test
	public void test_getRelativeHealth_stalk2(){
		testInterface();
		Predator p = new Predator('m',43,"stalk");
		assertEquals("Hide huntStyle, herd size 1 and health 43.", 32.25, p.getRelativeHealth(),0.000001);
	}
	

	
	// ToString
	
	@Test
	public void test_toString() {
		testInterface();
		assertTrue("Should override toString and it should invoke parent toString (not getter methods in parent).", toStrInvokesParentToStr());
		Predator c = new Predator('b', 51, "stalk");

		assertEquals("[Predator] Type: b Health: 38.25 Hunt style: stalk", c.toString());
	}

	@Test
	public void test_toString2() {
		testInterface();
		assertTrue("Should override toString and it should invoke parent toString (not getter methods in parent).", toStrInvokesParentToStr());
		Predator c = new Predator('f', 5, "ambush");

		assertEquals("[Predator] Type: f Health: 7.5 Hunt style: ambush", c.toString());
	}
	
	// testing that getStatus is correctly calling overridden method getRelativeHealth
	
	@Test
	public void test_getStatusInParent_criticalAndTenuous(){
		testInterface();
		Predator c = new Predator('a', 1, "ambush");
		String actualStatus = c.getStatus();
		assertEquals("health of 1, ambush", "critical", actualStatus);

		c = new Predator('a', 50, "stalk");
		actualStatus = c.getStatus();
		assertEquals("health of 50, stalk", "tenuous", actualStatus);
	}
	@Test
	public void test_getStatusInParent_goodAndExcellent(){
		testInterface();
		Predator c = new Predator('a', 40, "ambush");
		String actualStatus = c.getStatus();
		assertEquals("health of 50, ambush", "good", actualStatus);

		c = new Predator('a', 100, "stalk");
		actualStatus = c.getStatus();
		assertEquals("health of 100, stalk", "excellent", actualStatus);
	}
}
