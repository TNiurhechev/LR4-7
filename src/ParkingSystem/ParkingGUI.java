package ParkingSystem;

import javax.swing.JButton;
import java.awt.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ParkingGUI extends JFrame {
    private JLabel nameLabel = new JLabel("   Welcome to the");
    private JLabel nameLabel2 = new JLabel("Parking Manager!");
    private JButton addButton = new JButton("Add car");
    private JButton billButton = new JButton("Bill report");
    private JButton removeButton = new JButton("Remove car");
    private JButton carButton = new JButton("Car report");
    private JButton journalButton = new JButton("In-out journal");
    private JButton ownerButton = new JButton("Owner report");
    private JButton printButton = new JButton("Print cars");
    private JButton mode = new JButton("Night mode");
    private JButton info = new JButton("Information");
    private JButton exitButton = new JButton("Exit");
    public ParkingGUI() {
        Parking parking = new Parking();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds(dimension.width/2-200,dimension.height/2-200,400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Parking Manager 1.2.0 ©2022");
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(8,2,3,3));
        //container.add(panel);
        this.setBackground(Color.WHITE);
        container.add(nameLabel);
        container.add(nameLabel2);
        Font font = new Font("Arial",Font.BOLD,22);
        nameLabel.setFont(font);
        nameLabel2.setFont(font);
        container.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addFrame = new JFrame();
                JLabel modelLabel = new JLabel("Model");
                JTextField modelField = new JTextField();
                JLabel ownerLabel = new JLabel("Owner");
                JTextField ownerField = new JTextField();
                JLabel balanceLabel = new JLabel("Balance");
                JSpinner balanceSpinner = new JSpinner();
                //JTextField balanceField = new JTextField();
                JLabel plateLabel = new JLabel("Licence plate");
                JTextField plateField = new JTextField();
                JButton addFrameButton = new JButton("Add");
                addFrame.setBounds(dimension.width/2-200,dimension.height/2-200,400,400);
                Container addContainer = addFrame.getContentPane();
                addContainer.setLayout(new GridLayout(7,2,3,3));
                addContainer.add(modelLabel);
                addContainer.add(modelField);
                addContainer.add(ownerLabel);
                addContainer.add(ownerField);
                addContainer.add(balanceLabel);
                addContainer.add(balanceSpinner);
                addContainer.add(plateLabel);
                addContainer.add(plateField);
                addFrame.add(addFrameButton);
                addFrameButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            parking.addCar(new Car(modelField.getText(),
                                    new CarOwner(ownerField.getText(),parseInt(balanceSpinner.getValue().toString())),
                                    plateField.getText()));
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                JButton addCloseButton = new JButton("Close all");
                addFrame.add(addCloseButton);
                addCloseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                addFrame.setVisible(true);
            }
        });
        container.add(removeButton);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame removeFrame = new JFrame();
                JLabel modelLabel = new JLabel("Model");
                JTextField modelField = new JTextField();
                JLabel ownerLabel = new JLabel("Owner");
                JTextField ownerField = new JTextField();
                JLabel balanceLabel = new JLabel("Balance");
                JSpinner balanceSpinner = new JSpinner();
                //JTextField balanceField = new JTextField();
                JLabel plateLabel = new JLabel("Licence plate");
                JTextField plateField = new JTextField();
                JButton removeFrameButton = new JButton("Remove");
                removeFrame.setBounds(dimension.width/2-200,dimension.height/2-200,400,400);
                Container removeContainer = removeFrame.getContentPane();
                removeContainer.setLayout(new GridLayout(7,2,3,3));
                removeContainer.add(modelLabel);
                removeContainer.add(modelField);
                removeContainer.add(ownerLabel);
                removeContainer.add(ownerField);
                removeContainer.add(balanceLabel);
                removeContainer.add(balanceSpinner);
                removeContainer.add(plateLabel);
                removeContainer.add(plateField);
                removeFrame.add(removeFrameButton);
                removeFrameButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            parking.removeCar(new Car(modelField.getText(),
                                    new CarOwner(ownerField.getText(),parseInt(balanceSpinner.getValue().toString())),
                                    plateField.getText()));
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                JButton removeCloseButton = new JButton("Close all");
                removeFrame.add(removeCloseButton);
                removeCloseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                removeFrame.setVisible(true);
            }
        });
        container.add(billButton);
        billButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame billFrame = new JFrame();
                billFrame.setBounds(dimension.width/2-200,dimension.height/2-100,400,200);
                JLabel choice = new JLabel("Choose field");
                ButtonGroup radioGroup = new ButtonGroup();
                JRadioButton plateRadio = new JRadioButton("License plate",true);
                JRadioButton ownerRadio = new JRadioButton("Owner");
                radioGroup.add(plateRadio);
                radioGroup.add(ownerRadio);
                JCheckBox reverseCheck = new JCheckBox("Reverse");
                JButton billReportButton = new JButton("Get report");
                Container billContainer = new Container();
                billContainer.setLayout(new GridLayout(5,1,3,3));
                billContainer.add(choice);
                billContainer.add(plateRadio);
                billContainer.add(ownerRadio);
                billContainer.add(reverseCheck);
                billContainer.add(billReportButton);
                billReportButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame reportFrame = new JFrame();
                        reportFrame.setBounds(dimension.width/2-200,dimension.height/2-100,400,200);
                        String field = plateRadio.isSelected() ? "plate" : "owner";
                        char sortMode = reverseCheck.isSelected() ? 'r' : 'a';
                        List<Bill> billsList = parking.billReport(field,sortMode);
                        int bills = billsList.size();
                        String[][] billsArray = new String[bills][4];
                        int count = 0;
                        for (Bill b:
                                billsList) {
                            billsArray[count][0] = b.getModel();
                            billsArray[count][1] = b.getOwnerName();
                            billsArray[count][2] = b.getLicensePlate();
                            billsArray[count][3] = Integer.toString(b.getBill());
                            count++;
                        }
                        String[] columns = {"Model","Owner","License Plate","Bill"};
                        JTable billsTable = new JTable(billsArray,columns);
                        reportFrame.add(billsTable);
                        reportFrame.setVisible(true);
                    }
                });
                billFrame.add(billContainer);
                billFrame.setVisible(true);
            }
        });
        container.add(carButton);
        carButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame carFrame = new JFrame();
                carFrame.setBounds(dimension.width/2-200,dimension.height/2-200,400,400);
                JLabel modelLabel = new JLabel("Model");
                JTextField modelField = new JTextField();
                JLabel ownerLabel = new JLabel("Owner");
                JTextField ownerField = new JTextField();
                JLabel balanceLabel = new JLabel("Balance");
                JSpinner balanceSpinner = new JSpinner();
                JLabel plateLabel = new JLabel("License plate");
                JTextField plateField = new JTextField();
                JButton carFrameButton = new JButton("Get report");
                JButton carCloseButton = new JButton("Close all");
                Container carContainer = new Container();
                carContainer.setLayout(new GridLayout(5,2,3,3));
                carContainer.add(modelLabel);
                carContainer.add(modelField);
                carContainer.add(ownerLabel);
                carContainer.add(ownerField);
                carContainer.add(balanceLabel);
                carContainer.add(balanceSpinner);
                carContainer.add(plateLabel);
                carContainer.add(plateField);
                carContainer.add(carFrameButton);
                carFrameButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame carReportFrame = new JFrame();
                        carReportFrame.setBounds(dimension.width/2-300,dimension.height/2-100,600,200);
                        List<String> carReportList = parking.carReportGUI(new Car(modelField.getText(),
                                new CarOwner(ownerField.getText(),Integer.parseInt(balanceSpinner.getValue().toString())),
                                        plateField.getText()));
                        int carReports = carReportList.size();
                        String[][] carReportsArray = new String[carReports][1];
                        int count = 0;
                        for (String s:
                                carReportList) {
                            carReportsArray[count][0] = s;
                            count++;
                        }
                        String[] columns = {"Report"};
                        JTable carsTable = new JTable(carReportsArray,columns);
                        carReportFrame.add(carsTable);
                        carReportFrame.setVisible(true);
                    }
                });
                carContainer.add(carCloseButton);
                carCloseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                carFrame.add(carContainer);
                carFrame.setVisible(true);
            }
        });
        container.add(ownerButton);
        ownerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ownerFrame = new JFrame();
                ownerFrame.setBounds(dimension.width/2-200,dimension.height/2-100,400,200);
                JLabel ownerLabel = new JLabel("Owner");
                JTextField ownerField = new JTextField();
                JLabel balanceLabel = new JLabel("Balance");
                JSpinner balanceSpinner = new JSpinner();
                JButton ownerFrameButton = new JButton("Get report");
                JButton ownerCloseButton = new JButton("Close all");
                Container ownerContainer = new Container();
                ownerContainer.setLayout(new GridLayout(3,2,3,3));
                ownerContainer.add(ownerLabel);
                ownerContainer.add(ownerField);
                ownerContainer.add(balanceLabel);
                ownerContainer.add(balanceSpinner);
                ownerContainer.add(ownerFrameButton);
                ownerFrameButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame ownerReportFrame = new JFrame();
                        ownerReportFrame.setBounds(dimension.width/2-300,dimension.height/2-100,600,200);
                        List<String> ownerReportList = parking.ownerReportGUI(new CarOwner(ownerField.getText(),
                                Integer.parseInt(balanceSpinner.getValue().toString())));
                        int ownerReports = ownerReportList.size();
                        String[][] ownerReportsArray = new String[ownerReports][1];
                        int count = 0;
                        for (String s:
                                ownerReportList) {
                            ownerReportsArray[count][0] = s;
                            count++;
                        }
                        String[] columns = {"Report"};
                        JTable ownersTable = new JTable(ownerReportsArray,columns);
                        ownerReportFrame.add(ownersTable);
                        ownerReportFrame.setVisible(true);
                    }
                });
                ownerContainer.add(ownerCloseButton);
                ownerCloseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                ownerFrame.add(ownerContainer);
                ownerFrame.setVisible(true);
            }
        });
        container.add(journalButton);
        journalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame journalFrame = new JFrame();
                journalFrame.setBounds(dimension.width/2-200,dimension.height/2-250,400,500);
                Container journalContainer = new Container();
                journalContainer.setLayout(new GridLayout(7,2,3,3));
                JLabel ddFromLabel = new JLabel("Day(from)");
                JTextField ddFromField = new JTextField();
                JLabel mmFromLabel = new JLabel("Month(from)");
                JTextField mmFromField = new JTextField();
                JLabel yyFromLabel = new JLabel("Year(from)");
                JTextField yyFromField = new JTextField();
                JLabel ddToLabel = new JLabel("Day(to)");
                JTextField ddToField = new JTextField();
                JLabel mmToLabel = new JLabel("Month(to)");
                JTextField mmToField = new JTextField();
                JLabel yyToLabel = new JLabel("Year(to)");
                JTextField yyToField = new JTextField();
                JButton journalButton = new JButton("Get journal");
                JButton journalCloseButton = new JButton("Close All");
                journalContainer.add(ddFromLabel);
                journalContainer.add(ddFromField);
                journalContainer.add(mmFromLabel);
                journalContainer.add(mmFromField);
                journalContainer.add(yyFromLabel);
                journalContainer.add(yyFromField);
                journalContainer.add(ddToLabel);
                journalContainer.add(ddToField);
                journalContainer.add(mmToLabel);
                journalContainer.add(mmToField);
                journalContainer.add(yyToLabel);
                journalContainer.add(yyToField);
                journalContainer.add(journalButton);
                journalButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame journalReportFrame = new JFrame();
                        journalReportFrame.setBounds(dimension.width/2-300,dimension.height/2-100,600,200);
                        List<String> journal = parking.getJournal(LocalDate.of(Integer.parseInt(yyFromField.getText())
                                ,Integer.parseInt(mmFromField.getText()), Integer.parseInt(ddFromField.getText())),
                                LocalDate.of(Integer.parseInt(yyToField.getText())
                                        ,Integer.parseInt(mmToField.getText()), Integer.parseInt(ddToField.getText())));
                        int journalRecords = journal.size();
                        String[][] journalRecordsArray = new String[journalRecords][1];
                        int count = 0;
                        for (String s:
                                journal) {
                            journalRecordsArray[count][0] = s;
                            count++;
                        }
                        String[] columns = {"Record"};
                        JTable journalRecordsTable = new JTable(journalRecordsArray,columns);
                        journalReportFrame.add(journalRecordsTable);
                        journalReportFrame.setVisible(true);
                    }
                });
                journalContainer.add(journalCloseButton);
                journalCloseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                journalFrame.add(journalContainer);
                journalFrame.setVisible(true);
            }
        });
        container.add(printButton);
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Car> carsList = parking.getCars();
                int cars = carsList.size();
                String[][] carsArray = new String[cars][6];
                int count = 0;
                for (Car c:
                     carsList) {
                    carsArray[count][0] = c.getModel();
                    carsArray[count][1] = c.getOwner().getName();
                    carsArray[count][2] = Integer.toString(c.getOwner().getBalance());
                    carsArray[count][3] = c.getLicensePlate();
                    carsArray[count][4] = c.getEntryDate().toString();
                    carsArray[count][5] = Integer.toString(c.getPaidBills());
                    count++;
                }
                String[] columns = {"Model","Owner","Balance","License Plate","Entry Date","Bills paid"};
                JFrame printFrame = new JFrame();
                printFrame.setBounds(dimension.width/2-300,dimension.height/2-100,600,200);
                JTable carsTable = new JTable(carsArray,columns);
                printFrame.add(carsTable);
                printFrame.setVisible(true);
            }
        });
        container.add(mode);
        mode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(container.getBackground()==Color.WHITE){
                    container.setBackground(Color.BLACK);
                    mode.setText("Day mode");
                }
                else{
                    container.setBackground(Color.WHITE);
                    mode.setText("Night mode");
                }
            }
        });
        container.add(info);
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame infoFrame = new JFrame();
                infoFrame.setBounds(dimension.width/2-200,dimension.height/2-100,400,200);
                String info = " The app is specifically designed for operating a\n parking lot" +
                        " using an integrated database via user\n interface. Statistics and automatic car management" +
                        "\n available. For any support contact\n nt600136@gmail.com. All rights reserved." +
                        "\n\n\n\n\tTymur Niurhechev ©2022";
                JTextArea infoArea = new JTextArea(info);
                infoArea.setFont(new Font("Arial",Font.BOLD,14));
                infoArea.setEditable(false);

                JLabel infoLabel = new JLabel("The app is specifically designed for operating a parking lot" +
                        " using an integrated database via user interface. Statistics and automatic car management" +
                        " available. For any support contact nt600136@gmail.com. All rights reserved.");
                infoFrame.add(infoArea);
                infoFrame.setVisible(true);
            }
        });
        container.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
