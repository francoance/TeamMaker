/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generadorequipos;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Franco
 */

public class mainWin extends JFrame implements ActionListener {
    private Container contenedor;
    private JButton agregarJug, eliminarJug, eliminarTodo, hacerEquipos;
    private JList listaJug, listaEquipo1, listaEquipo2;
    private DefaultListModel modeloJug, modeloEquipo1, modeloEquipo2, modeloAux;
    private JScrollPane jugScroll, equipo1Scroll, equipo2Scroll;
    private JTextField nomJug;
    private JLabel mensaje, titulo, cam1, cam2;
    private Random rand = new Random();
    private int ctidadJug;
    ArrayList equipo1 = new ArrayList();
    ArrayList equipo2 = new ArrayList();

    public mainWin(){
        this.iniciarVentana();  //inicia las propiedades de los componentes
        setTitle("TeamMaker");  //Añade titulo
        setSize(656, 480);  //Setea el tamaño de la ventana (alto, ancho)
        setLocationRelativeTo(null);    //Pone la ventana en el centro de la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //la aplicación se cierra el tocar la cruz
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/iconoPrograma.png"))); //define el ícono de la aplicación
    }
    
    public void iniciarVentana(){
        contenedor = getContentPane();
        contenedor.setLayout(null);
        
        nomJug = new JTextField();
        nomJug.setBounds(20, 80, 155, 23);
        
        agregarJug = new JButton();
        agregarJug.setText("Agregar");
        agregarJug.setBounds(180, 80, 120, 23);
        agregarJug.addActionListener(this);
        
        eliminarJug = new JButton();
        eliminarJug.setText("Eliminar jugador");
        eliminarJug.setBounds(20, 370, 138, 23);
        eliminarJug.addActionListener(this);
        
        eliminarTodo = new JButton();
        eliminarTodo.setText("Vaciar lista");
        eliminarTodo.setBounds(162, 370, 138, 23); //(x, y, ancho, alto)
        eliminarTodo.addActionListener(this);
        
        hacerEquipos = new JButton();
        hacerEquipos.setText("Hacer equipos");
        hacerEquipos.setBounds(320, 80, 300, 23);
        hacerEquipos.addActionListener(this);
        
        titulo = new JLabel();
        titulo.setFont(new java.awt.Font("Tahoma", 0, 28));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("|| TeamMaker ||");
        titulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        titulo.setBounds(40, 20, 560, 43);
    
        mensaje = new JLabel();
        mensaje.setBounds(20, 400, 280, 23);
                
        cam1 = new JLabel();
        cam1.setBounds(335, 165, 50, 50);
        cam1.setIcon(new ImageIcon(getClass().getResource("/imagenes/colombia.png")));
        
        cam2 = new JLabel();
        cam2.setBounds(335, 325, 50, 50);
        cam2.setIcon(new ImageIcon(getClass().getResource("/imagenes/mexico.png")));
        
        listaJug = new JList();
        listaJug.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        listaEquipo1 = new JList();
        listaEquipo1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        listaEquipo2 = new JList();
        listaEquipo2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        modeloJug = new DefaultListModel();
        
        modeloAux = new DefaultListModel();
        
        modeloEquipo1 = new DefaultListModel();
        
        modeloEquipo2 = new DefaultListModel();
        
        jugScroll = new JScrollPane();
        jugScroll.setBounds(50, 120, 220, 240);
        jugScroll.setViewportView(listaJug);
        
        equipo1Scroll = new JScrollPane();
        equipo1Scroll.setBounds(400, 120, 220, 140);
        equipo1Scroll.setViewportView(listaEquipo1);
        
        equipo2Scroll = new JScrollPane();
        equipo2Scroll.setBounds(400, 280, 220, 140); //(x, y, ancho, alto)
        equipo2Scroll.setViewportView(listaEquipo2);
        
        contenedor.add(titulo);
        contenedor.add(nomJug);
        contenedor.add(agregarJug);
        contenedor.add(hacerEquipos);
        contenedor.add(eliminarJug);
        contenedor.add(eliminarTodo);
        contenedor.add(mensaje);
        contenedor.add(jugScroll);
        contenedor.add(cam1);
        contenedor.add(equipo1Scroll);
        contenedor.add(cam2);
        contenedor.add(equipo2Scroll);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {//sobreescribimos el metodo del listener
 
        if(e.getSource() == agregarJug){
            agregarNombre();
            mensaje.setText("Se agregó un nuevo elemento");
        }
        if(e.getSource() == eliminarJug){
            eliminarNombre(listaJug.getSelectedIndex());
        }
        if(e.getSource() == eliminarTodo){
            borrarLista();
            mensaje.setText("Se vació la lista");
        }
        if(e.getSource() == hacerEquipos){
            hacerEquipos();
        }
    }
    
    private void agregarNombre(){
        String nombre = nomJug.getText();
        modeloJug.addElement(nombre);
        listaJug.setModel(modeloJug);
        nomJug.setText("");
    }
    
    private void eliminarNombre(int indice){
        if (indice>=0) {
            modeloJug.removeElementAt(indice);
            mensaje.setText("Se eliminó un elemento en la posición " + indice);
        }
        else {
            //JOptionPane.showMessageDialog(null, "Debe seleccionar un indice", "Error", JOptionPane.ERROR_MESSAGE);
            mensaje.setText("No se seleccionó ningún elemento");
        }
    }
    
    private void borrarLista(){
        modeloJug.clear();
    }
    
    private void hacerEquipos(){
        clonarLista(modeloJug, modeloAux);
        ctidadJug = modeloJug.getSize();
        modeloEquipo1.clear();
        modeloEquipo2.clear();
        
        
        while (modeloAux.getSize() > ctidadJug/2){
        int randomNum = rand.nextInt(modeloAux.getSize());
        modeloEquipo1.addElement(modeloAux.get(randomNum));
        listaEquipo1.setModel(modeloEquipo1);
        modeloAux.remove(randomNum);}
        
        while (modeloAux.getSize() > 0){
        int randomNum = rand.nextInt(modeloAux.getSize());
        modeloEquipo2.addElement(modeloAux.get(randomNum));
        listaEquipo2.setModel(modeloEquipo2);
        modeloAux.remove(randomNum);}
        
    }
    
    private void clonarLista(DefaultListModel orig, DefaultListModel clon){
        for (int i = 0 ; i < orig.getSize() ; i++){
            clon.addElement(orig.elementAt(i));
        }
    }
}
