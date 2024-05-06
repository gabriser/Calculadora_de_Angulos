package grafica;

import java.io.File;
import java.io.IOException;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import logica.Angulo;
import logica.WrongStringToAnguloFormatException;

import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class MainFrame {

	/*
	 * Clase principal de ejecución del programa "Calculadora de Angulos".
	 */
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
					System.err.println("El programa ha muerto");
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/grafica/assets/CalculadoraDeAngulos_logo.png")));
		frame.setBounds(100, 100, 450, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Calculadora de Angulos");
		frame.setResizable(false);
		
		// paleta de colores
		Color backgroundColor = new Color(136, 136, 136);
		
		Color buttonBgColor = new Color(102, 102, 102);
		Color buttonTextColor = new Color(255, 255, 255);
		Color buttonBgHoverColor = new Color(110, 190, 244);
		
		Color buttonRedBgColor = new Color(226, 20, 20);
		Color buttonRedBgHoverColor = new Color(249, 94, 94);
		
		Color buttonGreenBgColor = new Color(41, 206, 16);
		Color buttonGreenBgHoverColor = new Color(110, 249, 94);
		
		Color buttonBlueBgColor = new Color(24, 133, 206);
		
		Color textFieldBgColor = new Color(255, 255, 255);
		Color textFieldTextColor = new Color(0, 0, 0);
		
		// paleta de fuentes y tamaño
		Font logopixiesFont = null;
		Font latoFont = null;
		try {
			logopixiesFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/grafica/assets/logopixies/Logopixies-owwBB.ttf")).deriveFont(30f);
			latoFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/grafica/assets/lato/Lato-Regular.ttf")).deriveFont(15f);
		} catch (FontFormatException | IOException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido cargar la fuente del programa. Cargando fuente por defecto", "IOException o FontFormatException", JOptionPane.ERROR_MESSAGE);
		}
		
		// barra de menu con opciones
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
        JMenu menuPrograma = new JMenu("Programa");
        menuPrograma.setFont(latoFont);
        menuBar.add(menuPrograma);
        
        JMenuItem menuItemNuevaVentana = new JMenuItem("Abrir nueva ventana");
        menuItemNuevaVentana.setFont(latoFont);
        menuItemNuevaVentana.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		NuevaVentana.init();
        	}
        });
        menuItemNuevaVentana.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        
        JMenuItem menuItemAcercaDe = new JMenuItem("Acerca de");
        menuItemAcercaDe.setFont(latoFont);
        menuItemAcercaDe.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AcercaDe acercaDeDialog = new AcercaDe(frame);
                acercaDeDialog.setVisible(true);
        	}
        });
        menuItemAcercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        
        JMenuItem menuItemSalir = new JMenuItem("Salir");
        menuItemSalir.setFont(latoFont);
        menuItemSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        menuItemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        
        menuPrograma.add(menuItemNuevaVentana);
        menuPrograma.add(menuItemAcercaDe);
        menuPrograma.add(menuItemSalir);
        
        // layout principal
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        // panel de arriba
        JPanel pnl_top = new JPanel(new BorderLayout());
        pnl_top.setBackground(backgroundColor);
        frame.getContentPane().add(pnl_top, BorderLayout.NORTH);

        JTextField txtField = new JTextField();
        txtField.setFocusable(false);
        txtField.setFont(logopixiesFont);
        txtField.setBorder(new LineBorder(textFieldBgColor, 10));
        txtField.setBackground(textFieldBgColor);
        txtField.setForeground(textFieldTextColor);
        txtField.setEditable(false);
        pnl_top.add(txtField, BorderLayout.CENTER);
        
        // panel central
        JPanel pnl_center = new JPanel();
        pnl_center.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnl_center.setBackground(backgroundColor);
        frame.getContentPane().add(pnl_center, BorderLayout.CENTER);
        pnl_center.setLayout(new GridLayout(4, 4, 10, 10));
        
        JButton btn7 = new JButton("7");
        btn7.setFocusable(false);
        btn7.setFont(logopixiesFont);
        btn7.setBorder(null);
        btn7.setForeground(buttonTextColor);
        btn7.setBackground(buttonBgColor);
        btn7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btn7.setBackground(buttonBgHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btn7.setBackground(buttonBgColor);
            }
        	public void mouseClicked(MouseEvent e) {
        		txtField.setText(txtField.getText()+"7");
        	}
        });
        pnl_center.add(btn7);
        
        JButton btn8 = new JButton("8");
        btn8.setFocusable(false);
        btn8.setFont(logopixiesFont);
        btn8.setBackground(buttonBgColor);
        btn8.setBorder(null);
        btn8.setForeground(buttonTextColor);
        btn8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btn8.setBackground(buttonBgHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btn8.setBackground(buttonBgColor);
            }
            public void mouseClicked(MouseEvent e) {
        		txtField.setText(txtField.getText()+"8");
        	}
        });
        pnl_center.add(btn8);
        
        JButton btn9 = new JButton("9");
        btn9.setFocusable(false);
        btn9.setFont(logopixiesFont);
        btn9.setBackground(buttonBgColor);
        btn9.setBorder(null);
        btn9.setForeground(buttonTextColor);
        btn9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btn9.setBackground(buttonBgHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btn9.setBackground(buttonBgColor);
            }
            public void mouseClicked(MouseEvent e) {
        		txtField.setText(txtField.getText()+"9");
        	}
        });
        pnl_center.add(btn9);
        
        JButton btnC = new JButton("C");
        btnC.setFocusable(false);
        btnC.setFont(logopixiesFont);
        btnC.setBackground(buttonRedBgColor);
        btnC.setBorder(null);
        btnC.setForeground(buttonTextColor);
        btnC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btnC.setBackground(buttonRedBgHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btnC.setBackground(buttonRedBgColor);
            }
            public void mouseClicked(MouseEvent e) {
        		// C borrar el campo de texto
            	txtField.setText("");
        	}
        });
        pnl_center.add(btnC);
        
        JButton btn4 = new JButton("4");
        btn4.setFocusable(false);
        btn4.setFont(logopixiesFont);
        btn4.setBackground(buttonBgColor);
        btn4.setBorder(null);
        btn4.setForeground(buttonTextColor);
        btn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btn4.setBackground(buttonBgHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btn4.setBackground(buttonBgColor);
            }
            public void mouseClicked(MouseEvent e) {
        		txtField.setText(txtField.getText()+"4");
        	}
        });
        pnl_center.add(btn4);
        
        JButton btn5 = new JButton("5");
        btn5.setFocusable(false);
        btn5.setFont(logopixiesFont);
        btn5.setBackground(buttonBgColor);
        btn5.setBorder(null);
        btn5.setForeground(buttonTextColor);
        btn5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btn5.setBackground(buttonBgHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btn5.setBackground(buttonBgColor);
            }
            public void mouseClicked(MouseEvent e) {
        		txtField.setText(txtField.getText()+"5");
        	}
        });
        pnl_center.add(btn5);
        
        JButton btn6 = new JButton("6");
        btn6.setFocusable(false);
        btn6.setFont(logopixiesFont);
        btn6.setBackground(buttonBgColor);
        btn6.setBorder(null);
        btn6.setForeground(buttonTextColor);
        btn6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btn6.setBackground(buttonBgHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btn6.setBackground(buttonBgColor);
            }
            public void mouseClicked(MouseEvent e) {
        		txtField.setText(txtField.getText()+"6");
        	}
        });
        pnl_center.add(btn6);
        
        JButton btnSumar = new JButton("+");
        btnSumar.setFocusable(false);
        btnSumar.setFont(logopixiesFont);
        btnSumar.setBackground(buttonBlueBgColor);
        btnSumar.setBorder(null);
        btnSumar.setForeground(buttonTextColor);
        btnSumar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btnSumar.setBackground(buttonBgHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btnSumar.setBackground(buttonBlueBgColor);
            }
            public void mouseClicked(MouseEvent e) {
            	txtField.setText(txtField.getText()+"+");
        	}
        });
        pnl_center.add(btnSumar);
        
        JButton btn1 = new JButton("1");
        btn1.setFocusable(false);
        btn1.setFont(logopixiesFont);
        btn1.setBackground(buttonBgColor);
        btn1.setBorder(null);
        btn1.setForeground(buttonTextColor);
        btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btn1.setBackground(buttonBgHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btn1.setBackground(buttonBgColor);
            }
            public void mouseClicked(MouseEvent e) {
        		txtField.setText(txtField.getText()+"1");
        	}
        });
        pnl_center.add(btn1);
        
        JButton btn2 = new JButton("2");
        btn2.setFocusable(false);
        btn2.setFont(logopixiesFont);
        btn2.setBackground(buttonBgColor);
        btn2.setBorder(null);
        btn2.setForeground(buttonTextColor);
        btn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btn2.setBackground(buttonBgHoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btn2.setBackground(buttonBgColor);
            }
            public void mouseClicked(MouseEvent e) {
        		txtField.setText(txtField.getText()+"2");
        	}
        });
        pnl_center.add(btn2);
        
        JButton btn3 = new JButton("3");
        btn3.setFocusable(false);
        btn3.setFont(logopixiesFont);
        btn3.setBackground(buttonBgColor);
        btn3.setBorder(null);
        btn3.setForeground(buttonTextColor);
        btn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btn3.setBackground(buttonBgHoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btn3.setBackground(buttonBgColor);
            }
            public void mouseClicked(MouseEvent e) {
        		txtField.setText(txtField.getText()+"3");
        	}
        });
        pnl_center.add(btn3);
        
        JButton btnRestar = new JButton("-");
        btnRestar.setFocusable(false);
        btnRestar.setFont(logopixiesFont);
        btnRestar.setBackground(buttonBlueBgColor);
        btnRestar.setBorder(null);
        btnRestar.setForeground(buttonTextColor);
        btnRestar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btnRestar.setBackground(buttonBgHoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btnRestar.setBackground(buttonBlueBgColor);
            }
            public void mouseClicked(MouseEvent e) {
            	txtField.setText(txtField.getText()+"-");
        	}
        });
        pnl_center.add(btnRestar);
        
        JButton btnCl = new JButton("CL");
        btnCl.setFocusable(false);
        btnCl.setFont(logopixiesFont);
        btnCl.setBackground(buttonRedBgColor);
        btnCl.setBorder(null);
        btnCl.setForeground(buttonTextColor);
        btnCl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btnCl.setBackground(buttonRedBgHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btnCl.setBackground(buttonRedBgColor);
            }
            public void mouseClicked(MouseEvent e) {
        		// CL borrar el ultimo numero del final
            	String txtFieldtxt = txtField.getText();
                if (!txtFieldtxt.isEmpty()) {
                    txtField.setText(txtFieldtxt.substring(0, txtFieldtxt.length() - 1));
                }
        	}
        });
        pnl_center.add(btnCl);
        
        JButton btn0 = new JButton("0");
        btn0.setFocusable(false);
        btn0.setFont(logopixiesFont);
        btn0.setBackground(buttonBgColor);
        btn0.setBorder(null);
        btn0.setForeground(buttonTextColor);
        btn0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btn0.setBackground(buttonBgHoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btn0.setBackground(buttonBgColor);
            }
            public void mouseClicked(MouseEvent e) {
        		txtField.setText(txtField.getText()+"0");
        	}
        });
        pnl_center.add(btn0);
        
        JButton btnPuntos = new JButton(":");
        btnPuntos.setFocusable(false);
        btnPuntos.setFont(logopixiesFont);
        btnPuntos.setBackground(buttonBlueBgColor);
        btnPuntos.setBorder(null);
        btnPuntos.setForeground(buttonTextColor);
        btnPuntos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btnPuntos.setBackground(buttonBgHoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btnPuntos.setBackground(buttonBlueBgColor);
            }
            public void mouseClicked(MouseEvent e) {
        		txtField.setText(txtField.getText()+":");
        	}
        });
        pnl_center.add(btnPuntos);
        
        JButton btnIgual = new JButton("=");
        btnIgual.setFocusable(false);
        btnIgual.setFont(logopixiesFont);
        btnIgual.setBackground(buttonGreenBgColor);
        btnIgual.setBorder(null);
        btnIgual.setForeground(buttonTextColor);
        btnIgual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	btnIgual.setBackground(buttonGreenBgHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btnIgual.setBackground(buttonGreenBgColor);
            }
            public void mouseClicked(MouseEvent e) {
        		
            	// hacer el calculo y mostralo por el txtField
            	igualPulsado(txtField);
            	
        	}

        });
        pnl_center.add(btnIgual);
        
        JPanel pnl_bottom = new JPanel();
        pnl_bottom.setBackground(buttonBlueBgColor);
        frame.getContentPane().add(pnl_bottom, BorderLayout.SOUTH);
        
        JLabel lblHelpText = new JLabel("Formato de Angulo: 359:59:59");
        lblHelpText.setFont(latoFont);
        lblHelpText.setForeground(buttonTextColor);
        pnl_bottom.add(lblHelpText);
        
        // permitir escribir con el teclado
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                case '1':
                	txtField.setText(txtField.getText() + "1");
                	break;
                case '2':
                	txtField.setText(txtField.getText() + "2");
                	break;
                case '3':
                	txtField.setText(txtField.getText() + "3");
                	break;
                case '4':
                	txtField.setText(txtField.getText() + "4");
                	break;
                case '5':
                	txtField.setText(txtField.getText() + "5");
                	break;
                case '6':
                	txtField.setText(txtField.getText() + "6");
                	break;
                case '7':
                	txtField.setText(txtField.getText() + "7");
                	break;
                case '8':
                	txtField.setText(txtField.getText() + "8");
                	break;
                case '9':
                	txtField.setText(txtField.getText() + "9");
                	break;
                case '0':
                	txtField.setText(txtField.getText() + "0");
                	break;
                case '+':
                	txtField.setText(txtField.getText() + "+");
                	break;
                case '-':
                	txtField.setText(txtField.getText() + "-");
                	break;
                case ':':
                	txtField.setText(txtField.getText() + ":");
                	break;
                case 'C':
                    txtField.setText("");
                    break;
                case 'c':
                    txtField.setText("");
                    break;
                case '\b':
                    // tecla de borrar (igual que pulsar CL en los botones)
                	String txtFieldtxt = txtField.getText();
                    if (!txtFieldtxt.isEmpty()) {
                        txtField.setText(txtFieldtxt.substring(0, txtFieldtxt.length() - 1));
                    }
                    break;
                case '=':
                	// hacer el calculo y mostralo por el txtField
                	igualPulsado(txtField);
                	break;
                case '\n':
                	// tecla de enter (igual que pulsar =)
                	// hacer el calculo y mostralo por el txtField
                	igualPulsado(txtField);
                	break;
                }
            }
        });
        frame.setFocusable(true);

	}
	
	/**
	 * Metodo interno para separar los valores del txtField, calcular y pintar el resultado por el txtField.
	 * @param txtField Campo de texto donde se recoje los valores y pinta el resultado.
	 */
	private void igualPulsado(JTextField txtField) {
		
		/* --- --- ---
    	 * aqui se hace las sumas y restas
    	 --- --- --- */
    	
    	// si hay un + y - a la vez
		if (txtField.getText().contains("+") && txtField.getText().contains("-")) {
			JOptionPane.showMessageDialog(null, "Solo puedes hacer una suma o resta de 2 angulos a la vez", "Aviso", JOptionPane.ERROR_MESSAGE);
			txtField.setText("");
		}
		// si hay un +
		else if (txtField.getText().contains("+")) {
			boolean errorEncontrado = false;
			
			String arrTxtField[] = txtField.getText().split("\\+");
			if (arrTxtField.length != 2) {
				JOptionPane.showMessageDialog(null, "Solo puedes hacer una suma de 2 angulos a la vez", "Aviso", JOptionPane.ERROR_MESSAGE);
				errorEncontrado = true;
			}
			
			// la transformación de String a Angulo lo tengo en la logica
			Angulo angulo1 = null;
			try {
				angulo1 = Angulo.parseStringToAngulo(arrTxtField[0]);
			} catch (WrongStringToAnguloFormatException ex) {
    			JOptionPane.showMessageDialog(null, "El angulo '"+arrTxtField[0]+"' no tiene un formato adecuado", "Aviso", JOptionPane.ERROR_MESSAGE);
    			errorEncontrado = true;
			}
			Angulo angulo2 = null;
			try {
				angulo2 = Angulo.parseStringToAngulo(arrTxtField[1]);
			} catch (WrongStringToAnguloFormatException ex) {
				JOptionPane.showMessageDialog(null, "El angulo '"+arrTxtField[1]+"' no tiene un formato adecuado", "Aviso", JOptionPane.ERROR_MESSAGE);
				errorEncontrado = true;
			}
			
			// hacer el calculo de suma
			if (!errorEncontrado) {
				Angulo resultado = Angulo.sumar(angulo1, angulo2);
    			//System.out.println(resultado);
    			txtField.setText(resultado.toString());
			} else {
				// si hay error, no hago la suma y vuelve a estar vacio el campo
				txtField.setText("");
				errorEncontrado = false;
			}
			
			
		}
		// si hay un -
		else if (txtField.getText().contains("-")) {
			boolean errorEncontrado = false;
			
			String arrTxtField[] = txtField.getText().split("\\-");
			if (arrTxtField.length != 2) {
    			JOptionPane.showMessageDialog(null, "Solo puedes hacer una resta de 2 angulos a la vez", "Aviso", JOptionPane.ERROR_MESSAGE);
    			errorEncontrado = true;
			}
			
			// la transformación de String a Angulo lo tengo en la logica
			Angulo angulo1 = null;
			try {
				angulo1 = Angulo.parseStringToAngulo(arrTxtField[0]);
			} catch (WrongStringToAnguloFormatException ex) {
    			JOptionPane.showMessageDialog(null, "El angulo '"+arrTxtField[0]+"' no tiene un formato adecuado", "Aviso", JOptionPane.ERROR_MESSAGE);
    			errorEncontrado = true;
			}
			Angulo angulo2 = null;
			try {
				angulo2 = Angulo.parseStringToAngulo(arrTxtField[1]);
			} catch (WrongStringToAnguloFormatException ex) {
				JOptionPane.showMessageDialog(null, "El angulo '"+arrTxtField[1]+"' no tiene un formato adecuado", "Aviso", JOptionPane.ERROR_MESSAGE);
				errorEncontrado = true;
			}
			
			// hacer el calculo de resta
			if (!errorEncontrado) {
				Angulo resultado = Angulo.restar(angulo1, angulo2);
    			//System.out.println(resultado);
    			txtField.setText(resultado.toString());
			} else {
				// si hay error, no hago la resta y vuelve a estar vacio el campo
				txtField.setText("");
				errorEncontrado = false;
			}
			
		}
		// si no hay + o -
		else {
			JOptionPane.showMessageDialog(null, "No se ha encontrado si quieres sumar o restar", "Aviso", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
