package org.asmeta.simulationUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.asmeta.assertion_catalog.InvariantGUI;
import org.asmeta.assertion_catalog.LoadComboItem;
import org.asmeta.assertion_catalog.LoadDialog;
import org.asmeta.parser.ASMParser;
import org.asmeta.runtime_container.SimulationContainer;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import org.asmeta.runtime_container.Esit;
import org.asmeta.runtime_container.RunOutput;

import asmeta.AsmCollection;
import asmeta.definitions.Function;
import asmeta.definitions.domains.Domain;
import asmeta.definitions.impl.MonitoredFunctionImpl;
import asmeta.structure.Header;
import asmeta.structure.Signature;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.WindowFocusListener;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.awt.event.WindowEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;

/**
 * @author Federico Rebucini, Hernan Altamirano, Daniele Troiano
 */
public class SimGUI extends JFrame {

	//private JPanel contentPane;
	public static JPanel contentPane;
	static JScrollPane scrollPane;
	static JTextPane textPaneID;
	static JLabel lblSimID;
	static JTextPane textPaneModel;
	static JLabel lblModel;
	static JButton btnStop;
	static JButton btnRunStep;
	static JButton btnRunStepTimeout;
	static JButton btnRunUntilEmpty;
	static JButton btnRunUntilEmptyTimeout;
	static JMenuBar menuBar;
	static JMenuItem invManagerMenuItem;
	static ButtonGroup fontSizeGroup;
	static SimulationContainer containerInstance;
	static int currentLoadedID;
	static int currentMaxInstances;
	static String currentLoadedModel;
	public static boolean darkMode;
	public static int fontSize;
	static final String PROPERTIES_FILE_PATH = "src/org/asmeta/simulationUI/.properties";
	public static List<Image> icons;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SimulationContainer contInstance = new SimulationContainer();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					File propertiesFile = new File(PROPERTIES_FILE_PATH);
					Scanner reader = new Scanner(propertiesFile);
					while(reader.hasNextLine()) {
						String data = reader.nextLine();
						if(data.startsWith("mode=")) {
							if(data.endsWith("dark")) {
								darkMode = true;
							} else {
								darkMode = false;
							}
						} else if(data.startsWith("fontSize=")) {
							switch(Integer.parseInt(data.substring(9))) {
								case 14: fontSize = 14; break;
								case 16: fontSize = 16; break;
								case 18: fontSize = 18; break;
								default: fontSize = 12;
							}
						}
					}
					reader.close();
					if(darkMode) {
						UIManager.setLookAndFeel(new FlatDarkLaf());
					} else {
						UIManager.setLookAndFeel(new FlatLightLaf());
					}
					SimGUI frame = new SimGUI(contInstance);
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
	public SimGUI(SimulationContainer contInstance) {
		initialize();
		enableLoadSimButtons(false);
		containerInstance=contInstance;
		currentLoadedID=-99;
		currentLoadedModel="";
		currentMaxInstances=0;
	}
	
	
	private void initialize() {
		PrintStream previousConsole = System.out;
		 
        // Set the standard output to use newConsole.
        ByteArrayOutputStream newConsole = new ByteArrayOutputStream();
        icons = new Vector<Image>();
        
        icons.add(Toolkit.getDefaultToolkit().getImage(SimGUI.class.getResource("/org/asmeta/animator/icona_circolare_16.png")));
        icons.add(Toolkit.getDefaultToolkit().getImage(SimGUI.class.getResource("/org/asmeta/animator/icona_circolare_40.png")));
        
        setIconImages(icons);
        setResizable(true);
        setMinimumSize(new Dimension(630, 350));
		setTitle("Simulator Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 545);
		setLocationRelativeTo(null); // open the SimGUI window in the center of the screen
		
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				if (currentLoadedID>0) {
					boolean changed=true;
					Map<Integer,String> ids = containerInstance.getLoadedIDs();
					if (ids!=null && ids.containsKey(currentLoadedID))
						if (ids.get(currentLoadedID).equals(currentLoadedModel))
							changed=false;
					if (changed) {
						enableLoadSimButtons(false);
						textPaneModel.setText("No simulation loaded");
						textPaneID.setText("X");
						currentLoadedID=-99;
						currentLoadedModel="";
						JOptionPane.showMessageDialog(contentPane, "Previously loaded simulation was terminated or changed ID externally", "Warning", JOptionPane.WARNING_MESSAGE);	
					}
				}
			}
			public void windowLostFocus(WindowEvent arg0) {}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if(btnStop.isEnabled()) {
		        	btnStop.doClick();
		        }
		    }
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Handle window frame dynamic resizing of components
		addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				int frameWidth = e.getComponent().getWidth();
				int frameHeight = e.getComponent().getHeight();
				//System.out.println("Width: " + frameWidth);
				//System.out.println("Height: " + frameHeight);
				
				// Handle menuBar resizing
				menuBar.setBounds(new Rectangle(0, 0, frameWidth - 16, 22));
				
				// Handle textPaneModel and textPaneID resizing and replacement
				textPaneModel.setBounds(new Rectangle(47, 60, frameWidth - 215, 22));
				textPaneID.setBounds(new Rectangle(frameWidth - 145, 60, 78, 22));
				
				// Handle lblModel and lblSimID resizing and replacement
				lblSimID.setBounds(new Rectangle(frameWidth - 145, 33, 119, 22));
				lblModel.setBounds(new Rectangle(47, 33, 230, 22));
				
				// Handle scrollPane resizing
				scrollPane.setBounds(new Rectangle(47, 93, frameWidth - 114, frameHeight - 273));
				
				// Handle btnStop, btnRunStep, btnRunStepTimeout, btnRunUntilEmpty, btnRunUntilEmptyTimeout replacement
				if(frameWidth < (163*5 + 10*6 + 57*2)) {
					btnRunStep.setBounds(new Rectangle(57, frameHeight - 155, 163, 40));
					btnRunStepTimeout.setBounds(new Rectangle(57, frameHeight - 105, 163, 40));
					btnRunUntilEmpty.setBounds(new Rectangle(230, frameHeight - 155, 163, 40));
					btnRunUntilEmptyTimeout.setBounds(new Rectangle(230, frameHeight - 105, 163, 40));
					btnStop.setBounds(new Rectangle(Math.min(frameWidth - 230, 440), frameHeight - 145, 163, 70));
				} else {
					btnRunStep.setBounds(new Rectangle(57, frameHeight - 155, 163, 50));
					btnRunUntilEmpty.setBounds(new Rectangle(230, frameHeight - 155, 163, 50));
					btnRunStepTimeout.setBounds(new Rectangle(403, frameHeight - 155, 163, 50));
					btnRunUntilEmptyTimeout.setBounds(new Rectangle(576, frameHeight - 155, 163, 50));
					btnStop.setBounds(new Rectangle(frameWidth - 230, frameHeight - 165, 163, 70));
				}
				
			}

			@Override
			public void componentMoved(ComponentEvent e) { return; }

			@Override
			public void componentShown(ComponentEvent e) { return; }

			@Override
			public void componentHidden(ComponentEvent e) { return; }
		});
		
		/*
		 *	Deprecated: using openMenuItem and invManagerMenuItem instead (#22)
		 *
		 *	JButton choose = new JButton("Choose simulation");
		 *	choose.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		 *	choose.setBounds(649, 53, 170, 71);
		 *	contentPane.add(choose);
		 *	
		 *	JButton invmanager = new JButton("Show assertion catalog");
		 *	invmanager.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		 *	invmanager.setBounds(649, 142, 170, 71);
		 *	contentPane.add(invmanager);
		 *
		 */
			
		
		btnRunStep = new JButton("Run step");
		btnRunStep.setFont(new Font("Segoe UI", Font.PLAIN, fontSize));
		btnRunStep.setBounds(57, 390, 163, 40);
		contentPane.add(btnRunStep);
		
		btnRunUntilEmpty = new JButton("Run until empty");
		btnRunUntilEmpty.setFont(new Font("Segoe UI", Font.PLAIN, fontSize));
		btnRunUntilEmpty.setBounds(230, 390, 163, 40);
		contentPane.add(btnRunUntilEmpty);
		
		lblModel = new JLabel("Loaded model:");
		lblModel.setFont(new Font("Segoe UI", Font.BOLD, fontSize));
		lblModel.setHorizontalAlignment(SwingConstants.LEFT);
		lblModel.setBounds(47, 33, 230, 22);
		contentPane.add(lblModel);
		
		textPaneModel = new JTextPane();
		textPaneModel.setFont(new Font("Segoe UI", Font.PLAIN, fontSize));
		if(!darkMode) {
			textPaneModel.setBackground(Color.WHITE);
		} else {
			textPaneModel.setBackground(new Color(40, 40, 40));
		}
		textPaneModel.setText("No simulation loaded");
		textPaneModel.setBounds(47, 60, 465, 22);
		textPaneModel.setEditable(false);
		contentPane.add(textPaneModel);

		if(fontSize > 16) {
			lblSimID = new JLabel("Sim. ID:");
		} else {
			lblSimID = new JLabel("Simulation ID:");
		}
		lblSimID.setFont(new Font("Segoe UI", Font.BOLD, fontSize));
		lblSimID.setBounds(525, 33, 129, 22);
		lblSimID.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblSimID);
		
		textPaneID = new JTextPane();
		textPaneID.setFont(new Font("Segoe UI", Font.PLAIN, fontSize));
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		textPaneID.getStyledDocument().setParagraphAttributes(0, textPaneID.getStyledDocument().getLength(), center, false);
		if(!darkMode) {
			textPaneID.setBackground(Color.WHITE);
		} else {
			textPaneID.setBackground(new Color(40, 40, 40));
		}
		textPaneID.setText("X");
		textPaneID.setBounds(535, 60, 78, 22);
		textPaneID.setEditable(false);
		contentPane.add(textPaneID);
		
		btnStop = new JButton("Stop simulation");
		btnStop.setFont(new Font("Segoe UI", Font.PLAIN, fontSize));
		btnStop.setBounds(440, 400, 163, 70);
		btnStop.setEnabled(false);
		contentPane.add(btnStop);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 93, 566, 272);
		contentPane.add(scrollPane);
		
		JTextArea textAreaLog = new JTextArea();
		textAreaLog.setEditable(false);
		textAreaLog.setFont(new Font("Consolas", Font.PLAIN, fontSize + 1));
		if(!darkMode) {
			textAreaLog.setBackground(Color.WHITE);
		} else {
			textAreaLog.setBackground(new Color(40, 40, 40));
		}
		textAreaLog.setLineWrap(true);
		DefaultCaret caret = (DefaultCaret)textAreaLog.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane.setViewportView(textAreaLog);
		
		if(fontSize > 12) {
			btnRunStepTimeout = new JButton("Run step TO");
		} else {
			btnRunStepTimeout = new JButton("Run step timeout");
		}
		btnRunStepTimeout.setFont(new Font("Segoe UI", Font.PLAIN, fontSize));
		btnRunStepTimeout.setEnabled(false);
		btnRunStepTimeout.setBounds(57, 440, 163, 40);
		contentPane.add(btnRunStepTimeout);
		
		if(fontSize > 12) {
			btnRunUntilEmptyTimeout = new JButton("RunUntilEmptyTO");
		} else {
			btnRunUntilEmptyTimeout = new JButton("Run until empty timeout");	
		}
		btnRunUntilEmptyTimeout.setFont(new Font("Segoe UI", Font.PLAIN, Math.min(fontSize, 16)));
		btnRunUntilEmptyTimeout.setEnabled(false);
		btnRunUntilEmptyTimeout.setBounds(230, 440, 163, 40);
		contentPane.add(btnRunUntilEmptyTimeout);
		
		menuBar = new JMenuBar();
		if(!darkMode) {
			menuBar.setBackground(new Color(227, 227, 227));
		} else {
			menuBar.setBorder(null);
			menuBar.setBackground(new Color(40, 40, 40));
		}
		menuBar.setBounds(0, 0,  664, 22);
		contentPane.add(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem openMenuItem = new JMenuItem("Load simulations...");
		fileMenu.add(openMenuItem);
		
		invManagerMenuItem = new JMenuItem("Open assertion catalog");
		invManagerMenuItem.setEnabled(false);
		fileMenu.add(invManagerMenuItem);
		
		JMenu windowMenu = new JMenu("Window");
		menuBar.add(windowMenu);
		
		JCheckBoxMenuItem darkModeCheckItem = new JCheckBoxMenuItem("Dark Mode");
		windowMenu.add(darkModeCheckItem);
		darkModeCheckItem.setState(darkMode);
		
		JMenu fontSizeMenu = new JMenu("Font size");
		windowMenu.add(fontSizeMenu);
		
		fontSizeGroup = new ButtonGroup();
		
		JRadioButtonMenuItem _12fontRadioItem = new JRadioButtonMenuItem("12");
		fontSizeMenu.add(_12fontRadioItem);
		fontSizeGroup.add(_12fontRadioItem);
		
		JRadioButtonMenuItem _14fontRadioItem = new JRadioButtonMenuItem("14");
		fontSizeMenu.add(_14fontRadioItem);
		fontSizeGroup.add(_14fontRadioItem);
		
		JRadioButtonMenuItem _16fontRadioItem = new JRadioButtonMenuItem("16");
		fontSizeMenu.add(_16fontRadioItem);
		fontSizeGroup.add(_16fontRadioItem);
		
		JRadioButtonMenuItem _18fontRadioItem = new JRadioButtonMenuItem("18");
		fontSizeMenu.add(_18fontRadioItem);
		fontSizeGroup.add(_18fontRadioItem);
		
		switch(fontSize) {
			case 14: _14fontRadioItem.setSelected(true); break;
			case 16: _16fontRadioItem.setSelected(true); break;
			case 18: _18fontRadioItem.setSelected(true); break;
			default: _12fontRadioItem.setSelected(true);
		}
		
		_12fontRadioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimGUI.setProperty("fontSize", "12");
				JOptionPane.showMessageDialog(contentPane, "Re-open the application to apply the new font size!", "Mode", JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		
		_14fontRadioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimGUI.setProperty("fontSize", "14");
				JOptionPane.showMessageDialog(contentPane, "Re-open the application to apply the new font size!", "Mode", JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		
		_16fontRadioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimGUI.setProperty("fontSize", "16");
				JOptionPane.showMessageDialog(contentPane, "Re-open the application to apply the new font size!", "Mode", JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		
		_18fontRadioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimGUI.setProperty("fontSize", "18");
				JOptionPane.showMessageDialog(contentPane, "Re-open the application to apply the new font size!", "Mode", JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		
		darkModeCheckItem.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				if(darkModeCheckItem.getState()) {
					SimGUI.setProperty("mode", "dark");
					JOptionPane.showMessageDialog(contentPane, "Re-open the application to apply the dark mode!", "Mode", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					SimGUI.setProperty("mode", "light");
					JOptionPane.showMessageDialog(contentPane, "Re-open the application to disable the dark mode!", "Mode", JOptionPane.INFORMATION_MESSAGE, null);
				}
			}
		});

		
		openMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentMaxInstances<1)
				{
					//String num=JOptionPane.showInputDialog("How many simulations do you want to instantiate?", "1");
					//Object[] options = {"1", "2", "3"};
					String num = (String) JOptionPane.showInputDialog(
							contentPane, 											// parent component
							"How many simulations do you want to instantiate?", 	// message
							"Number of simulations", 								// title
							JOptionPane.QUESTION_MESSAGE, 							// message type
							null, 													// icon
							null, 													// options
							"1"														// initial default value
					);
					if(num!=null) {
						try {
							if(Integer.parseInt(num)>0) {
								currentMaxInstances=containerInstance.init(Integer.parseInt(num));
								openMenuItem.doClick();
							} else {
								throw new NumberFormatException();
							}
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(contentPane, "Error: not a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else { // currentMaxInstances>=1
					Map<Integer, String> ids = containerInstance.getLoadedIDs();
				
		    		LoadComboItem ci=null;
		    		if (!ids.isEmpty()) {
		    			// setAllEnabled(1);
		    			ci = new LoadDialog(containerInstance,ids).showDialog();
		    			//JOptionPane.showMessageDialog(null, ci.getStr());
		    		}else
		    		{
		    			LoadDialog ld = new LoadDialog(containerInstance,ids);
		    			ld.disablebutton();
		    			ci=new LoadDialog(containerInstance,ids).showDialog();
		    			
		    		}
		    		if (ci!=null) {
		    			currentLoadedID = ci.getInt();
		    			currentLoadedModel = ci.getStr();
		    			textPaneID.setText(""+currentLoadedID);
		    			//textPaneModel.setText(currentLoadedModel.substring(currentLoadedModel.lastIndexOf("\\")+1));
		    			if (currentLoadedModel.indexOf("\\")>=0)
		    				textPaneModel.setText(currentLoadedModel.substring(currentLoadedModel.lastIndexOf("\\")+1));
		    			else
		    				textPaneModel.setText(currentLoadedModel.substring(currentLoadedModel.lastIndexOf("\\")+1));
		    			enableLoadSimButtons(true);
		    			textAreaLog.setText("Simulation ready.\n");
		    			invManagerMenuItem.setEnabled(true);
		    			/*JTextPane jj=(JTextPane)contentPane.getComponent(1);
		    			jj.setText(currentLoadedModel);
		    			jj=(JTextPane)contentPane.getComponent(2);*/
		    		}
				}
			}
		});
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentLoadedID>0) {
					int stop = containerInstance.stopExecution(currentLoadedID);
					if (stop==-1)
						JOptionPane.showMessageDialog(contentPane, "Error: couldn't stop simulation!", "Error", JOptionPane.ERROR_MESSAGE);
					else {
						currentLoadedID=-99;
						currentLoadedModel="";
						enableLoadSimButtons(false);
						if(SimGUI.containerInstance.getLoadedIDs().isEmpty() || !SimGUI.containerInstance.getLoadedIDs().containsKey(InvariantGUI.getCurrentLoadedID())) {
							InvariantGUI.setAllEnabled(0);
						}
						textPaneModel.setText("No simulation loaded");
						textPaneID.setText("X");
						textAreaLog.append("Simulation stopped.\n");
					}
				}else {
					JOptionPane.showMessageDialog(contentPane, "Error: no simulation selected!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		invManagerMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentLoadedID<1)
					JOptionPane.showMessageDialog(contentPane, "Error: no simulation selected!", "Error", JOptionPane.ERROR_MESSAGE);
				else
					new InvariantGUI(containerInstance,currentLoadedID,currentLoadedModel).setVisible();
			}
		});
		
		btnRunUntilEmpty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.setErr(new PrintStream(newConsole));
				System.setOut(new PrintStream(newConsole));
				List<String> monitored = getMonitored();
				RunOutput out=new RunOutput(Esit.UNSAFE, "rout not intialized");
				if (monitored.size()<1)
					out=containerInstance.runUntilEmpty(currentLoadedID);
				else {
					Map<String, String> input = getInput(monitored);
					out=containerInstance.runUntilEmpty(currentLoadedID, input);
				}
				//JOptionPane.showMessageDialog(null, out.toString());	
				//textAreaLog.append("Runstep executed with current result:\n"+out.MytoString()+"\n");
				previousConsole.println(newConsole.toString()); // Display output of newConsole.
				 
		        // Restore back the standard console output.
		        System.setOut(previousConsole);
		        System.setErr(previousConsole);
		        textAreaLog.append("");
		        textAreaLog.append(newConsole.toString());
		        newConsole.reset();
			}
		});
		
		btnRunUntilEmptyTimeout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.setErr(new PrintStream(newConsole));
				System.setOut(new PrintStream(newConsole));
				List<String> monitored = getMonitored();
				RunOutput out=new RunOutput(Esit.UNSAFE, "rout not intialized");
				int timeout=-1;
				String num=JOptionPane.showInputDialog("Insert timeout (milliseconds):");
				if(num!=null)
				{
					try {
						timeout=Integer.parseInt(num);
						if(timeout < 0) {
							throw new NumberFormatException();
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(contentPane, "Error: not a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(timeout >= 0) {
					if (monitored.size()<1)
						out=containerInstance.runUntilEmptyTimeout(currentLoadedID,timeout);
					else {
						Map<String, String> input = getInput(monitored);
						out=containerInstance.runUntilEmptyTimeout(currentLoadedID, input,timeout);
					}
					//JOptionPane.showMessageDialog(null, out.toString());	
					//textAreaLog.append("Runstep with timeout executed with current result:\n"+out.MytoString()+"\n");
				} else
					textAreaLog.append("Couldn't execute operation.\n");
				previousConsole.println(newConsole.toString()); // Display output of newConsole.
				 
		        // Restore back the standard console output.
		        System.setOut(previousConsole);
		        System.setErr(previousConsole);
		        textAreaLog.append("");
		        textAreaLog.append(newConsole.toString());
		        newConsole.reset();
			}
		});
		
		btnRunStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.setErr(new PrintStream(newConsole));
				System.setOut(new PrintStream(newConsole));
				List<String> monitored = getMonitored();
				RunOutput out=new RunOutput(Esit.UNSAFE, "rout not intialized");
				if (monitored.size()<1)
					out=containerInstance.runStep(currentLoadedID);
				else {
					Map<String, String> input = getInput(monitored);
					out=containerInstance.runStep(currentLoadedID, input);
				}
				//JOptionPane.showMessageDialog(null, out.toString());	
				//textAreaLog.append("Runstep executed with current result:\n"+out.MytoString()+"\n");
				 
		        previousConsole.println(newConsole.toString()); // Display output of newConsole.
		 
		        // Restore back the standard console output.
		        System.setOut(previousConsole);
		        System.setErr(previousConsole);
		        textAreaLog.append("");
		        textAreaLog.append(newConsole.toString());
		        newConsole.reset();
			}
		});
		
		btnRunStepTimeout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.setErr(new PrintStream(newConsole));
				System.setOut(new PrintStream(newConsole));
				List<String> monitored = getMonitored();
				RunOutput out=new RunOutput(Esit.UNSAFE, "rout not intialized");
				int timeout=-1;
				String num=JOptionPane.showInputDialog("Insert timeout (milliseconds):");
				if(num!=null)
				{
					try {
						timeout=Integer.parseInt(num);
						if(timeout < 0) {
							throw new NumberFormatException();
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(contentPane, "Error: not a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(timeout >= 0) {
					if (monitored.size()<1)
						out=containerInstance.runStepTimeout(currentLoadedID,timeout);
					else {
						Map<String, String> input = getInput(monitored);
						out=containerInstance.runStepTimeout(currentLoadedID, input,timeout);
					}
					//JOptionPane.showMessageDialog(null, out.toString());	
					//textAreaLog.append("Runstep with timeout executed with current result:\n"+out.MytoString()+"\n");
				} else
					textAreaLog.append("Couldn't execute operation.\n");
				previousConsole.println(newConsole.toString()); // Display output of newConsole.
				 
		        // Restore back the standard console output.
		        System.setOut(previousConsole);
		        System.setErr(previousConsole);
		        textAreaLog.append("");
		        textAreaLog.append(newConsole.toString());
		        newConsole.reset();
			}
		});
	}
	
	/**
	 * Change a property value in the '.properties' file.
	 * @param propertyName: name of the property in '.properties' file.
	 * @param value: new property value.
	 */
	public static void setProperty(String propertyName, String value) {
		try {
			File propertiesFile = new File(PROPERTIES_FILE_PATH);
			ArrayList<String> fileContent = new ArrayList<>();
			String line = "";
			BufferedReader reader = new BufferedReader(new FileReader(propertiesFile));
			while((line = reader.readLine()) != null) {
				fileContent.add(line);
			}
			
			FileWriter writer = new FileWriter(propertiesFile);
			for(String property: fileContent) {
				property = property.replaceAll(("^" + propertyName + "=.+?$"), (propertyName + "=" + value));
				writer.write(property);
				writer.write(System.lineSeparator());
			}
			reader.close();
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void enableLoadSimButtons(boolean enable) {
		btnStop.setEnabled(enable);
		btnRunStep.setEnabled(enable);
		btnRunUntilEmpty.setEnabled(enable);
		btnRunStepTimeout.setEnabled(enable);
		btnRunUntilEmptyTimeout.setEnabled(enable);
		invManagerMenuItem.setEnabled(enable);
	}
	
	private List<String> getMonitored(){
		ArrayList<String> monitoredList = new ArrayList<String>();
		if (!currentLoadedModel.equals("")) {
			File asmFile = new File(currentLoadedModel);
			if (asmFile.exists()) {
				AsmCollection asm;
				try {
					asm = ASMParser.setUpReadAsm(asmFile);// cerco di prendere la classe delle monitorate
					for (int i = 0; i < asm.getMain().getHeaderSection().getSignature().getFunction().size(); i++) {
						if (asm.getMain().getHeaderSection().getSignature().getFunction()
								.get(i) instanceof MonitoredFunctionImpl)
							monitoredList.add(asm.getMain().getHeaderSection().getSignature().getFunction().get(i).getName());
				
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, "Error: asm parsing error!", "Error", JOptionPane.ERROR_MESSAGE);
				}			
			}
		}
		return monitoredList;
	}
	
	private Map<String, String> getInput(List<String> monitoredList) {
		Map<String, String> input = new HashMap<>();
		Map<String, Object[]> enumDomainFunction = new HashMap<>();
		String inputValue = new String();
		Object[] options;
		
		if(!currentLoadedModel.equals("")) {
			File asmFile = new File(currentLoadedModel);
			if(asmFile.exists()) {
				AsmCollection asm;
				try {
					asm = ASMParser.setUpReadAsm(asmFile);
					for(int i = 0; i < asm.getMain().getHeaderSection().getSignature().getFunction().size(); i++) {
						Function f = asm.getMain().getHeaderSection().getSignature().getFunction().get(i);
						if(monitoredList.contains(f.getName())) {
							if(f.getCodomain() != null) // supporto per le funzioni a 0 area: solo codominio
								enumDomainFunction.put(f.getName(), getEnumDomain(f.getCodomain().getName()).toArray());
						}
					}
				} catch (Exception e) {
					//e.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "Error: asm parsing error!", "Error", JOptionPane.ERROR_MESSAGE);
				}			
			}
		}
		
		for (String monitored: monitoredList) {
			options = enumDomainFunction.get(monitored);
			inputValue = (String) JOptionPane.showInputDialog(
					contentPane, 											// parent component
					"Insert " + monitored + " value:", 						// message
					"Input", 												// title
					JOptionPane.PLAIN_MESSAGE, 								// message type
					null, 													// icon
					options, 												// options
					null													// initial default value
			);
			input.put(monitored, inputValue);
		}
		return input;
	}
	
	/**
	 * Helper function to retrieve possible enum values from a limited
	 * enum domain from the currentLoadedModel asm source file.
	 * @param domainName: the name of the enum domain.
	 * @return an ArrayList of String which are the possible values for the enum domain (domainName).
	 */
	private ArrayList<String> getEnumDomain(String domainName) {
		ArrayList<String> values = new ArrayList<>();
		ArrayList<String> enumDomainContent = new ArrayList<>();
		BufferedReader reader;
		String[] valuesArray;
		Pattern domainPattern = Pattern.compile("\\{.*?\\}");
		//Pattern enumPattern = Pattern.compile(".*?\\|.*|[^\\||^\" \"]*");
		Matcher matcher;
		
		if(!currentLoadedModel.equals("")) {
			File asmFile = new File(currentLoadedModel);
			if(asmFile.exists()) {
				//AsmCollection asm;
				try {
					String line = "";
					reader = new BufferedReader(new FileReader(asmFile));
					while((line = reader.readLine()) != null) {
						if(line.contains("enum") && line.contains("domain") && line.contains(domainName) && !line.contains("//")) {
							line = line.trim();
							enumDomainContent.add(line);
							// DEBUG: System.out.println("\n" + line + "\n");
						}	
					}
					for(String enumDomain: enumDomainContent) {
						matcher = domainPattern.matcher(enumDomain);
						// DEBUG: System.out.println(enumDomain);
						if(matcher.find()) {
							enumDomain = matcher.group().trim();
							enumDomain = enumDomain.replaceAll(" ", "");
							enumDomain = enumDomain.replaceAll("\\{", "");
							enumDomain = enumDomain.replaceAll("\\}", "");
							// DEBUG: System.out.println("\n" + enumDomain + "\n");
							if(enumDomain.contains("|")) {
								valuesArray = enumDomain.split("\\|");
								for(String value: valuesArray) {
									// DEBUG: System.out.println(value);
									values.add(value);
								}
							} else {
								values.add(enumDomain);
							}
						}
					}
					reader.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, "Error: parsing error!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		return values;
	}
}



