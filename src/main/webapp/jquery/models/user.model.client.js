function User(username, password, firstName, lastName, role, phone, email, dateOfBirth, id) {

  this.username = username;
  this.password = password;
  this.firstName = firstName;
  this.lastName = lastName;
  this.role = role;
  this.phone = phone;
  this.email = email;
  this.dateOfBirth = dateOfBirth;
  this.id = id;

  this.setUsername = setUsername;
  this.getUsername = getUsername;
  this.setPassword = setPassword;
  this.getPassword = getPassword;
  this.setFirstName = setFirstName;
  this.getFirstName = getFirstName;
  this.setLastName = setLastName;
  this.getLastName = getLastName;
  this.setRole = setRole;
  this.getRole = getRole;
  this.setPhone = setPhone;
  this.getPhone = getPhone;
  this.setEmail = setEmail;
  this.getEmail = getEmail;
  this.setDateOfBirth = setDateOfBirth;
  this.getDateOfBirth = getDateOfBirth;
  this.setId = setId;
  this.getId = getId;

  function getUsername() {
    return this.username;
  }

  function getPassword() {
    return this.password;
  }

  function getFirstName() {
    return this.firstName;
  }

  function getLastName() {
    return this.lastName;
  }

  function getRole() {
    return this.role;
  }

  function getPhone() {
    return this.phone;
  }

  function getEmail() {
    return this.email;
  }

  function getDateOfBirth() {
    return this.dateOfBirth;
  }

  function getId() {
    return this.id;
  }

  function setUsername(username) {
    this.username = username;
  }

  function setPassword(password) {
    this.password = password;
  }

  function setFirstName(firstName) {
    this.firstName = firstName;
  }

  function setLastName(lastName) {
    this.lastName = lastName;
  }

  function setRole(role) {
    this.role = role;
  }

  function setPhone(phone) {
    this.phone = phone;
  }

  function setEmail(email) {
    this.email = email;
  }

  function setDateOfBirth(dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  function setId(id) {
    this.id = id;
  }
}

function parseUser(obj) {
  console.log('Parsing user', JSON.stringify(obj, null, 2));
  if (!obj) {
    console.warn('Cannot parse nonexistent user.');
    return null;
  }
  const {username, password, firstName, lastName, role, phone, email, dateOfBirth, id} = obj;
  return new User(username, password, firstName, lastName, role, phone, email, dateOfBirth, id);
}