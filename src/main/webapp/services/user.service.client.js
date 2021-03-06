function UserServiceClient() {

  this.createUser = createUser;
  this.findAllUsers = findAllUsers;
  this.deleteUser = deleteUser;
  this.findUserById = findUserById;
  this.updateUser = updateUser;
  this.register = register;
  this.login = login;
  this.updateProfile = updateProfile;
  this.logout = logout;
  this.getProfile = getProfile;
  this.url = '/api/user';
  this.loginUrl = '/api/login';
  this.profileUrl = '/api/profile';
  this.logoutUrl = '/api/logout';
  const self = this;

  function deleteUser(id) {
    return fetch(`${self.url}/${id}`, {
      method: 'delete'
    });
  }

  function findAllUsers() {
    return fetch(self.url)
      .then(response => response.json())
      .then(users => users.map(parseUser));
  }

  function createUser(user) {
    return fetch(self.url, {
      method: 'post',
      body: JSON.stringify(user),
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => response.json())
      .then(parseUser);
  }

  function findUserById(id) {
    return fetch(`${self.url}/${id}`)
      .then(response => response.json())
      .then(parseUser);
  }

  function updateUser(id, user) {
    return fetch(`${self.url}/${id}`, {
      method: 'put',
      body: JSON.stringify(user),
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => response.json())
      .then(parseUser);
  }

  function register(user) {
    return fetch(self.url, {
      method: 'post',
      body: JSON.stringify(user),
      headers: {
        'Content-Type': 'application/json'
      },
      credentials: 'include'
    })
      .then(response => response.json())
      .then(parseUser);
  }

  function login(user) {
    return fetch(self.loginUrl, {
      method: 'post',
      body: JSON.stringify(user),
      headers: {
        'Content-Type': 'application/json'
      },
      credentials: 'include'
    })
      .then(response => response.json())
      .then(parseUser);
  }

  function logout() {
    return fetch(self.logoutUrl, {
      method: 'post',
      credentials: 'include'
    });
  }

  function updateProfile(user) {
    return fetch(self.profileUrl, {
      method: 'put',
      body: JSON.stringify(user),
      headers: {
        'Content-Type': 'application/json'
      },
      credentials: 'include'
    })
      .then(response => response.json())
      .then(parseUser);
  }

  function getProfile() {
    return fetch(self.profileUrl, {
      credentials: 'include'
    })
      .then(response => response.json())
      .then(parseUser);
  }

}