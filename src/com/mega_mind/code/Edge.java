package com.mega_mind.code;


public class Edge<V> {

	private V vertex;
	
	private Double weight;
	
	public Edge(V vert, Double w) {
		vertex = vert;
		weight = w;
	}

	public V getVertex() {
		return vertex;
	}

	public void setVertex(V vertex) {
		this.vertex = vertex;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public String toString(){
		
		return "( "+ vertex + ", " + weight + " )";
	}

}
