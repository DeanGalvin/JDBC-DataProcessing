package Assignment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;


@SuppressWarnings("serial")
public class JDBCMainWindowContent extends JInternalFrame implements ActionListener
{
	// DB Connectivity Attributes
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	private Container content;

	private JPanel detailsPanel;
	private JPanel exportButtonPanel;
	private JPanel exportConceptDataPanel;
	private JScrollPane dbContentsPanel;

	private Border lineBorder;

	private JLabel recordIDLabel=new JLabel("Record ID:                 ");
	private JLabel stateLabel=new JLabel("State:                 ");
	private JLabel accountLengthLabel=new JLabel("Account Length:                 ");
	private JLabel areaCodeLabel=new JLabel("Area Code:                 ");
	private JLabel phoneNumberLabel=new JLabel("Phone Number:                 ");
	private JLabel intPlanLabel=new JLabel("International Plan:                 ");
	private JLabel vmPlanLabel=new JLabel("Voice Mail:                 ");
	private JLabel numVmailMessagesLabel=new JLabel("Voicemail Messages:                 ");
	private JLabel totalDayMinLabel=new JLabel("Total Day Minutes:                 ");
	private JLabel totalDayCallsLabel=new JLabel("Total Day Calls:                 ");
	private JLabel totalDayChargeLabel=new JLabel("Total Day Charge:                 ");
	private JLabel totalEveMinLabel=new JLabel("Total Eve Minutes:                   ");
	private JLabel totalEveCallsLabel=new JLabel("Total Eve Calls:                   ");
	private JLabel totalEveChargeLabel=new JLabel("Total Eve Charge:                 ");
	private JLabel totalNightMinLabel=new JLabel("Total Night Minutes:               ");
	private JLabel totalNightCallsLabel=new JLabel("Total Night Calls:               ");
	private JLabel totalNightChargeLabel=new JLabel("Total Night Charge:             ");
	private JLabel totalIntlMinLabel=new JLabel("Total Intl Minutes:                 ");
	private JLabel totalIntlCallsLabel=new JLabel("Total Intl Calls:                 ");
	private JLabel totalIntlChargeLabel=new JLabel("Total Intl Charge:               ");
	private JLabel numCustServCallsLabel=new JLabel("Customer Service Calls:         ");
	private JLabel testValueLabel=new JLabel("Test Value:                 ");

	private JTextField recordIDTF=new JTextField(10);
	private JTextField stateTF=new JTextField(10);
	private JTextField accountLengthTF=new JTextField(10);
	private JTextField areaCodeTF=new JTextField(10);
	private JTextField phoneNumberTF=new JTextField(10);
	private JTextField intPlanTF=new JTextField(10);
	private JTextField vmPlanTF=new JTextField(10);
	private JTextField numVmailMessagesTF=new JTextField(10);
	private JTextField totalDayMinTF=new JTextField(10);
	private JTextField totalDayCallsTF=new JTextField(10);
	private JTextField totalDayChargeTF=new JTextField(10);
	private JTextField totalEveMinTF=new JTextField(10);
	private JTextField totalEveCallsTF=new JTextField(10);
	private JTextField totalEveChargeTF=new JTextField(10);
	private JTextField totalNightMinTF=new JTextField(10);
	private JTextField totalNightCallsTF=new JTextField(10);
	private JTextField totalNightChargeTF=new JTextField(10);
	private JTextField totalIntlMinTF=new JTextField(10);
	private JTextField totalIntlCallsTF=new JTextField(10);
	private JTextField totalIntlChargeTF=new JTextField(10);
	private JTextField numCustServCallsTF=new JTextField(10);
	private JTextField testValueTF=new JTextField(10);


	private static QueryTableModel TableModel = new QueryTableModel();

	//Add the models to JTabels
	private JTable TableofDBContents=new JTable(TableModel);

	//Buttons for inserting, and updating members
	//also a clear button to clear details panel
	private JButton updateButton = new JButton("Update");
	private JButton insertButton = new JButton("Insert");
	private JButton exportButton  = new JButton("Export");
	private JButton deleteButton  = new JButton("Delete");
	private JButton clearButton  = new JButton("Clear");

	public JDBCMainWindowContent( String aTitle)
	{
		//setting up the GUI
		super(aTitle, false,false,false,false);
		setEnabled(true);

		initiate_db_conn();
		//add the 'main' panel to the Internal Frame
		content=getContentPane();
		content.setLayout(null);
		content.setBackground(Color.lightGray);
		lineBorder = BorderFactory.createEtchedBorder(15, Color.red, Color.black);

		//setup details panel and add the components to it
		detailsPanel=new JPanel();
		detailsPanel.setLayout(new GridLayout(22,2));
		detailsPanel.setBackground(Color.lightGray);
		detailsPanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "AP Details"));

		detailsPanel.add(recordIDLabel);
		detailsPanel.add(recordIDTF);
		detailsPanel.add(stateLabel);
		detailsPanel.add(stateTF);
		detailsPanel.add(accountLengthLabel);
		detailsPanel.add(accountLengthTF);
		detailsPanel.add(areaCodeLabel);
		detailsPanel.add(areaCodeTF);
		detailsPanel.add(phoneNumberLabel);
		detailsPanel.add(phoneNumberTF);
		detailsPanel.add(intPlanLabel);
		detailsPanel.add(intPlanTF);
		detailsPanel.add(vmPlanLabel);
		detailsPanel.add(vmPlanTF);
		detailsPanel.add(numVmailMessagesLabel);
		detailsPanel.add(numVmailMessagesTF);
		detailsPanel.add(totalDayMinLabel);
		detailsPanel.add(totalDayMinTF);
		detailsPanel.add(totalDayCallsLabel);
		detailsPanel.add(totalDayCallsTF);
		detailsPanel.add(totalDayChargeLabel);
		detailsPanel.add(totalDayChargeTF);
		detailsPanel.add(totalEveMinLabel);
		detailsPanel.add(totalEveMinTF);
		detailsPanel.add(totalEveCallsLabel);
		detailsPanel.add(totalEveCallsTF);
		detailsPanel.add(totalEveChargeLabel);
		detailsPanel.add(totalEveChargeTF);
		detailsPanel.add(totalNightMinLabel);
		detailsPanel.add(totalNightMinTF);
		detailsPanel.add(totalNightCallsLabel);
		detailsPanel.add(totalNightCallsTF);
		detailsPanel.add(totalNightChargeLabel);
		detailsPanel.add(totalNightChargeTF);
		detailsPanel.add(totalIntlMinLabel);
		detailsPanel.add(totalIntlMinTF);
		detailsPanel.add(totalIntlCallsLabel);
		detailsPanel.add(totalIntlCallsTF);
		detailsPanel.add(totalIntlChargeLabel);
		detailsPanel.add(totalIntlChargeTF);
		detailsPanel.add(numCustServCallsLabel);
		detailsPanel.add(numCustServCallsTF);
		detailsPanel.add(testValueLabel);
		detailsPanel.add(testValueTF);

		//setup details panel and add the components to it
		exportButtonPanel=new JPanel();
		exportButtonPanel.setLayout(new GridLayout(3,2));
		exportButtonPanel.setBackground(Color.lightGray);
		exportButtonPanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "Export Data"));
		/*exportButtonPanel.add(last3LossRates);
		exportButtonPanel.add(last3LossRatesTF);
		exportButtonPanel.add(avgofRSS);
		exportButtonPanel.add(avgofRSSTF);
		exportButtonPanel.add(overLappingAP);
		exportButtonPanel.add(overLappingChannels);*/
		exportButtonPanel.setSize(700, 200);
		exportButtonPanel.setLocation(476, 300);
		content.add(exportButtonPanel);

		insertButton.setSize(100, 30);
		updateButton.setSize(100, 30);
		exportButton.setSize (100, 30);
		deleteButton.setSize (100, 30);
		clearButton.setSize (100, 30);

		insertButton.setLocation(370, 10);
		updateButton.setLocation(370, 110);
		exportButton.setLocation (370, 160);
		deleteButton.setLocation (370, 60);
		clearButton.setLocation (370, 210);

		insertButton.addActionListener(this);
		updateButton.addActionListener(this);
		exportButton.addActionListener(this);
		deleteButton.addActionListener(this);
		clearButton.addActionListener(this);


		content.add(insertButton);
		content.add(updateButton);
		content.add(exportButton);
		content.add(deleteButton);
		content.add(clearButton);


		TableofDBContents.setPreferredScrollableViewportSize(new Dimension(900, 300));

		dbContentsPanel=new JScrollPane(TableofDBContents,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		dbContentsPanel.setBackground(Color.lightGray);
		dbContentsPanel.setBorder(BorderFactory.createTitledBorder(lineBorder,"Database Content"));

		detailsPanel.setSize(360, 500);
		detailsPanel.setLocation(3,0);
		dbContentsPanel.setSize(900, 300);
		dbContentsPanel.setLocation(477, 0);

		content.add(detailsPanel);
		content.add(dbContentsPanel);

		setSize(982,645);
		setVisible(true);

		TableModel.refreshFromDB(stmt);
	}

	public void initiate_db_conn()
	{
		try
		{
			// Load the JConnector Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Specify the DB Name
			String url="jdbc:mysql://localhost:3307/ChurnAssignment";
			// Connect to DB using DB URL, Username and password
			con = DriverManager.getConnection(url, "root", "admin");
			//Create a generic statement which is passed to the TestInternalFrame1
			stmt = con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Error: Failed to connect to database\n"+e.getMessage());
		}
	}

	//event handling for members desktop
	public void actionPerformed(ActionEvent e)
	{
		 Object target=e.getSource();
		 if (target == clearButton)
		 {
			 recordIDTF.setText("");
			 stateTF.setText("");
			 accountLengthTF.setText("");
			 areaCodeTF.setText("");
			 phoneNumberTF.setText("");
			 intPlanTF.setText("");
			 vmPlanTF.setText("");
			 numVmailMessagesTF.setText("");
			 totalDayMinTF.setText("");
			 totalDayCallsTF.setText("");
			 totalDayChargeTF.setText("");
			 totalEveMinTF.setText("");
			 totalEveCallsTF.setText("");
			 totalEveChargeTF.setText("");
			 totalNightMinTF.setText("");
			 totalNightCallsTF.setText("");
			 totalNightChargeTF.setText("");
			 totalIntlMinTF.setText("");
			 totalIntlCallsTF.setText("");
			 totalIntlChargeTF.setText("");
			 numCustServCallsTF.setText("");
			 testValueTF.setText("");
		 }

		 if (target == insertButton)
		 {
	 		try
	 		{
 				String updateTemp ="INSERT INTO ChurnData VALUES (null, '"+
 						stateTF.getText()+
 						"','"+accountLengthTF.getText()+
 						"','"+areaCodeTF.getText()+
 						"','"+phoneNumberTF.getText()+
 						"','"+intPlanTF.getText()+
 						"','"+vmPlanTF.getText()+
 						"','"+numVmailMessagesTF.getText()+
 						"','"+totalDayMinTF.getText()+
 						"','"+totalDayCallsTF.getText()+
 		 				"','"+totalDayChargeTF.getText()+
 		 				"','"+totalEveMinTF.getText()+
 		 				"','"+totalEveCallsTF.getText()+
 		 				"','"+totalEveChargeTF.getText()+
 		 				"','"+totalNightMinTF.getText()+
 		 				"','"+totalNightCallsTF.getText()+
 		 				"','"+totalNightChargeTF.getText()+
 		 				"','"+totalIntlMinTF.getText()+
 		 				"','"+totalIntlCallsTF.getText()+
 		 				"','"+totalIntlChargeTF.getText()+
 		 				"','"+numCustServCallsTF.getText()+
 		 				"','"+testValueTF.getText()+"');";

 				stmt.executeUpdate(updateTemp);
	 		}
	 		catch (SQLException sqle)
	 		{
	 			System.err.println("Error with members insert:\n"+sqle.toString());
	 		}
	 		finally
	 		{
	 			TableModel.refreshFromDB(stmt);
			}
		 }
		 if (target == deleteButton)
		 {

	 		try
	 		{
 				String updateTemp ="DELETE FROM ChurnData WHERE rec_id = "+recordIDTF.getText()+";";
 				stmt.executeUpdate(updateTemp);

	 		}
	 		catch (SQLException sqle)
	 		{
	 			System.err.println("Error with delete:\n"+sqle.toString());
	 		}
	 		finally
	 		{
	 			TableModel.refreshFromDB(stmt);
			}
		 }
		 if (target == updateButton)
		 {
	 		try
	 		{
 				String updateTemp ="update ChurnData set "+
 						"state = '" + stateTF.getText() + "'," +
 					    "accountLength = " + accountLengthTF.getText() + "," +
 					    "areaCode = " + areaCodeTF.getText() + "," +
 					    "phoneNumber = '" + phoneNumberTF.getText() + "'," +
 					    "internationalPlan = '" + intPlanTF.getText() + "'," +
 					    "voiceMailPlan = '" + vmPlanTF.getText() + "'," +
 					    "numberVmailMessages = " + numVmailMessagesTF.getText() + "," +
 					    "totalDayMinutes = " + totalDayMinTF.getText() + "," +
 					    "totalDayCalls = " + totalDayCallsTF.getText() + "," +
 					    "totalDayCharge = " + totalDayChargeTF.getText() + "," +
 					    "totalEveMinutes = " + totalEveMinTF.getText() + "," +
 					    "totalEveCalls = " + totalEveCallsTF.getText() + "," +
 					    "totalEveCharge = " + totalEveChargeTF.getText() + "," +
 					    "totalNightMinutes = " + totalNightMinTF.getText() + "," +
 					    "totalNightCalls = " + totalNightCallsTF.getText() + "," +
 					    "totalNightCharge = " + totalNightChargeTF.getText() + "," +
 					    "totalIntlMinutes = " + totalIntlMinTF.getText() + "," +
 					    "totalIntlCalls = " + totalIntlCallsTF.getText() + "," +
 					    "totalIntlCharge = " + totalIntlChargeTF.getText() + "," +
 					    "numCustomerServiceCalls = " + numCustServCallsTF.getText() + "," +
 					    "testValue = '" + testValueTF.getText() + "' where rec_id = " + recordIDTF.getText();

 				System.out.println(updateTemp);
 				stmt.executeUpdate(updateTemp);
 				//these lines do nothing but the table updates when we access the db.
 				rs = stmt.executeQuery("SELECT * from ChurnData ");
 				rs.next();
 				rs.close();
 			}
	 		catch (SQLException sqle){
	 			System.err.println("Error with members insert:\n"+sqle.toString());
	 		}
	 		finally{
	 			TableModel.refreshFromDB(stmt);
			}
		 }

		 if (target == exportButton) {
			 System.out.println("Export Button Pressed");
		 }
	}
}
