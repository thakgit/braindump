package com.bd.net;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.bd.util.Maths;

public class NeuralNet {

	public List<Neuron> neurons = new ArrayList<Neuron>();
	public List<Neuron> n_input = new ArrayList<Neuron>();
	public List<List<Neuron>> n_hidden = new ArrayList<List<Neuron>>();
	public List<Neuron> n_output = new ArrayList<Neuron>();

	private int max_in = 16;
	private int[] max_hid = { 32, 64, 64, 32 };
	private int max_out = 16;

	private double[] inData;

	// private double learnRate = 0.7;
	// private double threshold = 0.015;

	public void init() {

		String data = JOptionPane.showInputDialog("Input data separated by spaces:");
		String[] dataArr = data.split(" ");
		inData = new double[dataArr.length];
		for (int i = 0; i < dataArr.length; i++)
			inData[i] = Double.parseDouble(dataArr[i]);

		max_in = dataArr.length;

		int max_layers = Integer.parseInt(JOptionPane.showInputDialog("Number of hidden layers:"));
		max_hid = new int[max_layers];
		for (int i = 0; i < max_layers; i++)
			max_hid[i] = Integer.parseInt(JOptionPane.showInputDialog("Number of hidden nodes in layer " + i + ": "));

		max_out = Integer.parseInt(JOptionPane.showInputDialog("Number of output nodes:"));

		// initialize neurons
		for (int i = 0; i < max_in; i++) {
			Neuron n = new Neuron(0, Maths.randDouble(-1, 1));
			neurons.add(n);
			n_input.add(n);
		}

		for (int l = 0; l < max_hid.length; l++) {
			for (int i = 0; i < max_hid[l]; i++) {
				Neuron n = new Neuron(1, Maths.randDouble(-1, 1));
				neurons.add(n);
				n_hidden.add(new ArrayList<Neuron>());
				n_hidden.get(l).add(n);
			}
		}

		for (int i = 0; i < max_out; i++) {
			Neuron n = new Neuron(2, Maths.randDouble(-1, 1));
			neurons.add(n);
			n_output.add(n);
		}

		// initialize neuron links
		for (Neuron n : n_input) {
			for (Neuron n1 : n_hidden.get(0)) {
				n.link(n1);
			}
		}

		for (int i = 0; i < n_hidden.size(); i++) {
			for (Neuron n : n_hidden.get(i)) {
				if (i < n_hidden.size() - 1)
					for (Neuron n1 : n_hidden.get(i + 1))
						n.link(n1);
				else
					for (Neuron n1 : n_output)
						n.link(n1);
			}
		}

		System.out.println("Initialization complete.");
	}

	public void update() {
		// update all neurons
		for (Neuron n : neurons) {
			n.update();

			// backpropagation & genetic algorithm

		}
	}
}
