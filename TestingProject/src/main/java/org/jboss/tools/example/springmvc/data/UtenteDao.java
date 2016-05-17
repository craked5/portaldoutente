package org.jboss.tools.example.springmvc.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.jboss.tools.example.springmvc.controller.Cifras;
import org.jboss.tools.example.springmvc.sensitivedata.Utente;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Repository
@Transactional
public class UtenteDao {

	@Autowired
	private EntityManager em;
	
	public Utente newUtente(String username, String numUtente, String cc, String morada, String mail, String password, String telemovel, String nif, String code, String codeSms) throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		Utente novoUtente = new Utente(username, Integer.parseInt(numUtente), Integer.parseInt(cc), morada, mail, password, Integer.parseInt(telemovel), Integer.parseInt(nif), code, codeSms);
		em.persist(novoUtente);
		return novoUtente;
	}
	
//	public Utente novoUtente(String username, String password) {
//		Utente novoUtente = new Utente(Integer.parseInt(username), password);
//		em.persist(novoUtente);
//		return novoUtente;
//	}
	
	public Utente findUtenteById(int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<Utente> query = em.createNamedQuery(Utente.FIND_BY_ID, Utente.class);
		query.setParameter(Utente.ID, Cifras.encrypt(Integer.toString(numUtente)));
		try{
			return query.getSingleResult();
		}
		catch(PersistenceException e){
			return null;
		}
	}
	
	public Utente findUtenteByNIF(int nif) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<Utente> query = em.createNamedQuery(Utente.FIND_BY_NIF, Utente.class);
		String nif_cifrado = Cifras.encrypt(Integer.toString(nif));
		query.setParameter(Utente.NIF, nif_cifrado);
		try{
			return query.getSingleResult();
		}
		catch(PersistenceException e){
			return null;
		}
	}
	
	public List<Utente> findAll(){
		TypedQuery<Utente> query = em.createNamedQuery(Utente.FIND_ALL, Utente.class);
		try{
			return query.getResultList();
		}
		catch(PersistenceException e){
			return null;
		}
	}
	
	public boolean changePassword(int user, String password) {
		
		TypedQuery<Utente> query = em.createNamedQuery(Utente.FIND_BY_ID, Utente.class);
		try {
			query.setParameter(Utente.ID, Cifras.encrypt(Integer.toString(user)));
		
		Utente utente = query.getSingleResult();
		utente.setPassword(password);
		return true;
		}
		catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		
		
	}
	
	public boolean verifyUser(String user, String code) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		Utente ut = findUtenteById(Integer.parseInt(user));
		System.out.println(ut.verifyClient(code));
		System.out.println(ut.verifyClientSms(code));
		return ut.isVerified();
	}
	
	public boolean mudarIsencao(int username) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		Utente ut = findUtenteById(username);
		boolean cenas = ut.setIsencao();
		return cenas;
	}
}
