package main00;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

class HintTextField extends JTextField implements FocusListener {

	  private final String hint;
	  private boolean showingHint;

	  public HintTextField(final String hint) {
	    super(hint);
	    this.hint = hint;
	    this.showingHint = true;
	    this.setForeground(Color.GRAY);
	    super.addFocusListener(this);
	  }

	  @Override
	  public void focusGained(FocusEvent e) {
	    if(this.getText().isEmpty()) {
	      super.setText("");
	      showingHint = false;
	    }
	    this.setForeground(Color.BLACK);
	  }
	  @Override
	  public void focusLost(FocusEvent e) {
	    if(this.getText().isEmpty()) {
	      super.setText(hint);
	      showingHint = true;
	      this.setForeground(Color.GRAY);
	    }
	  }

	  @Override
	  public String getText() {
	    return showingHint ? "" : super.getText();
	  }
	}