
package com.mycompany.parcking;
//Importaciones
import javax.swing.*;
import java.awt.*; // se usa para importar todas las clases del paquete java.awt.El boton 
import java.awt.event.ActionEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level; // señala el nivel de seguridad que tendra el dato
import java.util.logging.Logger;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;



public class ParquimetroGui {
    //Declaracion de variables que se usaran para la interfaz grafica
    private JFrame mainFrame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTextField placaTextField;
    private JLabel tiempoLabel, infoLabel;
    private final Parquimetro parquimetro;
    private LocalDate fechaEntrada;
    private LocalTime horaEntrada;
    private Timer timer;
    private JLabel bienvenido;
    private JLabel saludos;
    
    
    
    //Constructor ParquimetroGui
    public ParquimetroGui() {
        parquimetro = new Parquimetro();
        initializeUI();   
    }

    
    //Método initializeUI
    private void initializeUI() {
        mainFrame = new JFrame("Parquímetro");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 800);
        mainFrame.setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setSize(800, 600);
         
        
        
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\nikens.pierrelouis\\Desktop\\img.png"); // Reemplaza con la ruta de tu imagen
            Image image = originalIcon.getImage(); // Convierte ImageIcon a Image
                Image resizedImage = image.getScaledInstance(450, 360, Image.SCALE_SMOOTH); // Cambia 200, 200 al tamaño deseado
                    ImageIcon imageIcon = new ImageIcon(resizedImage); // Convierte de nuevo a ImageIcon  
                        JLabel imageLabel = new JLabel(imageIcon);
                       
        setupRegistroPanel(imageLabel);
            setupTiempoPanel();
                mainFrame.add(cardPanel);
                    mainFrame.setVisible(true);
    }

    
    //Método setupRegistroPanel
    private void setupRegistroPanel(JLabel imageLabel) {
        JPanel registroPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
                placaTextField = new JTextField(15);  
                saludos= new JLabel("Bienvenido");
                        saludos.setFont(new Font("Arial", Font.BOLD, 26));
                    bienvenido = new JLabel("Estimado si desea usar el estacionamiento favor ingresar su numero de placa en el siguiente cuadro");
                        bienvenido.setFont(new Font("Arial", Font.BOLD, 14)); // Establece la fuente y el tamaño del texto
                            bienvenido.setForeground(Color.black); // Establece el color del texto
     
    JButton registrarButton = new JButton("Registrar Vehículo");
        registrarButton.addActionListener(this::registrarVehiculo);
        
        
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(10, 0, 350, 0); // Margen arriba y abajo del bienvenido
                registroPanel.add(saludos, gbc);
    
        
        

    // Configuraciones para el mensage de instruccion
   gbc = new GridBagConstraints(); // Reinicia gbc
        gbc.gridx = 0; // Columna 0
            gbc.gridy = 0; // Fila 0
                gbc.insets = new Insets(10, 0, 250, 0); // Margen arriba y abajo del bienvenido
                    registroPanel.add(bienvenido, gbc);
    
    // Configuración para placaTextField (campo donde se ingresara la placa del auto)
    gbc = new GridBagConstraints(); // Reinicia gbc
        gbc.gridx = 0; // Columna 0
            gbc.gridy = 0; // Fila 0
                gbc.insets = new Insets(5, 70, 70, 0);
                    gbc.anchor = GridBagConstraints.LINE_END;
                        registroPanel.add(placaTextField,gbc);
          
    // Configuración para registrarButton (el boton para dejar guardada la placa
    gbc = new GridBagConstraints(); // Reinicia gbc
        gbc.gridx = 0; // Columna 0 (Misma columna que placaTextField)
            gbc.gridy = 0; // Misma fila que placaTextField
                gbc.insets = new Insets(5, 10, 20, 14); // Espacio a la izquierda para separarlo de placaTextField
                    gbc.anchor = GridBagConstraints.LINE_END;
                        registroPanel.add(registrarButton, gbc);                   
                                                     
              // Configuración para la imagen
    gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(200, 0, 20, 200);
                registroPanel.add(imageLabel, gbc);
                               
    cardPanel.add(registroPanel, "Registro");
    }

     
     
     //Método setupTiempoPanel
    private void setupTiempoPanel() {
        JPanel tiempoPanel = new JPanel();
            infoLabel = new JLabel();
                tiempoLabel = new JLabel("00:00:00");
                    JButton calcularButton = new JButton("Calcular Monto");
                        calcularButton.addActionListener(this::calcularMonto);
                        
        tiempoPanel.add(infoLabel);
            tiempoPanel.add(tiempoLabel);
                tiempoPanel.add(calcularButton);

        cardPanel.add(tiempoPanel, "Tiempo");
    }

    
     //Método registrarVehiculo (Manejador de acción para registrarButton)
    private void registrarVehiculo(ActionEvent e) {
        String placa = placaTextField.getText();
            try {
                 parquimetro.registrarVehiculo(placa);
                    }          
                        catch (Exception ex) {
                            Logger.getLogger(ParquimetroGui.class.getName()).log(Level.SEVERE, null, ex);
                               }
            
        fechaEntrada = LocalDate.now();
            horaEntrada = LocalTime.now();
                infoLabel.setText("Vehículo asociado al numero de placa: " + "[ "+ placa + " ] " +" fue ingresado el: " + fechaEntrada +" a las: "+ horaEntrada + " Y lleva" );
                    startTimer();
                        cardLayout.show(cardPanel, "Tiempo");
    }

    
    //Método startTimer
    private void startTimer() {
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(1000, e -> updateTimer());
        timer.start();
    }

    //Método updateTimer (Manejador de acción para timer)
    private void updateTimer() {
        Duration duracion = Duration.between(horaEntrada, LocalTime.now());
            long horas = duracion.toHours();
                long minutos = duracion.toMinutesPart();
                    long segundos = duracion.toSecondsPart();
                        tiempoLabel.setText(String.format("%02d:%02d:%02d" +" Minutos estacionado" , horas, minutos, segundos));
    }

    //Método calcularMonto (Manejador de acción para calcularButton)
    private void calcularMonto(ActionEvent e) {
        try {
            timer.stop();
                // Esa parte del codigo es para calcular el monto y monstrarlo en pantalla
                double monto = parquimetro.calcularMonto(placaTextField.getText()); // Ejemplo, ajusta según tu lógica
                    JOptionPane.showMessageDialog(mainFrame, "Monto a pagar: " + monto + " Pesos");
                   }    
                        catch (Exception ex) {
                            Logger.getLogger(ParquimetroGui.class.getName()).log(Level.SEVERE, null, ex);
                                }
    }

   

    
    
    
    
    
    
    
    
    
    
    
/*private void calcularMonto(ActionEvent e) {

       try {

            timer.stop();
 
            // Calcula el tiempo total estacionado

            Duration duracion = Duration.between(horaEntrada, LocalTime.now());

            long minutosEstacionado = duracion.toMinutes();
 
            // Calcula el monto a pagar según la fórmula proporcionada

            double monto = (minutosEstacionado * montoAPagarPorMinuto) / 35;
 
            // Muestra el monto en pantalla

            JOptionPane.showMessageDialog(mainFrame, "Monto a pagar: $" + monto);

        } catch (Exception ex) {

            Logger.getLogger(ParquimetroGui.class.getName()).log(Level.SEVERE, null, ex);

        }*/

    }


    
    
    
    
    
    
    















