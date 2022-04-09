package librarysystem;

import business.*;
import dataaccess.DataAccessFacade;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Member;
import java.time.LocalDate;

public class CheckoutBookWindow extends JFrame implements LibWindow{
    public static final CheckoutBookWindow INSTANCE = new CheckoutBookWindow();

	private JPanel contentPane;
	private JTextField txtfMemberId;
	private JTextField txtfBookISBN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutBookWindow frame = new CheckoutBookWindow();
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
	public CheckoutBookWindow() {
		setForeground(Color.CYAN);
		setTitle("Data checking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMemberId = new JLabel("Member Id");
		lblMemberId.setBounds(27, 24, 87, 40);
		contentPane.add(lblMemberId);
		
		txtfMemberId = new JTextField();
		
		JLabel lblIdAvailability = new JLabel("");
		lblIdAvailability.setBounds(268, 34, 188, 14);
		contentPane.add(lblIdAvailability);

		txtfMemberId.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				txtfMemberId.setText("");
				lblIdAvailability.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				SystemController Data = new SystemController();
				boolean membersCheck = Data.allMemberIds().contains(txtfMemberId.getText());
				if(membersCheck) lblIdAvailability.setText("The Id is found");
				else lblIdAvailability.setText("The Id isn't found");	
			}
		  

		});
		
		txtfMemberId.setBounds(134, 34, 124, 20);
		contentPane.add(txtfMemberId);
		txtfMemberId.setColumns(10);
		
		JLabel lblBookISBN = new JLabel("Book ISBN number");
		lblBookISBN.setBounds(27, 75, 108, 20);
		contentPane.add(lblBookISBN);
		
		txtfBookISBN = new JTextField();
		txtfBookISBN.setBounds(134, 75, 124, 20);
		contentPane.add(txtfBookISBN);
		txtfBookISBN.setColumns(10);
		
		JLabel lblBookAvailability = new JLabel("");
		lblBookAvailability.setBounds(268, 75, 188, 14);
		contentPane.add(lblBookAvailability);
		
		txtfBookISBN.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				txtfBookISBN.setText("");
				lblBookAvailability.setText("");
			}

			@Override
			public void focusLost(FocusEvent e){
				// TODO Auto-generated method stub
				SystemController Data = new SystemController();
				boolean membersCheck = Data.allBookIds().contains(txtfBookISBN.getText());
				if(membersCheck) {
					lblBookAvailability.setText("The Book is found");
					DataAccessFacade book = new DataAccessFacade();
					Book foundedBook = book.readBooksMap().get(txtfBookISBN.getText());

					if (!foundedBook.isAvailable()) lblBookAvailability.setText("The Book is not available");
				}
				else lblBookAvailability.setText("The Book isn't found");	
			}
		  

		});
		
		
		JButton btnCheckAvailablity = new JButton("Check availability");
		btnCheckAvailablity.setBounds(268, 151, 135, 23);
		contentPane.add(btnCheckAvailablity);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtfBookISBN.setText("");
				lblBookAvailability.setText("");
				txtfMemberId.setText("");
				lblIdAvailability.setText("");
			}
		});
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReset.setBounds(134, 151, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lblBookAvailability.getText().equals("The Book is found") && lblIdAvailability.getText().equals("The Id is found")) {
					CheckoutRecordEntry item = new CheckoutRecordEntry(txtfBookISBN.getText(), txtfMemberId.getText() );	
					CheckoutRecord.entries.add(item);
					DataAccessFacade book = new DataAccessFacade();
					Book foundedBook = book.readBooksMap().get(txtfBookISBN.getText());
					foundedBook.getNextAvailableCopy().changeAvailability();
					JOptionPane.showMessageDialog(btnCheckout, "Item added to records");
				}
				else JOptionPane.showMessageDialog(btnCheckout, "Can not add to records");
			}
		});
		btnCheckout.setBounds(206, 185, 124, 23);
		contentPane.add(btnCheckout);
		
		JButton btnShowRecord = new JButton("show records");
		btnShowRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnShowRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				checkoutRecordTableWindow window = new checkoutRecordTableWindow();
				window.setVisible(true);
			}
		});
		btnShowRecord.setBounds(206, 216, 124, 23);
		contentPane.add(btnShowRecord);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub
		
	}
}
