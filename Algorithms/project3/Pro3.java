package project3;

import java.io.*;
import java.util.*;

public class Pro3 {

	private Map<String, Node> myMap = new TreeMap<String, Node>();
	// private Node a = new Node();
	private ArrayList<String> allKey = new ArrayList<String>();

	private String[] prev;
	private Integer[] dist;

	public Pro3() {
		prev = new String[19];
		dist = new Integer[19];
		for (int i = 0; i < 19; i++) {
			prev[i] = "";
			dist[i] = 99999;

		}
	}

	public void initialize(String myFileName) {
		// create arraylist of lines
		ArrayList<String> lines = new ArrayList<String>();
		String line;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(myFileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);// add lines to ArrayList of lines
			}

			System.out.println();
			System.out.println("!!!!!!!!!!DONE READING FILE!!!!!!!!!!");
			System.out.println();

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + myFileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + myFileName + "'");
		}
		// after reading file, start to split each line by calling split method
		for (String s : lines) {
			splitLine(s);
		}

	}

	// split method to split each line into tokens
	public void splitLine(String line) {

		Node node = new Node();

		// tokens array of string save string after split by ":"
		String[] tokens = line.split(":");
		// description array of string save string tokens[1] after split by ","
		String[] description = tokens[1].split(",");
		// convert 1st string of description into double data type
		for (int i = 0; i < description.length; i++) {
			String[] split = description[i].split("=");
			int foo = Integer.parseInt(split[1]);
			node.addNode(split[0], foo);
		}
		// save all desciption's array elements into Description class

		// add service name and its descriptions into dictionary
		myMap.put(tokens[0], node);

	}


	/*
	 * procedure dijkstra(G,l,s) input: graph G = (V,E); node s; positive edge
	 * lengths le output: for each node u, dist[u] is set to its distance from s
	 * 
	 * for u in V: dist[u] = ∞ prev[u] = nil dist[s] = 0 H = makequeue(V) // key =
	 * dist[] while H is not empty: u = deletemin(H) for each edge (u,v) in E: if
	 * dist[v] > dist[u] + l(u,v): dist[v] = dist[u] + l(u,v) prev[v] = u
	 * decreasekey(H,v)
	 * 
	 */

	public void dijkstra(String building) {

		getAllKey();

		if (allKey.contains(building)) {

			int x = allKey.indexOf(building);
			dist[x] = 0;// dist from building is 0
			String[] queueH = new String[19];// make queue

			ArrayList<String> unsettleNode = new ArrayList<String>();

			for (String copy : allKey) {
				unsettleNode.add(copy);
			}

			while (unsettleNode.size() != 0) {

				queueH = new String[unsettleNode.size()];

				for (int i = 0; i < unsettleNode.size(); i++) {
					queueH[i] = unsettleNode.get(i);// queue with all buildings
				}

				String u = deletemin(queueH);
				unsettleNode.remove(u);

				int indexU = allKey.indexOf(u);// index of u in allKey
				Node node = myMap.get(u);
				queueH = new String[unsettleNode.size()];

				for (int i = 0; i < unsettleNode.size(); i++) {
					queueH[i] = unsettleNode.get(i);// queue with all buildings
				}

				for (String v : node.getVertex()) {

					int indexV = allKey.indexOf(v);

					if (dist[indexV] > dist[indexU] + node.getEdgeLengt(v)) {
						dist[indexV] = dist[indexU] + node.getEdgeLengt(v);
						prev[indexV] = u;
					}

					String u1 = deletemin(queueH);

					unsettleNode.remove(u1);

					int indexU1 = allKey.indexOf(v);
					Node node1 = myMap.get(v);

					for (String v1 : node1.getVertex()) {

						int indexV1 = allKey.indexOf(v1);

						if (dist[indexV1] > dist[indexU1] + node1.getEdgeLengt(v1)) {
							dist[indexV1] = dist[indexU1] + node1.getEdgeLengt(v1);
							prev[indexV1] = v;
						}
					}
				}
			}
		}


		for (int i = 0; i < dist.length; i++) {
			System.out.print("dist   " + allKey.get(i) + dist[i] + "   prev  " + prev[i]);
			System.out.println();
		}

	}

	public String deletemin(String[] queueH) {
		// get all index of queueH and compare the dist from it index in allKey, to find
		// min and return the key of the min
		ArrayList<Integer> indexofQueue = new ArrayList<Integer>();
		int min = 0;
		if (queueH.length != 0) {

			for (int i = 0; i < queueH.length; i++) {
				indexofQueue.add(allKey.indexOf(queueH[i]));
			}
			min = indexofQueue.get(0);
			for (int index : indexofQueue) {
				// min = index;
				if (dist[min] > dist[index])
					min = index;
			}
			return allKey.get(min);
		}
		return null;

	}

	public void getAllKey() {
		for (String key : myMap.keySet()) {
			// List of all services
			allKey.add(key);
		}
	}

	@Override
	public String toString() {
		String tr = "";
		for (String x : myMap.keySet()) {
			System.out.println(x + ": " + myMap.get(x) + "\n");
		}
		return tr;
	}

}
