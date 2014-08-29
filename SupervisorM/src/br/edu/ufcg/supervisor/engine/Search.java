/**
 * Copyright 2013-2014 Elthon Oliveira and Marcos Ferreira
 * 
 * This file is part of Supervisor for Healthcare Professional software.
 * 
 *  Supervisor for Healthcare Professional is free software: you can 
 *  redistribute it and/or modify it under the terms of the GNU General 
 *  Public License as published by the Free Software Foundation, either 
 *  version 3 of the License, or (at your option) any later version.
 *  
 *  Supervisor for Healthcare Professional is distributed in the hope that
 *  it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See 
 *  the GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with Supervisor for Healthcare Professional. 
 *  If not, see <http://www.gnu.org/licenses/>.
 *  
 *  Contact: el7hon at gmail dot com
 */
package br.edu.ufcg.supervisor.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.edu.ufcg.supervisor.model.*;

/**
 * This class is an adaptation of the classic Dijkstra's algorithm
 * that solves the single-source shortest path problem for a graph 
 * with non-negative edge path costs, producing a shortest path tree.
 * 
 * @author elthon oliveira
 *
 */
public class Search {
	private final List<State> nodes;
	private final List<Transition> edges;
	private Set<State> settledNodes;
	private Set<State> unSettledNodes;
	private Map<State, State> predecessors;
	private Map<State, Integer> distance;

	public Search(Automaton a) {
		this.nodes = new ArrayList<State>(a.getVectorEstados());
		this.edges = new ArrayList<Transition>(a.getArrayTransicoes());
	}

	public void execute(State source) {
		settledNodes = new HashSet<State>();
		unSettledNodes = new HashSet<State>();
		distance = new HashMap<State, Integer>();
		predecessors = new HashMap<State, State>();
		distance.put(source, 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			State node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(State node) {
		List<State> adjacentNodes = getNeighbors(node);
		for (State target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node)
					+ getDistance(node, target)) {
				distance.put(target, getShortestDistance(node)
						+ getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}
	}

	private int getDistance(State node, State target) {
		for (Transition edge : edges) {
			if (edge.getEstadoOrigem().equals(node)
					&& edge.getEstadoDestino().equals(target)) {
				return 1;
			}
		}
		throw new RuntimeException("Should not happen");
	}

	private List<State> getNeighbors(State node) {
		List<State> neighbors = new ArrayList<State>();
		for (Transition edge : edges) {
			if (edge.getEstadoOrigem().equals(node)
					&& !isSettled(edge.getEstadoDestino())) {
				neighbors.add(edge.getEstadoDestino());
			}
		}
		return neighbors;
	}

	private State getMinimum(Set<State> vertexes) {
		State minimum = null;
		for (State vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(State vertex) {
		return settledNodes.contains(vertex);
	}

	private int getShortestDistance(State destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	public LinkedList<State> getPath(State target) {
		LinkedList<State> path = new LinkedList<State>();
		State step = target;
		// Check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}

} 