import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*; 

/**
 * Class deals with the GUI.
 * @author David Watanabe
 *
 */
public class Interface extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 6508264993886617118L;

	private final Dimension minimumSize = new Dimension(100, 50);
	
	private JButton delete, complete, incomplete, create;
	private JTextField textField;
	private JComboBox monthBox, dayBox, yearBox;
	private ArrayList<JRadioButton> radioList;
	private JPanel leftPanel, assignmentPanel;
	
	private final String[] months = {
			"January",
			"Febuary",
			"March",
			"April",
			"May",
			"June",
			"July",
			"August",
			"September",
			"October",
			"November",
			"December"
	};
	
	private final String[] days = {
			"1", "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19", "20", "21",
			"22", "23", "24", "25", "26", "27", "28",
			"29", "30", "31"
	};
	
	private final String[] years = {
			"2014", "2015", "2016", "2017", "2018"
	};
	
	/**
	 * shows the interface
	 * @param title  name of the program
	 */
	public Interface(String title) {
		super(title);
		display();
		this.setSize(700, 400);
		this.setVisible(true);
	}
	
	/**
	 * starts the interface with a split pane that will have components added to it.
	 */
	private void display() {
		JScrollPane left = leftUI();
		JPanel right = rightUI();
		
		JSplitPane splitPane = new JSplitPane();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
		splitPane.setDividerLocation(300);
		splitPane.setResizeWeight(0.25);
		this.add(splitPane);
	}
	
	/**
	 * the left portion of the GUI
	 * @return this JComponent
	 */
	private JScrollPane leftUI() {
		
		leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		leftPanel.setMinimumSize(minimumSize);
		
		JScrollPane scrollPane = new JScrollPane(leftPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		scrollPane.setViewportView(leftPanel);
		
		assignmentPanel = assignments();
		
		leftPanel.add(assignmentPanel);
		
		return scrollPane;
	}
	
	/**
	 * creates a panel that lists the assignments using radio buttons.
	 * used in leftUI(). 
	 * @return this JComponent
	 */
	private JPanel assignments() {
		JPanel radioPanel = new JPanel(new GridLayout(0,1));
		
		TextScanner scanner = new TextScanner();
		Format fmt = new Format();
		ArrayList<String> data = scanner.getData();
		JRadioButton thisButton;
		radioList = new ArrayList<JRadioButton>();
		ButtonGroup group = new ButtonGroup();
		for(String d : data) {
			radioList.add(new JRadioButton(fmt.radioText(d)));
			thisButton = radioList.get(radioList.size() - 1);
			thisButton.setFont(fmt.radioFont(d));
			group.add(thisButton);
			radioPanel.add(thisButton);
			thisButton.addActionListener(this);
		}
		
		return radioPanel;
	}
	
	/**
	 * the right portion of the GUI
	 * @return this JComponent
	 */
	private JPanel rightUI() {
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		rightPanel.setMinimumSize(minimumSize);
		
		JPanel buttons = buttonPanel();
		JPanel inputs = inputPanel();
		
		rightPanel.add(buttons);
		rightPanel.add(inputs);
		
		return rightPanel;
	}
	
	/**
	 * panel of buttons that can modify assignments that are selected in assignments().
	 * used in rightUI().
	 * @return this JComponent
	 */
	private JPanel buttonPanel() {
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel header = new JLabel("Modify Selected Assigment:");
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		buttonPanel.add(header, c);
		
		complete = new JButton("Mark as Complete");
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		buttonPanel.add(complete, c);
		
		incomplete = new JButton("Mark as Incomplete");
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		buttonPanel.add(incomplete, c);
		
		delete = new JButton("Delete");
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		buttonPanel.add(delete, c);
		
		complete.addActionListener(this);
		incomplete.addActionListener(this);
		delete.addActionListener(this);
		
		return buttonPanel;
	}
	
	/**
	 * panel of inputs that are used to create new assignments.
	 * used in rightUI().
	 * @return this JComponent
	 */
	private JPanel inputPanel() {
		JPanel inputPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		JLabel header = new JLabel("Create New Assigment:");
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		inputPanel.add(header, c);
		
		JPanel titlePanel = new JPanel();
		JLabel titleLabel  = new JLabel("Title:");
		textField = new JTextField(15);
		titlePanel.add(titleLabel);
		titlePanel.add(textField);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		inputPanel.add(titlePanel, c);
		
		JPanel boxPanel = new JPanel();
		JLabel dateLabel = new JLabel("Due Date:");
		monthBox = new JComboBox(months);
		dayBox = new JComboBox(days);
		yearBox = new JComboBox(years);
		boxPanel.add(dateLabel);
		boxPanel.add(monthBox);
		boxPanel.add(dayBox);
		boxPanel.add(yearBox);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		inputPanel.add(boxPanel, c);
		
		create = new JButton("Create");
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		inputPanel.add(create, c);
		
		create.addActionListener(this);
		monthBox.addActionListener(this);
		dayBox.addActionListener(this);
		yearBox.addActionListener(this);
		textField.addActionListener(this);
		
		return inputPanel;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		Format fmt = new Format();
		
		for (JRadioButton rb : radioList) {
			int index = radioList.indexOf(rb);
			if (e.getSource() == complete && rb.isSelected()) { // Mark assignment as complete
				fmt.markComplete(index);
				leftPanel.remove(assignmentPanel);
				assignmentPanel = assignments();
				radioList.get(index).setSelected(true);
				leftPanel.add(assignmentPanel);
				leftPanel.validate();
				leftPanel.repaint();
			}
			if (e.getSource() == incomplete && rb.isSelected()) { // Mark assignment as incomplete
				fmt.markIncomplete(index);
				leftPanel.remove(assignmentPanel);
				assignmentPanel = assignments();
				radioList.get(index).setSelected(true);
				leftPanel.add(assignmentPanel);
				leftPanel.validate();
				leftPanel.repaint();
			}
			if (e.getSource() == delete && rb.isSelected()) { // Delete assignment (with confirmation dialog)
				JFrame optionFrame = new JFrame();
				int n = JOptionPane.showConfirmDialog(
                        optionFrame, "Delete the selected assignment?",
                        "Confirmation Dialog",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
				if (n == JOptionPane.YES_OPTION) {
					fmt.remove(index);
					leftPanel.remove(assignmentPanel);
					assignmentPanel = assignments();
					leftPanel.add(assignmentPanel);
					leftPanel.validate();
					leftPanel.repaint();
				}
			}
		}
		if (e.getSource() == create) { // Create new assignment
			String t = textField.getText();
			String m = new Integer(Arrays.asList(months).indexOf(monthBox.getSelectedItem()) + 1).toString();
			String d = (String) dayBox.getSelectedItem();
			String y = ((String) yearBox.getSelectedItem()).substring(2);
			fmt.addData(t, m, d, y);
			textField.setText("");
			leftPanel.remove(assignmentPanel);
			assignmentPanel = assignments();
			leftPanel.add(assignmentPanel);
			leftPanel.validate();
			leftPanel.repaint();
		}
	}
}
