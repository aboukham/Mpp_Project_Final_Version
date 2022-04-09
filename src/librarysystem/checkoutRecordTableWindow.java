package librarysystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import business.CheckoutRecordEntry;
import business.CheckoutRecord;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class checkoutRecordTableWindow extends JFrame {
	
	private JPanel contentPane;
	private Object[] header= new String[] {"Book ISBN","Book title","Checkout date","due date","member Id"};
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					checkoutRecordTableWindow frame = new checkoutRecordTableWindow();
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
	public checkoutRecordTableWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 424, 205);
		contentPane.add(scrollPane);
		table = new JTable(new DefaultTableModel(header, CheckoutRecord.entries.size()));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Books Checkout record table");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(134, 11, 180, 23);
		contentPane.add(lblNewLabel);
	    for(int i = 0; i < header.length; i++) {
            TableColumn column = new TableColumn(i);
            column.setHeaderValue(header[i]);
        }
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	  //  model.addRow(abjevtToAddToRow(recordHistory.get(recordHistory.size()-1)));
	    if(CheckoutRecord.entries.isEmpty()) {
			JOptionPane.showMessageDialog(table, "There is no record to show");;
	    }
	    else {
	    	model.setRowCount(0);
	    	for( int i =0; i<CheckoutRecord.entries.size() ;i++) {
	    		CheckoutRecordEntry a = CheckoutRecord.entries.get(i);
	    		String row[]= abjevtToAddToRow(a);
	    		model.addRow(row);
	    	}
	    }
	}
	
	public String[] abjevtToAddToRow(CheckoutRecordEntry recordEntry) {
		String[] item = {CheckoutRecordEntry.getISBN(),recordEntry.getBookTitle(),recordEntry.getCheckoutDate().toString(),recordEntry.getCheckoutDate().toString(),recordEntry.getMemberId()};
		return item;
	}
}
