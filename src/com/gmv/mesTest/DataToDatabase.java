package com.gmv.mesTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataToDatabase extends Form implements ActionListener {
	Purchase purchase = new Purchase();
	Orders order = new Orders();
	Product produkt = new Product();
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session;

	public DataToDatabase() {
		bDodajGLOWNY.addActionListener(this);
		bPokazGLOWNY.addActionListener(this);
		bPowrotPOKAZ.addActionListener(this);
		bZmienDaneEDYTUJ.addActionListener(this);
		bAnulujEDYTUJ.addActionListener(this);
		bEdytujGLOWNY.addActionListener(this);
		bSrednia.addActionListener(this);
		cbID.addActionListener(this);
		cbNazwaProduktu.addActionListener(this);
		daneDoCBOrderMain();
		removeData();

	}

	public void purchaseToDatabase() {
		session.beginTransaction();

		purchase.setDate(new Date());
		purchase.setPrice(Float.parseFloat(tCena.getText()));
		purchase.setProduct(tProduct.getText());
		purchase.setQuantityOfProduct(Integer.parseInt(tIlosc.getText()));
		session.save(purchase);
		session.getTransaction().commit();
		session.close();
	}

	public void ordersToDatabase() {
		session.beginTransaction();

		order.setDate(new Date());
		order.setPrice(Float.parseFloat(tCena.getText()));
		order.setProduct(String.valueOf(cbNazwaProduktu.getSelectedItem()));
		order.setQuantityOfProduct(Integer.parseInt(tIlosc.getText()));

		session.save(order);
		session.getTransaction().commit();
		session.close();
	}

	public void showDatas() {
		textArea.setText("");
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Orders order by id");
		List<Orders> lista = (List<Orders>) query.list();
		for (Orders o : lista) {
			// labels
			StringBuilder dane = new StringBuilder(" | " + o.getProduct() + " | " + o.getPrice() + " | " + o.getQuantityOfProduct() + " | " + o.getDate() + "\n");
			StringBuilder kreska = new StringBuilder("----------\n");
			textArea.append(dane.toString() + kreska.toString());

		}
		session.getTransaction().commit();
		session.close();

	}

	public void daneDoCBPurchaseEdit() {

		session = sessionFactory.openSession();
		Query query = session.createQuery("from Purchase");
		List iloscRekordow = query.list();
		for (int i = 0; i < iloscRekordow.size() + 1; i++) {
			cbID.addItem(i);
		}
		session.close();

	}

	public void daneDoCBOrderEdit() {

		session = sessionFactory.openSession();
		Query query = session.createQuery("from Orders");
		List iloscRekordow = query.list();
		for (int i = 0; i < iloscRekordow.size() + 1; i++) {
			cbID.addItem(i);
		}
		session.close();

	}

	public void daneDoCBOrderMain() {

		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Purchase order by id");
		List iloscRekordow = query.list();
		List<Purchase> listaProduktow = (List<Purchase>) query.list();
		for (Purchase p : listaProduktow) {
			cbNazwaProduktu.addItem(p.getProduct());

		}
		session.close();

	}

	public void datasToEditPanel() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		order = null;
		order = (Orders) session.get(Orders.class, (int) cbID.getSelectedItem());
		tCenaToEdit.setText(String.valueOf(order.getPrice()));
		lNazwaProduktu.setText(String.valueOf(order.getProduct()));
		tIloscToEdit.setText(String.valueOf(order.getQuantityOfProduct()));

		session.getTransaction().commit();
		session.close();

	}

	public void updateData() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		order = null;
		order = (Orders) session.get(Orders.class, (int) cbID.getSelectedItem());
		order.setPrice(Float.parseFloat(tCenaToEdit.getText()));
		// order.setProduct(String.valueOf(tProductToEdit.getText()));
		order.setQuantityOfProduct(Integer.parseInt(tIloscToEdit.getText()));
		session.update(order);
		session.getTransaction().commit();
		session.close();

	}

	public void removeData() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete from Orders");
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	public void countAverage() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Orders");
		List iloscRekordow = query.list();
		List<Orders> zamowienia = (List<Orders>) query.list();
		float wynik = 0;
		for (Orders o : zamowienia) {
			wynik = wynik + (o.getQuantityOfProduct() * o.getPrice());

		}

		session.getTransaction().commit();
		session.close();
		JOptionPane.showConfirmDialog(this, "Srednia cena za produkty wynosi: " + (wynik / iloscRekordow.size()), "Informacja", JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object zrodlo = e.getSource();

		if (zrodlo == bDodajGLOWNY) {
			if ((tCena.getText().length() == 0) || (tIlosc.getText().length() == 0) || (String.valueOf(tCena.getText()).equals("0") || (String.valueOf(tIlosc.getText()).equals("0")))) {
				JOptionPane.showMessageDialog(this, "Pole z ceną i iloscą nie moze byc puste,\nani równac się zero.", "Blad", JOptionPane.PLAIN_MESSAGE);
			} else {
				session = sessionFactory.openSession();
				Query query = session.createQuery("from Orders where product='" + String.valueOf(cbNazwaProduktu.getSelectedItem()) + "' and price=" + Float.parseFloat(tCena.getText()));
				List iloscTakichSamych = query.list();
				if (iloscTakichSamych.size() >= 1) {

					JOptionPane.showMessageDialog(this, "Taki przedmiot juz został dodany.", "Blad", JOptionPane.PLAIN_MESSAGE);
				} else {
					ordersToDatabase();
				}
			}
		} else if (zrodlo == bPokazGLOWNY) {
			showDatas();
			panelGlowny.setVisible(false);
			panelPokaz.setVisible(true);

		} else if (zrodlo == bEdytujGLOWNY) {
			cbID.removeAllItems();
			panelEdytuj.setVisible(true);
			bEdytujGLOWNY.setEnabled(false);
			daneDoCBOrderEdit();

		} else if (zrodlo == bAnulujEDYTUJ) {
			panelEdytuj.setVisible(false);
			bEdytujGLOWNY.setEnabled(true);

		} else if (zrodlo == bZmienDaneEDYTUJ) {
			cbNazwaProduktu.removeAll();
			daneDoCBPurchaseEdit();
			updateData();
		} else if (zrodlo == cbID) {
			Integer wybor = ((Integer) cbID.getSelectedItem());
			if (wybor == null) {
				wybor = 0;
			}
			if (wybor == 0) {
				lNazwaProduktu.setText("");
				tCenaToEdit.setText("");
				tIloscToEdit.setText("");
			} else if (wybor >= 1) {
				datasToEditPanel();

			}
		} else if (zrodlo == cbNazwaProduktu) {
			tCena.setText("");
			tIlosc.setText("");

		} else if (zrodlo == bPowrotPOKAZ) {

			panelPokaz.setVisible(false);
			panelGlowny.setVisible(true);
		} else if (zrodlo == bSrednia) {
			countAverage();

		}
	}

}
