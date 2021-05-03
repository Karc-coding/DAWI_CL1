package com.cinemovie.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.cinemovie.model.Pelicula;
import com.cinemovie.model.TipoPelicula;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class FrmMntPelicula extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDirector;
	private JTextField txtActores;
	private JTextArea txtADescripcion;
	private JComboBox<TipoPelicula> cboTipo;
	private JTable tblListPeliculas;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMntPelicula frame = new FrmMntPelicula();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FrmMntPelicula() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPeliculas = new JLabel("Peliculas");
		lblPeliculas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeliculas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPeliculas.setBounds(340, 10, 270, 25);
		contentPane.add(lblPeliculas);
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(10, 68, 95, 19);
		contentPane.add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion :");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescripcion.setBounds(524, 128, 95, 19);
		contentPane.add(lblDescripcion);
		
		JLabel lblDirector = new JLabel("Director :");
		lblDirector.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDirector.setBounds(10, 123, 95, 19);
		contentPane.add(lblDirector);
		
		JLabel lblActores = new JLabel("Actores :");
		lblActores.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblActores.setBounds(10, 173, 95, 19);
		contentPane.add(lblActores);
		
		JLabel lblTipo = new JLabel("Tipo :");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTipo.setBounds(524, 70, 95, 19);
		contentPane.add(lblTipo);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNombre.setColumns(10);
		txtNombre.setBounds(115, 65, 230, 25);
		contentPane.add(txtNombre);
		
		txtDirector = new JTextField();
		txtDirector.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDirector.setColumns(10);
		txtDirector.setBounds(115, 120, 230, 25);
		contentPane.add(txtDirector);
		
		cboTipo = new JComboBox();
		cboTipo.setBounds(629, 68, 181, 25);
		contentPane.add(cboTipo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(629, 128, 347, 70);
		contentPane.add(scrollPane);
		
		txtADescripcion = new JTextArea();
		scrollPane.setViewportView(txtADescripcion);
		
		txtActores = new JTextField();
		txtActores.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtActores.setColumns(10);
		txtActores.setBounds(115, 173, 354, 25);
		contentPane.add(txtActores);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearPelicula();
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegistrar.setBounds(861, 33, 115, 30);
		contentPane.add(btnRegistrar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 218, 966, 275);
		contentPane.add(scrollPane_1);
		
		tblListPeliculas = new JTable();
		tblListPeliculas.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tblListPeliculas);
		
		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Nombre");
		model.addColumn("Descripcion");
		model.addColumn("Director");
		model.addColumn("Actores");
		model.addColumn("Tipo");
		tblListPeliculas.setModel(model);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					validateFieldFlt(txtNombre.getText());
					listarActoresPorNombreDePelicula(txtNombre.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(FrmMntPelicula.this, e2.getMessage());
				}
			}
		});
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFiltrar.setBounds(861, 73, 115, 30);
		contentPane.add(btnFiltrar);
		
		listComboBoxType();
		listPeliculas();
	}
	
	private void crearPelicula() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_CL1_Movie");
		EntityManager em = factory.createEntityManager();
		
		try {

			readField();
			
			em.getTransaction().begin();
			em.persist(readPelicula());
			em.getTransaction().commit();
			
			JOptionPane.showMessageDialog(this, "Se ha creado un nuevo registro", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
			clearText();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} finally {
			factory.close();
			em.close();
			listPeliculas();
		}
	}
	
	
	private void listPeliculas() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_CL1_Movie");
		EntityManager em = factory.createEntityManager();
		
		try {
			
			TypedQuery<Pelicula> consultationPelicula = em.createNamedQuery("findAllP", Pelicula.class);
			List<Pelicula> listPelicula = consultationPelicula.getResultList();
			
			TypedQuery<TipoPelicula> consultationTypePelicula = em.createNamedQuery("findAllTP", TipoPelicula.class);
			List<TipoPelicula> listType = consultationTypePelicula.getResultList();
			
			if (listPelicula == null) {
				JOptionPane.showMessageDialog(this, "No hay datos");
			} else {
				model.setRowCount(0);
				for (Pelicula p : listPelicula) {
					
					String career = "";
					
					for (TipoPelicula tp : listType) {
						if (p.getId() == tp.getId())
							career = tp.getName();
					}
					
					Object[] row = { p.getId(),
									 p.getName(),
									 p.getDescription(),
									 p.getDirector(),
									 p.getActores(),
									 career };
					model.addRow(row);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
			em.close();
		}
	}
	
	private void listarActoresPorNombreDePelicula(String nombre) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_CL1_Movie");
		EntityManager em = factory.createEntityManager();
		
		try {
			
			TypedQuery<Pelicula> consultationPeliculaFN = em.createNamedQuery("findxName", Pelicula.class);
			consultationPeliculaFN.setParameter("vname", "%" + nombre + "%");
			List<Pelicula> listPeliculaFN = consultationPeliculaFN.getResultList();
			
			TypedQuery<TipoPelicula> consultationTypePelicula = em.createNamedQuery("findAllTP", TipoPelicula.class);
			List<TipoPelicula> listType = consultationTypePelicula.getResultList();
			
			if (listPeliculaFN == null) {
				JOptionPane.showMessageDialog(this, "No hay datos");
			} else {
				model.setRowCount(0);
				for (Pelicula p : listPeliculaFN) {
					
					String career = "";
					
					for (TipoPelicula tp : listType) {
						if (p.getId() == tp.getId())
							career = tp.getName();
					}
					
					Object[] row = { p.getId(),
									 p.getName(),
									 p.getDescription(),
									 p.getDirector(),
									 p.getActores(),
									 career };
					model.addRow(row);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
			em.close();
		}
		
	}
	
	
	private Pelicula readPelicula() {
		Pelicula p = new Pelicula();
		p.setName(txtNombre.getText());
		p.setDescription(txtADescripcion.getText());
		p.setDirector(txtDirector.getText());
		p.setActores(txtActores.getText());
		p.setType(((TipoPelicula) cboTipo.getSelectedItem()).getId());
		
		return p;
	}
	
	private void readField() {
		validateField(txtNombre.getText());
		validateField(txtADescripcion.getText());
		validateField(txtDirector.getText());
		validateField(txtActores.getText());
		validateField(((TipoPelicula) cboTipo.getSelectedItem()).getId());
	}
	
	private void listComboBoxType() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_CL1_Movie");
		EntityManager em = factory.createEntityManager();
		
		try {
			cboTipo.addItem(new TipoPelicula(0, "[Seleccione]"));
			
			TypedQuery<TipoPelicula> consultationTypePelicula = em.createNamedQuery("findAllTP", TipoPelicula.class);
			List<TipoPelicula> listType = consultationTypePelicula.getResultList();
			
			if (listType == null) {
				JOptionPane.showMessageDialog(this, "No hay datos en la tabla tipos");
			} else {
				for (TipoPelicula type : listType) {
					cboTipo.addItem(type);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
			em.close();
		}
	}
	
	
	private void validateField(int num) {
		if (num == 0) {
			throw new IllegalArgumentException("Campo Tipo no seleccionado");
		}
	}
	
	private void validateField(String cad) {
		if (cad == null || cad.trim().length() == 0) {
			throw new NullPointerException("Existen campos vacios");
		}
	}
	
	private void validateFieldFlt(String cad) {
		if (cad == null || cad.trim().length() == 0) {
			throw new NullPointerException("El campo nombre esta vacio");
		}
	}
	
	private void clearText() {
		txtNombre.setText("");
		txtADescripcion.setText("");
		txtDirector.setText("");
		txtActores.setText("");
		cboTipo.setSelectedIndex(0);
	}
	
}
