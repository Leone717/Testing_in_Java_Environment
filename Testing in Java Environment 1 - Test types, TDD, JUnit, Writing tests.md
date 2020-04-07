# Testing in Java Environment #1 - Test types, TDD, JUnit,  Writing Tests  

```
  Unit Test  
Junit   TestNG

              Automated Functional Test
                      Selenium

                                        Stress Test
  Integration Test                        JMeter

                              Penetration Test

```

## Unit Test

**We test only one unit.**

```
Test cases:

input values        expected output values
3,3         ------>        6
9,6         ------>       15
0,0         ------>        0
4,4         ------>        8

public int summation(int a, int b,){
  return a+b;
}
```

If with all cases it runs, the **unit test is successful.**

```
Test cases:

input values        output values   
3,3         ------>        6            passed
9,6         ------>       15            passed
0,0***      ------>        1***         !failed!
4,4         ------>        8            passed

public int summation(int a, int b,){
  return a+b;
}
```
This unit test is **failed. We save it this case test, and we will run it time by time.** If somebody or we write to this code, we will run this test case.

## Integration Test

**The unit interacts with other elements(units)** This is the integration test. We test the operation of components.


The reference of the name **is wrong:**
```
ResultSet rs = createStatement.executeQery(sql);
    while(res.next()){
        String name = rs.getString("namme"*);
    }                                 |
          ----------------------------|-----
          |         Database          |    |
          ----------------------------V-----
          | ID   |     AGE     |     NAME  |

```

## Functional Test

**An user or a user testing framework uses the ready product finding bugs and errors**
```
#1
Click registration button --> Works?
Enter input fields        --> Works?
Click Exit button         --> Works?

#2
A beta game opened for the users, they will be the testers and feedback problems before release.
```
**The functional test can be automated.** If you have a webpage you can automatize: user register and get an email and go there and verify himself.

## Stress Test

**We test the resources of the product.** It can accept enough users registration or manage a lot of logged users/ server requests in one time.

## Penetration Test

**White hat hackers attack the software by the approval of the makers**

## TDD - Test-driven development

- you make a test case, **which is does not work or does not exist**
- it is **slow method**
- firstly **we make a unsuccessful test case**
- write to the code just **enough to pass the test, not more(!)**
- *the test drives the development*

```
1, make a small test case--> FAIL --> we don't have code
--------------------------------------------------------
2, write to the code to work--> PASS --> the test is successful
--------------------------------------------------------
3, make a new test case --> FAIL --> test code, we don't have this code
--------------------------------------------------------
4, write to the code --> PASS -- the test is successful
```
## Pratice

- make a new **Maven project** (Maven gets the dependencies) in STS (Spring Tool Suite), *sign 2 pipes*

*com.testmaven  
maven.test     
0.0.1-SNAPSHOT    
jar
TestProject*

We have **src/main/junit:**:

App.java
```java
package com.mvn.junit;

public class App {

	public static void main(String [] args) {

		System.out.println("Hello World!");
	}
}
```
Bycicle.java
```java
package com.mvn.junit;

public class Bicycle {

	public int sum(int a, int b, int c) {
		return 0;
	}
}
```
and in **src/test/java:**
TestBycicle.java
```java
package com.mvn.junit;

public class Testbicycle {

	Bicycle bike = null;

	@Before
	public void init() {
		bike = new Bicycle();
	}
}
```

Here click on *@Before --> Fix project setup...--> Add archive 'junit-4.12.jar'**      

Check Referenced Libraries --> *'junit-4.12.jar'*

- referesh the **pom.xml file:**

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.testmaven</groupId>
  <artifactId>maven.test</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>TestProject</name>

  <dependencies>
  	<!-- https://mvnrepository.com/artifact/junit/junit -->
	<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
	</dependency>
  </dependencies>

</project>
```

We add a new part to the Testbicycle.class:

```java
@Test
public void testSum() {
}
```

```java
package com.mvn.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestBicycle {

	Bicycle bike = null;

	@Before
	public void init() {
		bike = new Bicycle();
	}

	@Test
	public void testSum() {
		int expected = 3;

		int result = bike.sum(1,  1,  1);

		assertEquals(expected, result);

	}
}
```

- right click -> run as --> Junit test
- see the brown line, failed
- expand **Bycicle.java**:

```java
public int sum(int a, int b, int c) {
  return a+b+c;
  }
```

- **restart the application --> green line, the test is successful**
- expand our test case:

```java
@Test
public void testSum() {

  assertEquals(3, bike.sum(1, 1, 1));
  assertEquals(9, bike.sum(2, 3, 4));
  assertEquals(11, bike.sum(4, 3, 4));
  assertEquals(1980, bike.sum(2000, -30, 10));
}
```
- run-->ok
- try wrong number --> brawn line
- if **one line is wrong, the test is failed**

## Writing tests witout TDD

- make **List.java** in **src/main/java com.mcn.unit**:

```java
package com.mvn.junit;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class List {

		private ArrayList<String> people = new ArrayList<String>();

		public void add (String person) {
			 	people.add(person);
		}

		public void remove(String person) {
			if (!people.contains(person)) {
				throw new NoSuchElementException();
			}

			people.remove(person);
		}

		public int size() {
			return people.size();
		}

		public boolean isEmpty() {
			return people.isEmpty();
		}

		public void removeAll() {
			people.clear();
		}
}
```

- write test for List.class as **TestList.class**(com.mvn.unit package):

```java
package com.mvn.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestList {

	private List testPeople = new List();

	@Before
	public void init() {
		testPeople.add("Gyula");
		testPeople.add("Gizi");
	}

	@Test
	public void testSize() {
		assertEquals("Méret ellenőrzés", 2, testPeople.size());
	}
}
```
We will have 2 people as list, and we check it's size(2). Run, you get green.

**@Before:** with this annotation this method run firstly  
**@Test:** this annotation assigns the tested method

- run with wrong parameters, you get : *at com.mvn.junit.TestList.testSize(TestList.java:20)*

**assertTrue**: it checks the value is right  
**assertFalse**: it checks the value is false

- expand the **TestList.class**:

```java
package com.mvn.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestList {

	private List testPeople = new List();

	@Before
	public void init() {
		testPeople.add("Gyula");
		testPeople.add("Gizi");
	}

	@Test
	public void testSize() {
		assertEquals("Méret ellenőrzés", 2, testPeople.size());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(testPeople.isEmpty());
}
```
- run, you get *green*
- add theese lines:

```java
@Test
	public void testAdd() {
		testPeople.add("Jani");
		assertEquals("Hozzáadás ellenőrzés", 3, testPeople.size());
	}

	@Test
	public void testRemove() {
		testPeople.removeAll();
		assertTrue(testPeople.isEmpty());
	}
}
```

- run, you get *green*
- write *Norbi* into the *remove() method*
- modify the last 3 methods:

```java
@Test
public void testAdd() {
  testPeople.add("Jani");
  assertEquals("Hozzáadás ellenőrzés", 3, testPeople.size());
}

@Test
public void remove() {
  testPeople.remove("Norbi");

}
@Test
public void testRemoveAll() {
  testPeople.removeAll();
  assertTrue(testPeople.isEmpty());
  }
}
```

- **Norbi is not member, so we get brawn line**

This is great, because I am waiting error, I know Norbi is not member, so JUnit helps me. **We expand @Test annotation above remove() method:**

```java
@Test(expected = NoSuchElementException.class)
```
This assigns, we wait Exception, so the test will be successful in this case. Run.

But what happen, if we delete Gizi? The test fails.

- add this last method:
```java
@After
public void destroy() {
  testPeople.removeAll();
}
```

*Sequence of annotations:*

1, Firsty *@Before* runs   
2, Secondly the *@Test* methods runs   
3, Finally the *@After* method is the last
