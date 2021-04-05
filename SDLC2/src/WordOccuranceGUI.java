import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class WordOccuranceGUI {

	private JFrame frmTextAnalyzer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WordOccuranceGUI window = new WordOccuranceGUI();
					window.frmTextAnalyzer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WordOccuranceGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTextAnalyzer = new JFrame();
		frmTextAnalyzer.setTitle("Text Analyzer");
		frmTextAnalyzer.getContentPane().setBackground(Color.BLACK);
		frmTextAnalyzer.getContentPane().setForeground(Color.BLACK);
		frmTextAnalyzer.setBounds(100, 100, 683, 417);
		frmTextAnalyzer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTextAnalyzer.getContentPane().setLayout(null);
		
		File input = new File("input.txt");
		File test1 = new File("test1.txt");

		
		JButton start = new JButton("Begin");
		
		start.setBounds(124, 262, 124, 46);
		frmTextAnalyzer.getContentPane().add(start);
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exit.setBounds(442, 262, 111, 46);
		frmTextAnalyzer.getContentPane().add(exit);
		
		JLabel title2 = new JLabel("By Mohamed Hamdy");
		title2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		title2.setForeground(Color.RED);
		title2.setBounds(442, 144, 180, 31);
		frmTextAnalyzer.getContentPane().add(title2);
		
		JLabel title1 = new JLabel("Text Analyzer");
		title1.setForeground(Color.RED);
		title1.setFont(new Font("Times New Roman", Font.BOLD, 38));
		title1.setHorizontalAlignment(SwingConstants.CENTER);
		title1.setBounds(0, 51, 659, 51);
		frmTextAnalyzer.getContentPane().add(title1);
		
		JButton nxt = new JButton("Next");
		
		nxt.setBounds(285, 262, 111, 46);
		frmTextAnalyzer.getContentPane().add(nxt);
		
		JTextArea results = new JTextArea();
		results.setForeground(Color.RED);
		results.setLineWrap(true);
		results.setWrapStyleWord(true);
		results.setBackground(Color.BLACK);
		results.setBounds(10, 40, 659, 500);
		frmTextAnalyzer.getContentPane().add(results);
		nxt.setVisible(false);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				title1.setText("Text: The Raven by Edgar Allen Poe");
				title2.setVisible(false);
				start.setVisible(false);
				exit.setVisible(false);
				nxt.setVisible(true);
				
				
			}
		});
		nxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					title1.setBounds(0, 10, 659, 51);
					title1.setText("Results:");
					results.setVisible(true);
				results.setText(wordOccurances(test1));
				nxt.setVisible(false);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}




public String  wordOccurances(File input) throws FileNotFoundException
{
	
	StringBuilder str = new StringBuilder(); //string builder for final list
	String result = null;
   
    Scanner scan = new Scanner(input);
   
    Map<String,Integer> map = new HashMap<String, Integer>(); 
    while (scan.hasNext()) //this part takes the file contents and puts it into my hashmap
    {
        String temp = scan.next(); 
        if(map.containsKey(temp) == false) 
            map.put(temp,1);
        else 
        {
            int count = (int)(map.get(temp)); 
            map.remove(temp);  
            map.put(temp,count+1);
        }
    }
    Set<Map.Entry<String, Integer>> list = map.entrySet();
    List<Map.Entry<String, Integer>> sort = new ArrayList<Map.Entry<String, Integer>>(list); // sorts in descending order
    Collections.sort( sort, new Comparator<Map.Entry<String, Integer>>() 
    {
        public int compare( Map.Entry<String, Integer> a, Map.Entry<String, Integer> b ) 
        {
            return (b.getValue()).compareTo( a.getValue() ); 

        }
    } );
//prints the list
    for(int i = 0; i < 5; i++) {   
    	
      str.append( (i+1) + "." + " " + sort.get(i) + "\n ") ;
    }  
    
   result = str.toString();
 
    
    return result; 
	}
}
