package application;

import javax.crypto.Cipher;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;    
import javax.crypto.KeyGenerator;   
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
	
	static Cipher cipher; 
	/*@FXML
	private Label enclbl;
	
	@FXML
	private Label declbl;*/
	
	@FXML
	private TextField txtenc;
	
	@FXML
	private TextField txtdec;
	
	@FXML
	private TextField encodedtxt;
	
	@FXML
	private TextField decodedtxt;
	
	SecretKey secretKey;
	//SecretKey originalKey;
	
	//end code
	
	public void encode(ActionEvent event) throws Exception{
		
		/*KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		   keyGenerator.init(128); // block size is 128bits
*/		   secretKey = getSecretkeyNew();
		   cipher = Cipher.getInstance("AES"); 
		   
		String input= txtenc.getText();
		if(!(input.equals(null))  && (!(input.equals("")))){
			String encoded = encrypt(input, secretKey);
			System.out.println("encoded==="+encoded);
			//enclbl.setText(encoded);
			encodedtxt.setText(encoded);
		}else if(input.equals(null) || input.equals("")){
			
			//enclbl.setText(encoded);
			encodedtxt.setText("Enter value to encode");
		}
		
	}
	
	public void decode(ActionEvent event) throws Exception{
		
		//KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		  // keyGenerator.init(128); // block size is 128bits
		   SecretKey secretKey1 = getSecretkeyNew();
		   cipher = Cipher.getInstance("AES");
		   System.out.println("from decode");
		
		String input= txtdec.getText();
		if((!(input.equals(null))) && (!(input.equals("")))){
			 String decoded = decrypt(input, secretKey1);
			System.out.println("decoded==="+decoded);
			//declbl.setText(decoded);
			decodedtxt.setText(decoded);
		}else if((input.equals(null)) || input.equals("")){
			decodedtxt.setText("Enter value to decode");
		}
	}
	//
	 public static String encrypt(String plainText, SecretKey secretKey)
	            throws Exception {
		 //String test=test();
		 System.out.println("secret Key from encrypt="+secretKey.toString());
	        byte[] plainTextByte = plainText.getBytes();
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	        byte[] encryptedByte = cipher.doFinal(plainTextByte);
	        Base64.Encoder encoder = Base64.getEncoder();
	        String encryptedText = encoder.encodeToString(encryptedByte);
	        return encryptedText;
	    }

	    public static String decrypt(String encryptedText, SecretKey secretKey)
	            throws Exception {
	    	System.out.println("From decrypt");
	    	//secretKey= new javax.crypto.spec.SecretKeySpec@fffe8100;
	    	System.out.println("secret Key from decrypt="+secretKey.toString());
	        Base64.Decoder decoder = Base64.getDecoder();
	        byte[] encryptedTextByte = decoder.decode(encryptedText);
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
	        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
	        String decryptedText = new String(decryptedByte);
	        return decryptedText;
	    }
	    
	    public SecretKey getSecretKey() throws Exception{
	    	KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			   keyGenerator.init(128); // block size is 128bits
			   secretKey = keyGenerator.generateKey();
	    	return secretKey;
	    }
	    
	    public SecretKey getSecretkeyNew(){
	    	
	    	String key="z+7DuU2nTc5NoXNdd5gvrQ==";
	    	// decode the base64 encoded string
	    	
	    	byte[] decodedKey = Base64.getDecoder().decode(key);
	    	// rebuild key using SecretKeySpec
	    	SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
	    	
	    	return originalKey;
	    }
	    
	    /*public static String test() throws NoSuchAlgorithmException{
	    	// create new key
	    	SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
	    	// get base64 encoded version of the key
	    	String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
	    	
	    	System.out.println("String="+encodedKey);
	    	
	    	return encodedKey;
	    }*/
	//
	
public void close(ActionEvent event){
		
		System.exit(0);
	}

}
