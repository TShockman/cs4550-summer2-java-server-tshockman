function UserServiceClient() {

  this.createUser = createUser;
  this.findAllUsers = findAllUsers;
  this.deleteUser = deleteUser;
  this.findUserById = findUserById;
  this.updateUser = updateUser;
  this.register = register;
  this.login = login;
  this.url = '/api/user';
  this.loginUrl = '/api/login';
  const self = this;

  function deleteUser(id) {
    return fetch(`${self.url}/${id}`, {
      method: 'delete'
    });
  }

  function findAllUsers() {
    return fetch(self.url)
      .then(response => response.json());
  }

  function createUser(user) {
    return fetch(self.url, {
      method: 'put',
      body: JSON.stringify(user),
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(response => response.json());
  }

  function findUserById(id) {
    return fetch(`${self.url}/${id}`)
      .then(response => response.json());
  }

  function updateUser(id, user) {
    return fetch(`${self.url}/${id}`, {
      method: 'put',
      body: JSON.stringify(user),
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(response => response.json());
  }

  function register(user) {
    return fetch(self.url, {
      method: 'put',
      body: JSON.stringify(user),
      headers: {
        'Content-Type': 'application/json'
      },
      credentials: 'include'
    }).then(response => response.json());
  }

  function login(user) {
    return fetch(self.loginUrl, {
      method: 'post',
      body: JSON.stringify(user),
      headers: {
        'Content-Type': 'application/json'
      },
      credentials: 'include'
    }).then(response => response.json());
  }

}