import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ItemGUIComp {


	private JPanel item ;
	private JPanel name ;
	private JPanel itemDetails ;
	private JPanel fullName ;
	private JLabel priceValue ;
	private JLabel sizeValue ;
	private JLabel price ;
    	private JLabel size ;
    
   	private String itemName ;
	private String itemFullName ;
	
	// constructor
	public ItemGUIComp(String name, String fullName) {
		this.itemName = name ;
        	this.itemFullName = fullName ;
		this.price = new JLabel ("Current Price");
		this.size = new JLabel ("Lot Size");
		this.priceValue = new JLabel ();
		this.sizeValue = new JLabel() ;
	}
	
	public JPanel createItem() {
		
		// create a panel for item and set properties
		this.item = new JPanel();
		item.setBorder(BorderFactory.createLineBorder(Color.black,10));
		item.setLayout(new GridLayout(3,1));
		
		// create jpanel for hold name of the stock item
		this.name = new JPanel ();
		name.setBackground(new Color(211,211,211));
		name.setLayout(new GridBagLayout());
		JLabel label = new JLabel(itemName);
		label.setFont(new Font("Tahoma", 1, 20)); 
		label.setForeground(new Color(128,0,128)); 
		name.add(label); 
		
		// jpanel to hold lotsize and current price
		this.itemDetails = new JPanel ();
		itemDetails.setLayout(new GridLayout(2,1));
		itemDetails.setBackground(Color.lightGray);
		
		// set properties of price label
		price.setHorizontalAlignment(JLabel.CENTER);
		price.setFont(new Font("Tahoma", 1, 15)); 
		itemDetails.add(price);
		
		// set properties of priceValue label
		priceValue.setHorizontalAlignment(JLabel.CENTER);
        	priceValue.setFont(new Font("Tahoma", 1, 13));
        	priceValue.setForeground(new Color(0,0,0));
		itemDetails.add(priceValue);
		
		// set properties of size label
		size.setHorizontalAlignment(JLabel.CENTER);
		size.setFont(new Font("Tahoma", 1, 15));
		itemDetails.add(size);
		
		// set properties of sizeVlaue label
		sizeValue.setHorizontalAlignment(JLabel.CENTER);
        	sizeValue.setFont(new Font("Tahoma", 1, 13));
        	sizeValue.setForeground(new Color(0,0,0));
		itemDetails.add(sizeValue);
		
		// jpanel to hold full name of stock item
		this.fullName = new JPanel () ;
		fullName.setBackground(new Color(211,211,211));
		fullName.setLayout(new GridBagLayout());
		JLabel nameLabel = new JLabel (itemFullName);
		nameLabel.setForeground(Color.darkGray);
		fullName.add(nameLabel);
		

		// add created three panels to the item panel and retuen it
		item.add(name);
		item.add(itemDetails);
		item.add(fullName);
		
		return item ;
	}


	// set the current price
	public void setPriceValue(float price) {
        this.priceValue.setText(String.valueOf(price));
	}

	// set the lot size
	public void setLotSizeValue(int size) {
		this.sizeValue.setText(String.valueOf(size));
	}
	
    
}
