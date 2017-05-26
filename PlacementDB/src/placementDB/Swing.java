package placementDB; 
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Swing extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Statement stmt=Connect.stmt;
	Connection connection=Connect.connection;
	ResultSet rs;
	JTextArea tWelcome=new JTextArea("\n\n        PLACEMENT\n                 MANAGEMENT\n\t         DATABASE\n\n");
	JPanel panel=new JPanel(new BorderLayout());
	JButton bStudent=new JButton("Student's section");
	JButton bCompany=new JButton("Company section");
	JTextArea tBlank=new JTextArea("\n\n\n\n");
	Font f=new Font("Times New Roman",Font.BOLD+Font.ITALIC,20);
	Font fs=new Font("Times New Roman",Font.BOLD,15);
	Font fso=new Font("Times New Roman",Font.BOLD,11);
	
	
	
	void student()
	{
		JFrame fStudent=new JFrame();
		JPanel sPanel=new JPanel(new BorderLayout());
		JTextArea tStudent=new JTextArea("\n\n\n               Student's Section\n\n\n\n");
		tStudent.setFont(f);
		tStudent.setEditable(false);
		JButton bDetails=new JButton("Company Details");
		JButton bEligibility=new JButton("Eligibility Check");
		JButton bSal=new JButton("Intern salary");
		sPanel.add(bDetails,BorderLayout.NORTH);
		sPanel.add(bSal,BorderLayout.CENTER);
		sPanel.add(bEligibility,BorderLayout.SOUTH);
		fStudent.add(tStudent,BorderLayout.NORTH);
		fStudent.add(sPanel,BorderLayout.CENTER);
		fStudent.add(tBlank,BorderLayout.SOUTH);
		fStudent.setSize(400,350);
		fStudent.setVisible(true);
		fStudent.setLocationRelativeTo(null);
		
		
		
		//Company details codes
		
		bDetails.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e)
			  {
				  JFrame fCompany=new JFrame();
				   JTextArea tOutput=new JTextArea();
				   tOutput.setFont(fso);
				   tOutput.setEditable(false);
				   
				  try {
					  tOutput.append("\n\n  Company_id\t   Name\tLocation\tCriteria\n\n"); 
					rs=stmt.executeQuery("SELECT company_id,name,location,criteria FROM company");
					while(rs.next())
					{
						tOutput.append("       "+rs.getString("company_id")+"\t  "+rs.getString("name")+"\t"
								+rs.getString("location")+"\t  "+rs.getString("criteria")+"\n");
						
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  fCompany.add(tOutput,BorderLayout.CENTER);
				  fCompany.setSize(400,500);
				  fCompany.setVisible(true);
				  fCompany.setLocationRelativeTo(null);
		  
		  }
		  });
		
		
		
		//Eligibility check codes
		bEligibility.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e)
			  {	
				   JFrame fEligibility=new JFrame();
				   JPanel sePanel=new JPanel(new BorderLayout());
				   final JTextField tInput=new JTextField();
				   JTextField teHeading=new JTextField("Enter your CGPA");
				   teHeading.setFont(f);
				   teHeading.setEditable(false);
				   tInput.setFont(fs);
				   JButton bCheck=new JButton("Check");
				   final JTextArea tOutput=new JTextArea();
				   tOutput.setEditable(false);
				   tOutput.setFont(fs);
				   
				   
				   
				   
				   
				   
				   bCheck.addActionListener(new ActionListener(){
						  public void actionPerformed(ActionEvent e)
						
						  {
							  String str=null;
							  try{
							  str=tInput.getText().toString().trim();
							  }
							  catch(Exception et){
							  tOutput.setText("Enter an Input!!!");
							  }
							  
							  float cgpa=Float.valueOf(str);
							  
							  if(cgpa<=10&&cgpa>=6)
							  {
								  try {
									  tOutput.setText("\n  List of companies you can apply:\n\n\n");
									rs=stmt.executeQuery("SELECT name FROM company WHERE criteria<="+cgpa);
									while(rs.next())
									{
										String op=rs.getString("name");
										tOutput.append("\n  "+op);
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							  }
								  
							  else
								  tOutput.setText("Enter a valid CGPA!");
					  
					  }
					  });
				   
				   
				   
				   
				   
				   
				   sePanel.add(teHeading,BorderLayout.NORTH);
				   sePanel.add(tInput,BorderLayout.CENTER);
				   sePanel.add(bCheck,BorderLayout.SOUTH);
				   fEligibility.add(sePanel,BorderLayout.NORTH);
				   fEligibility.add(tOutput,BorderLayout.CENTER);
				   fEligibility.setSize(400,500);
				   fEligibility.setVisible(true);
				   fEligibility.setLocationRelativeTo(null);
		  
		  }
		  });
		
		
		// intern salary
		
		bSal.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e)
			  {	
				   JFrame fSal=new JFrame();
				   JPanel sePanel=new JPanel(new BorderLayout());
				   final JTextField tInput=new JTextField();
				   JTextField teHeading=new JTextField("Enter the Company name");
				   teHeading.setFont(f);
				   teHeading.setEditable(false);
				   tInput.setFont(fs);
				   JButton bCheck=new JButton("Get!");
				   final JTextArea tOutput=new JTextArea();
				   tOutput.setEditable(false);
				   tOutput.setFont(fs);
				   
				   bCheck.addActionListener(new ActionListener(){
						  public void actionPerformed(ActionEvent e)
						
						  {
							  String str=null;
							  try{
							  str=tInput.getText().toString().trim();
							  }
							  catch(Exception et){
							  et.printStackTrace();
							  }
							  
							  
							  if(str!=null)
							  {
								  try {
									  
									rs=stmt.executeQuery("SELECT sal(\'"+str+"\')");
									while(rs.next())
									{
										String op=rs.getString(1);
										if(op!=null)
										{
											tOutput.setText("\n\n\n  The Salary package is:\n\n");
										tOutput.append("\n    "+op);
										}
										else
											tOutput.setText("\nThe company doesn't exists in database");
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							  }
								  
							  else
								  tOutput.setText("Enter a Name!!!");
					  
					  }
					  });
				   
				   
				   
				   
				   
				   
				   sePanel.add(teHeading,BorderLayout.NORTH);
				   sePanel.add(tInput,BorderLayout.CENTER);
				   sePanel.add(bCheck,BorderLayout.SOUTH);
				   fSal.add(sePanel,BorderLayout.NORTH);
				   fSal.add(tOutput,BorderLayout.CENTER);
				   fSal.setSize(400,500);
				   fSal.setVisible(true);
				   fSal.setLocationRelativeTo(null);
		  
		  }
		  });
		}
			
	void company()
	{
		
		JFrame fCompany=new JFrame();
		JPanel sPanel=new JPanel(new BorderLayout());
		JPanel s2Panel=new JPanel(new BorderLayout());
		JTextArea tCompany=new JTextArea("\n\n\n               Company Section\n\n\n\n");
		tCompany.setFont(f);
		tCompany.setEditable(false);
		JButton bDetails=new JButton("Student Details");
		JButton bIntern=new JButton("Internship details");
		JButton bProject=new JButton("Projects list");
		JButton bBacklog=new JButton("Backlog details");
		JButton bExam=new JButton("Exam marks");
		JButton bninePointer=new JButton("Nine pointers list");
		sPanel.add(bDetails,BorderLayout.NORTH);
		sPanel.add(bProject,BorderLayout.CENTER);
		sPanel.add(bIntern,BorderLayout.SOUTH);
		s2Panel.add(bBacklog,BorderLayout.NORTH);
		s2Panel.add(bninePointer,BorderLayout.CENTER);
		s2Panel.add(bExam,BorderLayout.SOUTH);
		fCompany.add(tCompany,BorderLayout.NORTH);
		fCompany.add(sPanel,BorderLayout.CENTER);
		fCompany.add(s2Panel,BorderLayout.SOUTH);
		fCompany.setSize(400,350);
		fCompany.setVisible(true);
		fCompany.setLocationRelativeTo(null);
		
		
		
		//Student details
		
		
		
		
		bDetails.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e)
			  {	
				   JFrame fDetails=new JFrame();
				   JPanel sePanel=new JPanel(new BorderLayout());
				   String list[]={"1001","1002","1003","1004","1005","1006","1007","1008","1009","1010",
						   "1011","1012","1013","1014","1015","1016","1017","1018","1019","1020","1021","1022",
						   "1023","1024","1025","1026","1027","1028"};
				   final JComboBox<String> box=new JComboBox<String>(list);
				   JTextField teHeading=new JTextField("Select the Student ID");
				   teHeading.setFont(f);
				   teHeading.setEditable(false);
				   JButton bCheck=new JButton("Get details!");
				   final JTextArea tOutput=new JTextArea();
				   tOutput.setEditable(false);
				   tOutput.setFont(fs);
				   
				   bCheck.addActionListener(new ActionListener(){
						  public void actionPerformed(ActionEvent e)
						
						  {
							  String str=null;
							  try{
							  str=box.getSelectedItem().toString();
							  }
							  catch(Exception et){
							  et.printStackTrace();
							  }
							  
							  int id=Integer.valueOf(str);
							  
							  try {
									  tOutput.setText("\n\n\n   The student details is:\n\n");
									rs=stmt.executeQuery("select distinct s.s_id,s.name,s.age,s.gender," +
											"a.cgpa,c.name,d.name from student s,academics a,college c," +
											"department d where s.s_id=a.student_id and s.department_id=d.d_id and " +
											"s.college_code=c.code and s.department_id=d.d_id and s.s_id="+id);
									while(rs.next())
									{
										
										String str2=rs.getString(2);
										String str3=rs.getString(3);
										String str4=rs.getString(4);
										String str5=rs.getString(5);
										float str_float=Float.valueOf(str5);
										String str6=rs.getString(6);
										String str7=rs.getString(7);
										tOutput.append("\n   Name:\t"+str2+"\n   Age:\t"+str3+"\n   Gender:\t"+str4+
												"\n   CGPA:\t"+str_float+"\n   College:\t"+str6+"\n   Department:\t"+str7);
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							  }
								  
							 
					  
					  
					  });
				   
				   
				   
				   
				   
				   
				   sePanel.add(teHeading,BorderLayout.NORTH);
				   sePanel.add(box,BorderLayout.CENTER);
				   sePanel.add(bCheck,BorderLayout.SOUTH);
				   fDetails.add(sePanel,BorderLayout.NORTH);
				   fDetails.add(tOutput,BorderLayout.CENTER);
				   fDetails.setSize(400,500);
				   fDetails.setVisible(true);
				   fDetails.setLocationRelativeTo(null);
		  
		  }
		  });
		
		
		
		
		//Internship details using view "interns"
		
		bIntern.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e)
			  {
				  JFrame fIntern=new JFrame();
				   JTextArea tOutput=new JTextArea();
				   tOutput.setFont(fso);
				   tOutput.setEditable(false);
				   
				   try{
					   tOutput.setText("\n     Student\tCompany\tDuration in Months\n\n");
					   rs=stmt.executeQuery("select name,name_of_company,no_of_months from interns");
					   while(rs.next())
					   {
						   String str1=rs.getString("name");
						   String str2=rs.getString("name_of_company");
						   String str3=rs.getString("no_of_months");
						   tOutput.append("     "+str1+"\t"+str2+"\t      "+str3+"\n");
					   }
				   }
				   catch(Exception et)
				   {
					   et.printStackTrace();
				   }
				   
				   fIntern.add(tOutput,BorderLayout.CENTER);
					  fIntern.setSize(400,500);
					  fIntern.setVisible(true);
					  fIntern.setLocationRelativeTo(null);
		  
		  }
		  });
		
		
		//Project details
		bProject.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e)
			  {
				  JFrame fProject=new JFrame();
				   JTextArea tOutput=new JTextArea();
				   tOutput.setFont(fso);
				   tOutput.setEditable(false);
				   
				   try{
					   tOutput.setText("\n  Name\tProject\t\t   Marks\n\n");
					   rs=stmt.executeQuery("select name,p_name,marks_for_project from pro");
					   while(rs.next())
					   {
						   String str1=rs.getString("name");
						   String str2=rs.getString("p_name");
						   String str3=rs.getString("marks_for_project");
						   tOutput.append("  "+str1+"\t"+str2+"\t    "+str3+"\n");
					   }
				   }
				   catch(Exception et)
				   {
					   et.printStackTrace();
				   }
		  
				  fProject.add(tOutput,BorderLayout.CENTER);
				  fProject.setSize(400,500);
				  fProject.setVisible(true);
				  fProject.setLocationRelativeTo(null);
	  
		  }
		  });
		
		//ninePointers view
		
		bninePointer.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e)
			  {
				  
				  Frame fninePointer=new JFrame();
				   JTextArea tOutput=new JTextArea();
				   tOutput.setFont(fso);
				   tOutput.setEditable(false);
				   
				   try{
					   tOutput.setText("\n\n\n    Name\tDepartment\tCGPA\n\n");
					   rs=stmt.executeQuery("select student,department,cgpa from ninePointers");
					   while(rs.next())
					   {
						   String str1=rs.getString("student");
						   String str2=rs.getString("department");
						   String str3=rs.getString("cgpa");
						   float s=Float.valueOf(str3);
						   tOutput.append("    "+str1+"\t"+str2+"\t"+s+"\n");
					   }
				   }
				   catch(Exception et)
				   {
					   et.printStackTrace();
				   }
		  
				  fninePointer.add(tOutput,BorderLayout.CENTER);
				  fninePointer.setSize(400,500);
				  fninePointer.setVisible(true);
				  fninePointer.setLocationRelativeTo(null);
				  
				  
		  }
		  });
		
		
		//backlog -- function
		
		
				bBacklog.addActionListener(new ActionListener(){
					  public void actionPerformed(ActionEvent e)
					  {	
						   JFrame fBacklog=new JFrame();
						   JPanel sePanel=new JPanel(new BorderLayout());
						   final JTextField tInput=new JTextField();
						   JTextField teHeading=new JTextField("Enter the Student ID");
						   teHeading.setFont(f);
						   teHeading.setEditable(false);
						   tInput.setFont(fs);
						   JButton bCheck=new JButton("Get details!");
						   final JTextArea tOutput=new JTextArea();
						   tOutput.setEditable(false);
						   tOutput.setFont(fs);
						   
						   bCheck.addActionListener(new ActionListener(){
								  public void actionPerformed(ActionEvent e)
								
								  {
									  String str=null;
									  try{
									  str=tInput.getText().toString().trim();
									  }
									  catch(Exception et){
									  et.printStackTrace();
									  }
									  
									  int id=Integer.valueOf(str);
									  
									  if(id<=1028&&id>=1001)
									  {
										  try {
											  tOutput.setText("\n\n\n  The number of arrears is:\n\n");
											rs=stmt.executeQuery("SELECT backlog("+id+")");
											while(rs.next())
											{
												String op=rs.getString(1);
												tOutput.append("\n    "+op);
											}
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									  }
										  
									  else
										  tOutput.setText("Enter a valid ID!!!");
							  
							  }
							  });
						   
						   
						   
						   
						   
						   
						   sePanel.add(teHeading,BorderLayout.NORTH);
						   sePanel.add(tInput,BorderLayout.CENTER);
						   sePanel.add(bCheck,BorderLayout.SOUTH);
						   fBacklog.add(sePanel,BorderLayout.NORTH);
						   fBacklog.add(tOutput,BorderLayout.CENTER);
						   fBacklog.setSize(400,500);
						   fBacklog.setVisible(true);
						   fBacklog.setLocationRelativeTo(null);
				  
				  }
				  });
				
				
			
		
				//exam -- function
				
				
				bExam.addActionListener(new ActionListener(){
					  public void actionPerformed(ActionEvent e)
					  {	
						   JFrame fExam=new JFrame();
						   JPanel sePanel=new JPanel(new BorderLayout());
						   final JTextField tInput=new JTextField();
						   JTextField teHeading=new JTextField("Enter the Student ID");
						   teHeading.setFont(f);
						   teHeading.setEditable(false);
						   tInput.setFont(fs);
						   JButton bCheck=new JButton("Get Marks!");
						   final JTextArea tOutput=new JTextArea();
						   tOutput.setEditable(false);
						   tOutput.setFont(fs);
						   
						   bCheck.addActionListener(new ActionListener(){
								  public void actionPerformed(ActionEvent e)
								
								  {
									  String str=null;
									  try{
									  str=tInput.getText().toString().trim();
									  }
									  catch(Exception et){
									  et.printStackTrace();
									  }
									  
									  int id=Integer.valueOf(str);
									  
									  if(id<=1028&&id>=1001)
									  {
										  try {
											  tOutput.setText("\n\n\n  The written exam mark scored is:\n\n");
											rs=stmt.executeQuery("SELECT exam("+id+")");
											while(rs.next())
											{
												String op=rs.getString(1);
												tOutput.append("\n    "+op);
											}
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									  }
										  
									  else
										  tOutput.setText("Enter a valid ID!!!");
							  
							  }
							  });
						   
						   
						   
						   
						   
						   
						   sePanel.add(teHeading,BorderLayout.NORTH);
						   sePanel.add(tInput,BorderLayout.CENTER);
						   sePanel.add(bCheck,BorderLayout.SOUTH);
						   fExam.add(sePanel,BorderLayout.NORTH);
						   fExam.add(tOutput,BorderLayout.CENTER);
						   fExam.setSize(400,500);
						   fExam.setVisible(true);
						   fExam.setLocationRelativeTo(null);
				  
				  }
				  });
				}

    
	
	
			Swing()
		 {
			 try {
				stmt=connection.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		  setSize(400,350);
		  setLocationRelativeTo(null);
		  setLayout(new BorderLayout(5,10));
		  tWelcome.setFont(f);
		  tWelcome.setEditable(false);
		  tBlank.setEditable(false);
		  
		  bStudent.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e)
			  {
				  student();
		  
		  }
		  });
		  
		  bCompany.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e)
			  {
				  company(); 
		  
		  }
		  });
		  panel.add(bStudent,BorderLayout.NORTH);
		  panel.add(tBlank,BorderLayout.CENTER);
		  panel.add(bCompany,BorderLayout.SOUTH);
		  add(tWelcome,BorderLayout.NORTH);
		  add(panel,BorderLayout.CENTER);
		  add(tBlank,BorderLayout.SOUTH);
		  setVisible(true);
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		     }

		
	}
