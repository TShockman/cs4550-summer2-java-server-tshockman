
(function () {
  const userServiceClient = new UserServiceClient();
  const $create = $('#createBtn');
  const $update = $('#updateBtn');
  const $cancel = $('#cancelBtn');
  const $username = $('#usernameFld');
  const $password = $('#passwordFld');
  const $firstName = $('#firstNameFld');
  const $lastName = $('#lastNameFld');
  const $role = $('#roleFld');
  userServiceClient.findAllUsers().then(renderUsers);

  $create.click(createUser);
  $update.click(update);
  $cancel.click(clear);

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
      td.append(user.getUsername());
      tr.append(td);

      td = $('<td>');
      td.append('******');
      tr.append(td);

      td = $('<td>');
      td.append(user.getFirstName());
      tr.append(td);

      td = $('<td>');
      td.append(user.getLastName());
      tr.append(td);

      td = $('<td>');
      td.append(user.getRole());
      tr.append(td);

      td = $('<td>');
      const deleteButton = $('<i class="fa-2x fa fa-times"></i>');
      console.log('userid', user.getId());
      deleteButton.attr('id', user.getId());
      deleteButton.click(deleteUser);
      td.append(deleteButton);
      const editButton = $('<i class="fa-2x fa fa-pencil"></i>');
      editButton.data('user', user);
      editButton.click(loadEditUser);
      td.append(editButton);
      tr.append(td);

      tr.appendTo(tableBody);
    }
  }

  function update() {
    const id = $update.data('id');
    if (!id) {
      return alert('Please create user first.');
    }
    const user = new User($username.val(), null, $firstName.val(), $lastName.val(), $role.val());
    userServiceClient.updateUser(id, user)
      .then(userServiceClient.findAllUsers)
      .then(users => {
        clear();
        return renderUsers(users)
      });
  }

  function clear() {
    $update.attr('id', null);
    $username.val('');
    $password.val('');
    $password.prop('disabled', false);
    $firstName.val('');
    $lastName.val('');
    $role.val('');
  }

  function loadEditUser(event) {
    const $button = $(event.currentTarget);
    const user = $button.data('user');

    // store which user we're editing on the update button
    $update.data('id', user.getId());

    // load user
    $username.val(user.getUsername());
    $password.val('****');
    $password.prop('disabled', true);
    $firstName.val(user.getFirstName());
    $lastName.val(user.getLastName());
    $role.val(user.getRole());
  }

  function deleteUser(event) {
    const $button = $(event.currentTarget);
    console.log('Button', $button);
    const id = $button.attr('id');
    console.log('Deleting ID', id);

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

    if ($update.attr('id')) {
      return alert('Please clear form or submit update before continuing.');
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