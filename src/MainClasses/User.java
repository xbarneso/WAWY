/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

/**
 *
 * @author xbarn
 */

public abstract class User {
    
    private int id;
    private String idToken;
    private boolean admin;
    private String name, email, phoneNumber, direction, role, charge;
    
    public User(String token, boolean admin){
        this.idToken = token;
        this.admin=admin;
    }
    
    public User(int id, String name, String email, String phoneNumber, String direction, String role, String charge) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.direction = direction;
        this.role = role;
        this.charge = charge;

    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * @return the charge
	 */
	public String getCharge() {
		return charge;
	}
	/**
	 * @param charge the charge to set
	 */
	public void setCharge(String charge) {
		this.charge = charge;
	}

}

