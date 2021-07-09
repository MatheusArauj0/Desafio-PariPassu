package com.example.gerenciadorsenhasbancarias.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerenciadorsenhasbancarias.entities.Password;
import com.example.gerenciadorsenhasbancarias.repository.PasswordRepository;
import com.example.gerenciadorsenhasbancarias.services.exceptions.ResourceNotFoundException;

@Service
public class PasswordService {

	@Autowired
	private PasswordRepository repository;

	private List<Password> preferencialPasswords = new ArrayList<>();
	private List<Password> normalPasswords = new ArrayList<>();
	private List<Password> passwords = new ArrayList<>();



	public Password newPassword(Password entity) {
		if (preferencialPasswords == null && normalPasswords == null && passwords == null) {
			reset();
		}
		Password password = new Password();
		password.setPriority(entity.getPriority());
		try {
			if (password.getPriority()) {
				password.setNumber(preferencialPasswords.get(preferencialPasswords.size() - 1).getNumber() + 1);
			} else {
				password.setNumber(normalPasswords.get(normalPasswords.size() - 1).getNumber() + 1);
			}

		} catch (Exception e) {
			password.setNumber(1);
		}
		
		password.setPass(generatePassword(password));
		if (password.getPriority()) {
			preferencialPasswords.add(password);
		} else {
			normalPasswords.add(password);
		}
		organizeList();

		password = repository.save(password);

		return password;
	}

	public String generatePassword(Password pass) {
		String password = "";

		String passwordNumber = String.valueOf(pass.getNumber());
		
		int numberLength = passwordNumber.length();

		if (pass.getPriority()) {
			password += "P";
		} else {
			password += "N";
		}

		switch (numberLength) {
		case 1:
			password += "###" + passwordNumber;
			break;
		case 2:
			password += "##" + passwordNumber;
			break;
		case 3:
			password += "#" + passwordNumber;
			break;
		case 4:
			password += "" + passwordNumber;
			break;
		case 5:
			password += "ENCERRADO";
			break;
		}
		return password;
	}

	public void organizeList() {
		passwords.clear();
		for (int i = 0; i < preferencialPasswords.size(); i++) {
			passwords.add(preferencialPasswords.get(i));
		}
		for (int i = 0; i < normalPasswords.size(); i++) {
			passwords.add(normalPasswords.get(i));
		}
	}

	@Transactional
	public Password callNext() {
		try {
			Password password = passwords.get(0);
			
			if (password.getPriority()) {
				preferencialPasswords.remove(password);
			} else {
				normalPasswords.remove(password);
			}

			repository.deleteByPass(password.getPass());
			organizeList();
			return password;
		} catch (IndexOutOfBoundsException e) {
			throw new ResourceNotFoundException("Password not found ");
		}
	}

	public List<Password> list() {
		return this.passwords;
	}
	
	
	public void reset() {
		try {
			repository.deleteAll();
			preferencialPasswords = null;
			normalPasswords = null;
			passwords = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		normalPasswords = new ArrayList<>();
		preferencialPasswords = new ArrayList<>();
		passwords = new ArrayList<>();
	}

	public List<Password> recoverPasswords() {
		List<Password> list = repository.findAll();
		preferencialPasswords.clear();
		normalPasswords.clear();
		for (Password password : list) {
			if (password.getPriority()) {
				preferencialPasswords.add(password);
			} else {
				normalPasswords.add(password);
			}
			organizeList();
		}

		return list;
	}
}
