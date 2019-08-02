package pool.services;

import java.util.ArrayList;
import java.util.List;

import pool.model.Objectile;

public class ObjectileList {

	private static List<Objectile> objectiles = new ArrayList<>();
	
	private ObjectileList() {
	}

	public static List<Objectile> getInstance(){
		return objectiles;
	}
}
