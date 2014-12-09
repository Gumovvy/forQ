package com.gmv.mesTest;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Form extends JFrame {
	JLabel lProduct;
	JLabel lIlosc;
	JLabel lCena;
	JLabel lID = new JLabel("ID");
	JLabel lPodpowiedz = new JLabel("| Nazwa | Cena | Ilość | Data");
	JLabel lNazwaProduktu = new JLabel();

	JTextField tProduct;
	JTextField tIlosc;
	JTextField tCena;
	JTextField tProductToEdit = new JTextField();
	JTextField tIloscToEdit = new JTextField();
	JTextField tCenaToEdit = new JTextField();
	JTextField tProductInShowPanel;
	JTextField tIloscinShowPanel;
	JTextField tCenaInShowPanel;

	JButton bDodajGLOWNY = new JButton("Dodaj");
	JButton bPokazGLOWNY = new JButton("Pokaz");
	JButton bPowrotPOKAZ = new JButton("Powrot");
	JButton bEdytujGLOWNY = new JButton("Edytuj");
	JButton bZmienDaneEDYTUJ = new JButton("Zmien");
	JButton bAnulujEDYTUJ = new JButton("Anuluj");
	JButton bSrednia = new JButton("Średnia");

	JPanel panelGlowny = new JPanel();
	JPanel panelPokaz = new JPanel();
	JPanel panelEdytuj = new JPanel();
	JTextArea textArea = new JTextArea();
	JScrollPane scrolledArea = new JScrollPane(textArea);

	JComboBox<Integer> cbID = new JComboBox<Integer>();
	JComboBox<String> cbNazwaProduktu = new JComboBox<String>();

	public Form() {
		setSize(400, 400);
		setTitle("Zamowienie");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		add(panelGlowny);
		add(panelPokaz);

		comoponentInMainPanel();
		componentShowPanel();
		componentEditPanel();

	}

	void comoponentInMainPanel() {

		// penel glowy\ny
		panelGlowny.setLayout(null);
		panelGlowny.setBounds(0, 0, 400, 400);
		panelGlowny.setVisible(true);
		// labels
		lProduct = new JLabel("Wybierz produkt:");
		lIlosc = new JLabel("Ilość");
		lCena = new JLabel("Cena");

		lProduct.setBounds(10, 20, 250, 25);
		panelGlowny.add(lProduct);
		lIlosc.setBounds(10, 45, 100, 25);
		panelGlowny.add(lIlosc);
		lCena.setBounds(10, 70, 100, 25);
		panelGlowny.add(lCena);

		// textfields
		tProduct = new JTextField();
		tIlosc = new JTextField();
		tCena = new JTextField();
		cbNazwaProduktu.setBounds(140, 20, 100, 20);
		panelGlowny.add(cbNazwaProduktu);
		tIlosc.setBounds(80, 45, 250, 25);
		panelGlowny.add(tIlosc);
		tCena.setBounds(80, 70, 250, 25);
		panelGlowny.add(tCena);

		// buttons

		bDodajGLOWNY.setBounds(50, 130, 80, 25);
		panelGlowny.add(bDodajGLOWNY);
		bPokazGLOWNY.setBounds(140, 130, 80, 25);
		panelGlowny.add(bPokazGLOWNY);
		bEdytujGLOWNY.setBounds(230, 130, 80, 25);
		panelGlowny.add(bEdytujGLOWNY);

	}

	void componentShowPanel() {
		// panel Edytuj
		panelPokaz.setLayout(null);
		panelPokaz.setBounds(0, 0, 400, 400);
		panelPokaz.setVisible(false);
		// buttons
		bPowrotPOKAZ.setBounds(10, 270, 100, 25);
		panelPokaz.add(bPowrotPOKAZ);
		bSrednia.setBounds(10, 300, 100, 25);
		panelPokaz.add(bSrednia);
		// textArea
		scrolledArea.setBounds(5, 5, 385, 250);
		panelPokaz.add(scrolledArea);

		// labels

		lPodpowiedz.setBounds(120, 270, 250, 25);
		panelPokaz.add(lPodpowiedz);

	}

	void componentEditPanel() {
		lProduct = new JLabel("Nazwa");
		lIlosc = new JLabel("Ilość");
		lCena = new JLabel("Cena");
		panelEdytuj.setBounds(0, 160, 400, 400);
		panelEdytuj.setLayout(null);
		panelGlowny.add(panelEdytuj);
		panelEdytuj.setVisible(false);
		lNazwaProduktu.setBounds(80, 40, 250, 25);
		panelEdytuj.add(lNazwaProduktu);

		// combo
		cbID.setBounds(80, 10, 50, 25);
		panelEdytuj.add(cbID);
		// labels
		lID.setBounds(10, 10, 50, 25);
		panelEdytuj.add(lID);
		lProduct.setBounds(10, 40, 100, 25);
		panelEdytuj.add(lProduct);
		lIlosc.setBounds(10, 65, 100, 25);
		panelEdytuj.add(lIlosc);
		lCena.setBounds(10, 90, 100, 25);
		panelEdytuj.add(lCena);
		// textfields

		tIloscToEdit.setBounds(80, 65, 250, 25);
		panelEdytuj.add(tIloscToEdit);
		tCenaToEdit.setBounds(80, 90, 250, 25);
		panelEdytuj.add(tCenaToEdit);

		// buttons
		bZmienDaneEDYTUJ.setBounds(70, 130, 100, 25);
		panelEdytuj.add(bZmienDaneEDYTUJ);
		bAnulujEDYTUJ.setBounds(180, 130, 100, 25);
		panelEdytuj.add(bAnulujEDYTUJ);
	}

}
