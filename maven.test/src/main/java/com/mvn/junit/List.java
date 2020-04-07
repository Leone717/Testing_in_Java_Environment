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
