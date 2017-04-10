package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.items.INositelne;
import model.items.Jidlo;
import model.items.Odpad;
import model.items.Zbran;
import model.items.generator.Generator;

public class Batoh {
	private ArrayList<INositelne> obsah = new ArrayList<INositelne>();
	private static int I = 0;

	public Batoh() {
		this(I);
	}

	public Batoh(int i) {
		Generator gen = new Generator();
		this.addAll(gen.generujBatoh(i));
	}

	public void add(INositelne item) {
		getObsah().add(item);
	}

	public void addAll(ArrayList<INositelne> itemy) {
		getObsah().addAll(itemy);
	}

	public ArrayList<INositelne> getObsah() {
		return obsah;
	}

	public ArrayList<INositelne> getZbrane() {
		ArrayList<INositelne> zbrane = new ArrayList<INositelne>();
		for (INositelne item : getObsah()) {
			if (item instanceof Zbran) {
				zbrane.add(item);
			}
		}
		return zbrane;
	}

	public ArrayList<INositelne> getJidlo() {
		ArrayList<INositelne> jidlo = new ArrayList<INositelne>();
		for (INositelne item : getObsah()) {
			if (item instanceof Jidlo) {
				jidlo.add(item);
			}
		}
		return jidlo;
	}

	public ArrayList<INositelne> getOdpad() {
		ArrayList<INositelne> odpad = new ArrayList<INositelne>();
		for (INositelne item : getObsah()) {
			if (item instanceof Odpad) {
				odpad.add(item);
			}
		}
		return odpad;
	}

	public void sortByPower(boolean vzestupne) {
		getObsah().sort(Comparator.comparing(INositelne::getPower));
		if (vzestupne) {
			Collections.reverse(getObsah());
		}
	}
}
