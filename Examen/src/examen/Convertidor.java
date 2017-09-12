package examen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author J. Carlos Nevarez Tovar
 */
public class Convertidor extends JFrame{
    JTextField cuadro;
    JPanel numeros;
    JPanel botones;
    float num;
    boolean nuevo;
    
    //Constructor
    public Convertidor() {
        super();
        Ventana();
        Componentes();
    }
    
    public void Ventana(){
        setTitle("Convertidor");
        setSize(600, 500);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public void Componentes(){
        JPanel content = (JPanel) this.getContentPane();
        content.setLayout(new BorderLayout());
        
        cuadro = new JTextField();
        cuadro.setFont(new Font("Arial", Font.BOLD, 25));
        cuadro.setEditable(true);
        cuadro.add("North", cuadro);
        
        numeros = new JPanel();
        numeros.setLayout(new GridLayout(4, 3));
        for(int i = 9; i >= 0; i++){
            String numero = ""+i;
            agregarNums(numero);
        }
        
        JButton lmao = new JButton();
        lmao.setText(".");
        lmao.addMouseListener(
            new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent evt) {
                    JButton btn = (JButton) evt.getSource();
                    numeroPulsado(lmao.getText());
                }
            }
        );
        content.add("Center", numeros);
        
        
        botones = new JPanel();
        botones.setLayout(new GridLayout(6, 1));
        nuevoBotonOperacion("Convertir");
        nuevoBotonOperacion("CE");
        content.add("East", botones);

        validate();
    }
    
    private void agregarNums(String numero) {
        JButton num = new JButton();
        num.setText(numero);
        num.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton btn = (JButton) evt.getSource();
                numeroPulsado(btn.getText());
            }
        });
        numeros.add(num);
    }
    
    private void numeroPulsado(String numero) {
        if (cuadro.getText().equals("0") || nuevo) 
        {
            cuadro.setText(numero);
        } 
        else 
        {
            cuadro.setText(cuadro.getText() + numero);
        }
        nuevo = false;
    }
    
    private void nuevoBotonOperacion(String operacion) {
        JButton btn = new JButton(operacion);

        btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton btn = (JButton) evt.getSource();
                operacion(btn.getText());
            }
        });

        botones.add(btn);
    }
    
    private void operacion(String dato) {
        if (dato.equals("Convertir")) {
            num = new Float(cuadro.getText());
            calcular();
        } else if (dato.equals("CE")) {
            cuadro.setText("0");
            num = 0;
            nuevo = true;
        } 
        
        nuevo = true;
    }
    
    private void calcular(){
        num = (num/19);
        cuadro.setText("" + num);
    }
    
}
