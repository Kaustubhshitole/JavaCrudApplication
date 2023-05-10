

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class JavaCurdApplication {
    private JTextField searchTextField = new JTextField();
    private JPanel Main;
    private JTextField txtName;
    private JTextField txtPrice;
    private JTextField txtQty;
    private JTextField txtpid;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton searchButton;

    public static void main(String[] args) {

        JFrame frame = new JFrame("JavaCurdApplication");
        frame.setContentPane(new JavaCurdApplication().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

    Connection con;
    PreparedStatement pst;

    public JavaCurdApplication() {
        this.searchTextField = searchTextField;
        Connect();


        saveButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, price, qty;


                PreparedStatement pst;

                name = txtName.getText();
                price = txtPrice.getText();
                qty = txtQty.getText();

                try {

                    pst = con.prepareStatement("insert into products( name,price,qty,id)values(?,?,?)");
                    pst.setString(1, name);
                    pst.setString(2, price);
                    pst.setString(3, qty);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Addedddddd!!!!");

                    txtName.setText("");
                    txtPrice.setText("");
                    txtQty.setText("");
                    txtName.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });








        searchButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    try {

                        PreparedStatement pst;
                        String pid = txtpid.getText();
                        pst = con.prepareStatement("select p name,p price,p qty from products where p id = ?");
                        pst.setString(1, pid);
                        ResultSet rs = pst.executeQuery();

                        if (rs.next() == true) {
                            String name = rs.getString(1);
                            String price = rs.getString(2);
                            String qty = rs.getString(3);

                            txtName.setText(name);
                            txtPrice.setText(price);
                            txtQty.setText(qty);
                        } else {
                            txtName.setText("");
                            txtPrice.setText("");
                            txtQty.setText("");
                            JOptionPane.showMessageDialog(null, "Invalid Product ID");


                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }


                }


            }


            public void Connect() {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/product-db  ", "root", "Shit@222");
                    System.out.println("Success");
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                deleteButton.addActionListener(new ActionListener() {


                    /**
                     * Invoked when an action occurs.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String bid;

                        bid = txtpid.getText();


                        try {

                            PreparedStatement pst;
                            pst = con.prepareStatement("delete from product  where product id = ?");
                            pst.setString(1, bid);
                            JButton b = new JButton("Click Here");

                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");

                            txtName.setText("");
                            txtPrice.setText("");
                            txtQty.setText("");
                            txtName.requestFocus();
                            txtpid.setText("");
                        } catch (SQLException e1) {

                            e1.printStackTrace();
                        }
                    }
                });
                updateButton.addActionListener(new ActionListener() {


                    /**
                     * Invoked when an action occurs.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        String pid, name, price, qty;

                        name = txtName.getText();
                        price = txtPrice.getText();
                        qty = txtQty.getText();
                        pid = txtpid.getText();

                        try {

                            PreparedStatement pst;

                            pst = con.prepareStatement("update products set name = ?,price = ?,qty = ? where pid = ?");
                            JButton b = new JButton("Click Here");
                            pst.setString(1, name);
                            pst.setString(2, price);
                            pst.setString(3, qty);
                            pst.setString(4, pid);

                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");

                            txtName.setText("");
                            txtPrice.setText("");
                            txtQty.setText("");
                            txtName.requestFocus();
                            txtpid.setText("");
                        } catch (SQLException e1) {

                            e1.printStackTrace();
                        }


                    }
                });
            }


        });
    }


    private void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productdb  ", "root", "Shit@222");
            System.out.println("Success");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}