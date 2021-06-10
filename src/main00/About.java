package main00;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class About extends JPanel{
	private JLabel mem1,mem2,mem3;
	private Font font = new Font("Ink Free", Font.BOLD, 26);
    public About(){
        this.setLayout(null);
        this.setBackground(Color.white);

        mem1 = new JLabel("Phan Anh Tu - ITITIU18287 - Monitor");
        mem2 = new JLabel("Le Minh Tan - ITITIU18236 - Database manager");
        mem3 = new JLabel("Ha Nhat Tan - ITITIU18184 - Staff");

        mem1.setBounds(50,10,700,30);
        mem1.setFont(font);
        mem2.setBounds(50,50,700,30);
        mem2.setFont(font);
        mem3.setBounds(50,90,700,30);
        mem3.setFont(font);

        this.add(mem1);
        this.add(mem2);
        this.add(mem3);
    }
}
