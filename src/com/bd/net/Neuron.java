package com.bd.net;

import java.util.ArrayList;
import java.util.List;

import com.bd.util.Maths;

public class Neuron {

	private List<Neuron> n_axons = new ArrayList<Neuron>();

	private double weight = 0.1;
	private double ndata = 0;

	private int ntype = 0; // 0 = input, 1 = hidden, 2 = output

	public Neuron(int ntype, double weight) {
		this.ntype = ntype;
		this.weight = weight;
	}

	public void update() {
		for (Neuron axon : n_axons) {
			if (ntype == 0)
				axon.ndata = this.ndata * weight;
			else if (ntype == 1)
				axon.ndata = Maths.sigmoid(this.ndata * weight);
		}
		
		//if(ntype == 2)
			//System.out.println(ndata);
	}

	public void link(Neuron neuron) {
		n_axons.add(neuron);
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getNdata() {
		return ndata;
	}

	public void setNdata(double ndata) {
		this.ndata = ndata;
	}

	public int getNtype() {
		return ntype;
	}
}
