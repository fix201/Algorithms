package project3;

import java.util.*;

class Node {
	// private String name;
	// private int length;
	private Map<String, Integer> nodes;
	private ArrayList<String> nameVertex;
	private ArrayList<Integer> lengthEdge;

	public Node() {
		nodes = new HashMap<String, Integer>();
		nameVertex = new ArrayList<String>();
		lengthEdge = new ArrayList<Integer>();
	}

	public void addNode(String name, int length) {

		nodes.put(name, length);
		lengthEdge.add(length);
		nameVertex.add(name);
	}

	@Override
	public String toString() {
		String tr = "";
		for (String x : nodes.keySet()) {
			tr += x + "=" + nodes.get(x) + ",";
		}
		return tr;
	}

	public ArrayList<String> getVertex() {
		return nameVertex;
	}

	public int getEdgeLengt(String vertex) {
		return nodes.get(vertex);
	}



}
