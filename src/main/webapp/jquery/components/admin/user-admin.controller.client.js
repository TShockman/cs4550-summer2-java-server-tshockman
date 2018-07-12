
(function () {
  const userServiceClient = new UserServiceClient();
  const $create = $('#createBtn');
  const $username = $('#usernameFld');
  const $password = $('#passwordFld');
  const $firstName = $('#firstNameFld');
  const $lastName = $('#lastNameFld');
  const $role = $('#roleFld');
  userServiceClient.findAllUsers().then(renderUsers);

  $create.click(createUser);

  function renderUsers(users) {
    $username.val('');
    $password.val('');
    $firstName.val('');
    $lastName.val('');
    $role.val('');

    const tableBody = $('tbody');
    tableBody.empty();
    for (const user of users) {
      const tr = $('<tr>');

      let td = $('<td>');
      td.append(user.username);
      tr.append(td);

      td = $('<td>');
      td.append('******');
      tr.append(td);

      td = $('<td>');
      td.append(user.firstName);
      tr.append(td);

      td = $('<td>');
      td.append(user.lastName);
      tr.append(td);

      td = $('<td>');
      td.append(user.role);
      tr.append(td);

      td = $('<td>');
      const deleteButton = $('<i class="fa-2x fa fa-times"></i>');
      deleteButton.attr('id', user.id);
      deleteButton.click(deleteUser);
      td.append(deleteButton);
      tr.append(td);

      tr.appendTo(tableBody);
    }
  }

  function deleteUser(event) {
    const $button = $(event.currentTarget);
    const id = $button.attr('id');

    userServiceClient.deleteUser(id).then(() => {
      userServiceClient.findAllUsers().then(renderUsers)
    });
  }

  function createUser() {
    const username = $username.val();
    const password = $password.val();
    if (!username || !password) {
      return alert('Please enter username and password before creating user.');
    }

    const user = new User(
      $username.val(),
      $password.val(),
      $firstName.val(),
      $lastName.val(),
      $role.val()
    );

    userServiceClient.createUser(user)
      .then(() => userServiceClient.findAllUsers().then(renderUsers));
  }
})();