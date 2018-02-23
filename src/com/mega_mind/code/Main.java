package com.mega_mind.code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String fileName=args[0];
		String sourceCity=args[1];
		String destinationCity=args[2];
		String x= sourceCity+" "+destinationCity;
		System.out.println(x);
		
		Graph<String> graph = new Graph<String>();
		try {
			FileReader fr = new FileReader(fileName);
	        BufferedReader br = new BufferedReader(fr);
	        @SuppressWarnings("resource")
			Scanner scan = new Scanner (br);
	        @SuppressWarnings("unused")
			int count=0;
			Double dist;
	        String src, dest;
	        while(scan.hasNext()){
	        	src=scan.next();
	        	if(src.toUpperCase().equals("END"))
	        		break;
	        	count++;
	        	dest=scan.next();
	        	dist=scan.nextDouble();
	    		graph.addArc(src, dest, dist);
	        	
	        	
	        }
	        br.close();
	        fr.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
  

		


		//System.out.println(graph.toString());
		
		dijkstraShortestPath(graph,sourceCity,destinationCity);
		//System.out.println(sp);
		
	}
	public static <V> void  dijkstraShortestPath(Graph<V> graph,
			V source, V destination) {


 		HashMap<V, Double> distances = new HashMap<V, Double>();

		LinkedHashMap<V,ArrayList<V>> parentNdistance=  new LinkedHashMap<V,ArrayList<V>>();

		ArrayList<V> queue = new ArrayList<V>();
		ArrayList<V> visited = new ArrayList<V>();

		if (graph.getVertexList().contains(source))
			queue.add(0, source);
		distances.put(source, 0.0);
		
		boolean found=false;

		while (!queue.isEmpty()) {
			
			
			V currentVertex = queue.remove(queue.size() - 1);
			//System.out.println("currentVertex = "+currentVertex);
			/*if (currentVertex.equals(destination))
				continue;*/

			// to save time we initialize all the distances to infinity as we go
			//In our program, this line doesn't execute
			if (distances.get(currentVertex) == null) {
				distances.put(currentVertex, Double.POSITIVE_INFINITY);
				System.out.println("currentVertex NULL! LOL, Doesnt Excecute. ");
			}
			
			
			for (V adjacentVertex : graph.getAdjacentVertices(currentVertex)) {

				// First we initialize the adjacent vertices 
				//of current vertex with infinity
				if (distances.get(adjacentVertex) == null) {
					distances.put(adjacentVertex, Double.POSITIVE_INFINITY);
					
				}

				// if the distance between the source and the adjacent vertex is
				// greater than the distance between the source and the current
				// vertex PLUS the weight between the current and adjacent
				// vertex, then we have found a shorter path than already
				// existed
				

				if (true) {
					Double newDistance = graph.getDistanceBetween(currentVertex, adjacentVertex)
							+ distances.get(currentVertex);
					Double storedDistance= distances.get(adjacentVertex);
					
					if ( storedDistance> newDistance) {

						distances.put(adjacentVertex,newDistance);
						ArrayList<V> a = new ArrayList<V>();
						@SuppressWarnings("unchecked")
						V dist=((V) (graph.getDistanceBetween(currentVertex, adjacentVertex)+""));
						a.add(currentVertex);
						a.add(dist);
						parentNdistance.put(adjacentVertex, a);
						if (adjacentVertex.equals(destination))
							found=true;

					}		
				}

				if (!visited.contains(adjacentVertex)
						&& !queue.contains(adjacentVertex)) {
					queue.add(0, adjacentVertex);
				}
				
			}

			//System.out.println("Parent n distance list= "+parentNdistance.toString());
			//System.out.println("done an iteration ");
			
			visited.add(currentVertex);
				
		}

		// since the above statements only added the vertices as needed,
		// vertices that are completely unconnected to the source are not added
		// yet, so this adds them now
		/*for (V v : graph.getVertexList()) {
			if (!distances.containsKey(v)) {
				distances.put(v, Double.POSITIVE_INFINITY);
			}
		}*/
		//System.out.println("done the whole thing ");
		String path="";
		V tempDest= destination;
		while (parentNdistance.containsKey(tempDest)){
			
				String medium = (String) parentNdistance.get(tempDest).get(0);
				String dist = (String) parentNdistance.get(tempDest).get(1);
				path = medium+ " To " +tempDest+", "+dist+" Km\n"+path;
				
				tempDest = parentNdistance.get(tempDest).get(0);
				
		}
		if (found){
			System.out.println("distance : "+ distances.get(destination)+ " Km");
			System.out.println("route: ");
			System.out.println(path);
		}
		else{
			System.out.println("distance: infinity \nroute: \nnone \n");
		}
		
		//return distances;
	}

}
