# Testing in Java Environment #2 - Mocking, test objects types  

## Mockito

**Mocking**: *we don't want to test the original, complex object, a simple object is enough for us to put into the test. When we put not real elements into the test this name of mocking(of the external original objects).*

- make **AdatabaseConnection interface** in *com.mvn.junit*:

```java
package com.mvn.junit;

public interface DatabaseConnection {

	boolean checkUserPass(String user, String password);
}
```
Here we check, there is an user with password in the Database, so it gives true or false value.

Bicycle.java
```java
package com.mvn.junit;

public class Bicycle {

	DatabaseConnection dbCon;

	public Integer sum(String user, String password, int a, int b, int c) {
		//it asks the Database, does user exist in the Database?

		boolean checkUser = dbCon.checkUserPass(user, password);

		if (checkUser)
			return a+b+c;

		return null;
	}
}
```
TestBicycle.java
```java
package com.mvn.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestBicycle {

	Bicycle bike = null;
	DatabaseConnection dbCon = new DatabaseConnection() {
		public boolean checkUserPass(String user, String password) {
			return true;
		}
	};

	@Before
	public void init() {
		bike = new Bicycle();
	}

	@Test
	public void testSum() {
	}
}
```
The **DatabaseConnection dbCon is our mocked object** and gives back always True.

------------------------------------------------------
**dummy object**: a silly object, does not include implementation, we made just because we give it as parameter. We won't use.

**fake object**: it includes a really simple implementation, it's function returns a given value.

------------------------------------------------------
We make a constructor, we give a fake dependency what the bicycle set as own class variable. This **dbCon** is the "lamp of Bicycle" and always give back true, not a real object.

In Bicycle.java add constructors:
```java
public class Bicycle {

	DatabaseConnection dbCon;

	Bicycle() {

	}

	Bicycle(DatabaseConnection dbCon) {
		this.dbCon = dbCon;

	}

	public Integer sum(String user, String password, int a, int b, int c) {
	}
}
```
TestBicycle.java add *dbCon*
```java
@Before
	public void init() {
		bike = new Bicycle(dbCon);
	}
```
This calls checkUserPass method which get True always.

- expand *testSum()* in TestBicycle:
```java
@Test
public void testSum() {
  Integer expected = 3;
  assertEquals(expected, bike.sum("gyula", "jelszo", 1, 1, 1));
}
```

- run, you can get any password, the test will be successful

#### There are framework which can imitate objects. One of the most popular is **mockito.**

Search on internet *MVN Repository Mockito Core* and write into the *pom.xml*:

```xml
<!-- https://mvnrepository.com/artifact/org.mockito/mockito-core -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>3.3.3</version>
    <scope>test</scope>
</dependency>
```

## From this moment **I will be able to mock this dbCon object**:
```java
public class TestBicycle {

	Bicycle bike = null;

	DatabaseConnection dbCon = Mockito.mock(DatabaseConnection.class);

}
```

Frum this moment, this is not a fake object, but also a **stub object**

- run, it failed because it gives back null
- **teach to the object, give back mocked value:**
```
when(dbCon.checkUserPass("Gyula", "jelszo")).thenReturn(true);
```

```java
@Test
public void testSum() {

  when(dbCon.checkUserPass("gyula", "jelszo")).thenReturn(true);


  Integer expected = 3;
  assertEquals(expected, bike.sum("gyula", "jelszo", 1, 1, 1));  
  }
}
```
- run, you get green

**stub object**: a fake object which learnt certain cases. With a framework we can make and teach to give certain answers for certain asks.

*verify(dbCon).checkUserPass("gyula", "jelszo");*

```java
@Test
public void testSum() {

  when(dbCon.checkUserPass("gyula", "jelszo")).thenReturn(true);


  Integer expected = 3;
  assertEquals(expected, bike.sum("gyula", "jelszo", 1, 1, 1));
  verify(dbCon).checkUserPass("gyula", "jelszo");
}
```

**mock object**: if we can check the calling of stub object that is mock object
