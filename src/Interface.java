import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*; 


public class Interface extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 6508264993886617118L;

	Dimension minimumSize = new Dimension(100, 50);
	
	private JButton delete, complete, incomplete, create;
	private JTextField textField;
	private JComboBox monthBox, dayBox, yearBox;
	private ButtonGroup group;
	private ArrayList<JRadioButton> radioList;
	private JPanel leftPanel, assignmentPanel;
	
	String[] months = {
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
	
	String[] days = {
			"1", "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19", "20", "21",
			"22", "23", "24", "25", "26", "27", "28",
			"29", "30", "31"
	};
	
	String[] years = {
			"2014", "2015", "2016", "2017", "2018"
	};
	
	public Interface(String title) { // main interface
		super(title);
		display();
		this.setSize(700, 400);
		this.setVisible(true);
	}
	
	public void display() {
		JScrollPane left = leftUI();
		JPanel right = rightUI();
		
		JSplitPane splitPane = new JSplitPane();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
		splitPane.setDividerLocation(300);
		splitPane.setResizeWeight(0.25);
		this.add(splitPane);
	}
	
	public JScrollPane leftUI() {
		
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
	
	public JPanel assignments() {
		JPanel radioPanel = new JPanel(new GridLayout(0,1));
		
		TextScanner scanner = new TextScanner();
		Format fmt = new Format();
		ArrayList<String> data = scanner.getData();
		radioList = new ArrayList<JRadioButton>();
		group = new ButtonGroup();
		for(String s : data) {
			radioList.add(new JRadioButton(fmt.radioText(s)));
			group.add(radioList.get(radioList.size() - 1));
			radioPanel.add(radioList.get(radioList.size() - 1));
			(radioList.get(radioList.size() - 1)).addActionListener(this);
		}
		
		return radioPanel;
	}
	
	public JPanel rightUI() {
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		rightPanel.setMinimumSize(minimumSize);
		
		JPanel buttons = buttonPanel();
		JPanel inputs = inputPanel();
		
		rightPanel.add(buttons);
		rightPanel.add(inputs);
		
		return rightPanel;
	}
	
	public JPanel buttonPanel() {
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel header = new JLabel("Selected Assigment:");
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
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
	
	public JPanel inputPanel() {
		JPanel inputPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		JLabel header = new JLabel("New Assigment:");
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
	
	public void actionPerformed(ActionEvent e) {
		Format fmt = new Format();
		for (JRadioButton rb : radioList) {
			if (e.getSource() == complete && rb.isSelected()) {
				int index = radioList.indexOf(rb);
				fmt.markComplete(index);
				leftPanel.remove(assignmentPanel);
				assignmentPanel = assignments();
				leftPanel.add(assignmentPanel);
				leftPanel.validate();
				leftPanel.repaint();
			}
			if (e.getSource() == incomplete && rb.isSelected()) {
				int index = radioList.indexOf(rb);
				fmt.markIncomplete(index);
				leftPanel.remove(assignmentPanel);
				assignmentPanel = assignments();
				leftPanel.add(assignmentPanel);
				leftPanel.validate();
				leftPanel.repaint();
			}
			if (e.getSource() == delete && rb.isSelected()) {
				int index = radioList.indexOf(rb);
				group.clearSelection();
				group.remove(rb);
				radioList.remove(rb);
				fmt.remove(index);
				leftPanel.remove(assignmentPanel);
				assignmentPanel = assignments();
				leftPanel.add(assignmentPanel);
				leftPanel.validate();
				leftPanel.repaint();
			}
		}
		if (e.getSource() == create) {
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
